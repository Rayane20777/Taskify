package controllers;

import config.DatabaseConnection;
import dao.MemberDAO;
import dao.MemberDAOImpl;
import dao.TeamDAO;
import dao.TeamDAOImpl;
import dto.MemberDTO;
import models.Member;
import models.Team;
import models.enums.Role;
import services.MemberService;
import services.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MemberController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MemberService memberService;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConnection.getConnection();
            if (connection == null) {
                throw new SQLException("Failed to create database connection.");
            }
            MemberDAO memberDAO = new MemberDAOImpl(connection);
            TeamDAO teamDAO = new TeamDAOImpl(connection);
            memberService = new MemberServiceImpl(memberDAO, teamDAO);
        } catch (SQLException e) {
            throw new ServletException("Cannot initialize DAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (memberService == null) {
                throw new ServletException("MemberService is not initialized.");
            }

            int page = 1; // Default page number
            int pageSize = 10; // Default page size

            String pageStr = request.getParameter("page");
            if (pageStr != null) {
                try {
                    page = Integer.parseInt(pageStr);
                } catch (NumberFormatException e) {
                    // Handle invalid page number (set to default)
                }
            }

            List<MemberDTO> members = memberService.getAllMembers(page, pageSize);
            List<Team> teams = memberService.getAllTeams(); // Fetch all teams

            // Set attributes for JSP
            request.setAttribute("members", members);
            request.setAttribute("teams", teams);
            request.setAttribute("currentPage", page);

            request.getRequestDispatcher("/WEB-INF/jsp/Member.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to retrieve members or teams.");
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }



    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action"); 

        if ("update".equalsIgnoreCase(action)) {
            updateMember(request, response);
        } else if ("delete".equalsIgnoreCase(action)) {
            deleteMember(request, response);
        } else {
            addMember(request, response);
        }
    }

    private void addMember(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String roleStr = request.getParameter("role");  // This is the String role from the form
        String teamIdStr = request.getParameter("teamId");

        if (fname == null || lname == null || email == null || teamIdStr == null || fname.trim().isEmpty()
                || lname.trim().isEmpty() || email.trim().isEmpty() || teamIdStr.trim().isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required.");
            doGet(request, response);
            return;
        }

        try {
            int teamId = Integer.parseInt(teamIdStr);

             Role role = Role.valueOf(roleStr.toUpperCase());

            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setFname(fname);
            memberDTO.setLname(lname);
            memberDTO.setEmail(email);
            memberDTO.setRole(role);  
            memberDTO.setTeamId(teamId);

            memberService.addMember(memberDTO);
            response.sendRedirect("members");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to add member.");
            doGet(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", "Invalid role specified.");
            doGet(request, response);
        }
    }


    private void updateMember(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String email = request.getParameter("email");
            String roleStr = request.getParameter("role"); 
            int teamId = Integer.parseInt(request.getParameter("teamId"));

            
            Role role = Role.valueOf(roleStr.toUpperCase()); 

            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setId(id);
            memberDTO.setFname(fname);
            memberDTO.setLname(lname);
            memberDTO.setEmail(email);
            memberDTO.setRole(role); 
            memberDTO.setTeamId(teamId);

            memberService.updateMember(memberDTO);
            response.sendRedirect("members");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to update member.");
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", "Invalid role specified.");
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }


    private void deleteMember(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            memberService.deleteMember(id);
            response.sendRedirect("members");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to delete member.");
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }
}
