package scoreboard;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeasonServiceTests {

    @Autowired
    private SeasonService seasonService;
    @Autowired
    private GameService gameService;

    // unit tests
    @Test
    public void testHomeRotation() {
        List<Integer> teamIds = Arrays.asList(1, 2, 3, 4);
        List<Game> games = seasonService.scheduleSeasonHomeRotation(teamIds);

        assert(games.size() == teamIds.size() * 3);

        int homeGameCountForTeam1 = 0, awayGameCountForTeam1 = 0;
        for (Game game : games) {
            if (game.getHomeTeamId() == 1) {
                homeGameCountForTeam1++;
            } else if (game.getAwayTeamId() == 1) {
                awayGameCountForTeam1++;
            }
        }

        assert(homeGameCountForTeam1 == awayGameCountForTeam1);
        assert(homeGameCountForTeam1 + awayGameCountForTeam1 == teamIds.size() * 3 / 2);
    }

    @Test
    public void testRounds() {
        List<Integer> teamIds = Arrays.asList(1, 2, 3, 4);

        final int numGames = 4;

        List<Game> games = seasonService.scheduleSeasonRound(teamIds, numGames);

        assert(games.size() == teamIds.size() * numGames / 2);

        int homeGameCountForTeam1 = 0, awayGameCountForTeam1 = 0;
        for (Game game : games) {
            if (game.getHomeTeamId() == 1) {
                homeGameCountForTeam1++;
            } else if (game.getAwayTeamId() == 1) {
                awayGameCountForTeam1++;
            }
        }

        assert(homeGameCountForTeam1 == awayGameCountForTeam1);
        assert(homeGameCountForTeam1 + awayGameCountForTeam1 == numGames);
    }

    // tests with DB
    @Test
    public void testScheduleSeasonHomeRotation() throws Exception {
        List<Integer> teamIds = new ArrayList<>();
        teamIds.add(65);
        teamIds.add(66);
        teamIds.add(67);
        teamIds.add(70);
        teamIds.add(72);
        teamIds.add(74);
        seasonService.scheduleSeason(ScheduleType.HOME_ROTATION, Sport.HOCKEY, League.TEST, teamIds, null, "Unit Test: testScheduleSeasonHomeRotation()");
    }

    @Test
    public void testScheduleSeasonRounds() throws Exception {
        List<Integer> teamIds = new ArrayList<>();
        teamIds.add(71);
        teamIds.add(72);
        teamIds.add(73);
        teamIds.add(76);
        seasonService.scheduleSeason(ScheduleType.ROUNDS, Sport.HOCKEY, League.TEST, teamIds, 6, "Unit Test: testScheduleSeasonRounds()");
    }

    @Test
    public void testGetNextSeasonGame() throws Exception {
        List<Integer> teamIds = new ArrayList<>();
        teamIds.add(65);
        teamIds.add(66);
        teamIds.add(67);
        seasonService.scheduleSeason(ScheduleType.HOME_ROTATION, Sport.HOCKEY, League.TEST, teamIds, null, "Unit Test: testGetNextSeasonGame()");
        Game game = gameService.getNextSeasonGame(1);
        assert(game != null);
    }
}
