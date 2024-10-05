<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Projects</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        .float-end {
            float: right;
        }
        @media (max-width: 768px) {
            .float-end {
                float: none;
                width: 100%;
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
<div class="container-fluid mt-4" style="width: 80%">

    <nav class="navbar navbar-expand-lg navbar-light mb-4">
        <a class="navbar-brand" href="#">Taskify</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="project?action=display">Projects</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="team">Teams</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="members">Members</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="index.jsp">Logout</a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="row mb-3 search-row d-flex justify-content-between align-items-center">
        <div class="col-lg-3 col-md-12">
            <form action="project" method="GET">
                <input type="hidden" name="action" value="create">
                <button type="submit" class="btn btn-primary" style="background-color: #219ebcff">Add New Project</button>
            </form>
        </div>

        <div class="col-lg-5 col-md-12">
            <form action="project" method="POST" class="form-inline w-100">
                <input type="hidden" name="action" value="search">
                <div class="input-group w-100">
                    <input type="text" id="searchTerm" name="searchTerm" class="form-control" placeholder="Enter project name or description" />
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-secondary">
                            <i class="fas fa-search"></i>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <c:if test="${not empty projects}">
        <div class="table-responsive">
            <table class="table table-striped table-hover" style="box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;">
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

        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="project?action=display&page=${currentPage - 1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <li class="page-item <c:if test='${i == currentPage}'>active</c:if>">
                        <a class="page-link" href="project?action=display&page=${i}">${i}</a>
                    </li>
                </c:forEach>
                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="project?action=display&page=${currentPage + 1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </c:if>

    <c:if test="${empty projects}">
        <div class="alert alert-warning" role="alert">
            No projects found.
        </div>
    </c:if>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
