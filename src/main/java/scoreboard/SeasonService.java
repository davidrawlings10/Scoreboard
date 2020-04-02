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
    @Autowired private StandingService standingService;

    public String scheduleAndPlay(int leagueId) throws InterruptedException {
        Season season = save(leagueId);
        schedule(season);
        play(season);
        return "Season finished";
    }

    private void schedule(Season season) {
        Iterable<Team> teams = teamService.getTeamsByLeagueId(season.getLeagueId());
        for (Team team_home : teams) {
            standingService.save(null, season.getId(), team_home.getId(), 0, 0, 0, 0, 0, 0);
            for (Team team_away : teams) {
                if (team_home.getId() != team_away.getId())
                    gameService.save(null, team_home.getId(), team_away.getId(), null, null, season.getId(), null);
            }
        }
    }

    private void play(Season season) throws InterruptedException {
        Iterable<Game> games = gameService.getGamesBySeasonId(season.getId());
        for (Game game : games) {
            gameService.playHockeyV2(game);

            Standing homeTeamStanding = standingService.findBySeasonIdAndTeamId(game.getSeasonId(), game.getHomeTeamId());
            Standing awayTeamStanding = standingService.findBySeasonIdAndTeamId(game.getSeasonId(), game.getAwayTeamId());

            if (game.getHomeScore() > game.getAwayScore()) {
                homeTeamStanding.setWin(homeTeamStanding.getWin() + 1);
                awayTeamStanding.setLoss(awayTeamStanding.getLoss() + 1);
            } else {
                homeTeamStanding.setLoss(homeTeamStanding.getLoss() + 1);
                awayTeamStanding.setWin(awayTeamStanding.getWin() + 1);
            }

            standingService.save(homeTeamStanding);
            standingService.save(awayTeamStanding);
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
