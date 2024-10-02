<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Team</title>
</head>
<body>
    <form action="addTeam" method="post">
        <label for="name">Team Name:</label>
        <input type="text" id="name" name="name" required>
        <input type="submit" value="Add Team">
    </form>
</body>
</html>
