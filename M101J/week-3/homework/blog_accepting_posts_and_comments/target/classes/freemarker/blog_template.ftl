<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>My Blog</title>
</head>
<body>

<#if username??>
    <p>Welcome ${username} <a href="/logout">Logout</a> | <a href="/newpost">New Post</a></p>
</#if>

<h1>My Blog</h1>

<#list myposts as post>
    <h2><a href="/post/${post["permalink"]}">${post["title"]}</a></h2>
    Posted ${post["date"]?datetime} <i>By ${post["author"]}</i><br/>
    Comments:
    <#if post["comments"]??>
        <#assign numComments = post["comments"]?size>
            <#else>
                <#assign numComments = 0>
    </#if>

    <a href="/post/${post["permalink"]}">${numComments}</a>
    <hr/>
    ${post["body"]!""}
    <p></p>

    <p>
        <em>Filed Under</em>:
        <#if post["tags"]??>
            <#list post["tags"] as tag>
                 ${tag}
            </#list>
        </#if>
</p>
    <p></p>
</#list>
</body>
</html>

