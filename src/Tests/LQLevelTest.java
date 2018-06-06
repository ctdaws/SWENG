import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LQLevelTest {
    LQLevel lqLevel;

    @Before
    public void setUp() throws Exception {
        LQQuestion lqQuestion1 = new LQQuestion("q1");
        LQQuestion lqQuestion2 = new LQQuestion("q2");
        LQExample lqExample = new LQExample("x1");
        LQQuestion lqQuestion3 = new LQQuestion("q3");

        lqLevel = new LQLevel("l1");

        lqLevel.add(lqExample);
        lqLevel.add(lqQuestion1);
        lqLevel.add(lqQuestion2);
        lqLevel.add(lqQuestion2);
    }


    @Test
    public void getIdTest() {assertEquals(lqLevel.getId(), "l1"); }


    @Test
    public void getLqQuestionArrayTest() { assertNotNull(lqLevel.getLqQuestionArray()); }
}