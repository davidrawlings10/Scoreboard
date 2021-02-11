package scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class HockeyPlayService {
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
            // public final static int gameplayTickMilli = 1000, shootoutSec = 40, intermissionSec = 1020; // standard (season 1, 4 teams)
            // public final static int gameplayTickMilli = 100, shootoutSec = 15, intermissionSec = 120; // accelerated (season 1, 4 teams)
            // public final static int gameplayTickMilli = 130, shootoutSec = 15, intermissionSec = 30; // accelerated (season 2, 8 teams)
            // public final static int gameplayTickMilli = 50, shootoutSec = 12, intermissionSec = 12; // accelerated (season 3, 16 teams)
            // public final static int gameplayTickMilli = 1000, shootoutSec = 40, intermissionSec = 1020; // standard (season 4, 8 teams)
            // public final static int gameplayTickMilli = 50, shootoutSec = 12, intermissionSec = 12; // accelerated (32 teams) HAVEN'T PLAYED
            public final static int gameplayTickMilli = 0, shootoutSec = 0, intermissionSec = 0; // immediate
        }
    }

    public Game playGame(Game game) throws InterruptedException {
        Clock clock = new Clock();
        clock.setPeriod(1);
        clock.setMinutes(20);

        // int homeScore = 0, awayScore = 0; `1

        double homeChance = Config.Chance.regulationScore + Config.Chance.regulationScoreHomeWeight, awayChance = Config.Chance.regulationScore + Config.Chance.regulationScoreAwayWeight;

        homeTeamName = getByTeamId(game.getHomeTeamId()).getName();
        awayTeamName = getByTeamId(game.getAwayTeamId()).getName();

        System.out.println(printScoreboard(game.getHomeScore(), game.getAwayScore(), clock.getPeriod(), clock.getMinutes(), clock.getSeconds(), false, clock.isIntermission()));

        while (true) {

            TimeUnit.MILLISECONDS.sleep(Config.TimeDelay.gameplayTickMilli);

            if (clock.getPeriod() == 5 && !clock.isIntermission()) {
                if (shootout()) {
                    game.setHomeScore(game.getHomeScore() + 1);
                } else {
                    game.setAwayScore(game.getAwayScore() + 1);
                }
                break;
            }

            // total GA (goals for) in 18-19 NHL regular season was 7664
            // games played in 18-19 NHL regular season was 82 * 31 = 2542
            // 7664 / 2542 = 3.014 goals a game
            // considering overtime goals rounding down to .9 goals a period
            // .9 / 20 = .045 average goals per minutes
            // .045 / 60 = .00075 average goals per second

            if (!clock.isIntermission() && RandomService.occur(homeChance)) {
                game.setHomeScore(game.getHomeScore() + 1);
                if (clock.getPeriod() == 4)
                    break;
            }
            if (!clock.isIntermission() && RandomService.occur(awayChance)) {
                game.setAwayScore(game.getAwayScore() + 1);
                if (clock.getPeriod() == 4)
                    break;
            }

            clock.tickDown();

            if (clock.isExpired()) {
                // period ends

                System.out.println(printScoreboard(game.getHomeScore(), game.getAwayScore(), clock.getPeriod(), clock.getMinutes(), clock.getSeconds(), false, clock.isIntermission()));

                // end of the game in regulation if scores are different, otherwise period will become 4
                if (clock.getPeriod() == 3 && !clock.isIntermission() && !game.getHomeScore().equals(game.getAwayScore())) {
                    break;
                } else {
                    if (!clock.isIntermission()) {
                        clock.setPeriod(clock.getPeriod() + 1);
                    }
                    clock.setIntermission(!clock.isIntermission());
                }

                // minutes are reset to 5 for overtime and 20 for periods 2 and 3
                clock.setMinutes(clock.getPeriod() > 3 ? 5 : 20);
                clock.setSeconds(0);

                // if overtime is starting update increase the chance of a goal as overtime is played 3 on 3
                if (clock.getPeriod() == 4) {
                    homeChance = Config.Chance.overtimeScore + Config.Chance.overtimeScoreHomeWeight;
                    awayChance = Config.Chance.overtimeScore + Config.Chance.overtimeScoreAwayWeight;
                }
            }

            System.out.println(printScoreboard(game.getHomeScore(), game.getAwayScore(), clock.getPeriod(), clock.getMinutes(), clock.getSeconds(), false, clock.isIntermission()));
        }

        // save(id, homeTeamId, awayTeamId, homeScore, awayScore, seasonId, clock.getPeriod()); `1
        System.out.println(printScoreboard(game.getHomeScore(), game.getAwayScore(), clock.getPeriod(), clock.getMinutes(), clock.getSeconds(), true, clock.isIntermission()));

        return game;
        // return printScoreboard(homeScore, awayScore, clock.getPeriod(), clock.getMinutes(), clock.getSeconds(), true, clock.isIntermission()); `1
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

    private String printScoreboard(int homeScore, int awayScore, int period, int minutes, int seconds, boolean isFinal, boolean isIntermission) {
        return homeTeamName + " | " + homeScore + " | " + awayTeamName +  " | " + awayScore + " | "
                + (isFinal ? "Final" : (isIntermission ? "Intermission" : "Period" ) + " | " + period + " | " + minutes + ":" + seconds);
    }

    private String printScoreboardShootout(int homeShootoutScore, int awayShootoutScore, int shootoutRound) {
        return homeTeamName + " | " + homeShootoutScore + " | " + awayTeamName +  " | " + awayShootoutScore + " | " +
                "Round | " + shootoutRound;
        // return homeTeamName + ": " + homeShootoutScore + " " + awayTeamName + ": " + awayShootoutScore + " SHOOTOUT ROUND: " + shootoutRound; `1
    }

    public Team getByTeamId(int teamId) { return teamService.getByTeamId(teamId); }
}
