package scoreboard;

import javax.persistence.*;

@Entity
public class GameEvent {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private Integer gameId, teamId, homeScore, awayScore, period, minutes, seconds;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    public GameEvent() {}

    public GameEvent(Game game, boolean isHomeTeam) {
        this.setGameId(game.getId());
        this.setEventType(EventType.SCORE);
        this.setTeamId(isHomeTeam ? game.getHomeTeamId() : game.getAwayTeamId());
        this.setHomeScore(game.getHomeScore());
        this.setAwayScore(game.getAwayScore());
        this.setPeriod(game.getClock().getPeriod());
        this.setMinutes(game.getClock().getMinutes());
        this.setSeconds(game.getClock().getSeconds());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
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

    public Integer getPeriod() {
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

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
