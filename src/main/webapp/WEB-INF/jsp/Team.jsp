<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Team</title>
</head>
<body>
    <form action="team" method="post"> <!-- Changed to match the servlet mapping -->
        <label for="name">Team Name:</label>
        <input type="text" id="name" name="name" required>
        <input type="submit" value="Add Team">
    </form>
    <c:if test="${not empty errorMessage}">
        <div style="color:red;">${errorMessage}</div>
    </c:if>
</body>
</html>
