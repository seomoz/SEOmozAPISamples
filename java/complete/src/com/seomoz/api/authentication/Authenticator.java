package com.seomoz.api.authentication;

import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


/**
 * The authentication class which is used to generate the authentication string
 * 
 * @author Radeep Solutions 
 */
public class Authenticator 
{
	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	
	/**
	 * accessID The user's Access ID
	 */
	private String accessID;
	
	/**
	 * secretKey The user's Secret Key 
	 */
	private String secretKey;
	
	/**
	 * expiresInterval The interval after which the authentication string expires
	 * Default 300s 
	 */
	private long expiresInterval = 300; 
	
	public Authenticator()
	{
		
	}
	
	/**
	 * Constructor to set all the variables
	 * 
	 * @param accessID
	 * @param secretKey
	 * @param expiresInterval
	 */
	public Authenticator(String accessID, String secretKey, long expiresInterval)
	{
		this.accessID = accessID;
		this.secretKey = secretKey;
		this.expiresInterval = expiresInterval;
	}
	
	/**
	 * 
	 * This method calculates the authentication String based on the 
	 * user's credentials.
	 * 
	 * Set the user credentials before calling this method
	 * 
	 * @return the authentication string
	 * 
	 * @see #setAccessID(String)
	 * @see #setSecretKey(String)
	 */
	public String getAuthenticationStr()
	{
		long expires = ((new Date()).getTime())/1000 + expiresInterval;
		
		String stringToSign = accessID + "\n" + expires;
		
		SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(), HMAC_SHA1_ALGORITHM);

		// get an hmac_sha1 Mac instance and initialize with the signing key
		Mac mac = null;
		try 
		{
			mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
			mac.init(signingKey);
		} 
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
			return "";
		}
		catch (InvalidKeyException e) 
		{
			e.printStackTrace();
			return "";
		}

		// compute the hmac on input data bytes
		byte[] rawHmac = mac.doFinal(stringToSign.getBytes());

		// base64-encode the hmac
		String urlSafeSignature = URLEncoder.encode(EncodeBase64(rawHmac));
		
		String authenticationStr = "AccessID=" + accessID + "&Expires=" + expires + "&Signature=" + urlSafeSignature;

		return authenticationStr;
	}
	
	/**
	 * Encodes the rawdata in Base64 format
	 * 
	 * @param rawData
	 * @return
	 */
	public String EncodeBase64(byte[] rawData) 
	{
		return Base64.encodeBytes(rawData);
	}

	/**
	 * @return the accessID
	 */
	public String getAccessID() {
		return accessID;
	}

	/**
	 * @param accessID the accessID to set
	 */
	public void setAccessID(String accessID) {
		this.accessID = accessID;
	}

	/**
	 * @return the secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}

	/**
	 * @param secretKey the secretKey to set
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	/**
	 * @return the expiresInterval
	 */
	public long getExpiresInterval() {
		return expiresInterval;
	}

	/**
	 * @param expiresInterval the expiresInterval to set
	 */
	public void setExpiresInterval(long expiresInterval) {
		this.expiresInterval = expiresInterval;
	}
}
