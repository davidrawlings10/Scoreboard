package scoreboard;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping(path="/team")
public class TeamController {
    @Autowired TeamService teamService;

    // http://localhost:8080/team/getTeams?league=AVES
    @CrossOrigin
    @GetMapping(path="getTeams")
    public @ResponseBody ResponseEntity<String> getTeams(@RequestParam League league) throws JsonProcessingException {
        String response = JsonUtil.getJsonList(teamService.getByLeague(league));
        // experiment with cache control - max-age
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)).body(response);
    }

    // http://localhost:8080/team/getTeamById?teamId=1
    @CrossOrigin
    @GetMapping(path="getTeamById")
    public @ResponseBody String getTeam(@RequestParam String teamId) throws JsonProcessingException {
        String response =  JsonUtil.getJson(teamService.getByTeamId(Integer.parseInt(teamId)));
        return response;
    }

    // http://localhost:8080/team/getTeamSeasonTotals?league=AVES
    @CrossOrigin
    @GetMapping(path="getTeamSeasonTotals")
    public @ResponseBody String getTeamSeasonTotals(@RequestParam League league) throws JsonProcessingException {
        String response = JsonUtil.getJsonList(teamService.getTeamSeasonTotals(league));
        return response;
    }


}
