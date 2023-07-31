package scoreboard;

/*import com.sun.org.apache.xpath.internal.operations.String;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class GameService {

    @Autowired private GameRepository gameRepository;
    // @Autowired private HockeyPlayService hockeyPlayService;
    @Autowired private PlayService playService;
    @Autowired private TeamService teamService;
    @Autowired private SeasonService seasonService;
    @Autowired private StandingService standingService;
    @Autowired private ClockService clockService;

    List<Game> currentGames = new ArrayList<>();
    List<Game> finishedGames = new ArrayList<>();

    Boolean running = false;
    int tickMilliseconds = 1000;
    int gamesToPlay = 0;
    int gamesPlayingConcurrently = 1;

    class ScoreboardState {
        Boolean running;
        int tickMilliseconds;
        int gamesToPlay;
        int gamesPlayingConcurrently;
        List<Game> games;
        List<Game> finishedGames;

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

        public int getGamesPlayingConcurrently() {
            return gamesPlayingConcurrently;
        }

        public void setGamesPlayingConcurrently(int gamesPlayingConcurrently) {
            this.gamesPlayingConcurrently = gamesPlayingConcurrently;
        }

        public List<Game> getGames() {
            return games;
        }

        public void setGames(List<Game> games) {
            this.games = games;
        }

        public List<Game> getFinishedGames() {
            return finishedGames;
        }

        public void setFinishedGames(List<Game> finishedGames) {
            this.finishedGames = finishedGames;
        }
    }

    public ScoreboardState getScoreboardState() {
        ScoreboardState scoreboardState = new ScoreboardState();
        scoreboardState.setRunning(running);
        scoreboardState.setTickMilliseconds(tickMilliseconds);
        scoreboardState.setGamesToPlay(gamesToPlay);
        scoreboardState.setGamesPlayingConcurrently(gamesPlayingConcurrently);
        scoreboardState.setGames(currentGames);
        scoreboardState.setFinishedGames(finishedGames);
        return scoreboardState;
    }

    public void playGames() throws InterruptedException {
        if (running) {
            return;
        }

        running = true;
        while (running) {
            try {
                TimeUnit.MILLISECONDS.sleep(tickMilliseconds);
                for (Game game : currentGames) {
                    if (Status.FINAL.equals(game.getStatus())) {
                        continue;
                    }

                    if (playService.playSec(game)) {
                        handleGameEnd(game);
                    }

                    /*if (game.getSport().equals(Sport.HOCKEY)) {
                        if (hockeyPlayService.playSec(game)) {
                            handleGameEnd(game);
                        }
                    }*/
                }
            } catch (Exception e) {
                // An exception happens when a game ends because currentGames is modified
                // while it is being iterated over. Not an ideal solution, but I'm catching
                // and eating the exception, so we can keep moving
                System.out.println("Eating exception in playGames() " + e);
            }
        }
    }

    public void pauseGames() {
        running = false;
    }

    public void setTickMilliseconds(int value) {
        tickMilliseconds = value;
    }

    public void setGamesToPlay(int numberOfGames) {
        gamesToPlay = numberOfGames;
    }

    public void setGamesPlayingConcurrently(int games) {
        gamesPlayingConcurrently = games;
    }

    private void handleGameEnd(Game game) throws Exception {
        currentGames.remove(game);
        finishedGames.add(0, game);
        // if finishedGames gets too big remove games off the end to make room
        // too many finishedGames has caused performance issues
        if (finishedGames.size() > 20) {
            // Remove two games at a time so that the number of finishedGames changes every time a game ends.
            // This is the signal to the ui to refresh season components (kinda hacky).
            // In other words every time a game ends, either two games or no games will be removed,
            // so that the number of games will always be alternating for example between 20 and 19
            finishedGames.remove(20);
            finishedGames.remove(19);
        }

        game.setEndingPeriod(game.getClock().getPeriod());
        gameRepository.save(game);

        Integer seasonId = game.getSeasonId();

        if (seasonId == null) {
            return;
        }

        standingService.updateStanding(game);
        standingService.updateRankings(game.getSeasonId());

        final int gamesToAdd = gamesPlayingConcurrently - currentGames.size();
        for (int i = 0; i < gamesToAdd; ++i) {
            if (gamesToPlay > 0) {
                Game nextSeasonGame = getNextSeasonGame(seasonId);
                if (nextSeasonGame.getTeamAlreadyPlaying().equals(TeamAlreadyPlaying.NONE)) {
                    playSeasonGame(nextSeasonGame.getId());
                    gamesToPlay--;
                }
            }
        }
    }

    public void setupGameForPlay(Game game) throws Exception {
        game.setHomeScore(0);
        game.setAwayScore(0);
        game.setHomeHasPossession(RandomUtil.occur(0.50)); // randomly set which team starts with possession
        game.setPossessionSecondsRemaining(game.getNextPossessionSeconds());
        game.setStatus(Status.PLAYING);

        Clock clock = new Clock(game.getSport());
        clock.setGameId(game.getId());
        clock.reset();
        game.setClock(clock);

        gameRepository.save(game);
        clockService.save(clock);

        // cache home/away location and name
        Team homeTeam = teamService.getByTeamId(game.getHomeTeamId());
        Team awayTeam = teamService.getByTeamId(game.getAwayTeamId());

        game.setHomeName(homeTeam.getName());
        game.setAwayName(awayTeam.getName());
        game.setHomeLocation(homeTeam.getLocation());
        game.setAwayLocation(awayTeam.getLocation());
    }

    public void resumeIncompleteSeasonGame(int gameId) throws Exception {
        Game game = gameRepository.findByGameId(gameId);

        Clock clock = clockService.getClockByGameId(gameId);
        // clock.initializeConstants(game.getSport());

        // handle edge case by adding a second to the clock so the intermission handling kicks in
        if (clock.getMinutes() == 0 && clock.getSeconds() == 0) {
            clock.setSeconds(1);
        }

        game.setClock(clock);

        addGameToCurrentGames(game);
    }

    public void startSingleGame(Sport sport, int homeTeamId, int awayTeamId) throws Exception {
        Game game = new Game(sport, homeTeamId, awayTeamId);
        setupGameForPlay(game);
        addGameToCurrentGames(game);
    }

    public List<Game> getBySeasonId(int seasonId, Integer teamId) {
        if (teamId == null) {
            return gameRepository.findBySeasonIdNoFilter(seasonId);
        } else {
            return gameRepository.findBySeasonIdTeamFilter(seasonId, teamId);
        }
    }

    public Game getNextSeasonGame(int seasonId) {
        List<Game> games = gameRepository.findScheduledGamesBySeasonId(seasonId);

        if (games.size() == 0)
            return null;

        Game game = games.get(0);

        game.setTeamAlreadyPlaying(IsEitherTeamAlreadyPlaying(game));

        return game;
    }

    private TeamAlreadyPlaying IsEitherTeamAlreadyPlaying(Game game) {
        Integer homeTeamId = game.getHomeTeamId();
        Integer awayTeamId = game.getAwayTeamId();

        boolean homeIsPlaying = false;
        boolean awayIsPlaying = false;

        for (Game currentGame : currentGames) {
            if (homeTeamId.equals(currentGame.getHomeTeamId()) || homeTeamId.equals(currentGame.getAwayTeamId())) {
                homeIsPlaying = true;
            }

            if (awayTeamId.equals(currentGame.getHomeTeamId()) || awayTeamId.equals(currentGame.getAwayTeamId())) {
                awayIsPlaying = true;
            }
        }

        if (homeIsPlaying && awayIsPlaying) {
            return TeamAlreadyPlaying.BOTH;
        } else if (homeIsPlaying) {
            return TeamAlreadyPlaying.HOME;
        } else if (awayIsPlaying) {
            return TeamAlreadyPlaying.AWAY;
        } else {
            return TeamAlreadyPlaying.NONE;
        }
    }

    // search for any games that were playing but were incomplete because the back end stopped
    public List<Game> getIncompleteGames(int seasonId) {
        List<Game> gamesWithStatusPlaying = gameRepository.findPlayingGamesBySeasonId(seasonId);
        List<Game> incompleteGames = new ArrayList<Game>();

        for (Game game : gamesWithStatusPlaying) {
            if (!gameInCurrentGames(game.getId())) {
                game.setClock(clockService.getClockByGameId(game.getId()));
                incompleteGames.add(game);
            }
        }

        return incompleteGames;
    }

    private boolean gameInCurrentGames(int gameId) {
        for (Game currentGame : currentGames) {
            if (gameId == currentGame.getId()) {
                return true;
            }
        }
        return false;
    }

    public void playSeasonGame(int gameId) throws Exception {
        Game game = gameRepository.findByGameId(gameId);
        setupGameForPlay(game);
        addGameToCurrentGames(game);
    }

    // ensure that the game to be added isn't already in current games
    private void addGameToCurrentGames(Game game) {
        for (Game currentGame : currentGames) {
            if (currentGame.getId().equals(game.getId())) {
                return;
            }
        }
        currentGames.add(0, game);
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
                    game.setHomeScore(null);
                    game.setAwayScore(null);
                    save(game);

                    clockService.deleteByGameId(game.getId());
                }
                iterator.remove();
            }
        }
    }

    public String getInsertSQL(int seasonId) {
        List<Game> games = gameRepository.findBySeasonIdNoFilter(seasonId);
        StringBuilder sb = new StringBuilder("INSERT INTO game VALUES ");
        for (Game game : games) {
            sb.append(String.format("(%d, \"%s\", \"%s\", \"%s\", %d, %d, %d, %d, %d, \"%s\", %d),", game.getId(), game.getCreated(), game.getUpdated(), game.getSport(), game.getSeasonId(), game.getHomeTeamId(), game.getAwayTeamId(), game.getHomeScore(), game.getAwayScore(), game.getStatus(), game.getEndingPeriod()));
        };
        sb.replace(sb.length() - 1, sb.length(), ";");
        return sb.toString();
    }

    public int numberOfGamesBySeasonId(int seasonId) {
        return gameRepository.findBySeasonId(seasonId).size();
    }

    // data access

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

    public Team getByTeamId(int teamId) { return teamService.getByTeamId(teamId); }


    // deprecated

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

    /*public List<Game> getBySeasonId(int seasonId, int page, int pageSize, Integer homeTeamId, Integer awayTeamId) {
        if (homeTeamId == null && awayTeamId == null) {
            return gameRepository.findBySeasonIdNoFilter(seasonId, pageSize, (int)(page - 1) * pageSize);
        } else if (homeTeamId != null && awayTeamId == null) {
            return gameRepository.findBySeasonIdHomeFilter(seasonId, homeTeamId);
        } else if (homeTeamId == null && awayTeamId != null) {
            return gameRepository.findBySeasonIdAwayFilter(seasonId, awayTeamId);
        } else {
            return gameRepository.findBySeasonIdHomeAndAwayFilter(seasonId, homeTeamId, awayTeamId);
        }
    }*/

    /*private void startNextSeasonGame(int seasonId) {
        List<Game> games = gameRepository.findNextGameBySeasonId(seasonId);

        if (games.size() == 0)
            return;

        Game game = games.get(0);
        setupGameForPlay(game);
        currentGames.add(0, game);
    }*/

    /*public List<Game> getGames() {
        return currentGames;
    }*/

    /*public String playGame(Game game) throws InterruptedException {
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
    }*/

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
