package scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import java.util.List; `1
import java.util.Optional;

@Service
public class SeasonService {

    @Autowired private SeasonRepository seasonRepository;
    @Autowired private GameService gameService;
    @Autowired private TeamService teamService;
    @Autowired private StandingService standingService;

    public int schedulePlaySeason(int leagueId) throws InterruptedException {
        Season season = scheduleSeason(leagueId);
        playSeason(season, null);
        return season.getId();
    }

    public Season scheduleSeason(int leagueId) {
        Season season = save(leagueId);
        Iterable<Team> teams = teamService.getByLeagueId(season.getLeagueId());
        for (Team team_home : teams) {
            standingService.save(null, season.getId(), team_home.getId(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            for (Team team_away : teams) {
                if (team_home.getId() != team_away.getId())
                    gameService.save(null, team_home.getId(), team_away.getId(), null, null, season.getId(), null);
            }
        }
        return season;
    }

    public void playSeason(int seasonId, Integer numOfGames) throws InterruptedException {
        playSeason(findById(seasonId), numOfGames);
    }

    public void playSeason(Season season, Integer numOfGames) throws InterruptedException {
        Iterable<Game> games = gameService.getBySeasonId(season.getId());
        for (Game game : games) {
            if (game.getEndingPeriod() == null) {
                playGame(game);
                if (numOfGames != null) {
                    --numOfGames;
                    if (numOfGames == 0)
                        break;
                }
            }
        }
    }

    private String playGame(Game game) throws InterruptedException {
        String gameResultString = gameService.playHockeyV2(game);

        Standing homeTeamStanding = standingService.findBySeasonIdAndTeamId(game.getSeasonId(), game.getHomeTeamId());
        Standing awayTeamStanding = standingService.findBySeasonIdAndTeamId(game.getSeasonId(), game.getAwayTeamId());

        updateStanding(homeTeamStanding, awayTeamStanding, game);

        standingService.save(homeTeamStanding);
        standingService.save(awayTeamStanding);

        return gameResultString;
    }

    private void updateStanding(Standing homeTeamStanding, Standing awayTeamStanding, Game game) {
        homeTeamStanding.setGp(homeTeamStanding.getGp() + 1);
        awayTeamStanding.setGp(awayTeamStanding.getGp() + 1);

        // !!! refactor all this in a separate function
        if (game.getHomeScore() > game.getAwayScore()) {
            homeTeamStanding.setWin(homeTeamStanding.getWin() + 1);
            homeTeamStanding.setPoint(homeTeamStanding.getPoint() + 2);
            homeTeamStanding.setHomeWin(homeTeamStanding.getHomeWin() + 1);
            awayTeamStanding.setAwayLoss(awayTeamStanding.getAwayLoss() + 1);
            if (game.getEndingPeriod() < 4) {
                awayTeamStanding.setLoss(awayTeamStanding.getLoss() + 1);
            } else {
                awayTeamStanding.setOtloss(awayTeamStanding.getOtloss() + 1);
                awayTeamStanding.setPoint(awayTeamStanding.getPoint() + 1);
            }
        } else {
            awayTeamStanding.setWin(awayTeamStanding.getWin() + 1);
            awayTeamStanding.setPoint(awayTeamStanding.getPoint() + 2);
            awayTeamStanding.setAwayWin(awayTeamStanding.getAwayWin() + 1);
            homeTeamStanding.setHomeLoss(homeTeamStanding.getHomeLoss() + 1);
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
    }

    // data access

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
