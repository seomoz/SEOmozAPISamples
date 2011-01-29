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
	 * @param objectURL
	 * @param col  A set of metrics can be requested by indicating them as bit flags in the Cols query parameter.
	 * @param offset The start record of the page can be specified using the Offset parameter
	 * @param limit The size of the page can by specified using the Limit parameter. 
	 * @return
	 */
	public function getTopPages($objectURL, $col = 0, $offset = -1, $limit = -1)
	{
		$urlToFetch = "http://lsapi.seomoz.com/linkscape/top-pages/" . urlencode($objectURL) . "?" . $this->authenticator->getAuthenticationStr();
		if($offset >= 0)
		{
			$urlToFetch = $urlToFetch . "&Offset=" . $offset;
		}
		if($limit >= 0)
		{
			$urlToFetch = $urlToFetch . "&Limit=" . $limit;
		}
		if($col > 0)
		{
			$urlToFetch = $urlToFetch . "&Cols=" . $col;
		}
		
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