require 'openssl'
require 'base64'
require 'cgi'
require 'open-uri'

# Get your access id and secret key here: http://moz.com/products/api/keys
ACCESS_ID	= "ACCESS_ID_HERE"
SECRET_KEY	= "SECRET_KEY_HERE"

# Set your expires time for several minutes into the future.
# Expires times excessively far in the future will not be honored by the Mozscape API.
expires	= Time.now.to_i + 300

# Put each parameter on a new line.
string_to_sign = "#{ACCESS_ID}\n#{expires}"

# Get the "raw" or binary output of the hmac hash.
binary_signature = OpenSSL::HMAC.digest('sha1', SECRET_KEY, string_to_sign)

# Base64-encode it and then url-encode that.
URL_SAFE_SIGNATURE = CGI::escape(Base64.encode64(binary_signature).chomp)

# Specify the URL that you want metrics for.
object_url = 'www.moz.com'

# Add up all the bit flags you want returned.
# Learn more here: https://moz.com/help/guides/moz-api/mozscape/api-reference/url-metrics
cols = '103079215108'

# Now put your entire request together.
# This example uses the Mozscape URL Metrics API.
request_url = "http://lsapi.seomoz.com/linkscape/url-metrics/#{object_url}?Cols=#{cols}&AccessID=#{ACCESS_ID}&Expires=#{expires}&Signature=#{URL_SAFE_SIGNATURE}"

# Go and fetch the URL
response = open(request_url).read

# "response" is now the JSON string returned fron the Mozscape API
puts response
