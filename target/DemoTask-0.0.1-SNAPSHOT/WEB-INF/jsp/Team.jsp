<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Teams Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        /* Custom styles */
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

        .table-container {
            margin-top: 40px;
        }

        /* Custom background color for update input fields */
        .update-input {
            background-color: rgba(0, 0, 0, 0); 
        }
        .navbar-nav {
            display: flex;
            justify-content: flex-end;
        }
    </style>
</head>
<body>
<div class="container-fluid mt-5 position-relative" style="width: 80%">

    <!-- Navbar -->
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


    
    <!-- Form in one line -->
    <form action="team" method="POST" class="row g-2 mt-5">
        <div class="col-auto">
            <label for="name" class="col-form-label">Team Name:</label>
        </div>
        <div class="col-auto">
            <input type="text" id="name" name="name" class="form-control" required>
        </div>
        <div class="col-auto">
            <button type="submit" class="btn btn-primary" style="background-color: #219ebcff">Add Team</button>
        </div>
    </form>

    <!-- Error message -->
    <c:if test="${not empty errorMessage}">
        <p class="text-danger">${errorMessage}</p>
    </c:if>

    <!-- Team List Table -->
    <div class="table-container">
    <table class="table table-striped table-bordered">
        <thead class="table-dark">
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
                    <td>
                        <!-- Update form with editable name input -->
                        <form action="team" method="post" class="d-inline">
                            <input type="hidden" name="_method" value="PUT" />
                            <input type="hidden" name="id" value="${team.id}" />
                            <input type="text" name="name" value="${team.name}" class="form-control update-input">
                        </form>
                    </td>
                    <td>
                        <!-- Delete form -->
                        <form action="team" method="post" class="d-inline">
                            <input type="hidden" name="_method" value="DELETE" />
                            <input type="hidden" name="id" value="${team.id}" />
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<!-- Pagination controls -->
<nav aria-label="Page navigation">
    <ul class="pagination justify-content-center">
        <c:if test="${currentPage > 1}">
            <li class="page-item">
                <a class="page-link" href="team?page=${currentPage - 1}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        </c:if>

        <c:forEach var="i" begin="1" end="${totalPages}">
            <li class="page-item <c:if test='${i == currentPage}'>active</c:if>">
                <a class="page-link" href="team?page=${i}">${i}</a>
            </li>
        </c:forEach>

        <c:if test="${currentPage < totalPages}">
            <li class="page-item">
                <a class="page-link" href="team?page=${currentPage + 1}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </c:if>
    </ul>
</nav>

</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-TRKxV8f+NIcLMygUQpppEYZZ5LQ0kq3ZK0w4XHnjTnAAaPzHnjSFi44PbLOdApQm" crossorigin="anonymous"></script>
</body>
</html>
