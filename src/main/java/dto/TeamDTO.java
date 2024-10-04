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


	public Team dtoToModel() {
		return new Team(id,name);
	}

	public static TeamDTO modelToDTO(Team team) {
		return new TeamDTO(team.getId(), team.getName());
	}
}
