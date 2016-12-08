#!/usr/bin/env python

from mozscape import Mozscape

client = Mozscape(
    'my-access-id',
    'my-secret-key')

# As you may have noticed, there are lots of columns available
# I did what I could to make them easily-accessible, but there
# are a lot, and the names are long. So, the API calls have
# defaults

# Let's get some URL metrics. Results are now an array of dictionaries
# the i'th dictionary is the results for the i'th URL
metrics = client.urlMetrics(['www.moz.com', 'www.moz.com/blog'])
# Now let's say we only want specific columns in the results
authorities = client.urlMetrics(
    ['www.moz.com'],
    Mozscape.UMCols.domainAuthority | Mozscape.UMCols.pageAuthority)
# Or if you just need results for one URL
mozMetrics = client.urlMetrics('www.moz.com')

# Now for some anchor text results. Not available on the Free API
anchorResults = client.anchorText('www.moz.com/blog')
# Or for just specific columns
anchorTermResults = client.anchorText(
    'www.moz.com/blog', cols=Mozscape.ATCols.term)

# Now for some links results
links = client.links('www.moz.com')
# The links API has more columns to specify, as well as sort, scope, etc.
links = client.links(
    'www.moz.com', scope='domain_to_domain', sort='domain_authority',
    filters=['external', 'nofollow'], targetCols=Mozscape.UMCols.url)
