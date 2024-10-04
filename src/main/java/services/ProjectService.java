package services;

import java.sql.SQLException;
import java.util.List;

import dto.ProjectDTO;
import models.Project;

public interface ProjectService {
	public Project addProject(ProjectDTO project) throws SQLException;
	public Project modifyProject(ProjectDTO project ) throws SQLException;
	public void deleteProject(Integer id) throws SQLException;
	public List<Project> getAllProjects() throws SQLException;
	public List<Project> searchProjects(String searchTerm) throws SQLException;
	public Project getProjectById(int id) throws SQLException;

}
