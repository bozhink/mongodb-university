/// <summary>
/// M101N HW 3.1
/// </summary>
namespace StudentsCleaner
{
    using System;
    using System.Linq;
    using System.Linq.Expressions;
    using System.Threading.Tasks;

    using Models;

    using MongoDB.Bson;
    using MongoDB.Driver;

    public class Program
    {
        public static void Main(string[] args)
        {
            MainAsync(args).Wait();
        }

        public static async Task MainAsync(string[] args)
        {
            var client = new MongoClient();
            var db = client.GetDatabase("school");
            var collection = db.GetCollection<Student>("students");

            Console.WriteLine(collection.Count(new BsonDocument()));

            using (var cursor = await collection.Find(new BsonDocument()).ToCursorAsync())
            {
                while (await cursor.MoveNextAsync())
                {
                    foreach (var student in cursor.Current)
                    {
                        var value = student.Scores.Where(s => s.Type == "homework").Min(s => s.Value);
                        var score = student.Scores.FirstOrDefault(s => s.Type == "homework" && s.Value == value);
                        Console.WriteLine(score);
                        Console.WriteLine();
                        student.Scores.Remove(score);

                        await collection.ReplaceOneAsync<Student>(s => s.Id == student.Id, student);
                    }
                }
            }

            Console.WriteLine(collection.Count(new BsonDocument()));
        }
    }
}