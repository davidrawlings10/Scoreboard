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
    @GetMapping(path="getTeams")
    public @ResponseBody String getTeams(@RequestParam String leagueId) throws JsonProcessingException {
        String response = JsonUtil.getJsonList(teamService.getByLeagueId(Integer.parseInt(leagueId)));
        return response;
    }

    @CrossOrigin
    @GetMapping(path="getTeamById")
    public @ResponseBody String getTeam(@RequestParam String teamId) throws JsonProcessingException {
        String response =  JsonUtil.getJson(teamService.getByTeamId(Integer.parseInt(teamId)));
        return response;
    }
}
