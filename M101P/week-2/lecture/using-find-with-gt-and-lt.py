import pymongo

# establish a connection to the database
connection = pymongo.MongoClient('mongodb://localhost')

# get a handle to the school database
db = connection.school
scores = db.scores


def find():
    print('Find, reporting for duty')

    query = {'type': 'exam', 'score': {'$gt': 50, '$lt': 70}}
    projection = {'student_id': 1, 'score': 1, '_id': 0}

    try:
        cursor = scores.find(query, projection)

        sanity = 0
        for doc in cursor:
            print(doc)
            sanity += 1
            if sanity > 10:
                break

    except Exception as e:
        print('Unexpected error: ', type(e), e)


find()
