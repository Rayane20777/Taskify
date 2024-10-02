package repositories;

import java.util.List;

import models.Team;

public interface TeamRepository {
    List<Team> searchTeamsByName(String name); 

}
