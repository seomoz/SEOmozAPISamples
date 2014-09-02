<?php
	define('LINKS_SCOPE_PAGE_TO_PAGE', 'page_to_page');
	define('LINKS_SCOPE_PAGE_TO_SUBDOMAIN', 'page_to_subdomain');
	define('LINKS_SCOPE_PAGE_TO_DOMAIN', 'page_to_domain');
	define('LINKS_SCOPE_DOMAIN_TO_PAGE', 'domain_to_page');
	define('LINKS_SCOPE_DOMAIN_TO_SUBDOMAIN', 'domain_to_subdomain');
	define('LINKS_SCOPE_DOMAIN_TO_DOMAIN', 'domain_to_domain');

	define('LINKS_SORT_PAGE_AUTHORITY', 'page_authority');
	define('LINKS_SORT_DOMAIN_AUTHORITY', 'domain_authority');
	define('LINKS_SORT_DOMAINS_LINKING_DOMAIN', 'domains_linking_domain');
	define('LINKS_SORT_DOMAINS_LINKING_PAGE', 'domains_linking_page');

	define('LINKS_FILTER_INTERNAL', 'internal');
	define('LINKS_FILTER_EXTERNAL', 'external');
	define('LINKS_FILTER_NOFOLLOW', 'nofollow');
	define('LINKS_FILTER_FOLLOW', 'follow');
	define('LINKS_FILTER_301', '301');

	define('LINKS_COL_URL', 0);
	define('LINKS_COL_FLAGS', 2);
	define('LINKS_COL_ANCHOR_TEXT', 4);
	define('LINKS_COL_NRML_ANCHOR_TEXT', 8);
	define('LINKS_COL_MOZRANK_PASSED', 16);

	// TARGET AND SOURCE COLS CAN HAVE URL METRICS
	// BITFLAGS APPLIED TO THEM AS WELL.
?>
