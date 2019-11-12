package scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class GameController {

    @Autowired GameService gameService;

    // http://localhost:8080/play
    @GetMapping(path="/play")
    public @ResponseBody String play() throws InterruptedException {
        return gameService.playGame(1, 2, Sport.HOCKEY, null);
    }

    // http://localhost:8080/findById?id=120
    @GetMapping(path="/findById")
    public @ResponseBody String findById(@RequestParam String id) throws InterruptedException {
        Game game = gameService.findById(Integer.parseInt(id));
        return game.getHomeScore() + "-" + game.getAwayScore();
    }

    /*@GetMapping(path="/findAll")
    public @ResponseBody String findAll() throws InterruptedException {
        gameService.findAll();
        return "Hello World";
    }*/


}