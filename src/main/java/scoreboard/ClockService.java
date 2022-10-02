package scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClockService {
    @Autowired
    private ClockRepository clockRepository;

    // data access
    public Clock getClockByGameId(int gameId) {
        return clockRepository.findByGameId(gameId).get(0);
    }

    public Clock save(Clock clock) {
        return clockRepository.save(clock);
    }
}
