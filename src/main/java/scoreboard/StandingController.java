package scoreboard;

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
    public @ResponseBody
    List<Standing> getStanding(@RequestParam String seasonId) {
        return standingService.findBySeasonId(Integer.parseInt(seasonId));
    }
}
