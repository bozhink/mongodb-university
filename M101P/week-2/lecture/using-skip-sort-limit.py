#!/usr/bin/python3.4
import pymongo

# connect to mongo
connection = pymongo.MongoClient('mongodb://localhost')

# get a handle to the reddit database
db = connection.school
scores = db.scores


def find():
    print('Find, reporting for duty')

    query = {}

    try:
        cursor = scores.find(query).sort([('student_id', pymongo.ASCENDING),('score', pymongo.DESCENDING)]).skip(4).limit(4)

        counter = 0
        for doc in cursor:
            print(doc)
            counter += 1
            if counter > 10:
                break

    except Exception as e:
        print('Unexpected error: ', type(e), e)


find()





