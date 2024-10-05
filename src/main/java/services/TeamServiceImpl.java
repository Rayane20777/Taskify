package services;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import dao.TeamDAO;
import dto.TeamDTO;
import models.Team;
import repositories.TeamRepository;

public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;
    private TeamDAO teamDAO;

    public TeamServiceImpl(TeamDAO teamDAO) {
        if (teamDAO == null) {
            throw new IllegalArgumentException("teamDAO cannot be null");
        }
        this.teamDAO = teamDAO;    }
    
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    

    @Override
    public Team addTeam(TeamDTO team) throws SQLException {
        Team teamModel = team.dtoToModel();
        return teamDAO.addTeam(teamModel);
    }

    @Override
    public Team updateTeam(TeamDTO team) throws SQLException {
        if (teamDAO.getTeamById(team.getId()) == null) {
            return null;
        } else {
            Team teamModel = team.dtoToModel();
            return teamDAO.updateTeam(teamModel);
        }
    }

    @Override
    public void deleteTeam(Integer id) throws SQLException {
        if (teamDAO.getTeamById(id) == null) {
            throw new SQLException("Team not found with ID: " + id);
        }
        teamDAO.deleteTeam(id);
    }

    @Override
    public List<Team> getAllTeams(int page, int pageSize) throws SQLException {
        List<Team> allTeams = teamDAO.getAllTeams();
        int skipCount = (page - 1) * pageSize;

    	    return allTeams.stream()
    	            .skip(skipCount)
    	            .limit(pageSize)
    	            .collect(Collectors.toList());    }


    public List<Team> getAllTeams() throws SQLException {
        return teamDAO.getAllTeams();
    }

    @Override
    public List<Team> searchTeams(String name) throws SQLException {
        return teamRepository.searchTeams(name);
    }
    @Override
    public Team getTeamById(int id) throws SQLException {
        return teamDAO.getTeamById(id);
    }
}
