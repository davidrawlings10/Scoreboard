package scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeamService {

    @Autowired private TeamRepository teamRepository;
    @Autowired private SeasonService seasonService;
    @Autowired private StandingService standingService;

    // data access

    public Team getByTeamId(int teamId) {
        return teamRepository.findByTeamId(teamId);
    }

    public Iterable<Team> getByLeague(League league) {
        //return teamRepository.findAll();

        return teamRepository.findByLeague(league);
    }

    public Iterable<TeamSeasonTotal> getTeamSeasonTotals(League league) {
        Iterable<Team> teams = teamRepository.findByLeague(league);

        // put teamSeasonTotal objects into a map
        Map<Integer, TeamSeasonTotal> teamSeasonTotalMap = new HashMap<>();
        for (Team team : teams) {
            teamSeasonTotalMap.put(team.getId(), new TeamSeasonTotal(team.getId()));
        }

        Iterable<Season> seasons = seasonService.findByLeague(league);

        for (Season season : seasons) {
            if (season.getWinnerTeamId() == null) {
                continue;
            }

            teamSeasonTotalMap.get(season.getWinnerTeamId()).incSeasonsWon();
            teamSeasonTotalMap.get(season.getWinnerTeamId()).incWinPoints(season.getNumTeams());
            teamSeasonTotalMap.get(season.getWinnerTeamId()).addTrophy(season.getNumTeams());

            Iterable<Standing> standings =  standingService.findBySeasonId(season.getId());
            for (Standing standing : standings) {
                teamSeasonTotalMap.get(standing.getTeamId()).incSeasonPlayed();
                teamSeasonTotalMap.get(standing.getTeamId()).incWinPointsPossible(season.getNumTeams());
            }
        }

        // take teamSeasonTotal objects out of the map
        List<TeamSeasonTotal> teamSeasonTotals = new ArrayList<>();
        for (Team team : teams) {
            teamSeasonTotals.add(teamSeasonTotalMap.get(team.getId()));
        }

        return teamSeasonTotals;
    }

    class TeamSeasonTotal {
        int teamId;
        int seasonsWon;
        int seasonsPlayed;
        int winPoints;
        int winPointsPossible;
        List<Integer> trophies = new ArrayList<>();

        public TeamSeasonTotal(int teamId) {
            this.teamId = teamId;
        }

        public void incSeasonsWon() {
            seasonsWon++;
        }

        public void incSeasonPlayed() {
            seasonsPlayed++;
        }

        public void incWinPoints(int val) {
            winPoints += val;
        }

        public void incWinPointsPossible(int val) {
            winPointsPossible += val;
        }

        public void addTrophy(int trophy) {
            trophies.add(trophy);
        }

        public int getTeamId() {
            return teamId;
        }

        public void setTeamId(int teamId) {
            this.teamId = teamId;
        }

        public int getSeasonsWon() {
            return seasonsWon;
        }

        public void setSeasonsWon(int seasonsWon) {
            this.seasonsWon = seasonsWon;
        }

        public int getSeasonsPlayed() {
            return seasonsPlayed;
        }

        public void setSeasonsPlayed(int seasonsPlayed) {
            this.seasonsPlayed = seasonsPlayed;
        }

        public int getWinPoints() {
            return winPoints;
        }

        public void setWinPoints(int winPoints) {
            this.winPoints = winPoints;
        }

        public int getWinPointsPossible() {
            return winPointsPossible;
        }

        public void setWinPointsPossible(int winPointsPossible) {
            this.winPointsPossible = winPointsPossible;
        }

        public List<Integer> getTrophies() {
            return trophies;
        }

        public void setTrophies(List<Integer> trophies) {
            this.trophies = trophies;
        }
    }

    // DEPRECATED

    /* public List<Integer> getTeamIdsByLeague(League league) {
        return teamRepository.findTeamIdsByLeague(league);
    }*/
}
