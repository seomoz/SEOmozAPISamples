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
require_once 'AbstractService.php';
class AnchorTextService extends AbstractService
{	
	/**
	 * This method returns a set of anchor text terms of phrases aggregated across links to a page or domain.
	 * 
	 * @param string objectURL
	 * @param array options
	 * @return a set of anchor text terms of phrases aggregated across links to a page or domain.
	 */
	public function getAnchorText($objectURL, array $options = array())
	{
		$urlToFetch = "http://lsapi.seomoz.com/linkscape/anchor-text/" . urlencode($objectURL) . "?" . $this->getAuthenticator()->getAuthenticationStr();
      
      /**
       * scope determines the scope of the link, and takes one of the following values:
       *    phrase_to_page: returns a set of phrases found in links to the specified page
       *    phrase_to_subdomain: returns a set of phrases found in links to the specified subdomain
       *    phrase_to_domain: returns a set of phrases found in links to the specified root domain
       *    term_to_page: returns a set of terms found in links to the specified page
       *    term_to_subdomain: returns a a set of terms found in links to the specified subdomain
       *    term_to_domain: returns a a set of terms found in links to the specified root domain
       */ 
      if (isset($options['scope']) && $options['scope'] !== null) {
         $urlToFetch .= "&Scope=" . $options['scope'];
      }
      
      /**
       *  sort determines the sorting of the links, in combination with limit and offset, this allows fast access to the top links by several orders:
       *    domains_linking_page: the phrases or terms found in links from the most number of root domains linking are returned first  
       */
      if (isset($options['sort']) && $options['sort'] !== null) {
         $urlToFetch .= "&Sort=" . $options['sort'];
      }
      
      /**
       * cols determines what fields are returned
       */
      if (isset($options['cols']) && (int)$options['cols'] > 0) {
          $urlToFetch .= "&Cols=" . (int)$options['cols'];
      }
      
      /**
       *  Offset The start record of the page can be specified using the Offset parameter
       */
      if ((isset($options['offset']) && (int)$options['offset'] > 0)) {
          $urlToFetch .= "&Offset=" . (int)$options['offset'];
      }
      
      /**
       * limit The size of the page can by specified using the Limit parameter. 
       */
      if ((isset($options['limit']) && (int)$options['limit'] > 0)) {
          $urlToFetch .= "&Limit=" . $options['limit'];
      }
         
      $response = ConnectionUtil::makeRequest($urlToFetch);
		
      return $response;
	}	
}