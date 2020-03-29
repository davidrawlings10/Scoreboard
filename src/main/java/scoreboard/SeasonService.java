package scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeasonService {

    @Autowired private SeasonRepository seasonRepository;
    @Autowired private GameService gameService;
    @Autowired private TeamService teamService;

    public String start(int leagueId) {
        Season season = save(leagueId);
        schedule(season);
        play(season);
        return "Season started";
    }

    private void schedule(Season season) {
        Iterable<Team> teams = teamService.getTeamsByLeagueId(season.getLeagueId());
        for (Team team_home : teams) {
            for (Team team_away : teams) {
                gameService.save(null, team_home.getId(), team_away.getId(), null, null, season.getId());
            }
        }
    }

    private void play(Season season) {
        Iterable<Game> games = gameService.getGamesBySeasonId(season.getId());
        for (Game game : games) {
            gameService.playHockeyV2(game);
        }
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
