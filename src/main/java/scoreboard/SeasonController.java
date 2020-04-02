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

    // http://localhost:8080/season/play?leagueId=1
    @GetMapping(path="/play")
    public @ResponseBody String start(@RequestParam String leagueId) throws InterruptedException {
        return seasonService.scheduleAndPlay(Integer.parseInt(leagueId));
        //return seasonService.playGame(1, 2, Sport.HOCKEY, null);
    }

}
