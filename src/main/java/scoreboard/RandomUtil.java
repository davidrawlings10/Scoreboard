package scoreboard;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RandomUtil {
    public static boolean occur(double chance) {
        Random rand = new Random();
        final int MULTIPLIER = 10000000;
        int weightInt = (int) (chance * MULTIPLIER);
        int random = rand.nextInt(MULTIPLIER * 100);
        //System.out.println("random: "+random + ", weight: "+weight);
        return random < weightInt;
    }

    public static int getRandom(int bound) {
        Random rand = new Random();
        return rand.nextInt(bound);
    }

    public static SportEvent getRandomSportEvent(Game game) throws Exception {
        final int bound = 1000000;
        int random = getRandom(bound);
        int n = 0;
        for (SportEvent sportEvent : SportInfoUtil.getSportInfo(game.getSport()).getSportEvents()) {
            n += bound * (game.homeHasPossession ? sportEvent.getChanceHome() : sportEvent.getChanceAway()) * .01;
            if (n > random) {
                return sportEvent;
            }
        }
        return null;
    }
}