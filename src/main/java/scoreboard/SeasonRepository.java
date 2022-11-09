package scoreboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeasonRepository extends CrudRepository<Season, Integer> {
    @Query("select s from Season s")
    public List<Season> findAll();

    @Query("select s from Season s where s.winnerTeamId = :teamId")
    public List<Season> findByWinnerTeamId(@Param("teamId") Integer teamId);
}
