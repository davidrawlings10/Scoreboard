package scoreboard;

import java.util.ArrayList;
import java.util.List;

public class StandingResponse extends Standing {
    private String teamName;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /*List<Standing> standingList = new ArrayList<>();
    // List<StandingDisplay> standingDisplayList = new ArrayList<>();

    public List<Standing> getStandingList() {
        return standingList;
    }

    public void setStandingList(List<Standing> standingList) {
        this.standingList = standingList;
    }

    public StandingResponse(List<Standing> standings) {
        List<StandingDisplay> standingDisplayList = new ArrayList<StandingDisplay>();
        for (Standing standing : standings) {
            StandingDisplay standingDisplay = new StandingDisplay();
            standingDisplay
        }
    }

    public List<StandingDisplay> getStandingDisplayList() {
        return standingDisplayList;
    }

    public void setStandingDisplayList(List<StandingDisplay> standingDisplayList) {
        this.standingDisplayList = standingDisplayList;
    }*/


}
