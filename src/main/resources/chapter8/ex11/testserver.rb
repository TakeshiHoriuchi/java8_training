require 'webrick'

srv = WEBrick::HTTPServer.new(
  Port: 4567,
  DocumentRoot: File.dirname(__FILE__),
  RequestCallback: -> req, res do
    WEBrick::HTTPAuth.basic_auth(req, res, 'my realm') do |user, pass|
      user == 'user' && pass == 'pass'
    end
  end
)

trap(:INT) { srv.shutdown }
srv.start