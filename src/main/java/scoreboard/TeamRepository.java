package scoreboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Integer> {
    @Query("select t from Team t where t.league = ?1")
    public Iterable<Team> findByLeague(League league);

    @Query("select t from Team t where t.id = ?1")
    public Team findByTeamId(int teamId);

    // DEPRECATED
    /* @Query("select t.id from Team t where t.league = ?1 and t.active = true")
    public List<Integer> findTeamIdsByLeague(String league);*/
}
