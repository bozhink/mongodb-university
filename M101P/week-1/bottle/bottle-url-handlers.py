import bottle


@bottle.route('/')
def home_page():
    return 'Hello World\n'


@bottle.route('/testpage')
def test_page():
    return '<html><title></title><body><p>this is a test page</p></body></html>'


bottle.debug(True)
bottle.run(host='localhost', port=8082)
