package controllers;

import config.DatabaseConnection;
import dao.ProjectDAOImpl;
import dao.TeamDAOImpl;
import dto.ProjectDTO;
import dto.TeamDTO;
import models.Project;
import models.enums.ProjectStatus;
import repositories.ProjectRepositoryImpl;
import services.ProjectServiceImpl;
import services.TeamServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ProjectController extends HttpServlet {

    private ProjectServiceImpl projectService;
    private TeamServiceImpl teamService;

    @Override
    public void init() throws ServletException {
        Connection connection = DatabaseConnection.getConnection();

        ProjectRepositoryImpl projectRepository = new ProjectRepositoryImpl(connection);
        ProjectDAOImpl projectDAO = new ProjectDAOImpl(connection);

        TeamDAOImpl teamDAO = new TeamDAOImpl();

        projectService = new ProjectServiceImpl(projectRepository, projectDAO);
        teamService = new TeamServiceImpl(teamDAO);

        getServletContext().setAttribute("projectService", projectService);
        getServletContext().setAttribute("teamService", teamService);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                Project project = projectService.getProjectById(id);
                request.setAttribute("project", project);
                request.setAttribute("teams", teamService.getAllTeams());
                request.getRequestDispatcher("/WEB-INF/jsp/project/ProjectForm.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if ("create".equals(action)) {
            try {
                request.setAttribute("teams", teamService.getAllTeams());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.getRequestDispatcher("/WEB-INF/jsp/project/ProjectForm.jsp").forward(request, response);
        }else if ("delete".equals(action)) {
            // Delete the project
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                projectService.deleteProject(id);
                response.sendRedirect("project");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            List<Project> projects = null;
            String searchTerm = request.getParameter("searchTerm");

            try {
                if (searchTerm != null && !searchTerm.trim().isEmpty()) {
                    // Perform search
                    projects = projectService.searchProjects(searchTerm);
                } else {
                    // Display all projects
                    projects = projectService.getAllProjects();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            request.setAttribute("projects", projects);
            try {
                request.setAttribute("teams", teamService.getAllTeams());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.getRequestDispatcher("/WEB-INF/jsp/project/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle project creation, update, and search
        String action = request.getParameter("action");

        if ("search".equals(action)) {
            // Search using POST
            String searchTerm = request.getParameter("searchTerm");
            response.sendRedirect("project?action=search&searchTerm=" + searchTerm);
        } else if ("create".equals(action)) {
            // Create a new project
            createOrUpdateProject(request, response, null);
        } else if ("update".equals(action)) {
            // Update an existing project
            int projectId = Integer.parseInt(request.getParameter("id"));
            createOrUpdateProject(request, response, projectId);
        }
    }

    private void createOrUpdateProject(HttpServletRequest request, HttpServletResponse response, Integer projectId) throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
        String statusStr = request.getParameter("status");
        ProjectStatus status = ProjectStatus.valueOf(statusStr);
        int teamId = Integer.parseInt(request.getParameter("team_id"));

        try {
            TeamDTO team = teamService.getTeamById(teamId);
            ProjectDTO projectDTO = new ProjectDTO(projectId, name, description, startDate, endDate, status, team, 0, 0, 0.0);

            if (projectId == null) {
                projectService.addProject(projectDTO);
            } else {
                projectService.modifyProject(projectDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("project");
    }
}
