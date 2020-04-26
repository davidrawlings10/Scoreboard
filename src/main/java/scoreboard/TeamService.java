package scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import java.util.Collection; `1

@Service
public class TeamService {

    @Autowired private TeamRepository teamRepository;

    // data access

    public Team getByTeamId(int teamId) {
        return teamRepository.findByTeamId(teamId);
    }

    public Iterable<Team> getByLeagueId(int leagueId) {
        //return teamRepository.findAll();

        return teamRepository.findByLeagueId(leagueId);
    }
}
