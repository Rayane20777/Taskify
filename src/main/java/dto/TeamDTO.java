package dto;

import models.Team;

public class TeamDTO {
	private int id;
	private String name;
	
	public TeamDTO() {
		
	}
	public TeamDTO(int id, String name) {
		this.setId(id);
		this.setName(name);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
<<<<<<< HEAD
	public Team dtoToModel() {
=======
	public Team dtoModel() {
>>>>>>> ec1fc4fb2f251c36bcc2f503b95da35b93c796fa
		return new Team(id,name);
	}
	
	public static TeamDTO modelToDTO(Team team) {
		return new TeamDTO(team.getId(), team.getName());
	}
}
