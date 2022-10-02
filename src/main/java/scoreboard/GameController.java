package scoreboard;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/game")
public class GameController {

    @Autowired GameService gameService;

    @CrossOrigin
    @GetMapping(path="/startSingleGame")
    public @ResponseBody void startSingleGame(@RequestParam String sport, @RequestParam String homeTeamId, @RequestParam String awayTeamId) {
        gameService.startSingleGame(Sport.valueOf(sport), Integer.parseInt(homeTeamId), Integer.parseInt(awayTeamId));
    }

    @CrossOrigin
    @GetMapping(path="/getScoreboardState")
    public @ResponseBody String getScoreboardState() throws JsonProcessingException {
        String response = JsonUtil.getJson(gameService.getScoreboardState());
        return response;
    }

    @CrossOrigin
    @GetMapping(path="/getNextSeasonGame")
    public @ResponseBody String getNextSeasonGame(@RequestParam Integer seasonId) throws JsonProcessingException {
        String response = JsonUtil.getJson(gameService.getNextSeasonGame(seasonId));
        return response;
    }

    @CrossOrigin
    @GetMapping(path="/playSeasonGame")
    public @ResponseBody void playNextSeasonGame(@RequestParam Integer gameId) throws JsonProcessingException {
        gameService.playSeasonGame(gameId);
    }

    // http://localhost:8080/game/getGamesBySeasonId?seasonId=1
    @CrossOrigin
    @GetMapping(path="/getGamesBySeasonId")
    public @ResponseBody String getGamesBySeasonId(@RequestParam String seasonId, @RequestParam Integer page, @RequestParam Integer pageSize, @RequestParam String teamId) throws JsonProcessingException {
        String response = JsonUtil.getJsonList(gameService.getBySeasonId(Integer.parseInt(seasonId), page, pageSize, !teamId.equals("null") ? Integer.parseInt(teamId) : null));
        return response;
    }

    // http://localhost:8080/game/getInterruptedGames?seasonId=1
    @CrossOrigin
    @GetMapping(path="/getInterruptedGames")
    public @ResponseBody String getInterruptedGames(@RequestParam String seasonId) throws JsonProcessingException {
        String response = JsonUtil.getJsonList(gameService.getInterruptedGames(Integer.parseInt(seasonId)));
        return response;
    }

    @CrossOrigin
    @GetMapping(path="/resumeInterruptedSeasonGame")
    public @ResponseBody void resumeInterruptedSeasonGame(@RequestParam Integer gameId) throws JsonProcessingException {
        gameService.resumeInterruptedSeasonGame(gameId);
    }

    @CrossOrigin
    @GetMapping(path="/playGames")
    public @ResponseBody void playGames() throws InterruptedException {
        gameService.playGames();
    }

    @CrossOrigin
    @GetMapping(path="/pauseGames")
    public @ResponseBody void pauseGames() {
        gameService.pauseGames();
    }

    @CrossOrigin
    @GetMapping(path="/setGamesToPlay")
    public @ResponseBody void setGamesToPlay(@RequestParam Integer numGames) {
        gameService.setGamesToPlay(numGames);
    }

    @CrossOrigin
    @GetMapping(path="/setTickMilliseconds")
    public @ResponseBody void setTickMilli(@RequestParam Integer value) {
        gameService.setTickMilliseconds(value);
    }

    @CrossOrigin
    @GetMapping(path="/terminateCurrentGame")
    public @ResponseBody String terminateCurrentGame(@RequestParam String gameId) {
        gameService.terminateCurrentGame(Integer.parseInt(gameId));
        return "ok";
    }

    @CrossOrigin
    @GetMapping(path="/numberGamesBySeasonId")
    public @ResponseBody int numberGamesBySeasonId(@RequestParam int seasonId) {
        return gameService.numberOfGamesBySeasonId(seasonId);
    }


    // DEPRECATED
    @CrossOrigin
    @GetMapping(path="/restTest")
    public @ResponseBody String test() {
        return "{\"homeScore\":159561, \"awayScore\":159562, \"homeName\":\"Home\", \"awayName\":\"Away\"}";
    }

    /*@CrossOrigin
    // http://localhost:8080/game/findById?gameId=120
    @GetMapping(path="/findById")
    public @ResponseBody String findById(@RequestParam String gameId) {
        Game game = gameService.findById(Integer.parseInt(gameId));
        return game.getHomeScore() + "-" + game.getAwayScore();
    }

    @CrossOrigin
    // http://localhost:8080/game/updateById?gameId=120 (will update away score to 10 just as proof of concept)
    @GetMapping(path="/updateById")
    public @ResponseBody String updateById(@RequestParam String gameId) {
        Game game = gameService.updateById(Integer.parseInt(gameId));
        return "updated to " + game.getHomeScore() + "-" + game.getAwayScore();
    }

    @CrossOrigin
    @GetMapping(path="/adjustCurrentGame")
    public @ResponseBody String adjustCurrentGame(@RequestParam String gameId) {
        // gameService.terminateCurrentGame(Integer.parseInt(gameId));
        return "ok";
    }*/







    // removed home and away team filters in place of one filter but keeping this endpoint commented in case I change my mind
    /*@CrossOrigin
    @GetMapping(path="/getGamesBySeasonId")
    public @ResponseBody String getGamesBySeasonId(@RequestParam String seasonId, @RequestParam Integer page, @RequestParam Integer pageSize, @RequestParam String homeTeamId, @RequestParam String awayTeamId) throws JsonProcessingException {
        String response = JsonUtil.getJsonList(gameService.getBySeasonId(Integer.parseInt(seasonId), page, pageSize, !homeTeamId.equals("null") ? Integer.parseInt(homeTeamId) : null, !awayTeamId.equals("null") ? Integer.parseInt(awayTeamId) : null));
        return response;
    }*/

    /*
    @CrossOrigin
    @GetMapping(path="/startSeasonGame")
    public @ResponseBody void startSeasonGame(@RequestParam Integer seasonId) {
        gameService.startSeasonGame(seasonId);
    }
    */

    /*@CrossOrigin
    @GetMapping(path="/getGames")
    public @ResponseBody String getGames() throws JsonProcessingException {
        String response = JsonUtil.getJsonList(gameService.getGames());
        return response;
    }*/

    /*// http://localhost:8080/game/play
    @CrossOrigin
    @GetMapping(path="/play")
    public @ResponseBody String play() throws InterruptedException {
        // return gameService.playGame(52, 53, Sport.HOCKEY, null);
        Game game = new Game(1);
        game.setHomeTeamId(50);
        game.setAwayTeamId(40);
        return gameService.playGame(game);
    }*/

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