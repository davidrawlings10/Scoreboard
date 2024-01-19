package scoreboard;

public class SportEvent {
    private int scoreAmount;
    private double chanceHome;
    private double chanceAway;
    private EventType eventType;

    public SportEvent(int scoreAmount, double pointsPerGame, EventType eventType, SportInfo sportInfo) throws Exception {
        this.scoreAmount = scoreAmount;
        this.eventType = eventType;

        final double homeAwayChanceAdjustment = 1.0 / 15.0;

        final int secondsInGame = sportInfo.getENDING_PERIOD() * sportInfo.getMINUTES_IN_PERIOD() * 60;

        final int averagePossessionSeconds = (sportInfo.getMAX_POSSESSION_SECONDS() - sportInfo.getMIN_POSSESSION_SECONDS()) / 2 + sportInfo.getMIN_POSSESSION_SECONDS();

        this.chanceHome = (pointsPerGame + (pointsPerGame * homeAwayChanceAdjustment)) / scoreAmount / secondsInGame * averagePossessionSeconds * 2 * 100; // give home a little more chance to score
        this.chanceAway = (pointsPerGame - (pointsPerGame * homeAwayChanceAdjustment)) / scoreAmount / secondsInGame * averagePossessionSeconds * 2 * 100; // give away a little less chance to score
    }

    public int getScoreAmount() {
        return scoreAmount;
    }
    public void setScoreAmount(int scoreAmount) {
        this.scoreAmount = scoreAmount;
    }
    public double getChanceHome() {
        return chanceHome;
    }
    public void setChanceHome(double chanceHome) {
        this.chanceHome = chanceHome;
    }
    public double getChanceAway() {
        return chanceAway;
    }
    public void setChanceAway(double chanceAway) {
        this.chanceAway = chanceAway;
    }
    public EventType getEventType() {
        return eventType;
    }
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
