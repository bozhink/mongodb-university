#!/usr/bin/python3.4
import pymongo

# connect to mongo
connection = pymongo.MongoClient('mongodb://localhost')

# get a handle to the reddit database
db = connection.school
people = db.people


def insert():
    print('Insert, reporting for duty')

    richard = {'name':'Richard Kreuter', 'company': '10gen',
               'interests': ['horses', 'skydiving', 'fencing']}
    andrew = {'_id': 'erlichson', 'name': 'Andrew Erlichson', 'company': '10gen',
              'interests': ['running', 'cycling', 'photography']}

    try:
        del(andrew['_id'])
        people.insert_one(richard)
        people.insert_one(andrew)

    except Exception as e:
        print('Unexpected error: ', type(e), e)

    print(richard)
    print(andrew)


insert()
