package scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import java.util.ArrayList; `1
// import java.util.List;
// import java.util.Optional;

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
}
