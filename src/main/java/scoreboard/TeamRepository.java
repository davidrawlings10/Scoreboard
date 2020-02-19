package scoreboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TeamRepository extends CrudRepository<Team, Integer> {
    //@Query("select t from Team t join t.league l where l.id=:leagueId")
    //public Iterable<Team> findByLeagueId(@Param("leagueId") int leagueId);
}
