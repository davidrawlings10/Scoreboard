package scoreboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StandingRepository extends CrudRepository<Standing, Integer> {
    @Query("select s from Standing s where s.teamId = ?1")
    public Standing findByTeamId(int teamId);
}