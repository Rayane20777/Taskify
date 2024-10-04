package models;

import java.time.LocalDate;

import dto.TeamDTO;
import models.enums.ProjectStatus;

public class Project {
    Integer id;
    String name;
    String description;
    LocalDate startDate;
    LocalDate endDate;
    ProjectStatus status;
    Team team;
    private int totalTasks;
    private int completedTasks;  
    private double progressPercentage;

    public Project() {}

    public Project(Integer id, String name, String description, LocalDate startDate, LocalDate endDate, ProjectStatus status, Team team, int totalTasks, int completedTasks, double progressPercentage) {
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
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
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", team=" + team +
                ", totalTasks=" + totalTasks +
                ", completedTasks=" + completedTasks +
                ", progressPercentage=" + progressPercentage +
                '}';
    }
}
