import bottle


@bottle.route('/')
def home_page():
    things = ['apple', 'orange', 'banana', 'peach']
    # return bottle.template('hello_world', username='Andrew', things=things)
    return bottle.template('hello_world', {'username': 'Richard', 'things':things})


bottle.debug(True)
bottle.run(host='localhost', port=8082)


