package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Project;
import models.Team;
import models.enums.ProjectStatus;

public class ProjectDAOImpl implements ProjectDAO{
	private final Connection connection;
    private final TeamDAOImpl teamDAO;


    public ProjectDAOImpl(Connection connection) {
        this.connection = connection;
        this.teamDAO = new TeamDAOImpl();

    }
    
    
    @Override
    public Project addProject(Project project) throws SQLException {
        String query = "INSERT INTO Projects (name, description, startDate, endDate, status, team_id) VALUES (?,?,?,?,?,?)";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getDescription());
            preparedStatement.setDate(3, java.sql.Date.valueOf(project.getStartDate()));
            preparedStatement.setDate(4, java.sql.Date.valueOf(project.getEndDate()));
            preparedStatement.setString(5, project.getStatus().name()); 
            preparedStatement.setInt(6, project.getTeam().getId());
            
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        project.setId(generatedKeys.getInt(1));
                    }
                }
            }
        }

        return project;
    }

    @Override
    public Project modifyProject(Project project) throws SQLException {
        String query = "UPDATE Projects SET name = ?, description = ?, startDate = ?, endDate = ?, status = ?, team_id = ? WHERE id = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getDescription());
            preparedStatement.setDate(3, java.sql.Date.valueOf(project.getStartDate()));
            preparedStatement.setDate(4, java.sql.Date.valueOf(project.getEndDate()));
            preparedStatement.setString(5, project.getStatus().name());  // Enum stored as String in the DB
            preparedStatement.setInt(6, project.getTeam().getId());
            preparedStatement.setInt(7, project.getId());

            preparedStatement.executeUpdate();
        }

        return project;
    }

    @Override
    public void deleteProject(Integer id) throws SQLException {
        String query = "DELETE FROM Projects WHERE id = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Project> getAllProjects() throws SQLException {
        List<Project> projects = new ArrayList<>();
        
        String query = "SELECT p.*, "
                     + "COUNT(t.id) AS total_tasks, "
                     + "SUM(CASE WHEN t.status = 'DONE' THEN 1 ELSE 0 END) AS completed_tasks, "
                     + "(SUM(CASE WHEN t.status = 'DONE' THEN 1 ELSE 0 END) / COUNT(t.id)) * 100 AS progress_percentage "
                     + "FROM Projects p "
                     + "LEFT JOIN Tasks t ON p.id = t.project_id "
                     + "GROUP BY p.id";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Team team = teamDAO.getTeamById(resultSet.getInt("team_id"));
                Project project = new Project(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDate("start_date").toLocalDate(),
                        resultSet.getDate("end_date").toLocalDate(),
                        ProjectStatus.valueOf(resultSet.getString("status")),
                        new Team(team.getId(), team.getName()),
                        resultSet.getInt("total_tasks"),
                        resultSet.getInt("completed_tasks"),
                        resultSet.getDouble("progress_percentage")
                    );

                    projects.add(project);
            }
        }

        return projects;
    }


    @Override
    public Project getProjectById(int id) throws SQLException {
        String query = "SELECT p.*, "
                     + "COUNT(t.id) AS total_tasks, "
                     + "SUM(CASE WHEN t.status = 'DONE' THEN 1 ELSE 0 END) AS completed_tasks, "
                     + "(SUM(CASE WHEN t.status = 'DONE' THEN 1 ELSE 0 END) / COUNT(t.id)) * 100 AS progress_percentage "
                     + "FROM Projects p "
                     + "LEFT JOIN Tasks t ON p.id = t.project_id "
                     + "WHERE p.id = ? "  // Add filtering for the specific project ID
                     + "GROUP BY p.id";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id); 

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) { 
                    Team team = teamDAO.getTeamById(resultSet.getInt("team_id"));
	            	 return new Project(
	                         resultSet.getInt("id"),
	                         resultSet.getString("name"),
	                         resultSet.getString("description"),
	                         resultSet.getDate("start_date").toLocalDate(),
	                         resultSet.getDate("end_date").toLocalDate(),
	                         ProjectStatus.valueOf(resultSet.getString("status")),
	                         new Team(team.getId(), team.getName()),
	                         resultSet.getInt("total_tasks"),
	                         resultSet.getInt("completed_tasks"),
	                         resultSet.getDouble("progress_percentage")
	                     );
                } else {
                    return null; 
                }
            }
        }
    }


}
