package repositories;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import models.Project;

public interface ProjectRepository {
	public List<Project> searchProjects(String searchTerm) throws SQLException;
}
