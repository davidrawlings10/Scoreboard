package scoreboard;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomService {
    public static boolean decide(double weight) {
        Random rand = new Random();
        final int MULTIPLIER = 10000000;
        int weightInt = (int) (weight * MULTIPLIER);
        int random = rand.nextInt(MULTIPLIER * 100);
        //System.out.println("random: "+random + ", weight: "+weight);
        return random < weightInt;
    }
}