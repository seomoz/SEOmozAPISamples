Python Mozscape API
====================

Python 3 support(not tested for anchor text). For more information on the Mozscape API in general, please consult the
[Mozscape API Help](https://moz.com/help/guides/moz-api)

General
-------

The lsapi class is initialized with your access id and your secret key, and
then can be used to make API calls:

	from lsapi import lsapi
	l = lsapi('my_access_id', 'my_secret_key')
	# Now I can make API calls!

All calls in this class return the deserialized json objects that the
API returns. Typically these are either dictionaries, or lists of
dictionaries.  For the meaning of each of the resultant keys, and the
bit flags for the columns you can request, consult the
[Help](https://moz.com/help/guides/moz-api).

URL Metrics
-----------

The first API call exposed is
[URL Metrics](https://moz.com/help/guides/moz-api/mozscape/api-reference/url-metrics).
It can provide summary information on a single (via a GET) or multiple
(via a POST) url or urls.

	# Let's get some URL metrics. Results are now an array of dictionaries
	# the i'th dictionary is the results for the i'th URL
	metrics = l.urlMetrics(['www.moz.com', 'www.moz.com'])
	# Now let's say we only want specific columns in the results
	authorities = l.urlMetrics(['www.moz.com'], lsapi.UMCols.domainAuthority | lsapi.UMCols.pageAuthority)
	# Or if you just need results for one URL
	mozMetrics = l.urlMetrics('www.moz.com')

Anchor Text
-----------

Next exposed is the
[Anchor Text](https://moz.com/help/guides/moz-api/mozscape/api-reference/anchor-text-metrics)
call, which returns a set of anchor text terms of phrases aggregated
across links to a page or domain.

	# Now for some anchor text results
	anchorResults = l.anchorText('www.moz.com/blog')
	# Or for just specific columns
	anchorTermResults = l.anchorText('www.moz.com/blog', cols=lsapi.ATCols.term)

Links
-----

Lastly, we have the
[Links](https://moz.com/help/guides/moz-api/mozscape/api-reference/link-metrics)
call, which returns a set of links to a page or domain.

	# Now for some links results
	links = l.links('www.moz.com')
	# The links API has more columns to specify, as well as sort, scope, etc.
	links = l.links('wwww.moz.com', scope='domain_to_domain', sort='domain_authority',
		filters=['external', 'nofollow'], targetCols = lsapi.UMCols.url)
