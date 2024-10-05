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
            Connection connection = DatabaseConnection.getConnection(); 
            if (connection == null) {
                throw new SQLException("Failed to create database connection.");
            }
            TeamDAO teamDAO = new TeamDAOImpl(connection);
            teamService = new TeamServiceImpl(teamDAO); 
        } catch (SQLException e) {
            throw new ServletException("Cannot initialize DAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int page = 1;
            int pageSize = 10; 

            String pageStr = request.getParameter("page");

            if (pageStr != null) {
                page = Integer.parseInt(pageStr);
            }

            List<Team> teams = teamService.getAllTeams(page, pageSize);
            int teamCount = teamService.getAllTeams(1, Integer.MAX_VALUE).size(); // Get total count of teams
            int totalPages = (int) Math.ceil(teamCount * 1.0 / pageSize);

            request.setAttribute("teams", teams);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);

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
        String method = request.getParameter("_method");

        if ("PUT".equalsIgnoreCase(method)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                
                TeamDTO teamDTO = new TeamDTO();
                teamDTO.setId(id);
                teamDTO.setName(name);
                
                teamService.updateTeam(teamDTO);
                response.sendRedirect("team");  
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Failed to update team.");
                request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
            }
        } else if ("DELETE".equalsIgnoreCase(method)) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                teamService.deleteTeam(id);
                response.sendRedirect("team");
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Failed to delete team.");
                doGet(request, response);
            }
        } else {
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

    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                teamService.deleteTeam(id);
                response.sendRedirect("team");
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Failed to delete team.");
                doGet(request, response);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid team ID.");
                doGet(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Team ID is missing.");
            doGet(request, response);
        }
    }


}
