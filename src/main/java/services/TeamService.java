package services;

import java.util.List;

import dto.TeamDTO;

public interface TeamService {
	
    void addTeam(TeamDTO teamDTO);
    List<TeamDTO> getAllTeams();
    void updateTeam(TeamDTO teamDTO);
    void deleteTeam(int id);
    List<TeamDTO> searchTeamsByName(String name);
    

}
