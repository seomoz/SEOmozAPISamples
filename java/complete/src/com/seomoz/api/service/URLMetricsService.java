package com.seomoz.api.service;

import java.net.URLEncoder;
import java.math.BigInteger;

import com.seomoz.api.authentication.Authenticator;
import com.seomoz.api.util.ConnectionUtil;

/**
 * 
 * Service class to call the various methods to
 * URL Metrics
 * 
 * URL Metrics is a paid API that returns the metrics about a URL or set of URLs.  
 * 
 * @author Radeep Solutions
 *
 */
public class URLMetricsService 
{
	private Authenticator authenticator;
	
	public URLMetricsService()
	{
		
	}
	
	/**
	 * 
	 * @param authenticator
	 */
	public URLMetricsService(Authenticator authenticator)
	{
		this.setAuthenticator(authenticator);
	}
	
	/**
	 * 
	 * This method returns the metrics about a URL or set of URLs.  
	 * 
	 * @param objectURL
	 * @param col This field filters the data to get only specific columns
	 * 			  col = 0 fetches all the data
	 * @return
	 */
	public String getUrlMetrics(String objectURL, BigInteger col)
	{
		
		String urlToFetch = "http://lsapi.seomoz.com/linkscape/url-metrics/" + URLEncoder.encode(objectURL) + "?" + authenticator.getAuthenticationStr();
		//System.out.println(urlToFetch);
		if(col.signum() == 1)
		{
			urlToFetch = urlToFetch + "&Cols=" + col;
		}
		String response = ConnectionUtil.makeRequest(urlToFetch);
		
		return response;
	}
	public String getUrlMetrics(String objectURL, long col) { return getUrlMetrics(objectURL, BigInteger.valueOf(col)); }
	
	/**
	 * 
	 * Fetch all the Url Metrics for the objectURL
	 * 
	 * @param objectURL
	 * @return
	 * 
	 * @see URLMetricsService#getUrlMetrics(String, int)
	 */
	public String getUrlMetrics(String objectURL)
	{
		return getUrlMetrics(objectURL, 0);		
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
