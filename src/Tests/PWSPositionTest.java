import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PWSPositionTest {
    PWSPosition pwsPosition;

    @Before
    public void setUp() throws Exception {
        double x  = 1;
        double y  = 2;
        double x2 = 3;
        double y2 = 4;

        pwsPosition = new PWSPosition(x, y, x2, y2);

    }

    @Test
    public void getXTest() {
        assertEquals(pwsPosition.getX(), 1, 0);
    }

    @Test
    public void getYTest() {
        assertEquals(pwsPosition.getY(), 2, 0);
    }

    @Test
    public void getX2Test() {
        assertEquals(pwsPosition.getX2(), 3, 0);
    }

    @Test
    public void getY2Test() {
        assertEquals(pwsPosition.getY2(), 4, 0);
    }

    @Test
    public void getWidthTest() {
        assertEquals(pwsPosition.getWidth(), 2,0);
    }

    @Test
    public void getHeightTest() {
        assertEquals(pwsPosition.getHeight(), 2,0);
    }

    @Test
    public void getCenterXTest() {
        assertEquals(pwsPosition.getCenterX(), 2,0);
    }

    @Test
    public void getCenterYTest() {
        assertEquals(pwsPosition.getCenterY(), 3,0);
    }

    @Test
    public void toStringTest() {
        assertEquals(pwsPosition.toString(), "PWSPosition: x = " + pwsPosition.getX() + ", y = " + pwsPosition.getY() + ", x2 = " + pwsPosition.getX2() + ", y2 = " + pwsPosition.getY2());
    }
}