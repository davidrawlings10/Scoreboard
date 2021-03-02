package scoreboard;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Game {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private Integer sportId, seasonId, homeTeamId, awayTeamId, homeScore, awayScore, endingPeriod;

    @Transient Clock clock;

    public Game() {
        this.clock = new Clock(1);
    }

    public Game(int sportId) {
        this.sportId = sportId;
        this.clock = new Clock(sportId);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSportId() {
        return sportId;
    }

    public void setSportId(Integer sportId) {
        this.sportId = sportId;
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

    public Clock getClock() {
        return clock;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }
}