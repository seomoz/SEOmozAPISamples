require 'net/http'
require 'uri'

ACCESS_ID  = "your_access_id"
SECRET_KEY = "your_secret_key"
API_CALL = 'url-metrics'
URL = 'www.seomoz.org'

url = URI.parse("http://lsapi.seomoz.com/linkscape/#{API_CALL}/#{URL}")
puts url.to_s
Net::HTTP.start(url.host) do |http|
  req = Net::HTTP::Get.new(url.request_uri)
  req.basic_auth ACCESS_ID, SECRET_KEY
  response = http.request(req)
  result = response.body
  # 'result' is now the JSON string returned fron the API
  puts result
end