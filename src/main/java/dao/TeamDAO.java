package dao;

import java.util.List;

import models.Team;


public interface TeamDAO {
	
    void addTeam(Team team);
    List<Team> getAllTeams();
    void updateTeam(Team team);
    
}
