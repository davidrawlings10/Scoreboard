package scoreboard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameServiceTests {

    @Autowired
    private GameService gameService;

    // unit tests
    // @Test

    // tests with DB
    @Test
    public void testStartGame() throws Exception {
        gameService.startGame(Sport.HOCKEY, 65, 66);
    }

    @Test
    public void testSaveUnplayedGame() throws Exception {
        Game game = new Game(Sport.HOCKEY, 65, 66);
        gameService.save(game);
    }

    @Test
    public void testSavePlayedGame() throws Exception {
        Game game = new Game(Sport.HOCKEY, 65, 66);
        game.setHomeScore(3);
        game.setAwayScore(2);
        game.setEndingPeriod(3);
        game.setTest(true);
        gameService.save(game);
    }
}
