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
        return seasonService.schedulePlaySeason(Integer.parseInt(leagueId));
        //return seasonService.playGame(1, 2, Sport.HOCKEY, null);
    }

    // http://localhost:8080/season/schedule?leagueId=1
    @GetMapping(path="/schedule")
    public @ResponseBody String scheduleSeason(@RequestParam String leagueId) throws InterruptedException {
        return seasonService.scheduleSeason(Integer.parseInt(leagueId));
        //return seasonService.playGame(1, 2, Sport.HOCKEY, null);
    }

    // http://localhost:8080/season/play?leagueId=1
    @GetMapping(path="/play")
    public @ResponseBody String playSeason(@RequestParam String leagueId) throws InterruptedException {
        return seasonService.playSeason(Integer.parseInt(leagueId));
        //return seasonService.playGame(1, 2, Sport.HOCKEY, null);
    }

    /*@GetMapping(path="/playNextGame")
    public @ResponseBody String playNextGame(@RequestParam String leagueId) throws InterruptedException {
        return seasonService.playNextGame(Integer.parseInt(leagueId));
        //return seasonService.playGame(1, 2, Sport.HOCKEY, null);
    }*/

}
