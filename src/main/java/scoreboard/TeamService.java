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

            TeamSeasonTotal winningTeamTeamSeasonTotal = teamSeasonTotalMap.get(season.getWinnerTeamId());
            winningTeamTeamSeasonTotal.incSeasonsWon();
            winningTeamTeamSeasonTotal.incWinPoints(season.getNumTeams());
            winningTeamTeamSeasonTotal.addTrophy(season.getNumTeams());

            Iterable<Standing> standings =  standingService.findBySeasonId(season.getId());
            for (Standing standing : standings) {
                TeamSeasonTotal teamSeasonTotal = teamSeasonTotalMap.get(standing.getTeamId());
                teamSeasonTotal.incSeasonPlayed();
                teamSeasonTotal.incPointsPossible(season.getNumTeams());
                teamSeasonTotal.incPerformancePointsPossible(season.getNumTeams() - standing.getRanking() + 1);
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
        int performancePoints;
        int pointsPossible;
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

        public void incPointsPossible(int val) {
            pointsPossible += val;
        }

        public void incPerformancePointsPossible(int val) { performancePoints += val; }

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

        public int getPointsPossible() {
            return pointsPossible;
        }

        public void setPointsPossible(int pointsPossible) {
            this.pointsPossible = pointsPossible;
        }

        public int getPerformancePoints() {
            return performancePoints;
        }

        public void setPerformancePoints(int performancePoints) {
            this.performancePoints = performancePoints;
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
