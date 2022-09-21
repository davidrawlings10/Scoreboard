package scoreboard;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Standing {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(insertable = false, updatable = false)
    private Timestamp created, updated;
    private Integer seasonId, teamId, win, loss, tie, otloss, point, gf, ga, gp,
                    homeWin, homeLoss, homeTie, homeOtloss, homePoint, homeGp,
                    awayWin, awayLoss, awayTie, awayOtloss, awayPoint, awayGp;

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

    public void incWin(int val) {
        this.win += val;
    }

    public void incLoss(int val) {
        this.loss += val;
    }

    public void incTie(int val) {
        this.tie += val;
    }

    public void incOtloss(int val) {
        this.otloss += val;
    }

    public void incPoint(int val) {
        this.point += val;
    }

    public void incGf(int val) {
        this.gf += val;
    }

    public void incGa(int val) {
        this.ga += val;
    }

    public void incGp(int val) {
        this.gp += val;
    }

    public void incHomeWin(int val) {
        this.homeWin += val;
    }

    public void incHomeLoss(int val) {
        this.homeLoss += val;
    }

    public void incHomeTie(int val) {
        this.homeTie += val;
    }

    public void incHomeOtloss(int val) {
        this.homeOtloss += val;
    }

    public void incHomePoint(int val) {
        this.homePoint += val;
    }

    public void incHomeGp(int val) {
        this.homeGp += val;
    }

    public void incAwayWin(int val) {
        this.awayWin += val;
    }

    public void incAwayLoss(int val) {
        this.awayLoss += val;
    }

    public void incAwayTie(int val) {
        this.awayTie += val;
    }

    public void incAwayOtloss(int val) {
        this.awayOtloss += val;
    }

    public void incAwayPoint(int val) {
        this.awayPoint += val;
    }

    public void incAwayGp(int val) {
        this.awayGp += val;
    }
}
