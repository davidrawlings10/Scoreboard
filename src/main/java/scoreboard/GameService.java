package scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class GameService {

    @Autowired private GameRepository gameRepository;
    @Autowired private HockeyPlayService hockeyPlayService;
    @Autowired private TeamService teamService;

    List<Game> currentGames = new ArrayList<>();
    Map<Integer, Integer> seasonNumOfGamesToPlay = new HashMap<>();
    Boolean running = false;

    public void startGame(Sport sport, int homeTeamId, int awayTeamId) {
        Game game = new Game(sport);

        game.setHomeTeamId(homeTeamId);
        game.setAwayTeamId(awayTeamId);

        Team homeTeam = teamService.getByTeamId(game.getHomeTeamId());
        Team awayTeam = teamService.getByTeamId(game.getAwayTeamId());

        game.setHomeName(homeTeam.getLocation());
        game.setAwayName(awayTeam.getLocation());
        // game.setHomeName(homeTeam.getLocation() != null ?  homeTeam.getLocation() + ' ' + homeTeam.getName() : homeTeam.getName());
        // game.setAwayName(awayTeam.getLocation() != null ?  awayTeam.getLocation() + ' ' + awayTeam.getName() : awayTeam.getName());

        game.setClock(new Clock(game.getSport()));
        game.getClock().reset();
        game.setHomeScore(0);
        game.setAwayScore(0);

        currentGames.add(0, game);
    }

    public void playGames() throws InterruptedException {
        if (running) {
            return;
        }

        running = true;
        while (running) {
            TimeUnit.MILLISECONDS.sleep(HockeyPlayService.Config.TimeDelay.gameplayTickMilli);
            for (Game game : currentGames) {
                if (game.getSport() == Sport.HOCKEY) {
                    hockeyPlayService.playSec(game);
                }
            }
        }
    }

    public void pauseGames() {
        running = false;
    }

    public List<Game> getGames() {
        return currentGames;
    }

    public void setSeasonNumOfGamesToPlay(int seasonId, int numGames) {
        seasonNumOfGamesToPlay.put(seasonId, numGames);
    }

    public void startSeasonGame(int seasonId) {
        // Game game = gameRepository.findNextGameBySeasonId(seasonId);
        // addGame()
    }

    // data access

    public Game save(Integer id, int homeTeamId, int awayTeamId, Integer homeScore, Integer awayScore, Integer seasonId, Integer endingPeriod) {
        Game game = new Game(Sport.HOCKEY);
        game.setId(id);
        game.setHomeTeamId(homeTeamId);
        game.setAwayTeamId(awayTeamId);
        game.setHomeScore(homeScore);
        game.setAwayScore(awayScore);
        game.setSeasonId(seasonId);
        game.setEndingPeriod(endingPeriod);
        return gameRepository.save(game);
    }

    public Game updateById(int gameId) {
        Optional<Game> gameOptional = gameRepository.findById(gameId);
        Game game = gameOptional.get();
        game.setAwayScore(10);
        gameRepository.save(game);
        return game;
    }

    public Game findById(int gameId) {
        Optional<Game> gameOptional = gameRepository.findById(gameId);
        return gameOptional.get();
    }

    public List<Game> getBySeasonId(int seasonId) {
        return gameRepository.findBySeasonId(seasonId);
    }

    public Team getByTeamId(int teamId) { return teamService.getByTeamId(teamId); }


    // deprecated
    public String playGame(Game game) throws InterruptedException {
        game.setClock(new Clock(game.getSport()));
        game.setHomeScore(0);
        game.setAwayScore(0);
        hockeyPlayService.playGame(game);
        gameRepository.save(game);
        return game.getHomeScore() + "-" + game.getAwayScore();
    }

    private String printScoreboard(Game game, boolean isFinal) {
        return game.getHomeScore() + " - " + game.getAwayScore() + " | "
                + (isFinal ? "Final" : (game.getClock().isIntermission() ? "Intermission" : "Period" ) + " | " + game.getClock().getPeriod() + " | " + game.getClock().getMinutes() + ":" + game.getClock().getSeconds());
    }

    /*public List<Game> playSec() {
        for (Game game : currentGames) {
            if (game.getSportId() == 1) {
                hockeyPlayService.playSec(game);
            }
        }
        return currentGames;
    }*/




    /*public Collection<Team> getTeamsByLeagueId(int leagueId) {

    }*/


    /*public boolean existsById(int id) {
        return gameRepository.existsById(id);
    }

    public void count() {
        gameRepository.count();
    }

    public void deleteById(int id) {
        gameRepository.deleteById(id);
    }

    public void findAll() {
        for (Game game : gameRepository.findAll()) {
            System.out.println(game.getHomeScore() + "-" + game.getAwayScore() + " : " + game.getId());
        }
    }*/
}
