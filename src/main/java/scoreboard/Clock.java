package scoreboard;

public class Clock {
    private int minutes, seconds, period;
    boolean isIntermission = true;

    /*public Clock() {

    }*/

    public void tickDown() {
        seconds--;
        if (seconds == -1) {
            minutes--;
            seconds = 59;
        }
    }

    public void tickUp() {
        seconds--;
        if (seconds == 60) {
            minutes--;
            seconds = 0;
        }
    }

    public boolean isExpired() {
        return minutes == 0 && seconds == 0;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public boolean isIntermission() {
        return isIntermission;
    }

    public void setIntermission(boolean isIntermission) {
        this.isIntermission = isIntermission;
    }
}
