namespace StudentsCleaner.Models
{
    using MongoDB.Bson;
    using MongoDB.Bson.Serialization.Attributes;

    public class Score
    {
        [BsonElement("type")]
        public string Type { get; set; }

        [BsonElement("score")]
        public double Value { get; set; }

        public override string ToString()
        {
            return $"{nameof(this.Type)}: {this.Type}, {nameof(this.Value)}: {this.Value}";
        }
    }
}