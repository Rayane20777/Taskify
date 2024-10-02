package dao;

import java.sql.SQLException;
import java.util.List;

import models.Team;


public interface TeamDAO {
	
<<<<<<< HEAD
	public Team addTeam(Team team) throws SQLException;
	public Team updateTeam(Team team ) throws SQLException;
	public void deleteTeam(Integer id) throws SQLException;
	public List<Team> getAllTeams() throws SQLException;
	public Team getTeamById(int id) throws SQLException;
=======
    void addTeam(Team team) throws SQLException;
    List<Team> getAllTeams() throws SQLException;
    Team getTeamById(int id) throws SQLException;
    void updateTeam(Team team) throws SQLException;
    void deleteTeam(int id) throws SQLException;
>>>>>>> ec1fc4fb2f251c36bcc2f503b95da35b93c796fa
}
