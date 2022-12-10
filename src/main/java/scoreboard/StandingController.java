package scoreboard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/standing")
public class StandingController {
    @Autowired StandingService standingService;

    @CrossOrigin
    @GetMapping(path="get")
    public @ResponseBody String getStanding(@RequestParam String seasonId) throws JsonProcessingException {
        /*StandingResponse standingResponse = new StandingResponse(); `1
        standingResponse.setStandingList(standingService.findBySeasonId(Integer.parseInt(seasonId)));
        return standingResponse;*/

        /*List<Standing> standingList = standingService.findBySeasonId(Integer.parseInt(seasonId));
        return standingService.getSeasonListJSON(standingList);
        return standingService.getSeasonListJSON(standingList);*/

        /*ObjectMapper mapper = new ObjectMapper();
        List<Standing> standingList = standingService.findBySeasonId(Integer.parseInt(seasonId));
        String json = mapper.writeValueAsString(standingList);
        String standingJSON = json.substring(1, json.length() -1);
        return "{\"standingList\":[" + standingJSON + "]}";*/

        for (int i = 1; i < 21; ++i) {
            standingService.updateRankings(i);
        }

        return JsonUtil.getJsonList(standingService.findBySeasonId(Integer.parseInt(seasonId)));
    }

    @CrossOrigin
    @GetMapping(path="getStandingByTeamId")
    public @ResponseBody String getStandingByTeamId(@RequestParam String teamId) throws JsonProcessingException {
        String response = JsonUtil.getJsonList(standingService.getStandingByTeamId(Integer.parseInt(teamId)));
        return response;
    }
}
