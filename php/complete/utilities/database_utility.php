<?php
/**
 * The dbConnector class is used to connect to a database
 *
 * @author SEOmoz
 */
class dbConnector
{
	/**
	 * hostname The hostname for mysql
	 */
	private $hostname;

	/**
	 * database The database to connect
	 */
	private $database;

	/**
	 * table The table to use
	 */
	private $table;

	/**
	 * username The username to connect with
	 */
	private $username;

	/**
	 * password The password to connect with
	 */
	private $password;

	/**
	 *
	 * This method sets up the database connection
	 *
	 * Set the credentials before calling this method
	 *
	 * @see #setHostname(String)
	 * @see #setSecretKey(String)
	 * @see #setPassword(String)
	 */
	public function connectDB() {
		$mysqlConnect = mysql_connect($this -> hostname, $this -> username, $this -> password);
		@mysql_select_db($this -> database) or die( "Unable to select database");
	}

	/**
	 *
	 * This method gets all the URLs from DB
	 *
	 * Assumes table has column named `url`
	 */
	public function getURLsFromDB() {
		$db_urls = mysql_query("SELECT * FROM " . $this -> table);
		return $db_urls;
	}

	/**
	 *
	 * This method batches the retrieved URLs
	 *
	 * Set batch_size before calling this method
	 *
	 * @see #setBatchSize(Integer)
	 */
	public function getBatchedURLs($db_urls) {
		$bulk_urls = array();
		while($row = mysql_fetch_array($db_urls)) {
			$bulk_urls[] = $row['url'];
		}
		$batchedDomains = array_chunk($bulk_urls, $this -> batch_size, false);
		return $batchedDomains;
	}

	/**
	 *
	 * This method closes the DB connection
	 *
	 */
	public function closeDB() {
		mysql_close();
	}

	/**
	 * @param $hostname the $hostname to set
	 */
	public function setHostname($hostname) {
		$this->hostname = $hostname;
	}

	/**
	 * @param $database the $database to set
	 */
	public function setDatabase($database) {
		$this->database = $database;
	}

	/**
	 * @param $username the $username to set
	 */
	public function setUsername($username) {
		$this->username = $username;
	}

	/**
	 * @param $password the $password to set
	 */
	public function setPassword($password) {
		$this->password = $password;
	}

	/**
	 * @param $table the $table to set
	 */
	public function setTable($table) {
		$this->table = $table;
	}

	/**
	 * @param $table the $table to set
	 */
	public function setBatchSize($batch_size) {
		$this->batch_size = $batch_size;
	}

}
?>