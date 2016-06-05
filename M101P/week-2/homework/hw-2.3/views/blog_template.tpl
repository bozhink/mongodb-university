<!DOCTYPE html>
<html>
<head>
    <title>My Blog</title>
</head>
<body>

%if (username != None):
<p>Welcome {{username}} <a href="/logout">Logout</a> | <a href="/newpost">New Post</a></p>
%end

<h1>My Blog</h1>

<p>
This is a placeholder for the blog
</p>

</body>
</html>


