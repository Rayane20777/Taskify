package dao;

import java.sql.*;


import config.DatabaseConnection;
import models.Team;


public class TeamDAOImpl {
	
	public void addTeam(Team team) {
		String query = "INSERT INTO team (name) VALUES (?)";
		
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)){
			
			stmt.setString(1, team.getName());
			
			stmt.executeUpdate();
		}catch(SQLException e) {
            e.printStackTrace();
		}
	}

}
