<?php
/**
 *
 * Service class to call the various methods to
 * Metadata API
 *
 * Medaata is a free API that returns Mozscape specific updates.
 *
 * @author SEOmoz
 *
 */
require_once 'abstract_service.php';
class MetadataService extends AbstractService {
	/**
	 *
	 * This method returns the metadata for the Mozscape API
	 *
	 * @param array $options    options which can be passed to make request
	 * @return response
	 */
	public function getMetadata(array $options = array()) {

		$urlToFetch = "http://lsapi.seomoz.com/linkscape/metadata/";

		// Determines the Mozscape update information to return
		if (isset($options['updates'])) {
			$urlToFetch .= $options['updates'] . "/";
		}

		$urlToFetch .= "?" . $this->getAuthenticator()->getAuthenticationStr();

		$response = ConnectionUtility::makeSimpleRequest($urlToFetch);

		return $response;
	}

}
?>
