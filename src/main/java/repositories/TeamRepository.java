package repositories;

import java.util.List;

import models.Team;

public interface TeamRepository {
    public List<Team> searchTeams(String name);

}
