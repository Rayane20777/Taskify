package dao;

import java.util.List;

import models.Team;


public interface TeamDAO {
	
    void addTeam(Team team);
    List<Team> getAllTeams();
    Team getTeamById(int id);
    void updateTeam(Team team);
    void deleteTeam(int id);
}
