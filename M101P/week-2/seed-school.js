use school;
db.scores.drop();
var types = ['exam', 'homework', 'quiz'];

for (var student_id = 0; student_id < 100; student_id += 1) {
    for (var type = 0; type < 3; type += 1) {
        var r = {
            'student_id': student_id,
            'type': types[type],
            'score': Math.random() * 100
        };

        db.scores.insert(r);
    }
}