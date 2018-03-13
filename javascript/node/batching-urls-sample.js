'use strict';

var crypto = require('crypto');
var http = require('http');

// `bigJs` is used for number-precision when summing the bitFlag values
var bigJs = require('big.js');

// Set your expires times for several minutes into the future.
// An expires time excessively far in the future will not be honored by the Mozscape API.
// Divide the result of Date.now() by 1000 to make sure your result is in seconds.
var expires = Math.floor((Date.now() / 1000)) + 300;
var accessId = process.env.accessId;
var secretKey = process.env.secretKey;

// `bitFlagExampleValues` is a list of bitFlag values as strings that we'll
// loop over and sum together using helper function: `sumColumnValues`
var bitFlagExampleValues = ['144115188075855872', '68719476736', '34359738368'];
var sumColumnValues = function(bitFlagValues) {
  return bitFlagValues.reduce(function (accu, bitFlag) {
    var accuValBig = new bigJs(accu);
    var bitFlagBig = new bigJs(bitFlag);
    var bigSum = accuValBig.plus(bitFlagBig);

    return bigSum.toString();
  }, 0);
};

// 'cols' is the sum of the bit flags representing each field you want returned.
// Learn more here: https://moz.com/help/guides/moz-api/mozscape/api-reference/url-metrics
// returns "144115291155070976"
var cols = sumColumnValues(bitFlagExampleValues);

// Put each parameter on a new line.
var stringToSign = accessId + "\n" + expires;

//create the hmac hash and Base64-encode it.
var signature = crypto.createHmac('sha1', secretKey).update(stringToSign).digest('base64');
//URL-encode the result of the above.
signature = encodeURIComponent(signature);

var postData = JSON.stringify(['www.moz.com', 'www.apple.com', 'www.pizza.com']);

var options = {
  hostname: 'lsapi.seomoz.com',
  path: '/linkscape/url-metrics/?Cols=' +
			cols + '&AccessID=' + accessId +
			'&Expires=' + expires + '&Signature=' + signature,
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
    'Content-Length': postData.length
  }
};

var responseData = "";

var req = http.request(options, function(response){
  response.setEncoding('utf8');
  response.on('data', function(chunk){
    responseData += chunk;
  });
  response.on('end', function(){
  	console.log(responseData);
  });
});

//Make the request.
req.write(postData);
req.end();
