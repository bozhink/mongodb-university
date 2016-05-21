db.grades.aggregate([{
    "$unwind": "$scores"
}, {
    "$match": {
        "scores.type": {
            "$in": ["exam", "homework"]
        }
    }
}, {
    "$group": {
        "_id": {
            "class_id": "$class_id",
            "student_id": "$student_id"
        },
        "score": {"$avg": "$scores.score"}
    }
}, {
    "$group": {
        "_id": "$_id.class_id",
        "score": {"$avg": "$score"}
    }
}, {
    "$sort": {
        "score":-1
    }
}]);