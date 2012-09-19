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

	// Metadata to retrieve (metadata_constants.php)
	$options = array(
		'updates' => METADATA_LAST_UPDATE
	);

	$metadataService = new MetadataService($authenticator);
	$response = $metadataService->getMetadata($options);

	echo "\n\n";
	print_r($response);
?>