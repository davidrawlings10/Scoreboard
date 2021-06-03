package scoreboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Integer> {
    @Query("select t from Team t where t.leagueId = ?1")
    public Iterable<Team> findByLeagueId(int leagueId);

    @Query("select t.id from Team t where t.leagueId = ?1 and t.active = true")
    public List<Integer> findTeamIdsByLeagueId(int leagueId);

    @Query("select t from Team t where t.id = ?1")
    public Team findByTeamId(int teamId);
}
