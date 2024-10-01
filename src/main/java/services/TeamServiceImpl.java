package services;

import dao.TeamDAO;
import dto.TeamDTO;
import models.Team;

public class TeamServiceImpl implements TeamService{
    private TeamDAO teamDAO;

    public TeamServiceImpl(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }
    
    @Override
    public void addTeam(TeamDTO teamDTO) {
        Team team = new Team();
        team.setName(teamDTO.getName());
        
        teamDAO.addTeam(team);
    }
}
