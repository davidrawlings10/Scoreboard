package scoreboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SeasonRepository extends CrudRepository<Season, Integer> {}
