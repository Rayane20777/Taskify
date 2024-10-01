package dao;

import java.sql.*;
import java.util.List;

import config.DatabaseConnection;
import models.Team;


public class TeamDAOImpl implements TeamDAO{
	
	public void addTeam(Team team) {
		String query = "INSERT INTO teams (name) VALUES (?)";
		
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)){
			
			stmt.setString(1, team.getName());
			
			stmt.executeUpdate();
		}catch(SQLException e) {
            e.printStackTrace();
		}
	}
	

	@Override
    public List<Team> getAllTeams() {
        String query = "SELECT * FROM teams";
        List<Team> teams = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Team team = new Team(rs.getInt("id"), rs.getString("name"));
                teams.add(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teams;
    }
	
    @Override
    public void updateTeam(Team team) {
        String query = "UPDATE teams SET name = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, team.getName());
            stmt.setInt(2, team.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
