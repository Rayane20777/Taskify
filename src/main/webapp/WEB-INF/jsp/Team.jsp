<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Teams</title>
</head>
<body>
    <h1>Teams Management</h1>

    <h2>Add New Team</h2>
    <form action="team" method="post">
        <label for="name">Team Name:</label>
        <input type="text" id="name" name="name" required>
        <input type="submit" value="Add Team">
    </form>

    <c:if test="${not empty errorMessage}">
        <p style="color:red;">${errorMessage}</p>
    </c:if>

    <h2>Team List</h2>
   <table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="team" items="${teams}">
            <tr>
                <td>${team.id}</td>
                <td>${team.name}</td>
                <td>
                    <form action="team" method="post" style="display:inline;">
                        <input type="hidden" name="_method" value="DELETE" />
                        <input type="hidden" name="id" value="${team.id}" />
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
