# Signed Authentication has been deprecated.
# Please check out the authentication wiki page: http://apiwiki.seomoz.org/HttpBasicAuth

import sha, hmac, base64, time, urllib, demjson

SECRET_KEY = "my_secret_key"
ACCESS_ID = "my_access_id"
URL = "www.example.com"

def gen(accessID, secretKey, expires) :
   stringToSign = "%s\n%s" % (accessID, expires)
   return base64.b64encode(hmac.new(secretKey, stringToSign, sha).digest())

expires = int(time.time()+300)
sig = gen(ACCESS_ID, SECRET_KEY, expires)
requestURL = "http://lsapi.seomoz.com/linkscape/url-metrics/%s?%s" % (urllib.urlencode({"":URL})[1:], urllib.urlencode({"AccessID":ACCESS_ID, "Expires" : expires, "Signature" : sig}))
f = urllib.urlopen(requestURL)
response = f.read()
f.close()

result = demjson.decode(response)
print("mozRank: %s" % result["umrp"])