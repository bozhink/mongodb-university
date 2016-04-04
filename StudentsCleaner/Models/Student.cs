namespace StudentsCleaner.Models
{
    using System.Collections.Generic;
    using MongoDB.Bson;
    using MongoDB.Bson.Serialization.Attributes;

    public class Student
    {
        public Student()
        {
            this.Scores = new HashSet<Score>();
        }

        public int Id { get; set; }

        [BsonElement("name")]
        public string Name { get; set; }

        [BsonElement("scores")]
        public ICollection<Score> Scores { get; set; }
    }
}