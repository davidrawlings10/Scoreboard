package scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        List<Integer> teamIds = teamService.getTeamIdsByLeagueId(season.getLeagueId());
        for (Integer teamHomeId : teamIds) {
            standingService.save(null, season.getId(), teamHomeId, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            for (Integer teamAwayId : teamIds) {
                if (!teamHomeId.equals(teamAwayId))
                    gameService.save(null, teamHomeId, teamAwayId, null, null, season.getId(), null);
            }
        }
        return season;
    }

    public Season scheduleSeason2(int leagueId, int num_games) {
        Season season = save(leagueId);
        List<Integer> teamIds = teamService.getTeamIdsByLeagueId(season.getLeagueId());

        for (Integer teamId : teamIds) {
            standingService.save(null, season.getId(), teamId, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        List<Integer> homeTeamIds = new ArrayList<>();
        List<Integer> awayTeamIds = new ArrayList<>();
        List<Integer> roundTeamIds = new ArrayList<>();

        for (int i = 0; i < num_games / 2; ++i) {
            homeTeamIds.addAll(teamIds);
            awayTeamIds.addAll(teamIds);
        }

        for (int i = 0; i < num_games; ++i) {
            roundTeamIds.addAll(teamIds);
            while (roundTeamIds.size() > 0) {

                // determine home team
                List<Integer> homeCandidates = new ArrayList<>();

                for (Integer roundTeamId : roundTeamIds) {
                    if (homeTeamIds.contains(roundTeamId)) {
                        homeCandidates.add(roundTeamId);
                    }
                }

                int homeCandidatesIndex = RandomService.getRandom(homeCandidates.size());
                int homeTeamId = homeCandidates.get(homeCandidatesIndex);
                roundTeamIds.remove(roundTeamIds.indexOf(homeTeamId));
                homeTeamIds.remove(homeTeamIds.indexOf(homeTeamId));

                // determine away team
                List<Integer> awayCandidates = new ArrayList<>();

                for (Integer roundTeamId : roundTeamIds) {
                    if (awayTeamIds.contains(roundTeamId)) {
                        awayCandidates.add(roundTeamId);
                    }
                }

                int awayCandidatesIndex = RandomService.getRandom(awayCandidates.size());
                int awayTeamId = awayCandidates.get(awayCandidatesIndex);
                roundTeamIds.remove(roundTeamIds.indexOf(awayTeamId));
                awayTeamIds.remove(awayTeamIds.indexOf(awayTeamId));

                gameService.save(null, homeTeamId, awayTeamId, null, null, season.getId(), null);
            }
        }

        /*for (Team team_home : teams) {
            for (Team team_away : teams) {
                if (team_home.getId() != team_away.getId())
                    gameService.save(null, team_home.getId(), team_away.getId(), null, null, season.getId(), null);
            }
        }*/

        return season;
    }

    private List<Team> getArrayList(Iterable<Team> teams) {
        List list = new ArrayList();
        for (Team team : teams) {
            list.add(team);
        }
        return list;
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
        String gameResultString = gameService.playGame(game);

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
