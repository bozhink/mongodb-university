#!/usr/bin/python3.4
import pymongo

# connect to mongo
connection = pymongo.MongoClient('mongodb://localhost')

# get a handle to the reddit database
db = connection.school
people = db.people
people.drop()


def insert():
    print('Insert, reporting for duty')

    richard = {'name':'Richard Kreuter', 'company': '10gen',
               'interests': ['horses', 'skydiving', 'fencing']}
    andrew = {'_id': 'erlichson', 'name': 'Andrew Erlichson', 'company': '10gen',
              'interests': ['running', 'cycling', 'photography']}

    people_to_insert = [richard, andrew]

    try:
        people.insert_many(people_to_insert, ordered=True)

    except Exception as e:
        print('Unexpected error: ', type(e), e)

    print(richard)
    print(andrew)


insert()
