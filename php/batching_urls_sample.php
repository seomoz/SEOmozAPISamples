<?php
// Get your access id and secret key here: https://moz.com/products/api/keys
$accessID = "ACCESS_ID_HERE";
$secretKey = "SECRET_KEY_HERE";

// Set your expires times for several minutes into the future.
// An expires time excessively far in the future will not be honored by the Mozscape API.
$expires = time() + 300;

// Put each parameter on a new line.
$stringToSign = $accessID."\n".$expires;

// Get the "raw" or binary output of the hmac hash.
$binarySignature = hash_hmac('sha1', $stringToSign, $secretKey, true);

// Base64-encode it and then url-encode that.
$urlSafeSignature = urlencode(base64_encode($binarySignature));

// Add up all the bit flags you want returned.
// Learn more here: https://moz.com/help/guides/moz-api/mozscape/api-reference/url-metrics
$cols = "68719476736";

// Put it all together and you get your request URL.
$requestUrl = "http://lsapi.seomoz.com/linkscape/url-metrics/?Cols=".$cols."&AccessID=".$accessID."&Expires=".$expires."&Signature=".$urlSafeSignature;

// Put your URLS into an array and json_encode them.
$batchedDomains = array('www.moz.com', 'www.apple.com', 'www.pizza.com');
$encodedDomains = json_encode($batchedDomains);

// Use Curl to send off your request.
// Send your encoded list of domains through Curl's POSTFIELDS.
$options = array(
	CURLOPT_RETURNTRANSFER => true,
	CURLOPT_POSTFIELDS     => $encodedDomains
	);

$ch = curl_init($requestUrl);
curl_setopt_array($ch, $options);
$content = curl_exec($ch);
curl_close( $ch );

$contents = json_decode($content);
print_r($contents);
?>
