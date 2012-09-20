require 'openssl'
require 'base64'
require 'cgi'
require 'open-uri'

# You can obtain you access id and secret key here: http://www.seomoz.org/api/keys
ACCESS_ID	= "ACCESS_ID_HERE"
SECRET_KEY	= "SECRET_KEY_HERE"

# Set your expires for several minutes into the future.
# Values excessively far in the future will not be honored by the Mozscape API.
expires	= Time.now.to_i + 300

# A new linefeed is necessary between your AccessID and Expires.
string_to_sign = "#{ACCESS_ID}\n#{expires}"

# Get the "raw" or binary output of the hmac hash.
binary_signature = OpenSSL::HMAC.digest('sha1', SECRET_KEY, string_to_sign)

# We need to base64-encode it and then url-encode that.
URL_SAFE_SIGNATURE = CGI::escape(Base64.encode64(binary_signature).chomp)

# This is the URL that we want metrics for.
object_url = 'www.seomoz.org'

# Add up all the bit flags you want returned.
# Learn more here: http://apiwiki.seomoz.org/categories/api-reference
cols = '103079215108'

# Now put your entire request together.
# This example uses the Mozscape URL Metrics API.
request_url = "http://lsapi.seomoz.com/linkscape/url-metrics/#{object_url}?Cols=#{cols}&AccessID=#{ACCESS_ID}&Expires=#{expires}&Signature=#{URL_SAFE_SIGNATURE}"

# Go and fetch the URL
response = open(request_url).read

# "response" is now the JSON string returned fron the API
puts response