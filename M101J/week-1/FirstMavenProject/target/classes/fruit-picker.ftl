<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Fruit Picker</title>
</head>
<body>
<form action="/favorite_fruit" method="post">
    <p>What is your favorite fruit?</p>
    <#list fruits as fruit>
    <label>
        <input type="radio" name="fruit" value="${fruit}"/>${fruit}
    </label>
    <br/>
    </#list>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>