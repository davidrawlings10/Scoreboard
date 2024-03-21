package scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameEventService {
    @Autowired
    private GameEventRepository gameEventRepository;

    // data access
    public GameEvent save(GameEvent gameEvent) {
        return gameEventRepository.save(gameEvent);
    }

    public List<GameEvent> getByGameId(int gameId, boolean excludePossessionEnded) {
        // return gameEventRepository.findByGameId(gameId);
        return excludePossessionEnded ? gameEventRepository.findByGameIdExcludingPossession(gameId) : gameEventRepository.findByGameId(gameId);
    }
}
