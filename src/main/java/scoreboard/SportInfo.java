package scoreboard;

import java.util.List;

public class SportInfo {
    List<SportEvent> sportEvents;

    // Clock clock;
    private int ENDING_PERIOD, MINUTES_IN_PERIOD, MINUTES_IN_OVERTIME, MINUTES_IN_INTERMISSION, MINUTES_IN_INTERMISSION_BEFORE_OVERTIME;

    public List<SportEvent> getSportEvents() {
        return sportEvents;
    }
    public void setSportEvents(List<SportEvent> sportEvents) {
        this.sportEvents = sportEvents;
    }

    /*public Clock getClock() {
        return clock;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }*/

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
}

