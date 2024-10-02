package controllers;

import dto.TeamDTO;
import services.TeamService;
import services.TeamServiceImpl;
import dao.TeamDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import config.DatabaseConnection; 

public class TeamController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private TeamService teamService;

    @Override
    public void init() throws ServletException {
        Connection connection = DatabaseConnection.getConnection(); // Get your database connection
        teamService = new TeamServiceImpl(new TeamDAOImpl(connection)); // Pass the connection to TeamDAOImpl
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/Team.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");

        if (name != null && !name.trim().isEmpty()) {
            TeamDTO teamDTO = new TeamDTO();
            teamDTO.setName(name);

            try {
                teamService.addTeam(teamDTO); 
                response.sendRedirect("team"); // Redirect after successful addition
            } catch (SQLException e) {
                request.setAttribute("errorMessage", "Error adding team: " + e.getMessage());
                request.getRequestDispatcher("/WEB-INF/jsp/Team.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Team name cannot be empty.");
            request.getRequestDispatcher("/WEB-INF/jsp/Team.jsp").forward(request, response);
        }
    }
}