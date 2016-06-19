package course;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class BlogPostDAO {
    MongoCollection<Document> postsCollection;

    public BlogPostDAO(final MongoDatabase blogDatabase) {
        this.postsCollection = blogDatabase.getCollection("posts");
    }

    // Return a single post corresponding to a permalink
    public Document findByPermalink(String permalink) {

        // XXX HW 3.2,  Work Here
        Document post = this.postsCollection
                .find(new Document("permalink", permalink))
                .first();
        return post;
    }

    // Return a list of posts in descending order. Limit determines
    // how many posts are returned.
    public List<Document> findByDateDescending(int limit) {

        // XXX HW 3.2,  Work Here
        // Return a list of DBObjects, each one a post from the posts collection
        List<Document> posts = this.postsCollection
                .find()
                .sort(new Document("date", -1))
                .skip(0)
                .limit(limit)
                .into(new ArrayList<Document>());

        return posts;
    }

    public String addPost(
            String title,
            String body,
            List tags,
            String username) {

        System.out.println("inserting blog entry " + title + " " + body);

        String permalink = title.replaceAll("\\s", "_") // whitespace becomes _
                .replaceAll("\\W", "") // get rid of non alphanumeric
                .toLowerCase();

        // XXX HW 3.2, Work Here
        // Remember that a valid post has the following keys:
        // author, body, permalink, tags, comments, date
        //
        // A few hints:
        // - Don't forget to create an empty list of comments
        // - for the value of the date key, today's datetime is fine.
        // - tags are already in list form that implements suitable interface.
        // - we created the permalink for you above.

        // Build the post object and insert it
        Document post = new Document()
                .append("title", title)
                .append("author", username)
                .append("body", body)
                .append("permalink", permalink)
                .append("tags", tags)
                .append("comments", new ArrayList<Document>())
                .append("date", new Date());

        try {
            this.postsCollection.insertOne(post);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return permalink;
    }


    // White space to protect the innocent


    // Append a comment to a blog post
    public void addPostComment(
            final String name,
            final String email,
            final String body,
            final String permalink) {

        // XXX HW 3.3, Work Here
        // Hints:
        // - email is optional and may come in NULL. Check for that.
        // - best solution uses an update command to the database and a suitable
        //   operator to append the comment on to any existing list of comments

        Document comment = new Document("author", name);
        if (email != null && !email.trim().equals("")) {
            comment = comment.append("email", email);
        }

        comment = comment.append("body", body);

        Document filter = new Document("permalink", permalink);
        Document update = new Document("$push", new Document("comments", comment));
        try {
            this.postsCollection.updateOne(filter, update);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}