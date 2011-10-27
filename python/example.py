#! /usr/bin/env python

from lsapi import lsapi

l = lsapi('my-access-id', 'my-secret-key')

# As you may have noticed, there are lots of columns available
# I did what I could to make them easily-accessible, but there
# are a lot, and the names are long. So, the API calls have
# defaults

# Let's get some urlmetrics. Results are now an array of dictionaries
# the i'th dictionary is the results for the i'th URL
metrics = l.urlMetrics(['www.seomoz.org', 'www.seomoz.org/blog'])
# Now let's say we only want specific columns in the results
authorities = l.urlMetrics(['www.seomoz.org'], lsapi.UMCols.domainAuthority | lsapi.UMCols.pageAuthority)
# Or if you just need results for one URL
mozMetrics = l.urlMetrics('www.seomoz.org')

# Now for some anchor text results
anchorResults = l.anchorText('www.seomoz.org/blog')
# Or for just specific columns
anchorTermResults = l.anchorText('www.seomoz.org/blog', cols=lsapi.ATCols.term)

# Now for some links results
links = l.links('www.seomoz.org')
# The links API has more columns to specify, as well as sort, scope, etc.
links = l.links('www.seomoz.org', scope='domain_to_domain', sort='domain_authority',
	filters=['external', 'nofollow'], targetCols = lsapi.UMCols.url)
