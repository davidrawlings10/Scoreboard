package scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Random;

@Service
public class GameService {

    @Autowired private GameRepository gameRepository;

    public String playGame(int homeTeamId, int awayTeamId, Sport sport, Integer seasonId) {

        //gameRepository.findAll();

        //gameRepository.findById(1);

        return playHockeyV2(homeTeamId, awayTeamId, seasonId);
    }

    private String playHockeyV1(int homeTeamId, int awayTeamId, Integer seasonId) {
        Random rand = new Random();

        final int maxScore = 6;

        int homeScore = rand.nextInt(maxScore);
        int awayScore = rand.nextInt(maxScore);

        saveGame(homeTeamId, awayTeamId, homeScore, awayScore, seasonId);
        return "HOME: " + homeScore + " AWAY: " + awayScore;
    }

    private String playHockeyV2(int homeTeamId, int awayTeamId, Integer seasonId) {
        int homeScore = 0, awayScore = 0, period = 1, minutes = 20, seconds = 0;

        double homeChance = 0.075, awayChance = 0.075;

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
                    homeChance = 0.1;
                    awayChance = 0.1;
                }
            }

            System.out.println("HOME: " + homeScore + " AWAY: " + awayScore + " PERIOD: " + period + " " + minutes + ":" + seconds);
        }

        saveGame(homeTeamId, awayTeamId, homeScore, awayScore, seasonId);
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
    
    public void saveGame(int homeTeamId, int awayTeamId, int homeScore, int awayScore, Integer seasonId) {
        Game game = new Game();
        game.setHomeTeamId(homeTeamId);
        game.setAwayTeamId(awayTeamId);
        game.setHomeScore(homeScore);
        game.setAwayScore(awayScore);
        game.setSeasonId(seasonId);
        gameRepository.save(game);
    }

    public void getGames() {
        for (Game game : gameRepository.findAll()) {
            System.out.println(game.getHomeScore() + "-" + game.getAwayScore());
        }
    }
}
