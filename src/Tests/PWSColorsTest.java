import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PWSColorsTest {
    private PWSColors pwsColors;

    @Before
    public void setUp() throws Exception {
        String pwsColor = "#000000";
        String pwsFill = "#000000";

        pwsColors = new PWSColors(pwsColor, pwsFill);
    }

    @Test
    public void getPwsColorTest() {
        assertEquals(pwsColors.getPwsColor(), "#000000");
    }

    @Test
    public void getPwsFillTest() {
        assertEquals(pwsColors.getPwsFill(), "#000000");

    }

    @Test
    public void getColorTest() {
        assertEquals(pwsColors.getColor(), Color.valueOf("#000000"));
    }

    @Test
    public void getFillTest() {
        assertEquals(pwsColors.getFill(), Color.valueOf("#000000"));
    }

    @Test
    public void toStringTest() {
        assertEquals( pwsColors.toString() ,"PWSColors: color = " + pwsColors.getColor() + ", fill = " + pwsColors.getFill() );
    }
}