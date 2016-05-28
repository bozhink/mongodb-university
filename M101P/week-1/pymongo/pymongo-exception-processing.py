import sys
import pymongo

connection = pymongo.MongoClient('mongodb://localhost')

db = connection.test
users = db.users

doc = {'firstName':'Andrew', 'lastName':'Erlichson'}
print(doc)

print('About to insert the document')
try:
    users.insert_one(doc)
except Exception as e:
    print('Insert failed: ', e)

print(doc)

print('Inserting again')
try:
    users.insert_one(doc)
except Exception as e:
    print('Second insert failed: ', e)


