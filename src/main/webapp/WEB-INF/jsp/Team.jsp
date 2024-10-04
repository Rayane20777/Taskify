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
 <table style="border-collapse: collapse; width: 100%; border: 1px solid black;">
    <thead>
        <tr>
            <th style="border: 1px solid black;">ID</th>
            <th style="border: 1px solid black;">Name</th>
            <th style="border: 1px solid black;">Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="team" items="${teams}">
            <tr>
                <td style="border: 1px solid black;">${team.id}</td>
                <td style="border: 1px solid black;">
                    <!-- Update form with editable name input -->
                    <form action="team" method="post" style="display:inline;">
                        <input type="hidden" name="_method" value="PUT" />
                        <input type="hidden" name="id" value="${team.id}" />
                        <input type="text" name="name" value="${team.name}" />
                    </form>
                </td>
                <td style="border: 1px solid black;">
                    <!-- Delete form in the same cell -->
                    <form action="team" method="post" style="display:inline;">
                        <input type="hidden" name="_method" value="DELETE" />
                        <input type="hidden" name="id" value="${team.id}" />
                        <button type="submit" >Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>





</body>
</html>
