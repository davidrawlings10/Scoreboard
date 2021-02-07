package scoreboard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Game {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id; // in database

    private Integer seasonId, homeTeamId, awayTeamId, homeScore, awayScore, endingPeriod; // in database

    // private Integer period, minutes, seconds; // not in database

    // private boolean isIntermission; // not in database

    // Clock clock = new Clock(); // not in database



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Integer homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Integer getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(Integer awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public Integer getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(Integer homeScore) {
        this.homeScore = homeScore;
    }

    public Integer getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(Integer awayScore) {
        this.awayScore = awayScore;
    }

    public Integer getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }

    public Integer getEndingPeriod() {
        return endingPeriod;
    }

    public void setEndingPeriod(Integer endingPeriod) {
        this.endingPeriod = endingPeriod;
    }

    /*public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
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

    public boolean isIntermission() {
        return isIntermission;
    }

    public void setIntermission(boolean isIntermission) {
        isIntermission = isIntermission;
    }*/
}