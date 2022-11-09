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

    /*public int scheduleAndPlaySeason(int leagueId) throws Exception {
        Season season = scheduleSeason(ScheduleType.HOME_ROTATION, Sport.HOCKEY, leagueId, null);
        playSeason(season, null);
        return season.getId();
    }*/

    public Season scheduleSeason(ScheduleType scheduleType, Sport sport, League league, List<Integer> teamIds, Integer numGames, String title) throws Exception {
        Season season = new Season();
        season.setTitle(title);
        season.setLeague(league);
        season.setScheduleType(scheduleType);
        season.setNumTeams(teamIds.size());
        seasonRepository.save(season);

        // List<Integer> teamIds = teamService.getTeamIdsByLeagueId(season.getLeagueId());

        for (Integer teamHomeId : teamIds) {
            standingService.save(null, season.getId(), teamHomeId, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        List<Game> games;

        switch (scheduleType) {
            case HOME_ROTATION:
                games = scheduleSeasonHomeRotation(teamIds);
                break;
            case ROUNDS:
                games = scheduleSeasonRound(teamIds, numGames);
                break;
            case HOME_ROTATION_RANDOM:
                games = scheduleSeasonHomeRotationRandom(teamIds);
                break;
            default:
                throw new Exception("Unrecognized league: " + league);
        }

        /*for (Game game : games) {
            gameService.save(null, game.getHomeTeamId(), game.getAwayTeamId(), null, null, season.getId(), null);
        }*/

        for (Game game : games) {
            game.setSport(sport); // TODO: sport is not saving correctly https://stackoverflow.com/questions/14762904/incorrect-integer-value-for-column-id-at-row-1

            game.setSeasonId(season.getId());
            game.setStatus(Status.SCHEDULED);
            gameService.save(game);
        }

        return season;
    }

    public List<Game> scheduleSeasonHomeRotation(List<Integer> teamIds) {
        List<Game> games = new ArrayList<>();
        for (Integer homeTeamId : teamIds) {
            for (Integer awayTeamId : teamIds) {
                if (!homeTeamId.equals(awayTeamId)) {
                    games.add(createGame(homeTeamId, awayTeamId));
                }
            }
        }
        return games;
    }

    public List<Game> scheduleSeasonHomeRotationRandom(List<Integer> teamIds) {
        return shuffle(scheduleSeasonHomeRotation(teamIds));
    }

    public List<Game> shuffle(List<Game> games) {
        List<Game> shuffledGames = new ArrayList<>();
        while (games.size() > 0) {
            int randomIndex = RandomUtil.getRandom(games.size());
            shuffledGames.add(games.get(randomIndex));
            games.remove(randomIndex);
        }
        return shuffledGames;
    }

    public List<Game> scheduleSeasonRound(List<Integer> teamIds, int numGames) {
        List<Game> games = new ArrayList<>();

        List<Integer> homeTeamIds = new ArrayList<>();
        List<Integer> awayTeamIds = new ArrayList<>();
        List<Integer> roundTeamIds = new ArrayList<>();

        for (int i = 0; i < numGames / 2; ++i) {
            homeTeamIds.addAll(teamIds);
            awayTeamIds.addAll(teamIds);
        }

        // schedule -----------------------------------------------------------------------------------------------------
        for (int i = 0; i < numGames; ++i) {
            roundTeamIds.addAll(teamIds);
            while (roundTeamIds.size() > 0) {

                // home -------------------------------------------------------------------------------------------------------------
                // check for teams that don't have any away games left. if found they should be set to next home team now
                Integer homeTeamIdForce = null;
                for (Integer teamId : teamIds) {
                    if (!awayTeamIds.contains(teamId) && homeTeamIds.contains(teamId) && roundTeamIds.contains(teamId)) {
                        homeTeamIdForce = teamId;
                    }
                }

                // determine home team
                List<Integer> homeCandidates = new ArrayList<>();

                // set homeCandidates to the intersection of roundTeamIds & homeTeamIds
                for (Integer roundTeamId : roundTeamIds) {
                    if (homeTeamIds.contains(roundTeamId)) {
                        homeCandidates.add(roundTeamId);
                    }
                }

                int homeCandidatesIndex = RandomUtil.getRandom(homeCandidates.size());
                int homeTeamId = homeTeamIdForce != null ? homeTeamIdForce : homeCandidates.get(homeCandidatesIndex);
                roundTeamIds.remove(roundTeamIds.indexOf(homeTeamId));
                homeTeamIds.remove(homeTeamIds.indexOf(homeTeamId));

                // away -------------------------------------------------------------------------------------------------------------
                // check for teams that don't have any away games left. if found they should be set to next home team now
                Integer awayTeamIdForce = null;
                for (Integer teamId : teamIds) {
                    if (!homeTeamIds.contains(teamId) && awayTeamIds.contains(teamId) && roundTeamIds.contains(teamId)) {
                        awayTeamIdForce = teamId;
                    }
                }

                // determine away team
                List<Integer> awayCandidates = new ArrayList<>();

                // set awayCandidates to the intersection of roundTeamIds & awayTeamIds
                for (Integer roundTeamId : roundTeamIds) {
                    if (awayTeamIds.contains(roundTeamId)) {
                        awayCandidates.add(roundTeamId);
                    }
                }

                int awayCandidatesIndex = RandomUtil.getRandom(awayCandidates.size());
                int awayTeamId = awayCandidates.get(awayCandidatesIndex);
                roundTeamIds.remove(roundTeamIds.indexOf(awayTeamId));
                awayTeamIds.remove(awayTeamIds.indexOf(awayTeamId));

                games.add(createGame(homeTeamId, awayTeamId));
            }
        }

        return games;
    }

    private Game createGame(int homeTeamId, int awayTeamId) {
        Game game = new Game();
        game.setHomeTeamId(homeTeamId);
        game.setAwayTeamId(awayTeamId);
        return game;
    }

    /*private boolean includes(int id_, List<Integer> ids) {
        for (Integer id : ids) {
            if (id_ == id)
                return true;
        }
        return false;
    }

    private List<Team> getArrayList(Iterable<Team> teams) {
        List list = new ArrayList();
        for (Team team : teams) {
            list.add(team);
        }
        return list;
    }*/

    private void updateStanding(Standing homeTeamStanding, Standing awayTeamStanding, Game game) {
        homeTeamStanding.setGp(homeTeamStanding.getGp() + 1);
        awayTeamStanding.setGp(awayTeamStanding.getGp() + 1);
        homeTeamStanding.setHomeGp(homeTeamStanding.getHomeGp() + 1);
        awayTeamStanding.setAwayGp(awayTeamStanding.getAwayGp() + 1);

        // !!! refactor all this in a separate function
        if (game.getHomeScore() > game.getAwayScore()) {
            homeTeamStanding.setWin(homeTeamStanding.getWin() + 1);
            homeTeamStanding.setHomeWin(homeTeamStanding.getHomeWin() + 1);
            homeTeamStanding.setPoint(homeTeamStanding.getPoint() + 2);
            homeTeamStanding.setHomePoint(homeTeamStanding.getHomePoint() + 2);

            if (game.getClock().getPeriod() < game.getClock().getENDING_PERIOD() + 1) {
                awayTeamStanding.setLoss(awayTeamStanding.getLoss() + 1);
                awayTeamStanding.setAwayLoss(awayTeamStanding.getAwayLoss() + 1);
            } else {
                awayTeamStanding.setOtloss(awayTeamStanding.getOtloss() + 1);
                awayTeamStanding.setAwayOtloss(awayTeamStanding.getAwayOtloss() + 1);
                awayTeamStanding.setPoint(awayTeamStanding.getPoint() + 1);
                awayTeamStanding.setAwayPoint(awayTeamStanding.getAwayPoint() + 1);
            }
        } else {
            awayTeamStanding.setWin(awayTeamStanding.getWin() + 1);
            awayTeamStanding.setAwayWin(awayTeamStanding.getAwayWin() + 1);
            awayTeamStanding.setPoint(awayTeamStanding.getPoint() + 2);
            awayTeamStanding.setAwayPoint(awayTeamStanding.getAwayPoint() + 2);

            if (game.getClock().getPeriod() < game.getClock().getENDING_PERIOD() + 1) {
                homeTeamStanding.setLoss(homeTeamStanding.getLoss() + 1);
                homeTeamStanding.setHomeLoss(homeTeamStanding.getHomeLoss() + 1);
            } else {
                homeTeamStanding.setOtloss(homeTeamStanding.getOtloss() + 1);
                homeTeamStanding.setHomeOtloss(homeTeamStanding.getHomeOtloss() + 1);
                homeTeamStanding.setPoint(homeTeamStanding.getPoint() + 1);
                homeTeamStanding.setHomePoint(homeTeamStanding.getHomePoint() + 1);
            }
        }

        homeTeamStanding.setGf(homeTeamStanding.getGf() + game.getHomeScore());
        homeTeamStanding.setGa(homeTeamStanding.getGa() + game.getAwayScore());
        awayTeamStanding.setGf(awayTeamStanding.getGf() + game.getAwayScore());
        awayTeamStanding.setGa(awayTeamStanding.getGa() + game.getHomeScore());
    }

    public String getCompleteInsertSQL(int seasonId) {
        return getInsertSQL(seasonId) +
                "\n" +
                "\n" +
                standingService.getInsertSQL(seasonId) +
                "\n" +
                "\n" +
                gameService.getInsertSQL(seasonId) +
                "\n";
    }

    private String getInsertSQL(int seasonId) {
        Optional<Season> seasonOptional = seasonRepository.findById(seasonId);
        Season season;
        if (seasonOptional.isPresent()) {
            season = seasonOptional.get();
            return String.format("INSERT INTO season VALUES (%d, \"%s\", \"%s\", \"%s\", %d, \"%s\", %d, \"%s\", \"%s\");", season.getId(), season.getCreated(), season.getUpdated(), season.getLeague(), season.getNumTeams(), season.getTitle(), season.getWinnerTeamId(), season.getSummary(), season.getScheduleType());
        } else {
            return "";
        }
    }

    // data access

    public Season findById(int id) {
        Optional<Season> seasonOptional = seasonRepository.findById(id);
        return seasonOptional.get();
    }

    public List<Season> findAll() {
        List<Season> seasons = seasonRepository.findAll();
        return seasons;
    }

    public Season update(int id, String title, Integer winnerTeamId, String summary) {
        Optional<Season> seasonOptional = seasonRepository.findById(id);
        Season season = seasonOptional.get();
        season.setTitle(title);
        season.setWinnerTeamId(winnerTeamId);
        if (summary != null) {
            season.setSummary(summary);
        }
        return seasonRepository.save(season);
    }

    public int findByWinnerTeamId(int teamId) {
        return seasonRepository.findByWinnerTeamId(teamId).size();
    }

    public void delete(int id) {
        seasonRepository.delete(findById(id));
    }

    // deprecated


    /*public Season save(int leagueId) {
        Season season = new Season();
        season.setLeagueId(leagueId);
        return seasonRepository.save(season);
    }

    public void playSeason(int seasonId, Integer numOfGames) throws InterruptedException {
        playSeason(findById(seasonId), numOfGames);
    }

    public void playSeason(Season season, Integer numOfGames) throws InterruptedException {
        List<Game> games = gameService.getBySeasonId(season.getId());
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
    }*/
}
