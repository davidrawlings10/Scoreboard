package scoreboard;

/* import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper; */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import java.util.ArrayList;
import java.util.List;

@Service
public class StandingService {

    @Autowired private StandingRepository standingRepository;

    // data access

    public Standing save(Standing standing) {
        return save(standing.getId(), standing.getSeasonId(), standing.getTeamId(), standing.getWin(), standing.getLoss(),
                standing.getTie(), standing.getOtloss(), standing.getPoint(), standing.getGf(), standing.getGa(), standing.getGp(),
                standing.getHomeWin(), standing.getHomeLoss(), standing.getHomeTie(), standing.getHomeOtloss(), standing.getHomePoint(), standing.getHomeGp(),
                standing.getAwayWin(), standing.getAwayLoss(), standing.getAwayTie(), standing.getAwayOtloss(), standing.getAwayPoint(), standing.getAwayGp());
    }

    public Standing save(Integer id, int seasonId, int teamId, int win, int loss, int tie, int otloss, int point, int gf, int ga, int gp,
                         int homeWin, int homeLoss, int homeTie, int homeOtloss, int homePoint, int homeGp,
                         int awayWin, int awayLoss, int awayTie, int awayOtloss, int awayPoint, int awayGp) {
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
        standing.setHomeTie(homeTie);
        standing.setHomeOtloss(homeOtloss);
        standing.setHomePoint(homePoint);
        standing.setHomeGp(homeGp);
        standing.setAwayWin(awayWin);
        standing.setAwayLoss(awayLoss);
        standing.setAwayTie(awayTie);
        standing.setAwayOtloss(awayOtloss);
        standing.setAwayPoint(awayPoint);
        standing.setAwayGp(awayGp);
        return standingRepository.save(standing);
    }

    public void updateStanding(Game game) {
        Standing homeTeamStanding = findBySeasonIdAndTeamId(game.getSeasonId(), game.getHomeTeamId());
        Standing awayTeamStanding = findBySeasonIdAndTeamId(game.getSeasonId(), game.getAwayTeamId());

        homeTeamStanding.incGp(1);
        awayTeamStanding.incGp(1);
        homeTeamStanding.incHomeGp(1);
        awayTeamStanding.incAwayGp(1);

        // !!! refactor all this in a separate function
        if (game.getHomeScore() > game.getAwayScore()) {
            homeTeamStanding.incWin(1);
            homeTeamStanding.incHomeWin(1);
            homeTeamStanding.incPoint(2);
            homeTeamStanding.incHomePoint(2);

            if (game.getEndingPeriod() < 4) {
                awayTeamStanding.incLoss(1);
                awayTeamStanding.incAwayLoss(1);
            } else {
                awayTeamStanding.incOtloss(1);
                awayTeamStanding.incAwayOtloss(1);
                awayTeamStanding.incPoint(1);
                awayTeamStanding.incAwayPoint(1);
            }
        } else {
            awayTeamStanding.incWin(1);
            awayTeamStanding.incAwayWin(1);
            awayTeamStanding.incPoint(2);
            awayTeamStanding.incAwayPoint(2);

            if (game.getEndingPeriod() < 4) {
                homeTeamStanding.incLoss(1);
                homeTeamStanding.incHomeLoss(1);
            } else {
                homeTeamStanding.incOtloss(1);
                homeTeamStanding.incHomeOtloss(1);
                homeTeamStanding.incPoint(1);
                homeTeamStanding.incHomePoint(1);
            }
        }

        homeTeamStanding.incGf(game.getHomeScore());
        homeTeamStanding.incGa(game.getAwayScore());
        awayTeamStanding.incGf(game.getAwayScore());
        awayTeamStanding.incGa(game.getHomeScore());

        standingRepository.save(homeTeamStanding);
        standingRepository.save(awayTeamStanding);
    }

    public Standing findBySeasonIdAndTeamId(int seasonId, int teamId) {
        return standingRepository.findBySeasonIdAndTeamId(seasonId, teamId);
    }

    public List<Standing> findBySeasonId(int seasonId) {
        return standingRepository.findBySeasonId(seasonId);
    }

    public String getInsertSQL(int seasonId) {
        List<Standing> standings = standingRepository.findBySeasonId(seasonId);
        StringBuilder sb = new StringBuilder("INSERT INTO standing VALUES ");
        for (Standing standing : standings) {
            sb.append(String.format("(%d, \"%s\", \"%s\", %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d),",
                    standing.getId(), standing.getCreated(), standing.getUpdated(), standing.getSeasonId(), standing.getTeamId(), standing.getWin(), standing.getLoss(), standing.getTie(), standing.getOtloss(), standing.getPoint(), standing.getGf(), standing.getGa(), standing.getGp(),
                    standing.getHomeWin(), standing.getHomeLoss(), standing.getHomeTie(), standing.getHomeOtloss(), standing.getHomePoint(), standing.getHomeGp(),
                    standing.getAwayWin(), standing.getAwayLoss(), standing.getAwayTie(), standing.getAwayOtloss(), standing.getAwayPoint(), standing.getAwayGp()));
        };
        sb.replace(sb.length() - 1, sb.length(), ";");
        return sb.toString();
    }

    /*private class StandingList {
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
    }*/
}
