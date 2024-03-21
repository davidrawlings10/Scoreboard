package scoreboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameEventRepository extends CrudRepository<GameEvent, Integer> {
    @Query("select ge from GameEvent ge where ge.gameId = ?1")
    public List<GameEvent> findByGameId(int gameId);

    @Query("select ge from GameEvent ge where ge.gameId = ?1 and ge.eventType != 'POSSESSION_ENDED'")
    public List<GameEvent> findByGameIdExcludingPossession(int gameId);

    /*@Query("select ge from GameEvent ge where ge.gameId = ?1")
    public List<Game> findBySeasonId(int gameId);*/
}
