#!/usr/bin/python3.4
import pymongo


# gets the next number in a sequence
def get_next_sequence_number(name):
    connection = pymongo.MongoClient("mongodb://localhost")

    db = connection.test
    counters = db.counters

    # find_one_and_update
    # find_one_and_delete
    # find_one_and_replace
    # all these map to the the command find_and_modify

    counter_value = None
    try:
        counter = counters.find_one_and_update(filter={'type': name},
                                               update={'$inc': {'value': 1}},
                                               upsert=True,
                                               return_document=pymongo.ReturnDocument.AFTER)
        counter_value = counter['value']

    except Exception as e:
        print("Exception: ", type(e), e)

    return counter_value


print(get_next_sequence_number('uid'))
print(get_next_sequence_number('uid'))
print(get_next_sequence_number('uid'))
