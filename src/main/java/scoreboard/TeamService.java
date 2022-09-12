package scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired private TeamRepository teamRepository;

    // data access

    public Team getByTeamId(int teamId) {
        return teamRepository.findByTeamId(teamId);
    }

    public Iterable<Team> getByLeague(League league) {
        //return teamRepository.findAll();

        return teamRepository.findByLeague(league);
    }

    // DEPRECATED

    /* public List<Integer> getTeamIdsByLeague(League league) {
        return teamRepository.findTeamIdsByLeague(league);
    }*/
}
