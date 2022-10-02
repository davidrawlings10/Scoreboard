package scoreboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClockRepository extends CrudRepository<Clock, Integer> {
    @Query("select c from Clock c where c.gameId = ?1")
    public List<Clock> findByGameId(int gameId);
}
