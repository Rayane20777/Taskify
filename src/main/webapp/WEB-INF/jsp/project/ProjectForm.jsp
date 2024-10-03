<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Project Form</title>
</head>
<body>
<h1>${project == null ? 'Create New Project' : 'Edit Project'}</h1>

<form action="project" method="POST">
    <input type="hidden" name="action" value="${project == null ? 'create' : 'update'}">
    <c:if test="${project != null}">
        <input type="hidden" name="id" value="${project.id}">
    </c:if>

    <!-- Project Name -->
    <div>
        <label for="name">Project Name:</label>
        <input type="text" id="name" name="name" value="${project != null ? project.name : ''}" required>
    </div>

    <!-- Description -->
    <div>
        <label for="description">Description:</label>
        <textarea id="description" name="description" required>${project != null ? project.description : ''}</textarea>
    </div>

    <!-- Start Date -->
    <div>
        <label for="startDate">Start Date:</label>
        <input type="date" id="startDate" name="startDate" value="${project != null ? project.startDate : ''}" required>
    </div>

    <!-- End Date -->
    <div>
        <label for="endDate">End Date:</label>
        <input type="date" id="endDate" name="endDate" value="${project != null ? project.endDate : ''}" required>
    </div>

    <!-- Status -->
    <div>
        <label for="status">Status:</label>
        <select id="status" name="status">
            <c:forEach var="status" items="${ProjectStatus.values()}">
                <option value="${status}" ${project != null && project.status == status ? 'selected' : ''}>${status}</option>
            </c:forEach>
        </select>
    </div>

    <!-- Team -->
    <div>
        <label for="team_id">Team:</label>
        <select id="team_id" name="team_id">
            <c:forEach var="team" items="${teams}">
                <option value="${team.id}" ${project != null && project.team != null && project.team.id == team.id ? 'selected' : ''}>${team.name}</option>
            </c:forEach>
        </select>
    </div>

    <!-- Submit Button -->
    <div>
        <button type="submit">${project == null ? 'Create Project' : 'Update Project'}</button>
    </div>
</form>

<div>
    <a href="project">Back to Projects List</a>
</div>
</body>
</html>
