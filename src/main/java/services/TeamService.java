package services;

import java.sql.SQLException;
import java.util.List;

import dto.TeamDTO;
import models.Team;

public interface TeamService {

    public Team addTeam(TeamDTO team)throws SQLException;
    public Team updateTeam(TeamDTO team)throws SQLException;
    public void deleteTeam(Integer id) throws SQLException;
    public List<Team> getAllTeams() throws SQLException;
    public List<Team> searchTeams(String name) throws SQLException;
    public Team getTeamById(int id) throws SQLException;
}
