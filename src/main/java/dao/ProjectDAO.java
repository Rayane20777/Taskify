package dao;

import java.sql.SQLException;
import java.util.List;

import models.Project;

public interface ProjectDAO {
	public Project addProject(Project project) throws SQLException;
	public Project modifyProject(Project project ) throws SQLException;
	public void deleteProject(Integer id) throws SQLException;
	public List<Project> getAllProjects(int offset, int limit) throws SQLException;
	public Project getProjectById(int id) throws SQLException;

	public int getTotalProjectsCount() throws SQLException;
}
