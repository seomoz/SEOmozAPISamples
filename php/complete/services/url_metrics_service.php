<?php
/**
 *
 * Service class to call the various methods to
 * URL Metrics
 *
 * URL Metrics is a paid API that returns the metrics about a URL or set of URLs.
 *
 * @author SEOmoz
 *
 */
require_once 'abstract_service.php';
class URLMetricsService extends AbstractService {
	/**
	 *
	 * This method returns the metrics about a URL or set of URLs.
	 *
	 * @param mixed <array|string> objectURL - if array is passed then batch request
	 * @param string cols
	 * @return response
	 */
	public function getUrlMetrics($objectURL, $cols) {

		$urlToFetch = "http://lsapi.seomoz.com/linkscape/url-metrics/";
		$postParams = null;

		// if passed more then 1 url - pass them through post
		if (!is_array($objectURL)) {
			$urlToFetch .= urlencode($objectURL);
		} else {
			$postParams = json_encode($objectURL);
		}

		$urlToFetch .= "?" . $this->getAuthenticator()->getAuthenticationStr();
		$urlToFetch .= "&Cols=" . $cols;

		$response = ConnectionUtility::makeRequest($urlToFetch, $postParams);

		$i = $this->getAuthenticator()->getRateLimit();

		// if request fails retry with exponential backoff
		if (isset($response['http']) && $response['http'] != "200") {

			do {
				$i = $i + $i;

				// output message (optional)
    		echo "\n\nERROR: HTTP Response Code (" . $response['http'] . ")";
				echo "\nWaiting $i seconds to retry";

				sleep($i);
				$response = ConnectionUtility::makeRequest($urlToFetch, $postParams);

			} while ($response['http'] != "200");

		}

		unset($response['http']);

		return $response;
	}

}
?>
