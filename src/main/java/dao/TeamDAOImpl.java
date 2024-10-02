package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.Team;


public class TeamDAOImpl implements TeamDAO{
    private final Connection connection;

    public TeamDAOImpl(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public Team addTeam(Team team) throws SQLException {
        String query = "INSERT INTO Teams (name) VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, team.getName());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        team.setId(generatedKeys.getInt(1));
                    }
                }
            }
        }

        return team;
    }
    
    @Override
    public Team updateTeam(Team team) throws SQLException {

        String query = "UPDATE teams SET name = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, team.getName());
            preparedStatement.setInt(2, team.getId());

            preparedStatement.executeUpdate();
        }

        return team;
    }
    
    @Override
    public void deleteTeam(Integer id) throws SQLException {
        String query = "DELETE FROM Teams WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
    
    @Override
    public List<Team> getAllTeams() throws SQLException {
        List<Team> teams = new ArrayList<>();
        String query = "SELECT * FROM Teams";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Team team = new Team(resultSet.getInt("id"), resultSet.getString("name"));
                teams.add(team);
            }
        }

        return teams;
    }
    

    @Override
    public Team getTeamById(int id) throws SQLException {
        String query = "SELECT * FROM Teams WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Team(resultSet.getInt("id"), resultSet.getString("name"));
                } else {
                    return null;
                }
            }
        }
    }
	
}
