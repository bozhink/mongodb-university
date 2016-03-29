
namespace GradesCleaner
{
    using MongoDB.Bson;
    using MongoDB.Bson.IO;
    using MongoDB.Bson.Serialization;
    using MongoDB.Driver;
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Linq.Expressions;
    using System.Text;
    using System.Threading.Tasks;

    public class Program
    {
        public static void Main(string[] args)
        {
            MainAsync(args).Wait();
        }

        public static async Task MainAsync(string[] args)
        {
            var client = new MongoClient();
            var db = client.GetDatabase("st");
            var collection = db.GetCollection<Student>("grades");

            Console.WriteLine(collection.Count(new BsonDocument()));

            var result = await collection.Find(s => s.Type == "homework")
                .SortBy(s => s.StudentId)
                .ThenBy(s => s.Score)
                //.Skip(100)
                //.Limit(12)
                .ToListAsync();

            result.ForEach(Console.WriteLine);

            Console.WriteLine("----------------------------------------");

            var itemsToDelete = result.GroupBy(s => s.StudentId)
                .Select(g => g.FirstOrDefault());

            foreach (var item in itemsToDelete)
            {

                Expression<Func<Student, bool>> filter = s => s.Id == item.Id;

                (await collection.Find(filter).ToListAsync()).ForEach(Console.WriteLine);

                await collection.DeleteOneAsync(filter);
            }

            Console.WriteLine(collection.Count(new BsonDocument()));
        }
    }
}
