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
    @Autowired private SeasonService seasonService;
    @Autowired private StandingService standingService;

    List<Game> currentGames = new ArrayList<>();

    Boolean running = false;
    int tickMilliseconds = 1000;
    int gamesToPlay = 0;

    public void startGame(Sport sport, int homeTeamId, int awayTeamId) {
        Game game = new Game(sport, homeTeamId, awayTeamId);
        setupGameForPlay(game);
        currentGames.add(0, game);
    }

    private void setupGameForPlay(Game game) {
        game.setClock(new Clock(game.getSport()));
        game.getClock().reset();
        game.setHomeScore(0);
        game.setAwayScore(0);
        game.setStatus(Status.PLAYING);
        gameRepository.save(game);

        // cache home/away location and name
        Team homeTeam = teamService.getByTeamId(game.getHomeTeamId());
        Team awayTeam = teamService.getByTeamId(game.getAwayTeamId());

        game.setHomeName(homeTeam.getName());
        game.setAwayName(awayTeam.getName());
        game.setHomeLocation(homeTeam.getLocation());
        game.setAwayLocation(awayTeam.getLocation());

    }

    public void playGames() throws InterruptedException {
        if (running) {
            return;
        }

        running = true;
        while (running) {
            TimeUnit.MILLISECONDS.sleep(tickMilliseconds);
            for (Game game : currentGames) {
                if (Status.FINAL.equals(game.getStatus())) {
                    continue;
                }

                if (game.getSport().equals(Sport.HOCKEY)) {
                    if (hockeyPlayService.playSec(game)) {
                        handleGameEnd(game);
                    }
                }
            }
        }
    }

    private void handleGameEnd(Game game) {
        game.setEndingPeriod(game.getClock().getPeriod());
        gameRepository.save(game);

        Integer seasonId = game.getSeasonId();

        if (seasonId == null) {
            return;
        }

        standingService.updateStanding(game);

        if (gamesToPlay > 0) {
            gamesToPlay--;
            startNextSeasonGame(seasonId);
        }
    }

    public void pauseGames() {
        running = false;
    }

    class ScoreboardState {
        Boolean running;
        int tickMilliseconds;
        int gamesToPlay;
        List<Game> games;

        public Boolean getRunning() {
            return running;
        }

        public void setRunning(Boolean running) {
            this.running = running;
        }

        public int getTickMilliseconds() {
            return tickMilliseconds;
        }

        public void setTickMilliseconds(int tickMilliseconds) {
            this.tickMilliseconds = tickMilliseconds;
        }

        public int getGamesToPlay() {
            return gamesToPlay;
        }

        public void setGamesToPlay(int gamesToPlay) {
            this.gamesToPlay = gamesToPlay;
        }

        public List<Game> getGames() {
            return games;
        }

        public void setGames(List<Game> games) {
            this.games = games;
        }
    }

    public ScoreboardState getScoreboardState() {
        ScoreboardState scoreboardState = new ScoreboardState();
        scoreboardState.setRunning(running);
        scoreboardState.setTickMilliseconds(tickMilliseconds);
        scoreboardState.setGamesToPlay(gamesToPlay);
        scoreboardState.setGames(currentGames);
        return scoreboardState;
    }

    public void setGamesToPlay(int numberOfGames) {
        gamesToPlay = numberOfGames;
    }

    public void startSeasonGame(int seasonId) {
        startNextSeasonGame(seasonId);
    }

    private void startNextSeasonGame(int seasonId) {
        List<Game> games = gameRepository.findNextGameBySeasonId(seasonId);

        if (games.size() == 0)
            return;

        Game game = games.get(0);
        setupGameForPlay(game);
        currentGames.add(0, game);
    }

    public void setTickMilliseconds(int value) {
        tickMilliseconds = value;
    }

    public void adjustCurrentGame(Game game) {

    }

    public void terminateCurrentGame(int gameId) {
        Iterator<Game> iterator = currentGames.iterator();

        if (!iterator.hasNext())
            return;

        while (iterator.hasNext()) {
            Game game = iterator.next();
            if (game.getId().equals(gameId)) {
                if (game.getSeasonId() != null) {
                    game.setStatus(Status.SCHEDULED);
                    save(game);
                }
                iterator.remove();
            }
        }
    }




    // data access

    /*public Game save(Integer id, int homeTeamId, int awayTeamId, Integer homeScore, Integer awayScore, Integer seasonId, Integer endingPeriod) {
        Game game = new Game(Sport.HOCKEY);
        game.setId(id);
        game.setHomeTeamId(homeTeamId);
        game.setAwayTeamId(awayTeamId);
        game.setHomeScore(homeScore);
        game.setAwayScore(awayScore);
        game.setSeasonId(seasonId);
        game.setEndingPeriod(endingPeriod);
        return gameRepository.save(game);
    }*/

    public Game save(Game game) {
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
    /*public List<Game> getGames() {
        return currentGames;
    }*/

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
