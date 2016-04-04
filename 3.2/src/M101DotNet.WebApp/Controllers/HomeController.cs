using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;
using MongoDB.Driver;
using M101DotNet.WebApp.Models;
using M101DotNet.WebApp.Models.Home;
using MongoDB.Bson;
using System.Linq.Expressions;
using System.Text.RegularExpressions;

namespace M101DotNet.WebApp.Controllers
{
    public class HomeController : Controller
    {
        private const int NumberOfItemsToShow = 10;

        public async Task<ActionResult> Index()
        {
            var blogContext = new BlogContext();
            // XXX WORK HERE
            // find the most recent 10 posts and order them
            // from newest to oldest

            var recentPosts = await blogContext.Posts.Find(p => true)
                .Sort(Builders<Post>.Sort.Descending(p => p.CreatedAtUtc))
                .Limit(NumberOfItemsToShow)
                .ToListAsync();

            var model = new IndexModel
            {
                RecentPosts = recentPosts
            };

            return View(model);
        }

        [HttpGet]
        public ActionResult NewPost()
        {
            return View(new NewPostModel());
        }

        [HttpPost]
        public async Task<ActionResult> NewPost(NewPostModel model)
        {
            if (!ModelState.IsValid)
            {
                return View(model);
            }

            var blogContext = new BlogContext();
            // XXX WORK HERE
            // Insert the post into the posts collection
            var post = new Post
            {
                Author = User.Identity.Name,
                Title = model.Title,
                Content = model.Content,
                CreatedAtUtc = DateTime.UtcNow,
                Tags = new HashSet<string>(Regex.Replace(model.Tags, @"[\s,;]+", ",")
                    .Split(',')
                    .Where(s => !string.IsNullOrWhiteSpace(s)))
            };

            await blogContext.Posts.InsertOneAsync(post);

            return RedirectToAction("Post", new { id = post.Id });
        }

        [HttpGet]
        public async Task<ActionResult> Post(string id)
        {
            var blogContext = new BlogContext();

            // XXX WORK HERE
            // Find the post with the given identifier

            var post = await blogContext.Posts
                .Find<Post>(p => p.Id == id)
                .FirstOrDefaultAsync();

            if (post == null)
            {
                return RedirectToAction("Index");
            }

            var model = new PostModel
            {
                Post = post
            };

            return View(model);
        }

        [HttpGet]
        public async Task<ActionResult> Posts(string tag = null)
        {
            var blogContext = new BlogContext();

            // XXX WORK HERE
            // Find all the posts with the given tag if it exists.
            // Otherwise, return all the posts.
            // Each of these results should be in descending order.

            Expression<Func<Post, bool>> filter;
            if (tag == null)
            {
                filter = p => true;
            }
            else
            {
                filter = p => p.Tags.Contains(tag);
            }

            var posts = await blogContext.Posts.Find<Post>(filter)
                   .Sort(Builders<Post>.Sort.Descending(p => p.CreatedAtUtc))
                   .ToListAsync();

            return View(posts);
        }

        [HttpPost]
        public async Task<ActionResult> NewComment(NewCommentModel model)
        {
            if (!ModelState.IsValid)
            {
                return RedirectToAction("Post", new { id = model.PostId });
            }

            var blogContext = new BlogContext();
            // XXX WORK HERE
            // add a comment to the post identified by model.PostId.
            // you can get the author from "this.User.Identity.Name"
            var comment = new Comment
            {
                Author = User.Identity.Name,
                Content = model.Content,
                CreatedAtUtc = DateTime.UtcNow
            };

            Expression<Func<Post, bool>> filter = p => p.Id == model.PostId;

            var post = await blogContext.Posts.Find(filter).FirstOrDefaultAsync();
            post.Comments.Add(comment);

            await blogContext.Posts.ReplaceOneAsync(filter, post);

            return RedirectToAction("Post", new { id = model.PostId });
        }
    }
}