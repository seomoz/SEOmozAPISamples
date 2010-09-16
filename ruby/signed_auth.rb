# Signed Authentication has been deprecated.
# Please check out the authentication wiki page: http://apiwiki.seomoz.org/HttpBasicAuth

require 'cgi'
require 'base64'
require 'open-uri'
require 'rubygems'
require 'hmac-sha1'     # sudo gem install ruby-hmac

ACCESS_ID  = CGI::escape("your_access_id")
EXPIRES    = Time.now.to_i + (60*60*2)
API_CALL = 'mozrank'
URL = 'www.seomoz.org'
SECRET_KEY = CGI::escape("your_secret_key")

# Generate the signature
signature = CGI::escape(Base64.encode64(HMAC::SHA1.digest(SECRET_KEY, "#{ACCESS_ID}\n#{EXPIRES}")).chomp)

# Go and fetch the URL
to_fetch = "http://lsapi.seomoz.com/linkscape/#{API_CALL}/#{URL}?AccessID=#{ACCESS_ID}&Expires=#{EXPIRES}&Signature=#{signature}"
result = open(to_fetch).read

# "result" is now the JSON string returned fron the API
puts result
