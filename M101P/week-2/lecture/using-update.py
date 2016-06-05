#!/usr/bin/python3.4
import pymongo
import datetime

connection = pymongo.MongoClient('mongodb://localhost')

def add_review_date_using_update_one(student_id):
    print('Updating record using update_one and $set')
    db = connection.school
    scores = db.scores

    try:
        score = scores.find_one({'student_id': student_id, 'type': 'homework'})
        print('Before: ', score)

        record_id = score['_id']
        result = scores.update_one({'_id': record_id},
                                   {'$set': {'review_date': datetime.datetime.utcnow()}})
        print('Number of matches: ', result.matched_count)

        score = scores.find_one({'_id': record_id})
        print('After: ', score)

    except Exception as e:
        raise


def add_review_dates_for_all():
    print('Updating record using update_many and $set')

    db = connection.school
    scores = db.scores

    try:
        result = scores.update_many({}, {'$set': {'review_date': datetime.datetime.utcnow()}})
        print('Number of matches: ', result.matched_count)

    except Exception as e:
        raise


add_review_date_using_update_one(1)
add_review_dates_for_all()

