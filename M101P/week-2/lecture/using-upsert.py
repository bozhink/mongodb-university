#!/usr/bin/python3.4
import pymongo

connection = pymongo.MongoClient('mongodb://localhost')
db = connection.test
things = db.things


def using_upsert():
    print('Updating with upsert')

    try:
        things.drop()

        things.update_one({'thing': 'apple'},
                          {'$set': {'color': 'red'}},
                          upsert=True)
        things.update_many({'thing': 'banana'},
                           {'$set': {'color': 'yellow'}},
                           upsert=True)
        things.replace_one({'thing': 'pear'},
                           {'color': 'green'},
                           upsert=True)

        apple = things.find_one({'thing': 'apple'})
        print('apple: ', apple)

        banana = things.find_one({'thing': 'banana'})
        print('banana: ', banana)

        pear = things.find_one({'thing': 'pear'})
        print('pear: ', pear)

    except Exception as e:
        raise 


using_upsert()

