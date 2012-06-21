package com.seomoz.api.service;

import java.net.URLEncoder;
import java.math.BigInteger;

import com.seomoz.api.authentication.Authenticator;
import com.seomoz.api.util.ConnectionUtil;

/**
 * Service class to call the various methods to 
 * Anchor Text API 
 * 
 * Anchor Text api returns a set of anchor text terms of phrases aggregated across links to a page or domain.
 * 
 * @author Radeep Solutions
 *
 */
public class AnchorTextService 
{
	private Authenticator authenticator;
	
	public AnchorTextService()
	{
		
	}
	
	/**
	 * 
	 * @param authenticator
	 */
	public AnchorTextService(Authenticator authenticator)
	{
		this.setAuthenticator(authenticator);
	}
	
	/**
	 * 
	 * @param objectURL
	 * @param scope
	 * @param sort
	 * @param col
	 * @return a set of anchor text terms of phrases aggregated across links to a page or domain. 
	 * 
	 * @see #getAnchorText(String, String, String, int, int, int)
	 */
	public String getAnchorText(String objectURL, String scope, String sort, BigInteger col)
	{
		return getAnchorText(objectURL, scope, sort, col, -1, -1);
	}
	public String getAnchorText(String objectURL, String scope, String sort, long col) { return getAnchorText(objectURL, scope, sort, BigInteger.valueOf(col)); }
	
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
	 * @param sort etermines the sorting of the links, in combination with limit and offset, this allows fast access to the top links by several orders:
	 *	domains_linking_page: the phrases or terms found in links from the most number of root domains linking are returned first
	 * @param col determines what fields are returned 
	 * @param offset The start record of the page can be specified using the Offset parameter
	 * @param limit The size of the page can by specified using the Limit parameter.
	 * @return a set of anchor text terms of phrases aggregated across links to a page or domain.
	 */
	public String getAnchorText(String objectURL, String scope, String sort, BigInteger col, int offset, int limit)
	{
		String urlToFetch = "http://lsapi.seomoz.com/linkscape/anchor-text/" + URLEncoder.encode(objectURL) + "?" + authenticator.getAuthenticationStr();
		
		if(scope != null)
		{
			urlToFetch = urlToFetch + "&Scope=" + scope;
		}
		if(sort != null)
		{
			urlToFetch = urlToFetch + "&Sort=" + sort;
		}
		if(col.signum() == 1)
		{
			urlToFetch = urlToFetch + "&Cols=" + col;
		}
		
		if(offset >= 0)
		{
			urlToFetch = urlToFetch + "&Offset=" + offset;
		}
		if(limit >= 0)
		{
			urlToFetch = urlToFetch + "&Limit=" + limit;
		}
		String response = ConnectionUtil.makeRequest(urlToFetch);
		
		return response;
	}
	public String getAnchorText(String objectURL, String scope, String sort, long col, int offset, int limit) { return getAnchorText(objectURL, scope, sort, BigInteger.valueOf(col), offset, limit); }

	/**
	 * @param authenticator the authenticator to set
	 */
	public void setAuthenticator(Authenticator authenticator) {
		this.authenticator = authenticator;
	}

	/**
	 * @return the authenticator
	 */
	public Authenticator getAuthenticator() {
		return authenticator;
	}
}
