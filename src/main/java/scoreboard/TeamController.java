package scoreboard;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/team")
public class TeamController {
    @Autowired TeamService teamService;

    @CrossOrigin
    @GetMapping(path="get")
    public @ResponseBody String getTeams(@RequestParam String leagueId) throws JsonProcessingException {
        return JsonUtil.getJsonList(teamService.getByLeagueId(Integer.parseInt(leagueId)));
    }
}
