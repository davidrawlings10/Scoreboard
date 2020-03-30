package scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StandingService {
    @Autowired private StandingRepository standingRepository;

    public Standing save(Standing standing) {
        return save(standing.getId(), standing.getSeasonId(), standing.getTeamId(), standing.getWin(), standing.getLoss(),
                standing.getOtloss(), standing.getPoint(), standing.getGf(), standing.getGa());
    }

    public Standing save(Integer id, int seasonId, int teamId, int win, int loss, int otloss, int point, int gf, int ga) {
        Standing standing = new Standing();
        standing.setId(id);
        standing.setSeasonId(seasonId);
        standing.setTeamId(teamId);
        standing.setWin(win);
        standing.setLoss(loss);
        standing.setOtloss(otloss);
        standing.setPoint(point);
        standing.setGf(gf);
        standing.setGa(ga);
        return standingRepository.save(standing);
    }

    public Standing findBySeasonIdAndTeamId(int teamId) {
        return standingRepository.findByTeamId(teamId);
    }
}
