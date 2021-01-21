package scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/game") // This means URL's start with /demo (after Application path)
public class GameController {

    @Autowired GameService gameService;

    // http://localhost:8080/game/play
    @GetMapping(path="/play")
    public @ResponseBody String play() throws InterruptedException {
        return gameService.playGame(50, 51, Sport.HOCKEY, null);
    }

    // http://localhost:8080/game/findById?gameId=120
    @GetMapping(path="/findById")
    public @ResponseBody String findById(@RequestParam String gameId) throws InterruptedException {
        Game game = gameService.findById(Integer.parseInt(gameId));
        return game.getHomeScore() + "-" + game.getAwayScore();
    }

    // http://localhost:8080/game/updateById?gameId=120 (will update away score to 10 just as proof of concept)
    @GetMapping(path="/updateById")
    public @ResponseBody String updateById(@RequestParam String gameId) throws InterruptedException {
        Game game = gameService.updateById(Integer.parseInt(gameId));
        return "updated to " + game.getHomeScore() + "-" + game.getAwayScore();
    }

    /*@GetMapping(path="/findAll")
    public @ResponseBody String findAll() throws InterruptedException {
        gameService.findAll();
        return "Hello World";
    }*/

    // http://localhost:8080/findById?id=120
    /*@GetMapping(path="/save")
    public @ResponseBody String findById(@RequestParam String id) throws InterruptedException {
        Game game = gameService.findById(Integer.parseInt(id));
        return game.getHomeScore() + "-" + game.getAwayScore();
    }*/
}