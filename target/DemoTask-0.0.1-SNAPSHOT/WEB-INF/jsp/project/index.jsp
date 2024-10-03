 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Projects</title>
</head>
<body>
<h1>Projects</h1>

<!-- Button to add a new project -->
<form action="project" method="GET">
    <input type="hidden" name="action" value="create">
    <button type="submit">Add New Project</button>
</form>

<!-- Search Form -->
<form action="project" method="POST">
    <input type="hidden" name="action" value="search">
    <label for="searchTerm">Search Projects:</label>
    <input type="text" id="searchTerm" name="searchTerm" placeholder="Enter project name or description" />
    <button type="submit">Search</button>
</form>

<!-- Project List Table -->
<c:if test="${not empty projects}">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Status</th>
            <th>Team</th>
            <th>Total Tasks</th>
            <th>Completed Tasks</th>
            <th>Progress (%)</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="project" items="${projects}">
            <tr>
                <td>${project.id}</td>
                <td>${project.name}</td>
                <td>${project.description}</td>
                <td>${project.startDate}</td>
                <td>${project.endDate}</td>
                <td>${project.status}</td>
                <td>${project.team.name}</td>
                <td>${project.totalTasks}</td>
                <td>${project.completedTasks}</td>
                <td>${project.progressPercentage}</td>
                <td>
                    <!-- Edit Button -->
                    <form action="project" method="GET" style="display:inline;">
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="id" value="${project.id}">
                        <button type="submit">Edit</button>
                    </form>
                    <!-- Delete Button -->
                    <form action="project" method="GET" style="display:inline;" onsubmit="return confirm('Are you sure you want to delete this project?');">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${project.id}">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<!-- Message if no projects are found -->
<c:if test="${empty projects}">
    <p>No projects found.</p>
</c:if>

<div>
    <a href="home">Back to Home</a>
</div>
</body>
</html>
