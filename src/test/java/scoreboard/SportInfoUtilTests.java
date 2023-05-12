package scoreboard;

import org.junit.Test;

public class SportInfoUtilTests {
    @Test
    public void testGetSportInfo() throws Exception {
        SportInfo sportInfoHockey = SportInfoUtil.getSportInfo(Sport.HOCKEY);
        assert(sportInfoHockey.getENDING_PERIOD() == 3);
        assert(sportInfoHockey.getMINUTES_IN_PERIOD() == 20);
    }
}
