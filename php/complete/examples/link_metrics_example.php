<?php
	include '../bootstrap.php';

	// Add your accessID here
	$AccessID = '';

	// Add your secretKey here
	$SecretKey = '';

	// Set the rate limit
	$rateLimit = 10;

	$authenticator = new Authenticator();
	$authenticator->setAccessID($AccessID);
	$authenticator->setSecretKey($SecretKey);
	$authenticator->setRateLimit($rateLimit);

	// URL to query
	$objectURL = "www.seomoz.org";

	// Metrics to retrieve (links_constants.php)
	$options = array(
		'scope' => LINKS_SCOPE_PAGE_TO_PAGE,
		'filters' => LINKS_FILTER_EXTERNAL,
		'sort' => LINKS_SORT_PAGE_AUTHORITY,
		'source_cols' => URLMETRICS_COL_SUBDOMAIN,
		'target_cols' => URLMETRICS_COL_SUBDOMAIN,
		'link_cols' => LINKS_COL_URL,
		'limit' => 10
	);

	$linksService = new LinksService($authenticator);
	$response = $linksService->getLinks($objectURL, $options);

	echo "\n\n";
	print_r($response);
?>