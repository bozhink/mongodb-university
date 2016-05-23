namespace SimpleMongo
{
    using Models;
    using MongoDB.Bson;
    using MongoDB.Bson.IO;
    using MongoDB.Bson.Serialization;
    using System;
    using System.Reflection;
    using System.Collections.Generic;
    using System.Threading.Tasks;
    using MongoDB.Bson.Serialization.Conventions;

    public class Program
    {
        public static void Main(string[] args)
        {
            MainAsync(args).Wait();
        }

        private static Task MainAsync(string[] args)
        {
            return Task.Run(() =>
            {
                ////var classMap = new BsonClassMap(typeof(Person));
                ////classMap.AutoMap();
                ////classMap.MapMember(typeof(Person).GetTypeInfo().DeclaredProperties)

                ////BsonClassMap.RegisterClassMap<Person>(cm =>
                ////{
                ////    cm.AutoMap();
                ////    cm.MapMember(x => x.Name).SetElementName("name");
                ////});

                var conventionPack = new ConventionPack();
                conventionPack.Add(new CamelCaseElementNameConvention());
                ConventionRegistry.Register("camelCase", conventionPack, t => true);

                var person = new Person
                {
                    Name = "Jones",
                    Age = 30,
                    Colors = new List<string> { "red", "blue" },
                    Pets = new List<Pet> {
                    new Pet
                    {
                        Name = "Fluffy",
                        Type = "Pig"
                    }
                },
                    ExtraElements = new BsonDocument("anotherName", "another")
                };

                using (var writer = new JsonWriter(Console.Out))
                {
                    BsonSerializer.Serialize(writer, person);
                }
            });
        }
    }
}