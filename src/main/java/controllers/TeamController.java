package controllers;

import dao.TeamDAO;
import dao.TeamDAOImpl; 
import models.Team;
import dto.TeamDTO;
import services.TeamService;
import services.TeamServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection; 
import java.sql.SQLException;
import java.util.List;

public class TeamController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TeamService teamService;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConnection.getConnection(); // Ensure this works
            if (connection == null) {
                throw new SQLException("Failed to create database connection.");
            }
            TeamDAO teamDAO = new TeamDAOImpl(connection);
            teamService = new TeamServiceImpl(teamDAO); // Pass in the DAO instance
        } catch (SQLException e) {
            throw new ServletException("Cannot initialize DAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (teamService == null) {
                throw new ServletException("TeamService is not initialized.");
            }
            List<Team> teams = teamService.getAllTeams();
            request.setAttribute("teams", teams);
            request.getRequestDispatcher("/WEB-INF/jsp/Team.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to retrieve teams.");
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");

        if (name != null && !name.trim().isEmpty()) {
            try {
                TeamDTO teamDTO = new TeamDTO();
                teamDTO.setName(name);
                teamService.addTeam(teamDTO);
                response.sendRedirect("team"); 
            } catch (SQLException e) {
                e.printStackTrace(); 
                request.setAttribute("errorMessage", "Failed to add team.");
                doGet(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Team name cannot be empty.");
            doGet(request, response);
        }
    }
}
