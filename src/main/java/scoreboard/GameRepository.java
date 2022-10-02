package scoreboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface GameRepository extends CrudRepository<Game, Integer> {
    /*@Query("select g from Game g where g.seasonId = ?1 and limit ?2")
    public List<Game> findBySeasonId(int seasonId, int page, int pageSize);*/

    /*@Query("select g from Game g where g.seasonId = :seasonId limit :limit offset :offset")*/
    @Query("select g from Game g where g.seasonId = :seasonId")
    public List<Game> findBySeasonIdNoFilter(@Param("seasonId") Integer seasonId /*, @Param("limit") Integer limit, @Param("offset") Integer offset*/);

    @Query("select g from Game g where g.seasonId = :seasonId and (g.homeTeamId = :teamId or g.awayTeamId = :teamId)")
    public List<Game> findBySeasonIdTeamFilter(@Param("seasonId") Integer seasonId, Integer teamId);

    /*@Query("select g from Game g where g.seasonId = :seasonId and g.homeTeamId = :homeTeamId")
    public List<Game> findBySeasonIdHomeFilter(@Param("seasonId") Integer seasonId, Integer homeTeamId);

    @Query("select g from Game g where g.seasonId = :seasonId and g.awayTeamId = :awayTeamId")
    public List<Game> findBySeasonIdAwayFilter(@Param("seasonId") Integer seasonId, Integer awayTeamId);

    @Query("select g from Game g where g.seasonId = :seasonId and g.homeTeamId = :homeTeamId and g.awayTeamId = :awayTeamId")
    public List<Game> findBySeasonIdHomeAndAwayFilter(@Param("seasonId") Integer seasonId, Integer homeTeamId, Integer awayTeamId);*/

    @Query("select g from Game g where g.seasonId = :seasonId")
    public List<Game> findBySeasonId(@Param("seasonId") Integer seasonId);

    @Query("select g from Game g where g.seasonId = ?1 and g.status = 'SCHEDULED' order by g.id") // limit 1 (not working)
    public List<Game> findScheduledGamesBySeasonId(int seasonId);

    @Query("select g from Game g where g.seasonId = ?1 and g.status = 'PLAYING' order by g.id") // limit 1 (not working)
    public List<Game> findPlayingGamesBySeasonId(int seasonId);

    @Query("select g from Game g where g.id = ?1")
    public Game findByGameId(int gameId);
}