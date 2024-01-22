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
        List<SportEvent> sportEvents = new ArrayList<>();

        if (sport.equals(Sport.HOCKEY)) {
            sportInfo.setENDING_PERIOD(3);
            sportInfo.setMINUTES_IN_PERIOD(20);
            sportInfo.setMINUTES_IN_OVERTIME(20);
            sportInfo.setMINUTES_IN_INTERMISSION(20);
            sportInfo.setMINUTES_IN_INTERMISSION_BEFORE_OVERTIME(5);
            sportInfo.setMIN_POSSESSION_SECONDS(5);
            sportInfo.setMAX_POSSESSION_SECONDS(120);
            sportInfo.setSuddenDeathOvertime(true);
            sportInfo.setHomeAwayChangeAdjustment(1.0 / 15.0); // 0.06666666667
            sportInfo.setTotalScoreCalibration(1.1);
            sportEvents.add(new SportEvent(1, 2.7, EventType.HOCKEY_GOAL, sportInfo));
        } else if (sport.equals(Sport.BASKETBALL)) {
            sportInfo.setENDING_PERIOD(2);
            sportInfo.setMINUTES_IN_PERIOD(20);
            sportInfo.setMINUTES_IN_OVERTIME(5);
            sportInfo.setMINUTES_IN_INTERMISSION(20);
            sportInfo.setMINUTES_IN_INTERMISSION_BEFORE_OVERTIME(5);
            sportInfo.setMIN_POSSESSION_SECONDS(5);
            sportInfo.setMAX_POSSESSION_SECONDS(45);
            sportInfo.setSuddenDeathOvertime(false);
            sportInfo.setHomeAwayChangeAdjustment(1.0 / 70.0); // 0.01428571428
            sportInfo.setTotalScoreCalibration(1.11);
            sportEvents.add(new SportEvent(1, 4.0, EventType.BASKETBALL_FREE_THROW_1_MADE, sportInfo));
            sportEvents.add(new SportEvent(2, 10.0, EventType.BASKETBALL_FREE_THROW_2_MADE, sportInfo));
            sportEvents.add(new SportEvent(2, 36.0, EventType.BASKETBALL_TWO_POINTER, sportInfo));
            sportEvents.add(new SportEvent(3, 24.0, EventType.BASKETBALL_THREE_POINTER, sportInfo));
        }

        sportInfo.setSportEvents(sportEvents);
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