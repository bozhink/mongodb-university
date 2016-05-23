using MongoDB.Bson;
using MongoDB.Driver;
using System.Threading.Tasks;

namespace InsertOne
{
    public class Program
    {
        public static void Main(string[] args)
        {
            MainAsync(args).Wait();
        }

        public static async Task MainAsync(string[] args)
        {
            var client = new MongoClient();
            var db = client.GetDatabase("test");
            var collection = db.GetCollection<BsonDocument>("people");
            var document = new BsonDocument
            {
                { "Name", "Smith" },
                { "Age", 30 },
                { "Proffesion", "Hacker" }
            };

            await collection.InsertOneAsync(document);

            // quiz
            var people = db.GetCollection<BsonDocument>("people");

            var person = new BsonDocument
            {
                    { "name", "Craig Wilson" },
                    {"company", "MongoDB" }
            };

            await people.InsertOneAsync(person);
            person.Remove("_id");
            await people.InsertOneAsync(person);
        }
    }
}