package scoreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SportInfoUtil {

    static Map<Sport, SportInfo> sportInfoCache;

    public static SportInfo getSportInfo(Sport sport) throws Exception {

        if (sportInfoCache == null) {
            sportInfoCache = new HashMap<>();
        }

        SportInfo sportInfo = sportInfoCache.get(sport);
        if (sportInfo == null) {
            sportInfo = buildSportInfo(sport);
            sportInfoCache.put(sport, sportInfo);
        }
        return sportInfo;
    }

    private static SportInfo buildSportInfo(Sport sport) throws Exception {
        SportInfo sportInfo = new SportInfo();
        Clock clock = new Clock();
        List<SportEvent> sportEvents = new ArrayList<>();

        if (sport.equals(Sport.HOCKEY)) {
            clock.setENDING_PERIOD(3);
            clock.setMINUTES_IN_PERIOD(20);
            clock.setMINUTES_IN_OVERTIME(20);
            clock.setMINUTES_IN_INTERMISSION(20);
            clock.setMINUTES_IN_INTERMISSION_BEFORE_OVERTIME(5);
            sportEvents.add(new SportEvent(1, 2.7, EventType.SCORE, clock));
        } else if (sport.equals(Sport.BASKETBALL)) {
            clock.setENDING_PERIOD(2);
            clock.setMINUTES_IN_PERIOD(20);
            clock.setMINUTES_IN_OVERTIME(5);
            clock.setMINUTES_IN_INTERMISSION(20);
            clock.setMINUTES_IN_INTERMISSION_BEFORE_OVERTIME(5);
            sportEvents.add(new SportEvent(1, 4.0, EventType.BASKETBALL_FREE_THROW, clock));
        }

        sportInfo.setSportEvents(sportEvents);
        sportInfo.setClock(clock);
        return sportInfo;
    }

    // HOCKEY
    // total GA (goals for) in 18-19 NHL regular season was 7664
    // games played in 18-19 NHL regular season was 82 * 31 = 2542
    // 7664 / 2542 = 3.014 goals a game
    // considering overtime goals rounding down to .9 goals a period
    // .9 / 20 = .045 average goals per minutes
    // .045 / 60 = .00075 average goals per second
}