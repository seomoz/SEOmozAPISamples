Python Linkscape API
====================

For more information on the Linkscape API in general, please consult the
[wiki](http://www.seomoz.org/api/index/)

General
-------

The lsapi class is initialized with your access id and your secret key, and
then can be used to make API calls:

	from lsapi import lsapi
	l = lsapi('my_access_id', 'my_secret_key')
	# Now I can make me some API calls!

All calls in this class return the deserialized json objects that the API
returns. Typically these are either dictionaries, or lists of dictionaries.
For the meaning of each of the resultant keys, consult the documentation.

The columns you can request are a bitmask, with constants provided in classes
in the lsapi class. For example, the columns for URL Metrics and Anchor Text
API calls are in `lsapi.UMCols` and `lsapi.ATCols` respectively.

URL Metrics
-----------

The first API call exposed is [URL Metrics](http://apiwiki.seomoz.org/w/page/13991153/URL%20Metrics%20API).
It can provide summary information on a single (via a GET) or multiple (via a POST) url or urls.

	# Let's get some urlmetrics. Results are now an array of dictionaries
	# the i'th dictionary is the results for the i'th URL
	metrics = l.urlMetrics(['www.seomoz.org', 'www.seomoz.org/blog'])
	# Now let's say we only want specific columns in the results
	authorities = l.urlMetrics(['www.seomoz.org'], lsapi.UMCols.domainAuthority | lsapi.UMCols.pageAuthority)
	# Or if you just need results for one URL
	mozMetrics = l.urlMetrics('www.seomoz.org')

Anchor Text
-----------

Next exposed is the [Anchor Text](http://apiwiki.seomoz.org/w/page/13991127/Anchor%20Text%20API)
call, which returns a set of anchor text terms of phrases aggregated across links to a page or domain.
	
	# Now for some anchor text results
	anchorResults = l.anchorText('www.seomoz.org/blog')
	# Or for just specific columns
	anchorTermResults = l.anchorText('www.seomoz.org/blog', cols=lsapi.ATCols.term)

Links
-----

Lastly, we have the [Links](http://apiwiki.seomoz.org/w/page/13991141/Links%20API) call, which
returns a set of links to a page or domain.
	
	# Now for some links results
	links = l.links('www.seomoz.org')
	# The links API has more columns to specify, as well as sort, scope, etc.
	links = l.links('www.seomoz.org', scope='domain_to_domain', sort='domain_authority',
