package scoreboard;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandingService {

    @Autowired private StandingRepository standingRepository;

    // data access

    public Standing save(Standing standing) {
        return save(standing.getId(), standing.getSeasonId(), standing.getTeamId(), standing.getWin(), standing.getLoss(),
                standing.getTie(), standing.getOtloss(), standing.getPoint(), standing.getGf(), standing.getGa(), standing.getGp(),
                standing.getHomeWin(), standing.getHomeLoss(), standing.getAwayWin(), standing.getAwayLoss());
    }

    public Standing save(Integer id, int seasonId, int teamId, int win, int loss, int tie, int otloss, int point, int gf, int ga, int gp,
                         int homeWin, int homeLoss, int awayWin, int awayLoss) {
        Standing standing = new Standing();
        standing.setId(id);
        standing.setSeasonId(seasonId);
        standing.setTeamId(teamId);
        standing.setWin(win);
        standing.setLoss(loss);
        standing.setTie(tie);
        standing.setOtloss(otloss);
        standing.setPoint(point);
        standing.setGf(gf);
        standing.setGa(ga);
        standing.setGp(gp);
        standing.setHomeWin(homeWin);
        standing.setHomeLoss(homeLoss);
        standing.setAwayWin(awayWin);
        standing.setAwayLoss(awayLoss);
        return standingRepository.save(standing);
    }

    public Standing findBySeasonIdAndTeamId(int seasonId, int teamId) {
        return standingRepository.findBySeasonIdAndTeamId(seasonId, teamId);
    }

    public List<Standing> findBySeasonId(int seasonId) {
        return standingRepository.findBySeasonId(seasonId);
    }

    public String getSeasonListJSON(List<Standing> standings) {
        return "{\"standingList\":[{\"id\":2,\"seasonId\":1,\"teamId\":\"Hummingbird\",\"win\":6,\"loss\":2,\"tie\":0,\"otloss\":0,\"point\":12,\"gf\":24,\"ga\":15,\"gp\":8,\"homeWin\":6,\"homeLoss\":1,\"awayWin\":0,\"awayLoss\":1},{\"id\":10,\"seasonId\":1,\"teamId\":2,\"win\":2,\"loss\":2,\"tie\":0,\"otloss\":0,\"point\":4,\"gf\":11,\"ga\":12,\"gp\":4,\"homeWin\":2,\"homeLoss\":1,\"awayWin\":0,\"awayLoss\":1},{\"id\":18,\"seasonId\":1,\"teamId\":3,\"win\":1,\"loss\":0,\"tie\":0,\"otloss\":1,\"point\":3,\"gf\":5,\"ga\":5,\"gp\":2,\"homeWin\":0,\"homeLoss\":0,\"awayWin\":1,\"awayLoss\":1},{\"id\":26,\"seasonId\":1,\"teamId\":4,\"win\":1,\"loss\":1,\"tie\":0,\"otloss\":0,\"point\":2,\"gf\":5,\"ga\":4,\"gp\":2,\"homeWin\":0,\"homeLoss\":0,\"awayWin\":1,\"awayLoss\":1},{\"id\":34,\"seasonId\":1,\"teamId\":5,\"win\":0,\"loss\":1,\"tie\":0,\"otloss\":0,\"point\":0,\"gf\":1,\"ga\":2,\"gp\":1,\"homeWin\":0,\"homeLoss\":0,\"awayWin\":0,\"awayLoss\":1},{\"id\":42,\"seasonId\":1,\"teamId\":6,\"win\":0,\"loss\":1,\"tie\":0,\"otloss\":0,\"point\":0,\"gf\":3,\"ga\":4,\"gp\":1,\"homeWin\":0,\"homeLoss\":0,\"awayWin\":0,\"awayLoss\":1},{\"id\":50,\"seasonId\":1,\"teamId\":7,\"win\":0,\"loss\":1,\"tie\":0,\"otloss\":0,\"point\":0,\"gf\":0,\"ga\":3,\"gp\":1,\"homeWin\":0,\"homeLoss\":0,\"awayWin\":0,\"awayLoss\":1},{\"id\":58,\"seasonId\":1,\"teamId\":8,\"win\":0,\"loss\":1,\"tie\":0,\"otloss\":0,\"point\":0,\"gf\":0,\"ga\":4,\"gp\":1,\"homeWin\":0,\"homeLoss\":0,\"awayWin\":0,\"awayLoss\":1}]}";
    }
}
