<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Projects</title>
    <!-- Include Bootstrap CSS and FontAwesome for icons -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        .float-end {
            float: right; /* Ensures the search bar and button float to the right */
        }
        @media (max-width: 768px) {
            .float-end {
                float: none; /* Disable floating on smaller screens */
                width: 100%; /* Full width for mobile view */
            }
        }
        .search-row {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .navbar-nav {
            display: flex;
            justify-content: flex-end;
        }
    </style>
</head>
<body>
<div class="container mt-4">

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light mb-4">
        <a class="navbar-brand" href="#">Taskify</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="project?action=list">Projects</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="team">Teams</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="task?action=list">Tasks</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="index.jsp">Logout</a>
                </li>
            </ul>
        </div>
    </nav>

    <!-- Search Bar and Add Project Button -->
    <div class="row mb-3 search-row d-flex justify-content-between align-items-center">
        <!-- Add New Project Button -->
        <div class="col-lg-3 col-md-12">
            <form action="project" method="GET">
                <input type="hidden" name="action" value="create">
                <button type="submit" class="btn btn-primary" style="background-color: #219ebcff">Add New Project</button>
            </form>
        </div>

        <div class="col-lg-5 col-md-12">
            <!-- Search Form with an icon on the right -->
            <form action="project" method="POST" class="form-inline w-100">
                <input type="hidden" name="action" value="search">
                <div class="input-group w-100">
                    <input type="text" id="searchTerm" name="searchTerm" class="form-control" placeholder="Enter project name or description" />
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-secondary">
                            <i class="fas fa-search"></i> <!-- Search icon -->
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <!-- Project List Table -->
    <c:if test="${not empty projects}">
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead class="thead-dark">
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
                                <button type="submit" class="btn btn-sm btn-warning">Edit</button>
                            </form>
                            <!-- Delete Button -->
                            <form action="project" method="GET" style="display:inline;" onsubmit="return confirm('Are you sure you want to delete this project?');">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="${project.id}">
                                <button type="submit" class="btn btn-sm btn-danger">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>

    <!-- Message if no projects are found -->
    <c:if test="${empty projects}">
        <div class="alert alert-warning" role="alert">
            No projects found.
        </div>
    </c:if>
</div>

<!-- Include Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
