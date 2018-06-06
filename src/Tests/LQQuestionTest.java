import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LQQuestionTest {
    LQQuestion lqQuestion;

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

        String id = "q1";
        lqQuestion = new LQQuestion(id);

        lqQuestion.add(lqSlideS1);
        lqQuestion.add(lqSlideS2);
        lqQuestion.add(lqSlideS3);
        lqQuestion.add(lqSlideS4);
    }

    @Test
    public void getIdTest() {
        assertEquals(lqQuestion.getId(), "q1");
    }

    @Test
    public void getLqSlideArrayTest() {
        assertNotNull(lqQuestion.getLqSlideArray());
    }
}