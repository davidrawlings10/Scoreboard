package scoreboard;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Clock {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(insertable = false, updatable = false)
    private Timestamp created, updated;

    private Integer gameId;
    private Integer minutes, seconds, period;
    private Boolean intermission;

    @Transient
    private int ENDING_PERIOD, MINUTES_IN_PERIOD, MINUTES_IN_OVERTIME, MINUTES_IN_INTERMISSION, MINUTES_IN_INTERMISSION_BEFORE_OVERTIME;

    // default constructor to make querying the database work
    public Clock() {
        ENDING_PERIOD = 1;
        MINUTES_IN_PERIOD = 1;
        MINUTES_IN_OVERTIME = 1;
        MINUTES_IN_INTERMISSION = 1;
        MINUTES_IN_INTERMISSION_BEFORE_OVERTIME = 1;
    }

    public Clock(Sport sport) throws Exception {
        initializeConstants(sport);
        period = 1;
        intermission = true;
    }

    public void initializeConstants(Sport sport) throws Exception {

        /* Clock clock = SportInfoUtil.getSportInfo(sport).getClock();
        ENDING_PERIOD = clock.getENDING_PERIOD();
        MINUTES_IN_PERIOD = clock.getMINUTES_IN_PERIOD();
        MINUTES_IN_OVERTIME = clock.getMINUTES_IN_OVERTIME();
        MINUTES_IN_INTERMISSION = clock.getMINUTES_IN_INTERMISSION();
        MINUTES_IN_INTERMISSION_BEFORE_OVERTIME = clock.getMINUTES_IN_INTERMISSION_BEFORE_OVERTIME();
*/
        switch (sport) {
            case HOCKEY:
                ENDING_PERIOD = 3;
                MINUTES_IN_PERIOD = 20;
                MINUTES_IN_OVERTIME = 20;
                MINUTES_IN_INTERMISSION = 20;
                MINUTES_IN_INTERMISSION_BEFORE_OVERTIME = 5;
                break;
            case BASKETBALL:
                ENDING_PERIOD = 4;
                MINUTES_IN_PERIOD = 12;
                MINUTES_IN_OVERTIME = 5;
                MINUTES_IN_INTERMISSION = 12;
                MINUTES_IN_INTERMISSION_BEFORE_OVERTIME = 5;
                break;
            case FOOTBALL:
                ENDING_PERIOD = 4;
                MINUTES_IN_PERIOD = 15;
                MINUTES_IN_OVERTIME = 15;
                MINUTES_IN_INTERMISSION = 15;
                MINUTES_IN_INTERMISSION_BEFORE_OVERTIME = 5;
                break;
            default:
                ENDING_PERIOD = 1;
                MINUTES_IN_PERIOD = 1;
                MINUTES_IN_OVERTIME = 1;
                MINUTES_IN_INTERMISSION = 1;
                MINUTES_IN_INTERMISSION_BEFORE_OVERTIME = 1;
                break;
        }
    }

    public void reset() {
        seconds = 0;
        if (period <= ENDING_PERIOD) {
            minutes = intermission ? MINUTES_IN_INTERMISSION : MINUTES_IN_PERIOD;
        } else {
            minutes = intermission ? MINUTES_IN_INTERMISSION_BEFORE_OVERTIME : MINUTES_IN_OVERTIME;
        }
    }

    public void tickDown() {
        seconds--;
        if (seconds == -1) {
            minutes--;
            seconds = 59;
        }
    }

    public void handlePeriodEnd() {
        if (isPeriodEnded()) {
            if (!intermission) {
                period += 1;
            }
            intermission = !intermission;
            reset();
        }
    }

    public void tickUp() {
        seconds--;
        if (seconds == 60) {
            minutes--;
            seconds = 0;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public boolean isPeriodEnded() {
        return minutes == 0 && seconds == 0;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Boolean getIntermission() {
        return intermission;
    }

    public void setIntermission(Boolean intermission) {
        this.intermission = intermission;
    }

    public int getENDING_PERIOD() {
        return ENDING_PERIOD;
    }

    public int getMINUTES_IN_PERIOD() {
        return MINUTES_IN_PERIOD;
    }

    public int getMINUTES_IN_OVERTIME() {
        return MINUTES_IN_OVERTIME;
    }

    public int getMINUTES_IN_INTERMISSION() {
        return MINUTES_IN_INTERMISSION;
    }

    public int getMINUTES_IN_INTERMISSION_BEFORE_OVERTIME() {
        return MINUTES_IN_INTERMISSION_BEFORE_OVERTIME;
    }

    public void setENDING_PERIOD(int ENDING_PERIOD) {
        this.ENDING_PERIOD = ENDING_PERIOD;
    }

    public void setMINUTES_IN_PERIOD(int MINUTES_IN_PERIOD) {
        this.MINUTES_IN_PERIOD = MINUTES_IN_PERIOD;
    }

    public void setMINUTES_IN_OVERTIME(int MINUTES_IN_OVERTIME) {
        this.MINUTES_IN_OVERTIME = MINUTES_IN_OVERTIME;
    }

    public void setMINUTES_IN_INTERMISSION(int MINUTES_IN_INTERMISSION) {
        this.MINUTES_IN_INTERMISSION = MINUTES_IN_INTERMISSION;
    }

    public void setMINUTES_IN_INTERMISSION_BEFORE_OVERTIME(int MINUTES_IN_INTERMISSION_BEFORE_OVERTIME) {
        this.MINUTES_IN_INTERMISSION_BEFORE_OVERTIME = MINUTES_IN_INTERMISSION_BEFORE_OVERTIME;
    }
}
