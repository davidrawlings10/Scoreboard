package scoreboard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeasonServiceTests {

    @Autowired
    private SeasonService seasonService;

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
}
