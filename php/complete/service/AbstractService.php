<?php
/**
 * abstract class for services
 *
 * @package Service
 * @author aur1mas <aur1mas@devnet.lt>
 */
abstract class AbstractService
{
    
	private $authenticator;
	
	public function __construct($authenticator)
	{
		$this->authenticator = $authenticator;		
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