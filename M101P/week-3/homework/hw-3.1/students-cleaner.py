#!/usr/bin/env python

import pymongo

# establish a connection to the database
connection = pymongo.MongoClient()

# get a handle to the test database
db = connection.school
students = db.students


def main():
    cursor = students.find({})

    for student in cursor:
        print(student)
        homeworks = [x for x in student["scores"] if
                     x["type"] == "homework"]  # filter(lambda x: x["type"] == "homework", student["scores"])
        homeworkScores = [x["score"] for x in homeworks]
        minimalHomeworkScore = min(homeworkScores)
        print(minimalHomeworkScore)

        student["scores"].remove({"type": "homework", "score": minimalHomeworkScore})

        students.save(student)

        print(student)

    return


if __name__ == '__main__':
    main()
