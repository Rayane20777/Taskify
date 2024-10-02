package services;

import java.util.ArrayList;
import java.util.List;

import dao.TeamDAO;
import dto.TeamDTO;
import models.Team;
import repositories.TeamRepository;

public class TeamServiceImpl implements TeamService{
    private TeamDAO teamDAO;
    private TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public TeamServiceImpl(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }
    
    @Override
    public void addTeam(TeamDTO teamDTO) {
        Team team = new Team();
        team.setName(teamDTO.getName());
        
        teamDAO.addTeam(team);
    }
    
    @Override
    public List<TeamDTO> getAllTeams() {
        List<Team> teams = teamDAO.getAllTeams();
        List<TeamDTO> teamDTOs = new ArrayList<>();
        for (Team team : teams) {
            teamDTOs.add(new TeamDTO(team.getId(), team.getName()));
        }
        return teamDTOs;
    }
    
    @Override
    public TeamDTO getTeamById(int id) {
        Team team = teamDAO.getTeamById(id);
        if (team != null) {
            return new TeamDTO(team.getId(), team.getName());
        }
        return null;
    }
    
    @Override
    public void updateTeam(TeamDTO teamDTO) {
        Team team = new Team();
        team.setId(teamDTO.getId());
        team.setName(teamDTO.getName());
        teamDAO.updateTeam(team);
    }
    
    @Override
    public void deleteTeam(int id) {
        teamDAO.deleteTeam(id);
    }
    
    @Override
    public List<TeamDTO> searchTeamsByName(String name) {
        List<Team> teams = teamRepository.searchTeamsByName(name);
        List<TeamDTO> teamDTOs = new ArrayList<>();
        for (Team team : teams) {
            teamDTOs.add(new TeamDTO(team.getId(), team.getName()));
        }
        return teamDTOs;
    }
    
}
