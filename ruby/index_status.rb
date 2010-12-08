require 'linkscape'

lsapi = Linkscape::Client.new(:accessID => "your_access_id", :secret => "your_secret_key")

print "Linkscape index last updated on #{Time.at(lsapi.status['index_metrics']['last_update'].to_s.to_i)}\n"
