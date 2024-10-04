package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;
import models.Team;

public class TeamRepositoryImpl implements TeamRepository {

    @Override
    public List<Team> searchTeams(String name) {
        String query = "SELECT * FROM teams WHERE name LIKE ?";
        List<Team> teams = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Team team = new Team(rs.getInt("id"), rs.getString("name"));
                teams.add(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teams;
    }
}
