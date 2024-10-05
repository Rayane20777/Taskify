<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Member Management</title>
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

<div class="container-fluid mt-4" style="width: 80%">
     <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light pb-5">
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
d

    <!-- Form in one line -->
    <form action="members" method="POST" class="row g-2 pt-5 ">
        <div class="col-auto">
            <label for="fname" class="col-form-label">First Name:</label>
        </div>
        <div class="col-auto">
            <input type="text" id="fname" name="fname" class="form-control" required>
        </div>
        
        <div class="col-auto">
            <label for="lname" class="col-form-label">Last Name:</label>
        </div>
        <div class="col-auto">
            <input type="text" id="lname" name="lname" class="form-control" required>
        </div>
        
        <div class="col-auto">
            <label for="email" class="col-form-label">Email:</label>
        </div>
        <div class="col-auto">
            <input type="email" id="email" name="email" class="form-control" required>
        </div>

        <div class="col-auto">
            <label for="role" class="col-form-label">Role:</label>
        </div>
        <div class="col-auto">
            <select id="role" name="role" class="form-select">
                <option value="DEVELOPER">Developer</option>
                <option value="DESIGNER">Designer</option>
                <option value="PROJECT_MANAGER">Project Manager</option>
            </select>
        </div>

       <div class="col-auto">
    <label for="teamId" class="col-form-label">Team:</label>
</div>
<div class="col-auto">
    <select id="teamId" name="teamId" class="form-select" required>
        <c:forEach var="team" items="${teams}">
            <option value="${team.id}">${team.name}</option>
        </c:forEach>
    </select>
</div>



        <div class="col-auto">
            <button type="submit" class="btn btn-primary" style="background-color: #219ebcff">Add Member</button>
        </div>
    </form>

    <!-- Error message -->
    <c:if test="${not empty errorMessage}">
        <p class="text-danger">${errorMessage}</p>
    </c:if>

    <!-- Members List Table -->
    <div class="table-container">
        <table class="table table-striped table-bordered">
            <thead class="table-dark">
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
                        <td>${member.id}</td>
                        <!-- Entire form wraps all input fields -->
                        <td>
                            <form action="members" method="post" class="d-inline">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="id" value="${member.id}">
                                <input type="text" name="fname" value="${member.fname}" class="form-control update-input" required>
                        </td>
                        <td><input type="text" name="lname" value="${member.lname}" class="form-control update-input" required></td>
                        <td><input type="email" name="email" value="${member.email}" class="form-control update-input" required></td>
                        <td>
                            <select name="role" class="form-select">
                                <option value="DEVELOPER" ${member.role == 'DEVELOPER' ? 'selected' : ''}>Developer</option>
                                <option value="DESIGNER" ${member.role == 'DESIGNER' ? 'selected' : ''}>Designer</option>
                                <option value="PROJECT_MANAGER" ${member.role == 'PROJECT_MANAGER' ? 'selected' : ''}>Project Manager</option>
                            </select>
                        </td>

<td>
    <select name="teamId" class="form-select" required>
        <c:forEach var="team" items="${teams}">
            <option value="${team.id}" ${team.id == member.teamId ? 'selected' : ''}>${team.name}</option>
        </c:forEach>
    </select>
</td>

                        <td>
                                                <button type="submit" class="btn btn-warning">Update</button>
                                
                            </form>

                            <!-- Delete form (separate action) -->
                            <form action="members" method="post" class="d-inline">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="${member.id}">
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-TRKxV8f+NIcLMygUQpppEYZZ5LQ0kq3ZK0w4XHnjTnAAaPzHnjSFi44PbLOdApQm" crossorigin="anonymous"></script>
</body>
</html>
