package services;

import java.sql.SQLException;
import java.util.List;

import dao.TeamDAO;
import dto.TeamDTO;
import models.Team;
import repositories.TeamRepository;

public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;
    private TeamDAO teamDAO;

<<<<<<< HEAD
    public TeamServiceImpl(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }
    
=======
>>>>>>> ec1fc4fb2f251c36bcc2f503b95da35b93c796fa
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    
<<<<<<< HEAD

=======
    public TeamServiceImpl(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }
    
>>>>>>> ec1fc4fb2f251c36bcc2f503b95da35b93c796fa
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
<<<<<<< HEAD
    public void deleteTeam(Integer id) throws SQLException {
        if (teamDAO.getTeamById(id) == null) {
            throw new SQLException("Team not found with ID: " + id);
        }
=======
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
>>>>>>> ec1fc4fb2f251c36bcc2f503b95da35b93c796fa
        teamDAO.deleteTeam(id);
    }

    @Override
    public List<Team> getAllTeams() throws SQLException {
        return teamDAO.getAllTeams();
    }

    @Override
    public List<Team> searchTeams(String name) throws SQLException {
        return teamRepository.searchTeams(name);
    }
}
