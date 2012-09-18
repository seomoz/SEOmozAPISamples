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
		'cols' => TOPPAGES_COL_URL,
		'limit' => 10
	);

	$topPagesService = new TopPagesService($authenticator);
		$response = $topPagesService->getTopPages($objectURL, $options);

	echo "\n\n";
	print_r($response);
?>