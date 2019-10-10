package scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameService {

    @Autowired private UserRepository userRepository;

    public String playGame(int homeTeamId, int awayTeamId, Sport sport, Integer seasonId) {
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

            // average goals per period .869
            // .869 / 20 = .04345 average goals per minutes
            // .04345 / 60 = .00072416 average goals per second

            if (RandomService.occur(0.072416)) {
                homeScore++;
                if (period == 4)
                    break;
            }
            if (RandomService.occur(0.072416)) {
                awayScore++;
                if (period == 4)
                    break;
            }

            seconds--;

            if (seconds == -1) {
                minutes--;
                seconds = 59;
            } else if (seconds == 0 && minutes == 0) {
                System.out.println("HOME: " + homeScore + " AWAY: " + awayScore + " PERIOD: " + period + " " + minutes + ":" + seconds);
                //TimeUnit.SECONDS.sleep(60);

                if (period == 3 && homeScore != awayScore) {
                    break;
                }

                period++;
                minutes = period == 4 ? 5 : 20;
                seconds = 0;
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
        userRepository.save(game);
    }
}
