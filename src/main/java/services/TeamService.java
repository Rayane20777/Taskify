package services;

import java.sql.SQLException;
import java.util.List;

import dto.TeamDTO;
import models.Team;

public interface TeamService {
	
<<<<<<< HEAD
    public Team addTeam(TeamDTO team)throws SQLException;
    public Team updateTeam(TeamDTO team)throws SQLException;
    public void deleteTeam(Integer id) throws SQLException;
    public List<Team> getAllTeams() throws SQLException;
    public List<Team> searchTeams(String name) throws SQLException;
=======
    void addTeam(TeamDTO teamDTO);
    List<TeamDTO> getAllTeams();
    TeamDTO getTeamById(int id);
    void updateTeam(TeamDTO teamDTO);
    void deleteTeam(int id);
    List<TeamDTO> searchTeamsByName(String name);
    

>>>>>>> ec1fc4fb2f251c36bcc2f503b95da35b93c796fa
}
