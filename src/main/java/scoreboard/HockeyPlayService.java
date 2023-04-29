package scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class HockeyPlayService {

    /* public boolean playSec(Game game) {
        if (game.isFinal()) {
            game.setStatus(Status.FINAL);
            return true;
        }

        double homeChance = 1 + 1, awayChance = 1 + 1;

        if (!game.getClock().getIntermission()) {
            if (RandomUtil.occur(homeChance)) {
                incHomeScore(game, 1);
                gameService.save(game);
                clockService.save(game.getClock());
            }
            if (RandomUtil.occur(awayChance)) {
                incAwayScore(game, 1);
                gameService.save(game);
                clockService.save(game.getClock());
            }
        }

        game.getClock().tickDown();

        // save the game every minute
        if (game.getClock().getSeconds() == 0) {
            clockService.save(game.getClock());
        }

        if (game.isFinal()) {
            game.setStatus(Status.FINAL);
            return true;
        }

        game.getClock().handlePeriodEnd();

        System.out.println(printScoreboard(game));

        return false;
    } */
}
