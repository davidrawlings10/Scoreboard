package scoreboard;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/season")
public class SeasonController {

    @Autowired SeasonService seasonService;

    // http://localhost:8080/season/schedule?scheduleType=ROUNDS&sport=HOCKEY&leagueId=1&numGames=4
    // http://localhost:8080/season/schedule?scheduleType=HOME_ROTATION&sport=HOCKEY&leagueId=1
    @CrossOrigin
    @GetMapping(path="/schedule")
    public @ResponseBody String scheduleSeason(@RequestParam String scheduleType, @RequestParam String sport, @RequestParam String leagueId, @RequestParam List<Integer> teamIds, @RequestParam(required = false) String numGames, @RequestParam String title) throws Exception {
        Season season = seasonService.scheduleSeason(ScheduleType.valueOf(scheduleType), Sport.valueOf(sport), Integer.parseInt(leagueId), teamIds, numGames != null ? Integer.parseInt(numGames) : null, title);
        return "Season scheduled, id:" + season.getId();
    }

    @CrossOrigin
    @GetMapping(path="/getSeasons")
    public @ResponseBody String getSeasons() throws JsonProcessingException {
        String response = JsonUtil.getJsonList(seasonService.findAll());
        return response;
    }

    // http://localhost:8080/season/schedulePlay?leagueId=1
    /*@GetMapping(path="/schedulePlay")
    public @ResponseBody String schedulePlaySeason(@RequestParam String leagueId) throws Exception {
        int seasonId = seasonService.scheduleAndPlaySeason(Integer.parseInt(leagueId));
        return "Season played, id:" + seasonId;
    }*/

    // http://localhost:8080/season/play?seasonId=1&numOfGames=8
    /*@CrossOrigin
    @GetMapping(path="/play")
    public @ResponseBody String playSeason(@RequestParam String seasonId, @RequestParam(required=false) String numOfGames) throws InterruptedException {
        Integer numOfGamesInt = (numOfGames != null ? Integer.parseInt(numOfGames) : null);
        seasonService.playSeason(Integer.parseInt(seasonId), numOfGamesInt);
        return "Season games played, id:" + seasonId;
    }*/
}
