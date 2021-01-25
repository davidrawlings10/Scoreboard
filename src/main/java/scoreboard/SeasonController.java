package scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/season")
public class SeasonController {

    @Autowired SeasonService seasonService;

    // http://localhost:8080/season/schedulePlay?leagueId=1
    @GetMapping(path="/schedulePlay")
    public @ResponseBody String schedulePlaySeason(@RequestParam String leagueId) throws InterruptedException {
        int seasonId = seasonService.schedulePlaySeason(Integer.parseInt(leagueId));
        return "Season played, id:" + seasonId;
    }

    // http://localhost:8080/season/schedule?leagueId=1
    @CrossOrigin
    @GetMapping(path="/schedule")
    public @ResponseBody String scheduleSeason(@RequestParam String leagueId) throws InterruptedException {
        Season season = seasonService.scheduleSeason(Integer.parseInt(leagueId));
        return "Season scheduled, id:" + season.getId();
    }

    // http://localhost:8080/season/play?seasonId=1&numOfGames=8
    @CrossOrigin
    @GetMapping(path="/play")
    public @ResponseBody String playSeason(@RequestParam String seasonId, @RequestParam(required=false) String numOfGames) throws InterruptedException {
        Integer numOfGamesInt = (numOfGames != null ? Integer.parseInt(numOfGames) : null);
        seasonService.playSeason(Integer.parseInt(seasonId), numOfGamesInt);
        return "Season games played, id:" + seasonId;
    }
}
