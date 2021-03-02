package scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/game") // This means URL's start with /demo (after Application path)
public class GameController {

    @Autowired GameService gameService;

    // http://localhost:8080/game/play
    @CrossOrigin
    @GetMapping(path="/play")
    public @ResponseBody String play() throws InterruptedException {
        // return gameService.playGame(52, 53, Sport.HOCKEY, null);
        Game game = new Game(1);
        game.setHomeTeamId(59);
        game.setAwayTeamId(60);
        return gameService.playGame(game);
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

    @CrossOrigin
    @GetMapping(path="/test")
    public @ResponseBody String test() {
        return "{\"name\": \"31\"}";
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