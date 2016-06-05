#!/usr/bin/python3.4
import pymongo

"""
Write a program in the language of your choice that will remove the grade of type "homework"
with the lowest score for each student from the dataset in the handout
"""

connection = pymongo.MongoClient('mongodb://localhost')
db = connection.students
grades = db.grades


def remove_grades():
    homeworks = grades.find({'type': 'homework'})
    cursor = homeworks.sort([('student_id', pymongo.ASCENDING), ('score', pymongo.ASCENDING)])

    student_id = -1
    try:
        for homework in cursor:
            if homework['student_id'] > student_id:
                print(homework)

                grades.delete_one({'_id': homework['_id']})

                student_id = homework['student_id']

    except Exception as e:
        print('Error: ', type(e), e)


remove_grades()
