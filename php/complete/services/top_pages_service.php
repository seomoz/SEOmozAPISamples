<?php
/**
 * Service class to call the various methods to
 * Top Pages Api
 *
 * Top pages is a paid API that returns the metrics about many URLs on a given subdomain.
 *
 * @author SEOmoz
 *
 */
require_once 'abstract_service.php';
class TopPagesService extends AbstractService {
	/**
	 * This method returns the metrics about many URLs on a given subdomain
	 *
	 * @param string objectURL
	 * @param array $options
	 * @return
	 */
	public function getTopPages($objectURL, array $options = array()) {

		$urlToFetch = "http://lsapi.seomoz.com/linkscape/top-pages/" . urlencode($objectURL) . "?" . $this->getAuthenticator()->getAuthenticationStr();

		/**
		 * @param cols - A set of metrics can be requested by indicating them as bit flags in the Cols query parameter.
		 */
		if ((isset($options['cols']) && (int)$options['cols'] > 0)) {
			$urlToFetch .= "&Cols=" . $options['cols'];
		}

		/**
		 * @param offset - The start record of the page can be specified using the Offset parameter
		 */
		if (isset($options['offset']) && (int)$options['offset'] > 0) {
			$urlToFetch .= "&Offset=" . $options['offset'];
		}

		/**
		 * @param limit - The size of the page can by specified using the Limit parameter.
		 */
		if ((isset($options['limit']) && (int)$options['limit'] > 0)) {
			$urlToFetch .= "&Limit=" . $options['limit'];
		}
		
		print_r($urlToFetch);
		$response = ConnectionUtility::makeRequest($urlToFetch);

		return $response;
	}

}
?>