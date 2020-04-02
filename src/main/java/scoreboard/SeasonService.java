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
            standingService.save(null, season.getId(), team_home.getId(), 0, 0, 0, 0, 0, 0, 0);
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
                homeTeamStanding.setPoint(homeTeamStanding.getPoint() + 2);
                if (game.getEndingPeriod() < 4) {
                    awayTeamStanding.setLoss(awayTeamStanding.getLoss() + 1);
                } else {
                    awayTeamStanding.setOtloss(awayTeamStanding.getOtloss() + 1);
                    awayTeamStanding.setPoint(awayTeamStanding.getPoint() + 1);
                }
            } else {
                awayTeamStanding.setWin(awayTeamStanding.getWin() + 1);
                awayTeamStanding.setPoint(awayTeamStanding.getPoint() + 2);
                if (game.getEndingPeriod() < 4) {
                    homeTeamStanding.setLoss(homeTeamStanding.getLoss() + 1);
                } else {
                    homeTeamStanding.setOtloss(homeTeamStanding.getOtloss() + 1);
                    homeTeamStanding.setPoint(homeTeamStanding.getPoint() + 1);
                }
            }

            homeTeamStanding.setGf(homeTeamStanding.getGf() + game.getHomeScore());
            homeTeamStanding.setGa(homeTeamStanding.getGa() + game.getAwayScore());
            awayTeamStanding.setGf(awayTeamStanding.getGf() + game.getAwayScore());
            awayTeamStanding.setGa(awayTeamStanding.getGa() + game.getHomeScore());

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
