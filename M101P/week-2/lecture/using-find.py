import pymongo
import sys


# establish a connection to the database
connection = pymongo.MongoClient('mongodb://localhost')

# get a handle to the school database
db = connection.school
scores = db.scores


def find():
    print('Find, reporting for duty')

    query = {'type': 'exam'}

    try:
        cursor = scores.find(query)

        sanity = 0
        for doc in cursor:
            print(doc)
            sanity += 1
            if sanity > 10:
                break

    except Exception as e:
        print('Unexpected error: ', type(e), e)


def find_one():
    print("Find one, reporting for duty")

    query = {'student_id': 10}

    try:
        doc = scores.find_one(query)
        print(doc)

    except Exception as e:
        print('Unexpected error: ', type(e), e)


find_one()
find()
