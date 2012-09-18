<?php
	include '../bootstrap.php';

	//Add your accessID here
	$AccessID = '';

	//Add your secretKey here
	$SecretKey = '';

	$authenticator = new Authenticator();
	$authenticator->setAccessID($AccessID);
	$authenticator->setSecretKey($SecretKey);

	// URL to query
	$objectURL = "www.seomoz.org";

	// Metrics to retrieve (anchor_text_constants.php)
	$options = array(
		'cols' => ANCHOR_COL_TERM_OR_PHRASE,
		'scope' => ANCHOR_SCOPE_TERM_TO_SUBDOMAIN,
		'sort' => ANCHOR_SORT_DOMAINS_LINKING_PAGE,
		'limit' => 10
	);

	$anchorTextService = new AnchorTextService($authenticator);
	$response = $anchorTextService->getAnchorText($objectURL, $options);
	
	echo "\n\n";
	print_r($response);
?>