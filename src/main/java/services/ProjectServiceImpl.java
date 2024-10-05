package services;

import java.sql.SQLException;
import java.util.List;

import dao.ProjectDAO;
import dto.ProjectDTO;
import models.Project;
import repositories.ProjectRepository;

public class ProjectServiceImpl implements ProjectService{
	private final ProjectRepository projectRepository;
	private final ProjectDAO projectDAO;
	
	public ProjectServiceImpl(ProjectRepository projectRepository, ProjectDAO projectDAO) {
		this.projectRepository = projectRepository;
		this.projectDAO = projectDAO;
		
	}
	
	@Override
	public Project addProject(ProjectDTO project) throws SQLException {
		Project projectModel = project.dtoToModel();
		return projectDAO.addProject(projectModel);
	}

	@Override
	public Project modifyProject(ProjectDTO project) throws SQLException {
		if(projectDAO.getProjectById(project.getId()) == null) {
			return null;
		}else {
			Project projectModel = project.dtoToModel();
			return projectDAO.modifyProject(projectModel);
		}
	}

	@Override
	public void deleteProject(Integer id) throws SQLException {
		if (projectDAO.getProjectById(id) == null) {
            throw new SQLException("Project not found with ID: " + id);
        }
        projectDAO.deleteProject(id);
	}

	@Override
	public List<Project> getAllProjects(int page, int pageSize) throws SQLException {
		int offset = (page - 1) * pageSize;
		return projectDAO.getAllProjects(offset, pageSize);
	}


	public int getTotalProjectsCount() throws SQLException {
		return projectDAO.getTotalProjectsCount();
	}

	@Override
	public List<Project> searchProjects(String searchTerm) throws SQLException {
		if(searchTerm.isEmpty()) {
			return null;
		}else {
			return projectRepository.searchProjects(searchTerm);
		}
	}

	@Override
	public Project getProjectById(int id) throws SQLException {
		return projectDAO.getProjectById(id);
	}

}
