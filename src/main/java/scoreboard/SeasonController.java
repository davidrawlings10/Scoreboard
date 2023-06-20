package scoreboard;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path="/season")
public class SeasonController {

    @Autowired SeasonService seasonService;
    @Autowired StandingService standingService;

    // http://localhost:8080/season/schedule?scheduleType=ROUNDS&sport=HOCKEY&leagueId=1&numGames=4
    // http://localhost:8080/season/schedule?scheduleType=HOME_ROTATION&sport=HOCKEY&leagueId=1
    @CrossOrigin
    @GetMapping(path="/schedule")
    public @ResponseBody String scheduleSeason(@RequestParam String scheduleType, @RequestParam String sport, @RequestParam String league, @RequestParam List<Integer> teamIds, @RequestParam(required = false) String numGames, @RequestParam String title) throws Exception {
        Season season = seasonService.scheduleSeason(ScheduleType.valueOf(scheduleType), Sport.valueOf(sport), League.valueOf(league), teamIds, numGames != null ? Integer.parseInt(numGames) : null, title);
        return "Season scheduled, id:" + season.getId();
    }

    @CrossOrigin
    @GetMapping(path="/getSeasons")
    public @ResponseBody String getSeasons() throws JsonProcessingException {
        String response = JsonUtil.getJsonList(seasonService.findAll());
        return response;
    }

    // http://localhost:8080/season/findById?seasonId=7
    @CrossOrigin
    @GetMapping(path="/findById")
    public @ResponseBody String findById(@RequestParam String seasonId) throws JsonProcessingException {
        String response = JsonUtil.getJson(seasonService.findById(Integer.parseInt(seasonId)));
        return response;
    }

    // http://localhost:8080/season/update?seasonId=13&summary=abcyo
    @CrossOrigin
    @GetMapping(path="/update")
    public @ResponseBody String update(@RequestParam String seasonId, @RequestParam String title, @RequestParam String winnerTeamId, @RequestParam String summary) {
        Season season = seasonService.update(Integer.parseInt(seasonId), title, !winnerTeamId.equals("null") ? Integer.parseInt(winnerTeamId) : null, !summary.equals("null") ? summary : null);
        return season.toString();
    }

    // http://localhost:8080/season/getLeagues
    @CrossOrigin
    @GetMapping(path="/getLeagues")
    public @ResponseBody String getLeagues() throws JsonProcessingException {
        String response = JsonUtil.getJsonList(League.values());
        return response;
    }

    // http://localhost:8080/season/getSQL?seasonId=1
    @CrossOrigin
    @GetMapping(path="/getFullSQL")
    public @ResponseBody String getSQL() {
        String response = seasonService.getCompleteInsertSQL();
        return response;
    }

    // http://localhost:8080/season/getSQL?seasonId=1
    @CrossOrigin
    @GetMapping(path="/getSQL")
    public @ResponseBody String getSQL(@RequestParam String seasonId) {
        String response = seasonService.getCompleteInsertSQL(Integer.parseInt(seasonId));
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
