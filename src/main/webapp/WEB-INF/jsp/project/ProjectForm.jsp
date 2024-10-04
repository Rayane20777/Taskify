<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${project == null ? 'Create New Project' : 'Edit Project'}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        /* Custom styles for box shadow and back arrow button */
        .form-container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Soft box shadow */
        }

        .back-button {
            position: absolute;
            top: 20px;
            left: 20px;
            font-size: 1.5rem;
            text-decoration: none;
            color: #219ebcff;
        }

        .back-button:hover {
            color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container mt-5 position-relative">

    <!-- Back button as an arrow -->
    <a href="project" class="back-button">
        <i class="fas fa-arrow-left"></i>
    </a>

    <!-- Form container with box shadow -->
    <div class="form-container">
        <h1 class="mb-4 text-center">${project == null ? 'Create New Project' : 'Edit Project'}</h1>

        <form action="project" method="POST">
            <input type="hidden" name="action" value="${project == null ? 'create' : 'update'}">
            <c:if test="${project != null}">
                <input type="hidden" name="id" value="${project.id}">
            </c:if>

            <!-- Project Name -->
            <div class="mb-3">
                <label for="name" class="form-label">Project Name:</label>
                <input type="text" id="name" name="name" value="${project != null ? project.name : ''}" class="form-control" required>
            </div>

            <!-- Description -->
            <div class="mb-3">
                <label for="description" class="form-label">Description:</label>
                <textarea id="description" name="description" class="form-control" rows="3" required>${project != null ? project.description : ''}</textarea>
            </div>

            <!-- Start Date -->
            <div class="mb-3">
                <label for="startDate" class="form-label">Start Date:</label>
                <input type="date" id="startDate" name="startDate" value="${project != null ? project.startDate : ''}" class="form-control" required>
            </div>

            <!-- End Date -->
            <div class="mb-3">
                <label for="endDate" class="form-label">End Date:</label>
                <input type="date" id="endDate" name="endDate" value="${project != null ? project.endDate : ''}" class="form-control" required>
            </div>

            <!-- Status -->
            <div class="mb-3">
                <label for="status" class="form-label">Status:</label>
                <select id="status" name="status" class="form-select">
                    <option value="" hidden>Pick Status</option>
                    <c:forEach var="status" items="${projectStatus}">
                        <option value="${status}" ${project != null && project.status == status ? 'selected' : ''}>${status}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- Team -->
            <div class="mb-3">
                <label for="team_id" class="form-label">Team:</label>
                <select id="team_id" name="team_id" class="form-select">
                    <c:forEach var="team" items="${teams}">
                        <option value="${team.id}" ${project != null && project.team != null && project.team.id == team.id ? 'selected' : ''}>${team.name}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- Submit Button -->
            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary" style="background-color: #219ebcff">${project == null ? 'Create Project' : 'Update Project'}</button>
            </div>
        </form>
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-TRKxV8f+NIcLMygUQpppEYZZ5LQ0kq3ZK0w4XHnjTnAAaPzHnjSFi44PbLOdApQm" crossorigin="anonymous"></script>
</body>
</html>
