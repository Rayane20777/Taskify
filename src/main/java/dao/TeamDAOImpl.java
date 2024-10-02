package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;
import models.Team;


public class TeamDAOImpl implements TeamDAO{
	
	public void addTeam(Team team) throws SQLException {
		String query = "INSERT INTO teams (name) VALUES (?)";
		
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)){
			
			stmt.setString(1, team.getName());
			
			stmt.executeUpdate();
		}
	
	}
	

	@Override
    public List<Team> getAllTeams() throws SQLException {
        String query = "SELECT * FROM teams";
        List<Team> teams = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Team team = new Team(rs.getInt("id"), rs.getString("name"));
                teams.add(team);
            }
        } 

        return teams;
    }
	
    @Override
    public Team getTeamById(int id) throws SQLException {
        String query = "SELECT * FROM teams WHERE id = ?";
        Team team = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                team = new Team(rs.getInt("id"), rs.getString("name"));
            }
        } 

        return team;
    }
	
    @Override
    public void updateTeam(Team team) throws SQLException {
        String query = "UPDATE teams SET name = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, team.getName());
            stmt.setInt(2, team.getId());
            stmt.executeUpdate();
        } 
    }
    
    @Override
    public void deleteTeam(int id) throws SQLException {
        String query = "DELETE FROM teams WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } 
    }
}
