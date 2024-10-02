package dao;

import java.sql.SQLException;
import java.util.List;

import models.Team;


public interface TeamDAO {
	
    void addTeam(Team team) throws SQLException;
    List<Team> getAllTeams() throws SQLException;
    Team getTeamById(int id) throws SQLException;
    void updateTeam(Team team) throws SQLException;
    void deleteTeam(int id) throws SQLException;
}
