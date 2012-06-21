package com.seomoz.api.service;

import java.net.URLEncoder;
import java.math.BigInteger;

import com.seomoz.api.authentication.Authenticator;
import com.seomoz.api.util.ConnectionUtil;

/**
 * 
 * Service class to call the various methods to 
 * Links API 
 * 
 * Links api returns a set of links to a page or domain.
 * 
 * @author Radeep Solutions
 *
 */
public class LinksService 
{
	private Authenticator authenticator;
	
	public LinksService()
	{
		
	}
	
	/**
	 * 
	 * @param authenticator
	 */
	public LinksService(Authenticator authenticator)
	{
		this.setAuthenticator(authenticator);
	}
	
	/**
	 * This method returns a set of links to a page or domain.
	 * 
	 * @param objectURL
	 * @param scope determines the scope of the Target link, as well as the Source results.
	 * @param filters  filters the links returned to only include links of the specified type.  You may include one or more of the following values separated by '+'
	 * @param sort determines the sorting of the links, in combination with limit and offset, this allows fast access to the top links by several orders:
	 * @param col specifies data about the source of the link is included
	 * @return
	 * 
	 * @see #getLinks(String, String, String, String, int, int, int)
	 */
	public String getLinks(String objectURL, String scope, String filters, String sort, BigInteger col)
	{
		return getLinks(objectURL, scope, filters, sort, col, -1, -1);
	}
	public String getLinks(String objectURL, String scope, String filters, String sort, long col) { return getLinks(objectURL, scope, filters, sort, BigInteger.valueOf(col)); }
	
	/**
	 * This method returns a set of links to a page or domain.
	 * 
	 * @param objectURL
	 * @param scope determines the scope of the Target link, as well as the Source results.
	 * @param filters  filters the links returned to only include links of the specified type.  You may include one or more of the following values separated by '+'
	 * @param sort determines the sorting of the links, in combination with limit and offset, this allows fast access to the top links by several orders:
	 * @param col specifies data about the source of the link is included
	 * @param offset The start record of the page can be specified using the Offset parameter
	 * @param limit The size of the page can by specified using the Limit parameter.
	 * @return
	 */
	public String getLinks(String objectURL, String scope, String filters, String sort, BigInteger col, int offset, int limit)
	{
		String urlToFetch = "http://lsapi.seomoz.com/linkscape/links/" + URLEncoder.encode(objectURL) + "?" + authenticator.getAuthenticationStr();
		
		if(scope != null)
		{
			urlToFetch = urlToFetch + "&Scope=" + scope;
		}
		if(filters != null)
		{
			urlToFetch = urlToFetch + "&Filter=" + filters;
		}
		if(sort != null)
		{
			urlToFetch = urlToFetch + "&Sort=" + sort;
		}
		if(col.signum() ==  1)
		{
			urlToFetch = urlToFetch + "&SourceCols=" + col;
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
	public String getLinks(String objectURL, String scope, String filters, String sort, long col, int offset, int limit) { return getLinks(objectURL, scope, filters, sort, BigInteger.valueOf(col), offset, limit); }

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
