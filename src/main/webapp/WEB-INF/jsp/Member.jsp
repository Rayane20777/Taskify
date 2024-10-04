<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Member Management</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h1>Members</h1>

    <!-- Form to add a new member -->
    <form action="members" method="post">
        <input type="hidden" name="action" value="add">
        <label for="fname">First Name:</label>
        <input type="text" id="fname" name="fname" required><br><br>

        <label for="lname">Last Name:</label>
        <input type="text" id="lname" name="lname" required><br><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>

        <label for="role">Role:</label>
        <select id="role" name="role">
            <option value="DEVELOPER">Developer</option>
            <option value="DESIGNER">Designer</option>
            <option value="PROJECT_MANAGER">Project Manager</option>
        </select><br><br>

        <label for="teamId">Team ID:</label>
        <input type="number" id="teamId" name="teamId" required><br><br>

        <input type="submit" value="Add Member">
    </form>

    <br><br>

    <!-- Display Members Table -->
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Role</th>
                <th>Team ID</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="member" items="${members}">
                <tr>
                    <!-- Form for updating the member -->
                    <form action="members" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="id" value="${member.id}">

                        <td>${member.id}</td>
                        <td><input type="text" name="fname" value="${member.fname}" required></td>
                        <td><input type="text" name="lname" value="${member.lname}" required></td>
                        <td><input type="email" name="email" value="${member.email}" required></td>
                        <td>
                            <select name="role">
                                <option value="DEVELOPER" ${member.role == 'DEVELOPER' ? 'selected' : ''}>Developer</option>
                                <option value="DESIGNER" ${member.role == 'DESIGNER' ? 'selected' : ''}>Designer</option>
                                <option value="PROJECT_MANAGER" ${member.role == 'PROJECT_MANAGER' ? 'selected' : ''}>Project Manager</option>
                            </select>
                        </td>
                        <td><input type="number" name="teamId" value="${member.teamId}" required></td>

                        <td>
                            <button type="submit">Update</button>
                    </form>

                    <!-- Form for deleting the member -->
                    <form action="members" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${member.id}">
                        <button type="submit">Delete</button>
                    </form>
                        </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
