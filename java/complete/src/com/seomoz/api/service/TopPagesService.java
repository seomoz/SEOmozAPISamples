package com.seomoz.api.service;

import java.net.URLEncoder;

import com.seomoz.api.authentication.Authenticator;
import com.seomoz.api.util.ConnectionUtil;
import java.math.BigInteger;

/**
 * Service class to call the various methods to
 * Top Pages Api
 * 
 * Top pages is a paid API that returns the metrics about many URLs on a given subdomain.
 * 
 * @author Radeep Solutions
 *
 */
public class TopPagesService 
{
private Authenticator authenticator;
	
	public TopPagesService()
	{
		
	}
	
	/**
	 * 
	 * @param authenticator
	 */
	public TopPagesService(Authenticator authenticator)
	{
		this.setAuthenticator(authenticator);
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
	public String getTopPages(String objectURL, BigInteger col, int offset, int limit)
	{
		String urlToFetch = "http://lsapi.seomoz.com/linkscape/top-pages/" + URLEncoder.encode(objectURL) + "?" + authenticator.getAuthenticationStr();
		if(offset >= 0 )
		{
			urlToFetch = urlToFetch + "&Offset=" + offset;
		}
		if(limit >= 0)
		{
			urlToFetch = urlToFetch + "&Limit=" + limit;
		}
		if(col.signum() == 1)
		{
			urlToFetch = urlToFetch + "&Cols=" + col;
		}
		//System.out.println(urlToFetch);
		
		String response = ConnectionUtil.makeRequest(urlToFetch);
		
		return response;
	}
	public String getTopPages(String objectURL, long col, int offset, int limit) { return getTopPages(objectURL, BigInteger.valueOf(col), offset, limit); }
	
	/**
	 * 
	 * @param objectURL
	 * @param col
	 * @return
	 * 
	 * @see #getTopPages(String, int, int, int)
	 */
	public String getTopPages(String objectURL, BigInteger col)
	{
		return getTopPages(objectURL, col, -1, -1);
	}
	public String getTopPages(String objectURL, long col) { return getTopPages(objectURL, BigInteger.valueOf(col)); }
	
	/**
	 * 
	 * @param objectURL
	 * @return
	 * 
	 * @see #getTopPages(String, int, int, int)
	 */
	public String getTopPages(String objectURL)
	{
		return getTopPages(objectURL, 0);
	}

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
