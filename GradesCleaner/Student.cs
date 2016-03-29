namespace GradesCleaner
{
    using MongoDB.Bson;
    using MongoDB.Bson.Serialization.Attributes;

    public class Student
    {
        public ObjectId Id { get; set; }

        [BsonElement("student_id")]
        public int StudentId { get; set; }

        [BsonElement("type")]
        public string Type { get; set; }

        [BsonElement("score")]
        public double Score { get; set; }

        public override string ToString()
        {
            return string.Format("{0} {1} {2} {3}", this.Id, this.StudentId, this.Type, this.Score);
        }
    }
}
