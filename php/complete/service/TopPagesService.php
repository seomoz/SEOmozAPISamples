<?php
/**
 * Service class to call the various methods to
 * Top Pages Api
 * 
 * Top pages is a paid API that returns the metrics about many URLs on a given subdomain.
 * 
 * @author Radeep Solutions
 *
 */
class TopPagesService 
{
	private $authenticator;
	
	public function __construct($authenticator)
	{
		$this->authenticator = $authenticator;		
	}
	
	/**
	 * This method returns the metrics about many URLs on a given subdomain
	 * 
	 * @param string objectURL
	 * @param array $options
	 * @return
	 */
	public function getTopPages($objectURL, array $options = array())
	{
		$urlToFetch = "http://lsapi.seomoz.com/linkscape/top-pages/" . urlencode($objectURL) . "?" . $this->authenticator->getAuthenticationStr();
        
        /**
         * @param cols  A set of metrics can be requested by indicating them as bit flags in the Cols query parameter.
         */
        $options['cols'] = (isset($options['cols']) && (int)$options['cols'] > 0) ? (int)$options['cols'] : 0;
        $urlToFetch .= "&Cols=" . $options['cols'];
        
        /**
         * @param offset The start record of the page can be specified using the Offset parameter
         */
        $options['offset'] = (isset($options['offset']) && (int)$options['offset'] > 0) ? (int)$options['offset'] : -1;
        $urlToFetch .= "&Offset=" . $options['offset'];
        
        /**
         * @param limit The size of the page can by specified using the Limit parameter. 
         */
        $options['limit'] = (isset($options['limit']) && (int)$options['limit'] > 0) ? (int)$options['limit'] : -1;
        $urlToFetch .= "&Limit=" . $options['limit'];
		
		$response = ConnectionUtil::makeRequest($urlToFetch);
		
		return $response;
	}

	/**
	 * @return the $authenticator
	 */
	public function getAuthenticator() {
		return $this->authenticator;
	}

	/**
	 * @param $authenticator the $authenticator to set
	 */
	public function setAuthenticator($authenticator) {
		$this->authenticator = $authenticator;
	}
}

?>