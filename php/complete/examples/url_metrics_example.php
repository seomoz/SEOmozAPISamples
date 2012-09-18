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

	// Metrics to retrieve (url_metrics_constants.php)
	$cols = URLMETRICS_COL_DEFAULT;

	$urlMetricsService = new URLMetricsService($authenticator);
	$response = $urlMetricsService->getUrlMetrics($objectURL, $cols);

	echo "\n\n";
	print_r($response);
?>