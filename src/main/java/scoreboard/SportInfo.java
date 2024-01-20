package scoreboard;

import java.util.List;

public class SportInfo {
    List<SportEvent> sportEvents;
    private int ENDING_PERIOD, MINUTES_IN_PERIOD, MINUTES_IN_OVERTIME,
            MINUTES_IN_INTERMISSION, MINUTES_IN_INTERMISSION_BEFORE_OVERTIME,
    MIN_POSSESSION_SECONDS, MAX_POSSESSION_SECONDS;

    private boolean suddenDeathOvertime;

    private double homeAwayChangeAdjustment;

    public List<SportEvent> getSportEvents() {
        return sportEvents;
    }
    public void setSportEvents(List<SportEvent> sportEvents) {
        this.sportEvents = sportEvents;
    }

    public int getENDING_PERIOD() {
        return ENDING_PERIOD;
    }

    public void setENDING_PERIOD(int ENDING_PERIOD) {
        this.ENDING_PERIOD = ENDING_PERIOD;
    }

    public int getMINUTES_IN_PERIOD() {
        return MINUTES_IN_PERIOD;
    }

    public void setMINUTES_IN_PERIOD(int MINUTES_IN_PERIOD) {
        this.MINUTES_IN_PERIOD = MINUTES_IN_PERIOD;
    }

    public int getMINUTES_IN_OVERTIME() {
        return MINUTES_IN_OVERTIME;
    }

    public void setMINUTES_IN_OVERTIME(int MINUTES_IN_OVERTIME) {
        this.MINUTES_IN_OVERTIME = MINUTES_IN_OVERTIME;
    }

    public int getMINUTES_IN_INTERMISSION() {
        return MINUTES_IN_INTERMISSION;
    }

    public void setMINUTES_IN_INTERMISSION(int MINUTES_IN_INTERMISSION) {
        this.MINUTES_IN_INTERMISSION = MINUTES_IN_INTERMISSION;
    }

    public int getMINUTES_IN_INTERMISSION_BEFORE_OVERTIME() {
        return MINUTES_IN_INTERMISSION_BEFORE_OVERTIME;
    }

    public void setMINUTES_IN_INTERMISSION_BEFORE_OVERTIME(int MINUTES_IN_INTERMISSION_BEFORE_OVERTIME) {
        this.MINUTES_IN_INTERMISSION_BEFORE_OVERTIME = MINUTES_IN_INTERMISSION_BEFORE_OVERTIME;
    }

    public int getMIN_POSSESSION_SECONDS() {
        return MIN_POSSESSION_SECONDS;
    }

    public void setMIN_POSSESSION_SECONDS(int MIN_POSSESSION_SECONDS) {
        this.MIN_POSSESSION_SECONDS = MIN_POSSESSION_SECONDS;
    }

    public int getMAX_POSSESSION_SECONDS() {
        return MAX_POSSESSION_SECONDS;
    }

    public void setMAX_POSSESSION_SECONDS(int MAX_POSSESSION_SECONDS) {
        this.MAX_POSSESSION_SECONDS = MAX_POSSESSION_SECONDS;
    }

    public boolean isSuddenDeathOvertime() {
        return suddenDeathOvertime;
    }

    public void setSuddenDeathOvertime(boolean suddenDeathOvertime) {
        this.suddenDeathOvertime = suddenDeathOvertime;
    }

    public double getHomeAwayChangeAdjustment() {
        return homeAwayChangeAdjustment;
    }

    public void setHomeAwayChangeAdjustment(double homeAwayChangeAdjustment) {
        this.homeAwayChangeAdjustment = homeAwayChangeAdjustment;
    }
}

