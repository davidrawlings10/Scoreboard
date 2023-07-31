package scoreboard;

import javax.persistence.*;
import java.sql.Timestamp;
// import java.time.LocalDateTime;
// import java.time.ZoneOffset;

@Entity // This tells Hibernate to make a table out of this class
public class Game {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(insertable = false, updatable = false)
    private Timestamp created, updated;

    private Integer seasonId, homeTeamId, awayTeamId, homeScore, awayScore, endingPeriod;

    @Enumerated(EnumType.STRING)
    private Sport sport;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Transient Clock clock;

    @Enumerated(EnumType.STRING)
    @Transient TeamAlreadyPlaying teamAlreadyPlaying;

    @Transient int possessionSecondsRemaining;
    @Transient boolean homeHasPossession;

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

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated; //.atOffset("America/Los_Angeles");
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
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

    public TeamAlreadyPlaying getTeamAlreadyPlaying() {
        return teamAlreadyPlaying;
    }

    public void setTeamAlreadyPlaying(TeamAlreadyPlaying teamAlreadyPlaying) {
        this.teamAlreadyPlaying = teamAlreadyPlaying;
    }

    public int getPossessionSecondsRemaining() {
        return possessionSecondsRemaining;
    }

    public void setPossessionSecondsRemaining(int possessionSecondsRemaining) {
        this.possessionSecondsRemaining = possessionSecondsRemaining;
    }

    public boolean isHomeHasPossession() {
        return homeHasPossession;
    }

    public void setHomeHasPossession(boolean homeHasPossession) {
        this.homeHasPossession = homeHasPossession;
    }

    public void decPossessionSecondsRemaining() {
        possessionSecondsRemaining -= 1;
    }

    public int getNextPossessionSeconds() throws Exception {
        SportInfo sportInfo = SportInfoUtil.getSportInfo(this.sport);
        return RandomUtil.getRandom(sportInfo.getMAX_POSSESSION_SECONDS()) + sportInfo.getMIN_POSSESSION_SECONDS();
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

    public boolean isFinal() throws Exception {
        if (homeScore == null || awayScore == null || clock == null)
            return false;

        return  // game ends at the end of the last period or overtime
                !homeScore.equals(awayScore)
                && clock.getPeriod() >= SportInfoUtil.getSportInfo(sport).getENDING_PERIOD()
                && clock.isPeriodEnded() && !clock.getIntermission()
                // game ends in overtime on a sudden death goal
                || !homeScore.equals(awayScore)
                && clock.getPeriod() > SportInfoUtil.getSportInfo(sport).getENDING_PERIOD()
                && SportInfoUtil.getSportInfo(sport).isSuddenDeathOvertime();
    }

    public void incHomeScore(int val) {
        homeScore += val;
    }

    public void incAwayScore(int val) {
        awayScore += val;
    }

    public void incScore(int val, boolean isHomeTeam) {
        if (isHomeTeam) {
            homeScore += val;
        } else {
            awayScore += val;
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public SportInfo getSportInfo() throws Exception {
        return SportInfoUtil.getSportInfo(sport);
    }
}