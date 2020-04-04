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

    public String schedulePlaySeason(int leagueId) throws InterruptedException {
        Season season = save(leagueId);
        scheduleSeason(season);
        playSeason(season);
        return "Season scheduled and played";
    }

    public String scheduleSeason(int seasonId) {
        scheduleSeason(findById(seasonId));
        return "Season scheduled";
    }

    private void scheduleSeason(Season season) {
        Iterable<Team> teams = teamService.getTeamsByLeagueId(season.getLeagueId());
        for (Team team_home : teams) {
            standingService.save(null, season.getId(), team_home.getId(), 0, 0, 0, 0, 0, 0, 0);
            for (Team team_away : teams) {
                if (team_home.getId() != team_away.getId())
                    gameService.save(null, team_home.getId(), team_away.getId(), null, null, season.getId(), null);
            }
        }
    }

    public String playSeason(int seasonId) throws InterruptedException {
        playSeason(findById(seasonId));
        return "Season played";
    }

    private void playSeason(Season season) throws InterruptedException {
        Iterable<Game> games = gameService.getGamesBySeasonId(season.getId());
        for (Game game : games) {
            playGame(game);
        }
    }

    private String playGame(Game game) throws InterruptedException {
        String gameResultString = gameService.playHockeyV2(game);

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

        return gameResultString;
    }

    /*public String playNextGame(int seasonId) throws InterruptedException {
        return playGame(gameService.findNextGameBySeasonId(seasonId));
    }*/

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
