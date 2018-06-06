import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LQExampleTest {
    LQExample lqExample;

    @Before
    public void setUp() throws Exception {
        String type;
        int duration = 10;
        int startI = 4;
        String start = "" + startI;
        PWSFonts pwsFonts = new PWSFonts("Arial", false,false,false,12, "LEFT");
        PWSColors pwsColors = new PWSColors("#000000", "#000000");
        PWSTransitions pwsTransitions = new PWSTransitions(start, duration);

        LQSlide lqSlideS1 = new LQSlide( "s1",  "Q",  pwsFonts,  pwsColors,  pwsTransitions);
        LQSlide lqSlideS2 = new LQSlide( "s2",  "Q",  pwsFonts,  pwsColors,  pwsTransitions);
        LQSlide lqSlideS3 = new LQSlide( "s3",  "Q",  pwsFonts,  pwsColors,  pwsTransitions);
        LQSlide lqSlideS4 = new LQSlide( "s4",  "Q",  pwsFonts,  pwsColors,  pwsTransitions);

        String id = "x1";
        lqExample = new LQExample(id);

        lqExample.add(lqSlideS1);
        lqExample.add(lqSlideS2);
        lqExample.add(lqSlideS3);
        lqExample.add(lqSlideS4);
    }

    @Test
    public void getIdTest() {
        assertEquals(lqExample.getId(), "x1");
    }

    @Test
    public void getLqSlideArrayTest() {
        assertNotNull(lqExample.getLqSlideArray());
    }
}