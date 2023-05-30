package scoreboard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClockTests {

    @Autowired
    private GameService gameService;
    @Autowired
    private PlayService playService;

    @Test
    public void testClockInit() throws Exception {
        Game game = new Game(Sport.HOCKEY, 1, 2);
        gameService.setupGameForPlay(game);

        assert(game.getClock().getMinutes().equals(20));
        assert(game.getClock().getSeconds().equals(0));
        assert(game.getClock().getPeriod().equals(1));
        assert(game.getClock().getIntermission());
    }

    @Test
    public void testTickOneSec() throws Exception {
        Game game = new Game(Sport.HOCKEY, 1, 2);
        gameService.setupGameForPlay(game);
        playService.playSec(game);

        assert(game.getClock().getMinutes().equals(19));
        assert(game.getClock().getSeconds().equals(59));
        assert(game.getClock().getPeriod().equals(1));
        assert(game.getClock().getIntermission());
    }

    @Test
    public void testTickOneMinute() throws Exception {
        Game game = new Game(Sport.HOCKEY, 1, 2);
        gameService.setupGameForPlay(game);

        for (int i = 0; i < 60; ++i) {
            playService.playSec(game);
        }
        assert(game.getClock().getMinutes().equals(19));
        assert(game.getClock().getSeconds().equals(0));
        assert(game.getClock().getPeriod().equals(1));
        assert(game.getClock().getIntermission());
    }

    @Test
    public void testTickToEndOfIntermission() throws Exception {
        Game game = new Game(Sport.HOCKEY, 1, 2);
        gameService.setupGameForPlay(game);

        for (int i = 0; i < 60 * 20; ++i) {
            playService.playSec(game);
        }
        assert(game.getClock().getMinutes().equals(20));
        assert(game.getClock().getSeconds().equals(0));
        assert(game.getClock().getPeriod().equals(1));
        assert(!game.getClock().getIntermission());
    }

    @Test
    public void testTickToEndOfPeriod() throws Exception {
        Game game = new Game(Sport.HOCKEY, 1, 2);
        gameService.setupGameForPlay(game);

        for (int i = 0; i < 60 * 20 * 2; ++i) {
            playService.playSec(game);
        }
        assert(game.getClock().getMinutes().equals(20));
        assert(game.getClock().getSeconds().equals(0));
        assert(game.getClock().getPeriod().equals(2));
        assert(game.getClock().getIntermission());
    }
}
