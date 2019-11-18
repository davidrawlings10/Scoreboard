package scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeasonService {

    @Autowired private SeasonRepository seasonRepository;
    @Autowired private GameService gameService;
    //@Autowired private TeamService teamService;

    public String start(int leagueId) {
        Season season = save(leagueId);
        schedule(season);
        return "Season started";
    }

    private void schedule(Season season) {

        /*for (Team team : teamService.getTeamsByLeagueId(season.getLeagueId())) {
            System.out.println(team.getId() + ", " + team.getLocation() + " : " + team.getName());
        }*/

        //season.getLeagueId()
        //gameService.save(1, 2, null, null, season.getId());
    }

    public Season save(int leagueId) {
        Season season = new Season();
        season.setLeagueId(leagueId);
        return seasonRepository.save(season);
    }

    public Season findById(int id) {
        Optional<Season> seasonOptional = seasonRepository.findById(id);
        return seasonOptional.get();
    }
}
