require 'openssl'
require 'base64'
require 'cgi'
require 'json'
require 'net/http'
require 'uri'

# You can obtain you access id and secret key here: http://moz.com/products/api/keys
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
# Learn more here: http://apiwiki.moz.com/query-parameters/
cols = '103079215108'

# Now put your entire request together.
# This example uses the Mozscape URL Metrics API.
request_url = "http://lsapi.seomoz.com/linkscape/url-metrics/?Cols=#{cols}&AccessID=#{ACCESS_ID}&Expires=#{expires}&Signature=#{URL_SAFE_SIGNATURE}"

# Put your URLS into an array and json_encode them.
batched_domains = ['www.moz.com', 'www.apple.com', 'www.pizza.com']
encoded_domains = batched_domains.to_json

# Go and fetch the URL
uri = URI.parse("#{request_url}")
http = Net::HTTP.new(uri.host, uri.port)
request = Net::HTTP::Post.new(uri.request_uri)
request.body = encoded_domains
response = http.request(request)

puts response.body
