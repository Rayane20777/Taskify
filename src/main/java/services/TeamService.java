package services;

import java.sql.SQLException;
import java.util.List;

import dto.TeamDTO;

public interface TeamService {
	
    void addTeam(TeamDTO teamDTO) throws SQLException;
    List<TeamDTO> getAllTeams() throws SQLException;
    TeamDTO getTeamById(int id) throws SQLException;
    void updateTeam(TeamDTO teamDTO) throws SQLException;
    void deleteTeam(int id) throws SQLException;
    List<TeamDTO> searchTeamsByName(String name);
    

}
