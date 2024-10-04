package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TeamDAO;
import dao.TeamDAOImpl;
import models.Project;
import models.Team;
import models.enums.ProjectStatus;

public class ProjectRepositoryImpl implements ProjectRepository{
	private final Connection connection;
	private final TeamDAOImpl teamDAO;

	public ProjectRepositoryImpl(Connection connection) {
        this.connection = connection;
		this.teamDAO = new TeamDAOImpl(connection);
    }
    
	@Override
	public List<Project> searchProjects(String searchTerm) throws SQLException {
	    List<Project> projects = new ArrayList<>();
	    
	    String query = "SELECT p.*, "
	                 + "COUNT(t.id) AS total_tasks, "
	                 + "SUM(CASE WHEN t.status = 'DONE' THEN 1 ELSE 0 END) AS completed_tasks, "
	                 + "(SUM(CASE WHEN t.status = 'DONE' THEN 1 ELSE 0 END) / COUNT(t.id)) * 100 AS progress_percentage "
	                 + "FROM Projects p "
	                 + "LEFT JOIN Tasks t ON p.id = t.project_id "
	                 + "WHERE p.name LIKE ? OR p.description LIKE ? "
	                 + "GROUP BY p.id";
	    
	    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	        String querySearch = "%" + searchTerm + "%";
	        preparedStatement.setString(1, querySearch);
	        preparedStatement.setString(2, querySearch);
	        
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
	    }

	    return projects;
	}


}
