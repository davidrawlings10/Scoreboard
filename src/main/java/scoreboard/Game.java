package scoreboard;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Game {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private Integer seasonId, homeTeamId, awayTeamId, homeScore, awayScore, endingPeriod;

    @Enumerated(EnumType.STRING)
    // @Transient
    private Sport sport;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Transient Clock clock;

    // caching
    @Transient String homeLocation, awayLocation, homeName, awayName;

    public Game() {}

    public Game(Sport sport, int homeTeamId, int awayTeamId) {
        this.sport = sport;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
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

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getAwayName() {
        return awayName;
    }

    public void setAwayName(String awayName) {
        this.awayName = awayName;
    }

    public String getHomeLocation() {
        return homeLocation;
    }

    public void setHomeLocation(String homeLocation) {
        this.homeLocation = homeLocation;
    }

    public String getAwayLocation() {
        return awayLocation;
    }

    public void setAwayLocation(String awayLocation) {
        this.awayLocation = awayLocation;
    }

    public boolean isFinal() {
        if (homeScore == null || awayScore == null || clock == null)
            return false;

        return !homeScore.equals(awayScore) && clock.getPeriod() == clock.getENDING_PERIOD() && clock.isPeriodEnded() && !clock.isIntermission // game ends in regulation
                || !homeScore.equals(awayScore) && clock.getPeriod() > clock.getENDING_PERIOD(); // game ends in overtime
    }

    public void incHomeScore(int val) {
        homeScore += val;
    }

    public void incAwayScore(int val) {
        awayScore += val;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}