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
import java.util.logging.Logger;

public class ProjectController extends HttpServlet {

    Logger logger = Logger.getLogger(ProjectController.class.getName());
    private ProjectServiceImpl projectService;
    private TeamServiceImpl teamService;

    @Override
    public void init() throws ServletException {
        Connection connection = DatabaseConnection.getConnection();

        ProjectRepositoryImpl projectRepository = new ProjectRepositoryImpl(connection);
        ProjectDAOImpl projectDAO = new ProjectDAOImpl(connection);

        TeamDAOImpl teamDAO = new TeamDAOImpl(connection);

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
                request.setAttribute("projectStatus", ProjectStatus.values());
                request.getRequestDispatcher("/WEB-INF/jsp/project/ProjectForm.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if ("create".equals(action)) {
            try {
                request.setAttribute("teams", teamService.getAllTeams());
                request.setAttribute("projectStatus", ProjectStatus.values());

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
            List<Project> myProjects ;
            String searchTerm = request.getParameter("searchTerm");

            try {
                if (searchTerm != null && !searchTerm.trim().isEmpty()) {
                    // Perform search
                    myProjects = projectService.searchProjects(searchTerm);
                    System.out.println("serv"+myProjects);
                } else {
                    // Display all projects
                    myProjects = projectService.getAllProjects();
                    for (Project project : myProjects) {
                        logger.severe("ProjectController"+project);
                    }
                }
                request.setAttribute("projects", myProjects);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

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
        String statusStr = ProjectStatus.valueOf(request.getParameter("status")).toString();
        ProjectStatus status = ProjectStatus.valueOf(statusStr);
        int teamId = Integer.parseInt(request.getParameter("team_id"));

        try {
            TeamDTO team = TeamDTO.modelToDTO(teamService.getTeamById(teamId));
            ProjectDTO projectDTO = new ProjectDTO(projectId, name, description, startDate, endDate, status, team, 0, 0, 0.0);
            logger.severe(projectDTO.getName());
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
