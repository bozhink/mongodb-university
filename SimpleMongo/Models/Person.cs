namespace SimpleMongo.Models
{
    using System.Collections.Generic;
    using MongoDB.Bson;
    using MongoDB.Bson.Serialization.Attributes;
    public class Person
    {
        public ObjectId Id { get; set; }

        [BsonElement("name")]
        public string Name { get; set; }

        public int Age { get; set; }

        public ICollection<string> Colors { get; set; }

        public ICollection<Pet> Pets { get; set; }

        public BsonDocument ExtraElements { get; set; }
    }
}