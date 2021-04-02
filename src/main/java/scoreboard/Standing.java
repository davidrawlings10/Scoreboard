package scoreboard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Standing {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Integer seasonId, teamId, win, loss, tie, otloss, point, gf, ga, gp,
                    homeWin, homeLoss, homeTie, homeOtloss, homePoint, homeGp,
                    awayWin, awayLoss, awayTie, awayOtloss, awayPoint, awayGp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getWin() {
        return win;
    }

    public void setWin(Integer win) {
        this.win = win;
    }

    public Integer getLoss() {
        return loss;
    }

    public void setLoss(Integer loss) {
        this.loss = loss;
    }

    public Integer getTie() {
        return tie;
    }

    public void setTie(Integer tie) {
        this.tie = tie;
    }

    public Integer getOtloss() {
        return otloss;
    }

    public void setOtloss(Integer otloss) {
        this.otloss = otloss;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getGf() {
        return gf;
    }

    public void setGf(Integer gf) {
        this.gf = gf;
    }

    public Integer getGa() {
        return ga;
    }

    public void setGa(Integer ga) {
        this.ga = ga;
    }

    public Integer getGp() {
        return gp;
    }

    public void setGp(Integer gp) {
        this.gp = gp;
    }

    public Integer getHomeWin() {
        return homeWin;
    }

    public void setHomeWin(Integer homeWin) {
        this.homeWin = homeWin;
    }

    public Integer getHomeLoss() {
        return homeLoss;
    }

    public void setHomeLoss(Integer homeLoss) {
        this.homeLoss = homeLoss;
    }

    public Integer getAwayWin() {
        return awayWin;
    }

    public void setAwayWin(Integer awayWin) {
        this.awayWin = awayWin;
    }

    public Integer getAwayLoss() {
        return awayLoss;
    }

    public void setAwayLoss(Integer awayLoss) {
        this.awayLoss = awayLoss;
    }

    public Integer getHomeTie() {
        return homeTie;
    }

    public void setHomeTie(Integer homeTie) {
        this.homeTie = homeTie;
    }

    public Integer getHomeOtloss() {
        return homeOtloss;
    }

    public void setHomeOtloss(Integer homeOtloss) {
        this.homeOtloss = homeOtloss;
    }

    public Integer getHomePoint() {
        return homePoint;
    }

    public void setHomePoint(Integer homePoint) {
        this.homePoint = homePoint;
    }

    public Integer getAwayTie() {
        return awayTie;
    }

    public void setAwayTie(Integer awayTie) {
        this.awayTie = awayTie;
    }

    public Integer getAwayOtloss() {
        return awayOtloss;
    }

    public void setAwayOtloss(Integer awayOtloss) {
        this.awayOtloss = awayOtloss;
    }

    public Integer getAwayPoint() {
        return awayPoint;
    }

    public void setAwayPoint(Integer awayPoint) {
        this.awayPoint = awayPoint;
    }

    public Integer getHomeGp() {
        return homeGp;
    }

    public void setHomeGp(Integer homeGp) {
        this.homeGp = homeGp;
    }

    public Integer getAwayGp() {
        return awayGp;
    }

    public void setAwayGp(Integer awayGp) {
        this.awayGp = awayGp;
    }
}
