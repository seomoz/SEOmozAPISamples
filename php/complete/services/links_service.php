<?php
/**
 *
 * Service class to call the various methods to
 * Links API
 *
 * Links api returns a set of links to a page or domain.
 *
 * @author SEOmoz
 *
 */
 require_once 'abstract_service.php';
class LinksService extends AbstractService {
   /**
   * This method returns a set of links to a page or domain.
   *
   * @param string $objectURL
   * @param array $options    options which can be passed to make request
   * @return stdObject
   */
	public function getLinks($objectURL, array $options = array()) {

		$urlToFetch = "http://lsapi.seomoz.com/linkscape/links/" . urlencode($objectURL) . "?" . $this->getAuthenticator()->getAuthenticationStr();

		// scope determines the scope of the Target link, as well as the Source results
		if (isset($options['scope'])) {
			$urlToFetch .= "&Scope=" . $options['scope'];
		}

		// filters the links returned to only include links of the specified type.  You may include one or more of the following values separated by '+'
		if (isset($options['filters'])) {
			$urlToFetch .= "&Filter=" . $options['filters'];
		}

		// sort determines the sorting of the links, in combination with limit and offset, this allows fast access to the top links by several orders.
		if (isset($options['sort'])) {
			$urlToFetch .= "&Sort=" . $options['sort'];
		}

		// specifies data about the source of the link is included
		if (isset($options['source_cols'])) {
			$urlToFetch .= "&SourceCols=" . $options['source_cols'];
		}

		// specifies data about the target of the link is included
		if (isset($options['target_cols'])) {
			$urlToFetch .= "&TargetCols=" . $options['target_cols'];
		}

		// specifies data about the link itself (e.g. if the link is nofollowed)
		if (isset($options['link_cols'])) {
			$urlToFetch .= "&LinkCols=" . $options['link_cols'];
		}

		// The start record of the page can be specified using the Offset parameter
		if (isset($options['offset']) && (int)$options['offset'] >= 0) {
			$urlToFetch .= "&Offset=" . (int)$options['offset'];
		}

		// The size of the page can by specified using the Limit parameter.
		if (isset($options['limit']) && (int)$options['limit'] >= 0) {
			$urlToFetch .= "&Limit=" . (int)$options['limit'];
		}

		$response = ConnectionUtility::makeRequest($urlToFetch);

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
