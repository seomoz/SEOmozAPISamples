<?php

	// Authentication
	include_once __DIR__ . '/authentication/authenticator.php';

	// Services
	include_once __DIR__ . '/services/anchor_text_service.php';
	include_once __DIR__ . '/services/url_metrics_service.php';
	include_once __DIR__ . '/services/links_service.php';
	include_once __DIR__ . '/services/top_pages_service.php';
	include_once __DIR__ . '/services/metadata_service.php';

	// Constants
	include_once __DIR__ . '/constants/anchor_text_constants.php';
	include_once __DIR__ . '/constants/links_constants.php';
	include_once __DIR__ . '/constants/top_pages_constants.php';
	include_once __DIR__ . '/constants/url_metrics_constants.php';
	include_once __DIR__ . '/constants/metadata_constants.php';

	// Utilities
	include_once __DIR__ . '/utilities/connection_utility.php';
	include_once __DIR__ . '/utilities/database_utility.php';

?>