package scoreboard;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@Controller
@RequestMapping(path="/game") // This means URL's start with /demo (after Application path)
public class GameController {

    @Autowired GameService gameService;
    // ObjectMapper mapper = new ObjectMapper(); `1

    // http://localhost:8080/game/play
    @CrossOrigin
    @GetMapping(path="/play")
    public @ResponseBody String play() throws InterruptedException {
        // return gameService.playGame(52, 53, Sport.HOCKEY, null);
        Game game = new Game(1);
        game.setHomeTeamId(50);
        game.setAwayTeamId(40);
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
    @GetMapping(path="/addGame")
    public @ResponseBody void addGame(@RequestParam String sportId, @RequestParam String homeTeamId, @RequestParam String awayTeamId) throws JsonProcessingException {
        gameService.addGame(Integer.parseInt(sportId), Integer.parseInt(homeTeamId), Integer.parseInt(awayTeamId));
        // return "";
        // return mapper.writeValueAsString();
    }

    @CrossOrigin
    @GetMapping(path="/getGames")
    public @ResponseBody String getGames() throws JsonProcessingException {
        /*List<Game> games = gameService.getGames();
        String json =  mapper.writeValueAsString(games);
        return json.substring(1, json.length() -1);*/

        return JsonUtil.getJson(gameService.getGames());
    }

    @CrossOrigin
    @GetMapping(path="/playGames")
    public @ResponseBody void playGames() throws JsonProcessingException, InterruptedException {
        gameService.playGames();
        // return "";
    }

    @CrossOrigin
    @GetMapping(path="/pauseGames")
    public @ResponseBody void pauseGames() throws JsonProcessingException, InterruptedException {
        gameService.pauseGames();
        // return "";
    }

    // http://localhost:8080/game/getGamesBySeasonId?seasonId=1
    @CrossOrigin
    @GetMapping(path="/getGamesBySeasonId")
    public @ResponseBody String getGamesBySeasonId(@RequestParam String seasonId) throws InterruptedException, JsonProcessingException {
        /*ObjectMapper mapper = new ObjectMapper();
        List<Game> games = gameService.getBySeasonId(Integer.parseInt(seasonId));
        String json = mapper.writeValueAsString(games);
        String gamesJSON = json.substring(1, json.length() -1);
        return "{\"gameList\":[" + gamesJSON + "]}";*/

        return JsonUtil.getJsonList(gameService.getBySeasonId(Integer.parseInt(seasonId)));
    }

    /*@CrossOrigin
    @GetMapping(path="/playSec")
    public @ResponseBody String playSec() throws JsonProcessingException {
        String json = mapper.writeValueAsString(gameService.playSec());
        return json.substring(1, json.length() -1);
    }*/

    // http://localhost:8080/game/getGamesBySeasonId?seasonId=1
    /*@CrossOrigin
    @GetMapping(path="/getGamesBySeasonId")
    public @ResponseBody String getGamesBySeasonId(@RequestParam String seasonId) throws InterruptedException, JsonProcessingException {
        String json = mapper.writeValueAsString(gameService.getBySeasonId(Integer.parseInt(seasonId)));
        return json.substring(1, json.length() - 1);
    }*/

    @CrossOrigin
    @GetMapping(path="/test")
    public @ResponseBody String test() {
        return "{\"homeScore\":159561, \"awayScore\":159562, \"homeName\":\"Home\", \"awayName\":\"Away\"}";
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