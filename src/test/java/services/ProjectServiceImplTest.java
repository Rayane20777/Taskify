package services;

import config.DatabaseConnection;
import dao.ProjectDAOImpl;
import dao.TeamDAOImpl;
import dto.TeamDTO;
import models.Team;
import models.enums.ProjectStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.ProjectRepositoryImpl;
import dto.ProjectDTO;
import models.Project;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectServiceImplTest {

    private ProjectServiceImpl projectService;
    private ProjectDAOImpl projectDAO;
    private ProjectRepositoryImpl projectRepository;
    private TeamServiceImpl teamService;
    private TeamDTO teamDTO;
    private Team team;

    @BeforeEach
    void setUp() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        projectDAO = new ProjectDAOImpl(connection);
        projectRepository = new ProjectRepositoryImpl(connection);
        projectService = new ProjectServiceImpl(projectRepository, projectDAO);
        teamService = new TeamServiceImpl(new TeamDAOImpl(connection));

        team = teamService.getTeamById(1);
        teamDTO = new TeamDTO(team.getId(), team.getName());
    }

    @AfterEach
    void tearDown() throws SQLException {
        List<Project> allProjects = projectService.getAllProjects(1, 10);
        for (Project project : allProjects) {
            projectService.deleteProject(project.getId());
        }
    }

    @Test
    void testAddProject() throws SQLException {
        ProjectDTO newProject = new ProjectDTO(1, "New Project", "Another project",
                LocalDate.parse("2024-02-01"), LocalDate.parse("2024-11-30"),
                ProjectStatus.DOING, teamDTO, 0, 0, 0);
        Project addedProject = projectService.addProject(newProject);

        assertNotNull(addedProject);
        assertEquals("New Project", addedProject.getName());
        assertEquals("Another project", addedProject.getDescription());
    }

    @Test
    void testModifyProject() throws SQLException {
        ProjectDTO newProject = new ProjectDTO(1, "New Project", "Another project",
                LocalDate.parse("2024-02-01"), LocalDate.parse("2024-11-30"),
                ProjectStatus.DOING, teamDTO, 0, 0, 0);
        Project addedProject = projectService.addProject(newProject);

        ProjectDTO modifiedProject = new ProjectDTO(addedProject.getId(), "Modified Project", "Updated description",
                LocalDate.parse("2024-02-01"), LocalDate.parse("2024-11-30"),
                ProjectStatus.PAUSED, teamDTO, 0, 0, 0);
        Project updatedProject = projectService.modifyProject(modifiedProject);

        assertNotNull(updatedProject);
        assertEquals("Modified Project", updatedProject.getName());
        assertEquals("Updated description", updatedProject.getDescription());
    }

    @Test
    void testGetAllProjects() throws SQLException {
        ProjectDTO newProject = new ProjectDTO(1, "New Project", "Another project",
                LocalDate.parse("2024-02-01"), LocalDate.parse("2024-11-30"),
                ProjectStatus.DOING, teamDTO, 0, 0, 0);
        projectService.addProject(newProject);

        List<Project> projects = projectService.getAllProjects(1, 10);
        assertFalse(projects.isEmpty());
    }

    @Test
    void testSearchProjects() throws SQLException {
        ProjectDTO newProject = new ProjectDTO(1, "Searchable Project", "Test project",
                LocalDate.parse("2024-02-01"), LocalDate.parse("2024-11-30"),
                ProjectStatus.DOING, teamDTO, 0, 0, 0);
        projectService.addProject(newProject);

        List<Project> foundProjects = projectService.searchProjects("Searchable");
        assertFalse(foundProjects.isEmpty());
        assertEquals("Searchable Project", foundProjects.get(0).getName());
    }

    @Test
    void testGetProjectById() throws SQLException {
        ProjectDTO newProject = new ProjectDTO(1, "Sample Project", "Test project",
                LocalDate.parse("2024-02-01"), LocalDate.parse("2024-11-30"),
                ProjectStatus.DOING, teamDTO, 0, 0, 0);
        Project addedProject = projectService.addProject(newProject);

        Project project = projectService.getProjectById(addedProject.getId());
        assertNotNull(project);
        assertEquals("Sample Project", project.getName());
    }

    @Test
    void testGetTotalProjectsCount() throws SQLException {
        ProjectDTO newProject = new ProjectDTO(1, "New Project", "Another project",
                LocalDate.parse("2024-02-01"), LocalDate.parse("2024-11-30"),
                ProjectStatus.DOING, teamDTO, 0, 0, 0);
        projectService.addProject(newProject);

        int totalProjects = projectService.getTotalProjectsCount();
        assertTrue(totalProjects > 0);
    }

    @Test
    void testDeleteProject() throws SQLException {
        ProjectDTO newProject = new ProjectDTO(1, "Delete Project", "Test project",
                LocalDate.parse("2024-02-01"), LocalDate.parse("2024-11-30"),
                ProjectStatus.DOING, teamDTO, 0, 0, 0);
        Project addedProject = projectService.addProject(newProject);

        projectService.deleteProject(addedProject.getId());

        Project deletedProject = projectService.getProjectById(addedProject.getId());
        assertNull(deletedProject);    }
}
