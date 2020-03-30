package scoreboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StandingRepository extends CrudRepository<Standing, Integer> {
    @Query("select s from Standing s where s.seasonId = :seasonId and s.teamId = :teamId")
    public Standing findBySeasonIdAndTeamId(@Param("seasonId") Integer seasonId, @Param("teamId") Integer teamId);
}