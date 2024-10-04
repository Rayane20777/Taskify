package dao;

import java.sql.SQLException;
import java.util.List;

import models.Team;


public interface TeamDAO {

    public Team addTeam(Team team) throws SQLException;
    public Team updateTeam(Team team ) throws SQLException;
    public void deleteTeam(Integer id) throws SQLException;
    public List<Team> getAllTeams() throws SQLException;
    public Team getTeamById(int id) throws SQLException;
    public Team getTeamById(String id) throws SQLException;
}
