<?php
	
	$objectURL = "www.seomoz.org";
	
	//Add your accessID here
	$AccessID = '';
	
	//Add your secretKey here
	$SecretKey = '';
	

    include '../bootstrap.php';
	
	$authenticator = new Authenticator();
	$authenticator->setAccessID($AccessID);
	$authenticator->setSecretKey($SecretKey);
	
	
	/*$urlMetricsService = new URLMetricsService();
	$response = $urlMetricsService->getUrlMetrics($objectURL);
	var_dump($response);*/
	
	$linksService = new LinksService($authenticator);
	$response = $linksService->getLinks($objectURL, LINKS_SCOPE_PAGE_TO_PAGE, null, LINKS_SORT_PAGE_AUTHORITY, LINKS_COL_URL, 0 , 10);
	var_dump($response);	
	
	/*
	$anchorTextService = new AnchorTextService($authenticator);
	$response = $anchorTextService->getAnchorText($objectURL, ANCHOR_SCOPE_TERM_TO_PAGE, ANCHOR_SORT_DOMAINS_LINKING_PAGE, ANCHOR_COL_TERM_OR_PHRASE + ANCHOR_COL_INTERNAL_PAGES_LINK);
	var_dump($response);
	*/
	
	/*
	$topPagesService = new TopPagesService();
	$response = $topPagesService->getTopPages($objectURL, TOPPAGES_COL_ALL, 0, 3);
	var_dump($response);
	*/
	
?>