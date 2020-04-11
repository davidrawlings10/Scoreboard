package scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/season")
public class SeasonController {

    @Autowired SeasonService seasonService;

    // http://localhost:8080/season/schedulePlay?leagueId=1
    @GetMapping(path="/schedulePlay")
    public @ResponseBody String schedulePlaySeason(@RequestParam String leagueId) throws InterruptedException {
        int seasonId = seasonService.schedulePlaySeason(Integer.parseInt(leagueId));
        return "Season scheduled, id:" + seasonId;
    }

    // http://localhost:8080/season/schedule?leagueId=1
    @GetMapping(path="/schedule")
    public @ResponseBody String scheduleSeason(@RequestParam String leagueId) throws InterruptedException {
        Season season = seasonService.scheduleSeason(Integer.parseInt(leagueId));
        return "Season scheduled, id:" + season.getId();
    }

    // http://localhost:8080/season/play?leagueId=1
    @GetMapping(path="/play")
    public @ResponseBody String playSeason(@RequestParam String seasonId) throws InterruptedException {
        seasonService.playSeason(Integer.parseInt(seasonId));
        return "Season played, id:" + seasonId;
    }
}
