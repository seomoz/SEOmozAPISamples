import hashlib
import hmac
import time
import base64
import urllib
import urllib2
try:
	# Try to use the more efficient alternative
	import simplejson as json
except:
	# But fall back to a built-in implementation
	import json

class lsapiException(Exception):
	'''A wrapper so that we can catch our own errors'''
	def __init__(self, value):
		self.value = value
	def __str__(self):
		return str(self.value)
	def __repr__(self):
		return repr(self.value)

class lsapi:
	'''An object that is tied to your id/key pair, and can make requests on your behalf'''
	
	class UMCols:
		'''UrlMetric colums'''
		# Flags for urlMetrics
		# Title of page if available
		title      				= 1
		# Canonical form of the url
		url        				= 4
		# The subdomain of the url
		subdomain  				= 8
		# The root domain of the url
		rootDomain 				= 16
		# The number of juice-passing external links to the url
		externalLinks 			= 32
		# The number of juice-passing external links to the subdomain
		subdomainExternalLinks 	= 64
		# The number of juice-passing external links
		rootDomainExternalLinks = 128
		# The number of juice-passing links (internal or external) to the url
		juicePassingLinks 		= 256
		# The number of subdomains with any pages linking to the url
		subdomainsLinking 		= 512
		# The number of root domains with any pages linking to the url
		rootDomainsLinking 		= 1024
		# The number of links (juice-passing or not, internal or external) to the url
		links 					= 2048
		# The number of subdomains with any pages linking to the subdomain of the url
		subdomainSubdomainsLinking = 4096
		# The number of root domains with any pages linking to the root domain of the url
		rootDomainRootDomainsLinking = 8192
		# The mozRank of the url.  Requesting this metric will provide both the 
		# pretty 10-point score (in umrp) and the raw score (umrr)
		mozRank 				= 16384
		# The mozRank of the subdomain of the url. Requesting this metric will
		#provide both the pretty 10-point score (fmrp) and the raw score (fmrr)
		subdomainMozRank 		= 32768
		# The mozRank of the Root Domain of the url. Requesting this metric will
		# provide both the pretty 10-point score (pmrp) and the raw score (pmrr) 
		rootDomainMozRank 		= 65536
		# The mozTrust of the url. Requesting this metric will provide both the
		# pretty 10-point score (utrp) and the raw score (utrr).
		mozTrust 				= 131072
		# The mozTrust of the subdomain of the url.  Requesting this metric will
		# provide both the pretty 10-point score (ftrp) and the raw score (ftrr)
		subdomainMozTrust 		= 262144
		# The mozTrust of the root domain of the url.  Requesting this metric
		# will provide both the pretty 10-point score (ptrp) and the raw score (ptrr)
		rootDomainMozTrust 		= 524288
		# The portion of the url's mozRank coming from external links.  Requesting
		# this metric will provide both the pretty 10-point score (uemrp) and the raw
		# score (uemrr)
		externalMozRank 		= 1048576
		# The portion of the mozRank of all pages on the subdomain coming from
		# external links.  Requesting this metric will provide both the pretty
		# 10-point score (fejp) and the raw score (fejr)
		subdomainExternalDomainJuice = 2097152 
		# The portion of the mozRank of all pages on the root domain coming from
		# external links.  Requesting this metric will provide both the pretty
		# 10-point score (pejp) and the raw score (pejr)
		rootDomainExternalDomainJuice = 4194304
		# The mozRank of all pages on the subdomain combined.  Requesting this
		# metric will provide both the pretty 10-point score (fjp) and the raw score (fjr)
		subdomainDomainJuice 	= 8388608
		# The mozRank of all pages on the root domain combined.  Requesting this
		# metric will provide both the pretty 10-point score (pjp) and the raw score (pjr)
		rootDomainDomainJuice 	= 16777216
		# The HTTP status code recorded by Linkscape for this URL (if available)
		httpStatusCode 			= 536870912
		# Total links (including internal and nofollow links) to the subdomain of
		# the url in question
		linksToSubdomain 		= 4294967296
		# Total links (including internal and nofollow links) to the root domain
		# of the url in question.
		linksToRootDomain 		= 8589934592
		# The number of root domains with at least one link to the subdomain of
		# the url in question
		rootDomainsLinkingToSubdomain = 17179869184
		# A score out of 100-points representing the likelihood for arbitrary content
		# to rank on this page
		pageAuthority 			= 34359738368
		# A score out of 100-points representing the likelihood for arbitrary content
		# to rank on this dom
		domainAuthority 		= 68719476736
		
		# This is the set of all free fields
		freeCols = (title |
			url |
			externalLinks |
			links |
			mozRank |
			subdomainMozRank |
			httpStatusCode |
			pageAuthority |
			domainAuthority)
	
	class ATCols:
		'''Anchor Text Cols'''
		# The anchor text term or phrase
		term 					= 2
		# The number of internal pages linking with this term or phrase
		internalPagesLinking 	= 8
		# The number of subdomains on the same root domain with at least one link with this term or phrase
		internalSubdomainsLinking = 16
		# The number of external pages linking with this term or phrase
		externalPagesLinking 	= 32
		# The number of external subdomains with at least one link with this term or phrase
		externalSubdomainsLinking = 64
		# The number of (external) root domains with at least one link with this term or phrase
		externalRootDomainsLinking = 128
		# The amount of mozRank passed over all internal links with this term or phrase (on the 10 point scale)
		internalMozRankPassed 	= 256
		# The amount of mozRank passed over all external links with this term or phrase (on the 10 point scale)
		externalMozRankPassed 	= 512
		# Currently only "1" is used to indicate the term or phrase is found in an image link
		flags 					= 1024
		
		# This is the set of all free fields
		freeCols = (term |
			internalPagesLinking |
			internalSubdomainsLinking |
			externalPagesLinking |
			externalSubdomainsLinking |
			externalRootDomainsLinking |
			internalMozRankPassed |
			externalMozRankPassed |
			flags)
	
	# The base url we request from
	base = 'http://lsapi.seomoz.com/linkscape/%s?%s'
	
	def __init__(self, access_id, secret_key):
		self.access_id  = access_id
		self.secret_key = secret_key
	
	def signature(self, expires):
		toSign  = '%s\n%i' % (self.access_id, expires)
		return base64.b64encode(hmac.new(self.secret_key, toSign, hashlib.sha1).digest())

	def query(self, method, data=None, **params):
		expires = int(time.time() + 300)
		params['AccessID' ] = self.access_id
		params['Expires'  ] = expires
		params['Signature'] = self.signature(expires)
		request = lsapi.base % (method.encode('utf-8'), urllib.urlencode(params))
		try:
			return json.loads(urllib2.urlopen(request, data).read())
		except urllib2.HTTPError as e:
			# The unauthorized status code can sometimes have meaningful data
			if e.code == 401:
				raise lsapiException(e.read())
			else:
				raise lsapiException(e)
		except Exception as e:
			raise lsapiException(e)
	
	def urlMetrics(self, urls, cols=UMCols.freeCols):
		if isinstance(urls, basestring):
			return self.query('url-metrics/%s' % urllib.quote(urls), Cols=cols)
		else:
			return self.query('url-metrics', data=json.dumps(urls), Cols=cols)
	
	def anchorText(self, url, scope='phrase_to_page', sort='domains_linking_page', cols=ATCols.freeCols):
		return self.query('anchor-text/%s' % urllib.quote(url), Scope=scope, Sort=sort, Cols=cols)
	
	def links(self, url, scope='page_to_page', sort='page_authority', filters=['internal'],
		targetCols=(UMCols.url | UMCols.pageAuthority),
		sourceCols=(UMCols.url | UMCols.pageAuthority),
		linkCols  =(UMCols.url | UMCols.pageAuthority)):
		'''This is currently broken. Have not figured it out'''
		return self.query('links/%s' % urllib.quote(url),
			Scope      = scope,
			Sort       = sort,
			Filter     = '+'.join(filters),
			TargetCols = targetCols,
			SourceCols = sourceCols,
			linkCols   = linkCols)
