package scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class GameController {

    @Autowired GameService gameService;

    @GetMapping(path="/play")
    public @ResponseBody String play (/*@RequestParam String name, @RequestParam String email*/) throws InterruptedException {
        return gameService.playGame(1, 2, Sport.HOCKEY, null);
    }
}