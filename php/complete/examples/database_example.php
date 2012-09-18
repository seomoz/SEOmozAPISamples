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

	// Add your hostname here
	$hostname = 'localhost';

	// Add your database here
	$database = '';

	// Add your table here
	$table = '';

	// Add your username here
	$username = '';

	// Add your password here
	$password = '';

	// Setup/Connect database
	$dbConnector = new dbConnector();
	$dbConnector -> setHostname($hostname);
	$dbConnector -> setDatabase($database);
	$dbConnector -> setUsername($username);
	$dbConnector -> setPassword($password);
	$dbConnector -> connectDB();

	// Query database for URLs
	$dbConnector -> setTable($table);
	$db_urls = $dbConnector->getURLsFromDB();

	// Set batch size here
	$batchSize = 200;

	// Batch URLs
	$dbConnector -> setBatchSize($batchSize);
	$batchedDomains = $dbConnector->getBatchedURLs($db_urls);

	// Close DB connection
	$dbConnector -> closeDB();

	echo "Batch size = $batchSize\n\n";

	// Metrics to retrieve (url_metrics_constants.php)
	$cols = URLMETRICS_COL_DEFAULT;

	// Send batches to Mozscape API
	$i = 0;
	foreach ($batchedDomains as $objectURL) {
		$i++;

		$urlMetricsService = new URLMetricsService($authenticator);
		$response = $urlMetricsService->getUrlMetrics($objectURL, $cols);

		echo "\n\n";
		print_r($response);
	}
?>