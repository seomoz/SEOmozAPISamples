import codecs
import hashlib
import hmac
import time
import base64


try:
    # For Python 3.0 and later
    from urllib.parse import urlencode, quote
except ImportError:
    # Fall back to Python 2's urllib
    from urllib import urlencode, quote

try:
    # For Python 3.0 and later
    from urllib.request import urlopen
except ImportError:
    # Fall back to Python 2's urllib2
    from urllib2 import urlopen

try:
    # For Python 3.0 and later
    from urllib.error import HTTPError
except ImportError:
    # Fall back to Python 2's urllib2
    from urllib2 import HTTPError

try:
    # Try to use the more efficient alternative
    import simplejson as json
except:
    # But fall back to a built-in implementation
    import json


class MozscapeError(Exception):
    """A wrapper so that we can catch our own errors"""

    def __init__(self, value):
        self.value = value

    def __str__(self):
        return str(self.value)

    def __repr__(self):
        return repr(self.value)


class Mozscape:
    """An object that is tied to your id/key pair and can make requests on
    your behalf."""

    class UMCols:
        """UrlMetric columns"""
        # Flags for urlMetrics
        #
        # Title of page, if available.
        # Free? Yes
        # Response code:  ut
        title = 1
        # Canonical form of the url.
        # Free? Yes
        # Response code:  uu
        url = 4
        # The subdomain of the url.
        # Free? No
        # Response code:  ufq
        subdomain = 8
        # The root domain of the url.
        # Free? No
        # Response code:  upl
        rootDomain = 16
        # The number of external quity links to the url.
        # Free? Yes
        # Response code:  ueid
        equityExternalLinks = 32
        # The number of juice-passing external links to the subdomain.
        # Free? No
        # Response code:  feid
        subdomainExternalLinks = 64
        # The number of juice-passing external links.
        # Free? No
        # Response code:  peid
        rootDomainExternalLinks = 128
        # The number of equity (juice-passing) links (internal or
        # external) to the url.
        # Free? No
        # Response code:  ujid
        juicePassingLinks = 256
        # The number of subdomains with any pages linking to the url.
        # Free? No
        # Response code:  uifq
        subdomainsLinking = 512
        # The number of root domains with any pages linking to the url.
        # Free? No
        # Response code:  uipl
        rootDomainsLinking = 1024
        # The number of links (juice-passing or not, internal or
        # external) to the url.
        # Free? yes
        # Response code:  uid
        links = 2048
        # The number of subdomains with any pages linking to the
        # subdomain of the url.
        # Free? No
        # Response code:  fid
        subdomainSubdomainsLinking = 4096
        # The number of root domains with any pages linking to the
        # root domain of the url.
        # Free? No
        # Response code:  pid
        rootDomainRootDomainsLinking = 8192
        # The mozRank of the url.  Requesting this metric will provide
        # both the pretty 10-point score (in umrp) and the raw score
        # (umrr).
        # Free? Yes
        # Response codes: umrp, umrr
        mozRank = 16384
        # The mozRank of the subdomain of the url. Requesting this
        # metric will provide both the pretty 10-point score (fmrp)
        # and the raw score (fmrr).
        # Free? Yes
        # Response codes: fmrp, fmrr
        subdomainMozRank = 32768
        # The mozRank of the Root Domain of the url. Requesting this
        # metric will provide both the pretty 10-point score (pmrp)
        # and the raw score (pmrr).
        # Free? No
        # Response code:  pmrp, pmrr
        rootDomainMozRank = 65536
        # The mozTrust of the url. Requesting this metric will provide both the
        # pretty 10-point score (utrp) and the raw score (utrr).
        # Free? No
        # Response code:  utrp, utrr
        mozTrust = 131072
        # The mozTrust of the subdomain of the url.  Requesting this
        # metric will provide both the pretty 10-point score (ftrp)
        # and the raw score (ftrr).
        # Free? No
        # Response code:  ftrp, ftrr
        subdomainMozTrust = 262144
        # The mozTrust of the root domain of the url.  Requesting this
        # metric will provide both the pretty 10-point score (ptrp)
        # and the raw score (ptrr).
        # Free? No
        # Response codes: ptrp, ptrr
        rootDomainMozTrust = 524288
        # The portion of the url's mozRank coming from external links.
        # Requesting this metric will provide both the pretty 10-point
        # score (uemrp) and the raw score (uemrr).
        # Free? No
        # Response codes: uemrp, uemrr
        externalMozRank = 1048576
        # The portion of the mozRank of all pages on the subdomain
        # coming from external links.  Requesting this metric will
        # provide both the pretty 10-point score (fejp) and the raw
        # score (fejr).
        # Free? No
        # Response codes: fejp, fejr
        subdomainExternalDomainJuice = 2097152
        # The portion of the mozRank of all pages on the root domain
        # coming from external links.  Requesting this metric will
        # provide both the pretty 10-point score (pejp) and the raw
        # score (pejr).
        # Free? No
        # Response code:  pejp, pejr
        rootDomainExternalDomainJuice = 4194304
        # The mozRank of all pages on the subdomain combined.
        # Requesting this metric will provide both the pretty 10-point
        # score (fjp) and the raw score (fjr).
        # Free? No
        # Response codes: fjp, fjr
        subdomainDomainJuice = 8388608
        # The mozRank of all pages on the root domain combined.
        # Requesting this metric will provide both the pretty 10-point
        # score (pjp) and the raw score (pjr).
        # Free? No
        # Response codes: pjp, pjr
        rootDomainDomainJuice = 16777216
        # Returns six different columns related to the Spam Score(TM) metric:
        # - Spam score for the page's subdomain (fspsc)
        # - Bit field of triggered spam flags (fspf)
        # - Language of the subdomain (flan)
        # - HTTP status code of the spam crawl (fsps)
        # - Epoch time when the subdomain was last crawled (fsplc)
        # - List of pages used for the subdomain's spam crawl (fspp).
        # Free? No
        # Response codes: fspsc, fspf, flan, fsps, fsplc, fspp
        subdomainSpamScore = 67108864
        # Returns social contact information found on the target entity:
        # - Facebook account (ffb)
        # - Twitter handle (ftw)
        # - Google+ account (fg+)
        # - Email address (fem)*
        # * Emails in the Contacts column are collected automatically,
        # and are not CAN-SPAM compliant - they cannot be used in
        # outbound mail campaigns.
        # Free? No
        # Response codes: ffb, ftw, fg+, fem*
        social = 134217728
        # The HTTP status code recorded by Linkscape for this URL (if
        # available).
        # Free? Yes
        # Response code:  us
        httpStatusCode = 536870912
        # Total links (including internal and nofollow links) to the
        # subdomain of the url in question.
        # Free? No
        # Response code:  fuid
        linksToSubdomain = 4294967296
        # Total links (including internal and nofollow links) to the
        # root domain of the url in question.
        # Free? No
        # Response code:  puid
        linksToRootDomain = 8589934592
        # The number of root domains with at least one link to the subdomain of
        # the url in question.
        # Free? No
        # Response code:  fipl
        rootDomainsLinkingToSubdomain = 17179869184
        # A score out of 100-points representing the likelihood for
        # arbitrary content to rank on this page.
        # Free? Yes
        # Response code:  upa
        pageAuthority = 34359738368
        # A score out of 100-points representing the likelihood for
        # arbitrary content to rank on this domain.
        # Free? Yes
        # Response code:  pda
        domainAuthority = 68719476736
        # The number of external links to the URL, including nofollowed links.
        # Free? No
        # Response code:  ued
        externalLinks = 549755813888
        # The number of external links to the subdomain, including
        # nofollowed links.
        # Free? No
        # Response code:  fed
        externalLinksToSubdomain = 140737488355328
        # The number of external links to the root domain, including
        # nofollowed links.
        # Free? No
        # Response code:  ped
        externalLinksToRootDomain = 2251799813685248
        # The number of links from the same C class IP addresses.
        # Free? No
        # Response code:  pib
        linkingCBlocks = 36028797018963968
        # The time and date on which Mozscape last crawled the URL,
        # returned in Unix epoch format. (http://www.epochconverter.com/)
        # Free? Yes
        # Response code:  ulc
        timeLastCrawled = 144115188075855872

        # This is the set of all free fields
        freeCols = (
            title |
            url |
            equityExternalLinks |
            links |
            mozRank |
            subdomainMozRank |
            httpStatusCode |
            pageAuthority |
            domainAuthority |
            timeLastCrawled)

    class ATCols:
        """Anchor Text Cols"""
        # The anchor text term or phrase
        term = 2
        # The number of internal pages linking with this term or phrase
        internalPagesLinking = 8
        # The number of subdomains on the same root domain with at
        # least one link with this term or phrase
        internalSubdomainsLinking = 16
        # The number of external pages linking with this term or phrase
        externalPagesLinking = 32
        # The number of external subdomains with at least one link
        # with this term or phrase
        externalSubdomainsLinking = 64
        # The number of (external) root domains with at least one link
        # with this term or phrase
        externalRootDomainsLinking = 128
        # The amount of mozRank passed over all internal links with
        # this term or phrase (on the 10 point scale)
        internalMozRankPassed = 256
        # The amount of mozRank passed over all external links with
        # this term or phrase (on the 10 point scale)
        externalMozRankPassed = 512
        # Currently only "1" is used to indicate the term or phrase is
        # found in an image link
        flags = 1024

        # This is the set of all free fields
        freeCols = (
            term |
            internalPagesLinking |
            internalSubdomainsLinking |
            externalPagesLinking |
            externalSubdomainsLinking |
            externalRootDomainsLinking |
            internalMozRankPassed |
            externalMozRankPassed |
            flags)

        class LinkCols:
            """Link Cols"""
            flags = 2
            anchorText = 4
            normalizedAnchorText = 8
            mozRankPassed = 16

    # The base url we request from
    base = 'http://lsapi.seomoz.com/linkscape/%s?%s'

    def __init__(self, access_id, secret_key):
        self.access_id = access_id
        self.secret_key = secret_key

    def signature(self, expires):
        to_sign = '%s\n%i' % (self.access_id, expires)
        return base64.b64encode(
            hmac.new(
                self.secret_key.encode('utf-8'),
                to_sign.encode('utf-8'),
                hashlib.sha1).digest())

    def query(self, method, data=None, **params):
        expires = int(time.time() + 300)
        params['AccessID'] = self.access_id
        params['Expires'] = expires
        params['Signature'] = self.signature(expires)
        request = Mozscape.base % (method, urlencode(params))
        try:
            reader = codecs.getreader('utf-8')
            return json.load(reader(urlopen(request, data)))
        except HTTPError as e:
            # The unauthorized status code can sometimes have meaningful data
            if e.code == 401:
                raise MozscapeError(e.read())
            else:
                raise MozscapeError(e)
        except Exception as e:
            raise MozscapeError(e)

    def urlMetrics(self, urls, cols=UMCols.freeCols):
        try:
            basestring
        except NameError:
            basestring = str

        if isinstance(urls, basestring):
            return self.query('url-metrics/%s' % quote(urls), Cols=cols)
        else:
            return self.query(
                'url-metrics',
                data=json.dumps(urls).encode('utf-8'),
                Cols=cols)

    def anchorText(
            self, url, scope='phrase_to_page', sort='domains_linking_page',
            cols=ATCols.freeCols):
        return self.query(
            'anchor-text/%s' % quote(url),
            Scope=scope, Sort=sort, Cols=cols)

    def links(
            self, url, scope='page_to_page', sort='page_authority',
            filters=None,
            targetCols=(UMCols.url | UMCols.pageAuthority),
            sourceCols=(UMCols.url | UMCols.pageAuthority),
            linkCols=0,
            limit=25,
            offset=0):
        if not filters:
            filters = []
        return self.query(
            'links/%s' % quote(url),
            Scope=scope,
            Sort=sort,
            Filter='+'.join(filters),
            TargetCols=targetCols,
            SourceCols=sourceCols,
            LinkCols=linkCols,
            Limit=limit,
            Offset=offset)
