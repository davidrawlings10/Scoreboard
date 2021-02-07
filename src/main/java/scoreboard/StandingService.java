package scoreboard;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    private class StandingList {
        List<Standing> standings;
        public StandingList(List<Standing> standings) {
            this.standings = standings;
        }
    }

    public String getSeasonListJSON(List<Standing> standings) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String standingsJSON = objectMapper.writeValueAsString(standings);

        // List<StandingResponse> standingResponseList = new ArrayList<>();

        return "{\"standingList\":" + standingsJSON + "}";
    }
}
