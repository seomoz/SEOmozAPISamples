<?php
# Please check out the authentication wiki page: http://apiwiki.seomoz.org/w/page/29574176/SignedAuthentication
$objectURL = "www.example.com/";
$accessID = "my access id";
$secretKey = "my secret key";
$expires = mktime() + 300;  // The request is good for the next 5 minutes, or 300 seconds from now.
$stringToSign = $accessID."\n".$expires;

// Get the "raw" or binary output of the hmac hash.
$binarySignature = hash_hmac('sha1', $stringToSign, $secretKey, true);

// We need to base64-encode it and then url-encode that.
$urlSafeSignature = urlencode(base64_encode($binarySignature));
$urlToFetch = "http://lsapi.seomoz.com/linkscape/url-metrics/".urlencode($objectURL)."?AccessID=".$accessID."&Expires=".$expires."&Signature=".$urlSafeSignature;

// You don't have to use fopen and can't in some scenarios. CURL is a better choice for production.
$handle = fopen($urlToFetch, "r");
$contents = '';

while (!feof($handle)) {
    $contents .= fread($handle, 8192);
}

fclose($handle);
echo $contents;

?>