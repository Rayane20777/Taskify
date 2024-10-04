package dto;

import java.time.LocalDate;
import models.Project;
import models.enums.ProjectStatus;

public class ProjectDTO {
    Integer id;
    String name;
    String description;
    LocalDate startDate;
    LocalDate endDate;
    ProjectStatus status;
    TeamDTO team;
    private int totalTasks; 
    private int completedTasks; 
    private double progressPercentage;

    public ProjectDTO() {}

    public ProjectDTO(Integer id, String name, String description, LocalDate startDate, LocalDate endDate, ProjectStatus status, TeamDTO team, int totalTasks, int completedTasks, double progressPercentage) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.team = team;
		this.totalTasks = totalTasks;
		this.completedTasks = completedTasks;
		this.progressPercentage = progressPercentage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }
    
    public int getTotalTasks() {
        return totalTasks;
    }

    public void setTotalTasks(int totalTasks) {
        this.totalTasks = totalTasks;
    }

    public int getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(int completedTasks) {
        this.completedTasks = completedTasks;
    }

    public double getProgressPercentage() {
        return progressPercentage;
    }

    public void setProgressPercentage(double progressPercentage) {
        this.progressPercentage = progressPercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectDTO that = (ProjectDTO) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!description.equals(that.description)) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (status != that.status) return false;
        return team != null ? team.equals(that.team) : that.team == null;
    }

    public Project dtoToModel() {
        return new Project(id, name, description, startDate, endDate, status, team.dtoToModel(), totalTasks, completedTasks, progressPercentage);
    }

    public static ProjectDTO modelToDTO(Project project) {
        return new ProjectDTO(
            project.getId(),
            project.getName(),
            project.getDescription(),
            project.getStartDate(),
            project.getEndDate(),
            project.getStatus(),
            TeamDTO.modelToDTO(project.getTeam()),
            project.getTotalTasks(),
            project.getCompletedTasks(),
            project.getProgressPercentage()
        );
    }
}
