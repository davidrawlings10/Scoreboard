package scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class GameService {

    @Autowired private GameRepository gameRepository;
    @Autowired private TeamService teamService;

    private static String homeTeamName, awayTeamName;

    public class Config {
        public class Chance {
            public final static double regulationScore = 0.075, regulationScoreHomeWeight = 0.005, regulationScoreAwayWeight = -0.005;
            public final static double overtimeScore = 0.1, overtimeScoreHomeWeight = 0.005, overtimeScoreAwayWeight = -0.005;
            public final static double shootoutScore = 31.94, shootoutScoreHomeWeight = 1, shootoutScoreAwayWeight = -1;
        }
        // time in seconds for how long things take
        public class TimeDelay {
            // public final static int gameplayTickMilli = 100, shootoutSec = 15, intermissionSec = 120; // standard
            public final static int gameplayTickMilli = 130, shootoutSec = 15, intermissionSec = 30; // accelerated
            // public final static int gameplayTickMilli = 0, shootoutSec = 0, intermissionSec = 0; // immediate
        }
    }

    //private String chancea = "{ \"score\": 1 " +
    //        "}";

    public String playGame(int homeTeamId, int awayTeamId, Sport sport, Integer seasonId) throws InterruptedException {

        /*JSONObject obj = new JSONObject("{\"name\": \"John\"}");
        obj.getString("name");
        System.out.println(obj);*/

        //gameRepository.findAll();

        //gameRepository.findById(1);

        return playHockeyV2(null, homeTeamId, awayTeamId, seasonId);
    }

    private String playHockeyV1(int homeTeamId, int awayTeamId, Integer seasonId) {
        Random rand = new Random();

        final int maxScore = 6;

        int homeScore = rand.nextInt(maxScore);
        int awayScore = rand.nextInt(maxScore);

        save(null, homeTeamId, awayTeamId, homeScore, awayScore, seasonId, 1);
        return "HOME: " + homeScore + " AWAY: " + awayScore;
    }

    public String playHockeyV2(Game game) throws InterruptedException {
        return playHockeyV2(game.getId(), game.getHomeTeamId(), game.getAwayTeamId(), game.getSeasonId());
    }

    private String playHockeyV2(Integer id, int homeTeamId, int awayTeamId, Integer seasonId) throws InterruptedException {
        int homeScore = 0, awayScore = 0, period = 1, minutes = 20, seconds = 0;

        double homeChance = Config.Chance.regulationScore + Config.Chance.regulationScoreHomeWeight, awayChance = Config.Chance.regulationScore + Config.Chance.regulationScoreAwayWeight;

        homeTeamName = getTeamByTeamId(homeTeamId).getName();
        awayTeamName = getTeamByTeamId(awayTeamId).getName();

        System.out.println(printScoreboard(homeScore, awayScore, period, minutes, seconds, id));

        TimeUnit.SECONDS.sleep(Config.TimeDelay.intermissionSec);

        while (true) {

            TimeUnit.MILLISECONDS.sleep(Config.TimeDelay.gameplayTickMilli);

            if (period == 5) {
                if (shootout()) {
                    homeScore++;
                } else {
                    awayScore++;
                }
                break;
            }

            // total GA (goals for) in 18-19 NHL regular season was 7664
            // games played in 18-19 NHL regular season was 82 * 31 = 2542
            // 7664 / 2542 = 3.014 goals a game
            // considering overtime goals rounding down to .9 goals a period
            // .9 / 20 = .045 average goals per minutes
            // .045 / 60 = .00075 average goals per second

            if (RandomService.occur(homeChance)) {
                homeScore++;
                if (period == 4)
                    break;
            }
            if (RandomService.occur(awayChance)) {
                awayScore++;
                if (period == 4)
                    break;
            }

            seconds--;

            if (seconds == -1) {
                minutes--;
                seconds = 59;
            } else if (seconds == 0 && minutes == 0) {
                // period ends

                // System.out.println(homeTeamName + ": " + homeScore + ", " + awayTeamName + ": " + awayScore + " PERIOD: " + period + " " + minutes + ":" + seconds); `1
                System.out.println(printScoreboard(homeScore, awayScore, period, minutes, seconds, id));

                // end of the game in regulation if scores are different, otherwise period will become 4
                if (period == 3 && homeScore != awayScore) {
                    break;
                } else {
                    TimeUnit.SECONDS.sleep(Config.TimeDelay.intermissionSec);
                }

                period++;

                // minutes are reset to 5 for overtime and 20 for periods 2 and 3
                minutes = period == 4 ? 5 : 20;
                seconds = 0;

                // if overtime is starting update increase the chance of a goal as overtime is played 3 on 3
                if (period == 4) {
                    homeChance = Config.Chance.overtimeScore + Config.Chance.overtimeScoreHomeWeight;
                    awayChance = Config.Chance.overtimeScore + Config.Chance.overtimeScoreAwayWeight;
                }
            }

            System.out.println(printScoreboard(homeScore, awayScore, period, minutes, seconds, id));
        }

        save(id, homeTeamId, awayTeamId, homeScore, awayScore, seasonId, period);
        return printScoreboard(homeScore, awayScore, period, minutes, seconds, id);
    }

    private boolean shootout() throws InterruptedException {
        int homeShootoutScore = 0, awayShootoutScore = 0, shootoutRound = 1;
        while (true) {
            if (RandomService.occur(Config.Chance.shootoutScore + Config.Chance.shootoutScoreHomeWeight)) {
                homeShootoutScore++;
                System.out.println(homeTeamName + " scores");
            } else {
                System.out.println(homeTeamName + " misses");
            }
            TimeUnit.SECONDS.sleep(Config.TimeDelay.shootoutSec);
            if (RandomService.occur(Config.Chance.shootoutScore + Config.Chance.shootoutScoreAwayWeight)) {
                awayShootoutScore++;
                System.out.println(awayTeamName + " scores");
            } else {
                System.out.println(awayTeamName + " misses");
            }
            System.out.println(printScoreboardShootout(homeShootoutScore, awayShootoutScore, shootoutRound));
            TimeUnit.SECONDS.sleep(Config.TimeDelay.shootoutSec);
            if (shootoutRound >= 3 && homeShootoutScore != awayShootoutScore) {
                break;
            }
            shootoutRound++;
        }

        return homeShootoutScore > awayShootoutScore;
    }

    private String printScoreboard(int homeScore, int awayScore, int period, int minutes, int seconds, Integer id) {
        return homeTeamName + ": " + homeScore + " " + awayTeamName +  ": " + awayScore + " PERIOD: " + period + " " + minutes + ":" + seconds;
    }

    private String printScoreboardShootout(int homeShootoutScore, int awayShootoutScore, int shootoutRound) {
        return homeTeamName + ": " + homeShootoutScore + " " + awayTeamName + ": " + awayShootoutScore + " SHOOTOUT ROUND: " + shootoutRound;
    }

    public Game save(Integer id, int homeTeamId, int awayTeamId, Integer homeScore, Integer awayScore, Integer seasonId, Integer endingPeriod) {
        Game game = new Game();
        game.setId(id);
        game.setHomeTeamId(homeTeamId);
        game.setAwayTeamId(awayTeamId);
        game.setHomeScore(homeScore);
        game.setAwayScore(awayScore);
        game.setSeasonId(seasonId);
        game.setEndingPeriod(endingPeriod);
        return gameRepository.save(game);
    }

    public Game findById(int gameId) {
        Optional<Game> gameOptional = gameRepository.findById(gameId);
        return gameOptional.get();
    }

    public Game updateById(int gameId) {
        Optional<Game> gameOptional = gameRepository.findById(gameId);
        Game game = gameOptional.get();
        game.setAwayScore(10);
        gameRepository.save(game);
        return game;
    }

    public Iterable<Game> getGamesBySeasonId(int leagueId) {
        return gameRepository.findBySeasonId(leagueId);
    }

    public Team getTeamByTeamId(int teamId) { return teamService.getTeamByTeamId(teamId); }






    /*public Collection<Team> getTeamsByLeagueId(int leagueId) {

    }*/


    /*public boolean existsById(int id) {
        return gameRepository.existsById(id);
    }

    public void count() {
        gameRepository.count();
    }

    public void deleteById(int id) {
        gameRepository.deleteById(id);
    }

    public void findAll() {
        for (Game game : gameRepository.findAll()) {
            System.out.println(game.getHomeScore() + "-" + game.getAwayScore() + " : " + game.getId());
        }
    }*/
}
