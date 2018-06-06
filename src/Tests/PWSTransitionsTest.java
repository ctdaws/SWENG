import javafx.util.Duration;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PWSTransitionsTest {
    private PWSTransitions pwsTransitions;

    @Before
    public void setUp() throws Exception {
        boolean userTrigger = false;
        int startI = 2;
        int duration = 5;
        String start = "" + startI;

        pwsTransitions = new PWSTransitions(start, duration);
    }

    @Test
    public void getStartTestTriggerless() {
        assertEquals(pwsTransitions.getStart(), Duration.millis(2001));
    }

    @Test
    public void getStartTestTrigger() {
        boolean userTrigger = true;
        int duration = 10;
        int startI = 4;
        String start = "" + startI;

        pwsTransitions = new PWSTransitions(start, duration);
        assertEquals(pwsTransitions.getStart(), Duration.millis(4001) );
    }

    @Test
    public void getDurationTest() {
        assertEquals(pwsTransitions.getDuration(), Duration.millis(7001));
    }

    @Test
    public void toStringTest() {
        assertEquals(pwsTransitions.toString(), "PWSTransitions: start = " + pwsTransitions.getStart() + ", duration = 5000");
    }
}