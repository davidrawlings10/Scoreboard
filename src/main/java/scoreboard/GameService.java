package scoreboard;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.Random;

@Service
public class GameService {

    @Autowired private GameRepository gameRepository;

    public class Chance {
        public final static double score = 0.075, scoreHomeWeight = 0.005, scoreAwayWeight = -0.005;
        public final static double shootout = 0.1, shootoutHomeWeight = 0.005, shootoutAwayWeight = -0.005;

    }

    //private String chancea = "{ \"score\": 1 " +
    //        "}";

    public String playGame(int homeTeamId, int awayTeamId, Sport sport, Integer seasonId) {

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

        save(null, homeTeamId, awayTeamId, homeScore, awayScore, seasonId);
        return "HOME: " + homeScore + " AWAY: " + awayScore;
    }

    public String playHockeyV2(Game game) {
        return playHockeyV2(game.getId(), game.getHomeTeamId(), game.getAwayTeamId(), game.getSeasonId());
    }

    private String playHockeyV2(Integer id, int homeTeamId, int awayTeamId, Integer seasonId) {
        int homeScore = 0, awayScore = 0, period = 1, minutes = 20, seconds = 0;

        double homeChance = Chance.score + Chance.scoreHomeWeight, awayChance = Chance.score + Chance.scoreAwayWeight;

        while (true) {

            //TimeUnit.SECONDS.sleep(1);

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

                System.out.println("HOME: " + homeScore + " AWAY: " + awayScore + " PERIOD: " + period + " " + minutes + ":" + seconds);
                //TimeUnit.SECONDS.sleep(60);

                // end of the game in regulation if scores are different, otherwise period will become 4
                if (period == 3 && homeScore != awayScore) {
                    break;
                }

                period++;

                // minutes are reset to 5 for overtime and 20 for periods 2 and 3
                minutes = period == 4 ? 5 : 20;
                seconds = 0;

                // if overtime is starting update increase the chance of a goal as overtime is played 3 on 3
                if (period == 4) {
                    homeChance = Chance.shootout + Chance.shootoutHomeWeight;
                    awayChance = Chance.shootout + Chance.shootoutAwayWeight;
                }
            }

            System.out.println("HOME: " + homeScore + " AWAY: " + awayScore + " PERIOD: " + period + " " + minutes + ":" + seconds + ", id:" + id);
        }

        save(id, homeTeamId, awayTeamId, homeScore, awayScore, seasonId);
        return "HOME: " + homeScore + " AWAY: " + awayScore + " PERIOD: " + period + " " + minutes + ":" + seconds;
    }

    private boolean shootout() {
        int homeShootoutScore = 0, awayShootoutScore = 0, shootoutRound = 1;
        System.out.println("Shootout round " + shootoutRound);
        while (shootoutRound < 4 || homeShootoutScore != awayShootoutScore) {
            if (RandomService.occur(31.94)) {
                homeShootoutScore++;
                System.out.println("Home scores");
            } else {
                System.out.println("Home misses");
            }
            if (RandomService.occur(31.94)) {
                awayShootoutScore++;
                System.out.println("Away scores");
            } else {
                System.out.println("Away misses");
            }
            System.out.println("HOME: " + homeShootoutScore + " AWAY: " + awayShootoutScore + " SHOOTOUT ROUND: " + shootoutRound);
            if (shootoutRound >= 3 && homeShootoutScore != awayShootoutScore) {
                break;
            }
            shootoutRound++;
        }

        return homeShootoutScore > awayShootoutScore;
    }

    public Game save(Integer id, int homeTeamId, int awayTeamId, Integer homeScore, Integer awayScore, Integer seasonId) {
        Game game = new Game();
        game.setId(id);
        game.setHomeTeamId(homeTeamId);
        game.setAwayTeamId(awayTeamId);
        game.setHomeScore(homeScore);
        game.setAwayScore(awayScore);
        game.setSeasonId(seasonId);
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
