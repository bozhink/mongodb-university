using MongoDB.Bson;
using MongoDB.Driver;
using System.Threading.Tasks;

namespace M101DotNet
{
    internal class InsertTest
    {
        private static void Main(string[] args)
        {
            MainAsync(args).GetAwaiter().GetResult();
        }

        private static async Task MainAsync(string[] args)
        {
            var client = new MongoClient();
            var db = client.GetDatabase("test");

            var animals = db.GetCollection<BsonDocument>("animals");

            var animal = new BsonDocument
                            {
                            {"animal", "monkey"}
                            };

            await animals.InsertOneAsync(animal);

            animal.Remove("animal");
            animal.Add("animal", "cat");

            await animals.InsertOneAsync(animal);

            animal.Remove("animal");
            animal.Add("animal", "lion");

            await animals.InsertOneAsync(animal);
        }
    }
}