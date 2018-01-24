Python Mozscape API
====================

For more information on the Mozscape API in general, please consult the
[Mozscape API Help](https://moz.com/help/guides/moz-api).

The current module supports both Python 2 and 3.

Installation instructions
-------------------------
```sh
git clone https://github.com/seomoz/SEOmozAPISamples.git
cd SEOmozAPISamples/python
pip install . # or alternatively python setup.py install
```

Alternatively, you can install directly from GitHub using this
invocation:

```
pip install git+https://github.com/seomoz/SEOmozAPISamples.git#egg=mozscape&subdirectory=python
```

General
-------

The Mozscape class is initialized with your access id and your secret key, and
then can be used to make API calls:

```python
from mozscape import Mozscape
client = Mozscape('my_access_id', 'my_secret_key')
# Now I can make API calls!
```

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

```python
# Let's get some URL metrics. Results are now an array of
# dictionaries the i'th dictionary is the results for the i'th URL
metrics = client.urlMetrics(['www.moz.com', 'www.moz.com'])
# Now let's say we only want specific columns in the results
authorities = client.urlMetrics(
    ['www.moz.com'],
    Mozscape.UMCols.domainAuthority | Mozscape.UMCols.pageAuthority)
# Or if you just need results for one URL
mozMetrics = client.urlMetrics('www.moz.com')
```

Anchor Text
-----------

Next exposed is the
[Anchor Text](https://moz.com/help/guides/moz-api/mozscape/api-reference/anchor-text-metrics)
call, which returns a set of anchor text terms of phrases aggregated
across links to a page or domain.

```python
# Now for some anchor text results
anchorResults = client.anchorText('www.moz.com/blog')
# Or for just specific columns
anchorTermResults = client.anchorText(
    'www.moz.com/blog', cols=Mozscape.ATCols.term)
```

Links
-----

Lastly, we have the
[Links](https://moz.com/help/guides/moz-api/mozscape/api-reference/link-metrics)
call, which returns a set of links to a page or domain.

```python
# Now for some links results
links = client.links('www.moz.com')
# The links API has more columns to specify, as well as sort, scope, etc.
links = client.links(
    'wwww.moz.com', scope='domain_to_domain', sort='domain_authority',
    filters=['external', 'nofollow'], targetCols = Mozscape.UMCols.url)
```
