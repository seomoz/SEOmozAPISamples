<?php
/**
 * Service class to call the various methods to 
 * Anchor Text API 
 * 
 * Anchor Text api returns a set of anchor text terms of phrases aggregated across links to a page or domain.
 * 
 * @author Radeep Solutions
 *
 */
class AnchorTextService 
{
	private $authenticator;
	
	public function __construct($authenticator)
	{
		$this->authenticator = $authenticator;		
	}
	
	/**
	 * This method returns a set of anchor text terms of phrases aggregated across links to a page or domain.
	 * 
	 * @param objectURL
	 * @param scope determines the scope of the link, and takes one of the following values:
	 * 	phrase_to_page: returns a set of phrases found in links to the specified page
	 *	phrase_to_subdomain: returns a set of phrases found in links to the specified subdomain
	 *	phrase_to_domain: returns a set of phrases found in links to the specified root domain
	 *	term_to_page: returns a set of terms found in links to the specified page
	 *	term_to_subdomain: returns a a set of terms found in links to the specified subdomain
	 *	term_to_domain: returns a a set of terms found in links to the specified root domain
	 * @param sort determines the sorting of the links, in combination with limit and offset, this allows fast access to the top links by several orders:
	 *	domains_linking_page: the phrases or terms found in links from the most number of root domains linking are returned first
	 * @param col determines what fields are returned
	 * @param offset The start record of the page can be specified using the Offset parameter
	 * @param limit The size of the page can by specified using the Limit parameter. 
	 * @return a set of anchor text terms of phrases aggregated across links to a page or domain.
	 */
	public function getAnchorText($objectURL, $scope = null, $sort = null, $col = 0, $offset = -1, $limit = -1 )
	{
		$urlToFetch = "http://lsapi.seomoz.com/linkscape/anchor-text/" . urlencode($objectURL) . "?" . $this->authenticator->getAuthenticationStr();
		
		if($scope != null)
		{
			$urlToFetch = $urlToFetch . "&Scope=" . $scope;
		}
		if($sort != null)
		{
			$urlToFetch = $urlToFetch . "&Sort=" . $sort;
		}
		if($col > 0)
		{
			$urlToFetch = $urlToFetch . "&Cols=" . $col;
		}
		if($offset >= 0)
		{
			$urlToFetch = $urlToFetch . "&Offset=" . $offset;
		}
		if($limit >= 0)
		{
			$urlToFetch = $urlToFetch . "&Limit=" . $limit;
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