package scoreboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface GameRepository extends CrudRepository<Game, Integer> {
    @Query("select g from Game g where g.seasonId = ?1")
    public Iterable<Game> findBySeasonId(int seasonId);

    @Query("select g from Game g where g.id = ?1")
    public Game findByGameId(int gameId);
}