import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PWSFontsTest {

    private PWSFonts pwsFont;
    private String font;
    private boolean italic ;
    private boolean bold;
    private boolean underline;
    private int textsize;
    private String align;

    @Before
    public void setUp() throws Exception {
        String font = "Arial";
        boolean italic = true;
        boolean bold = true;
        boolean underline = true;
        textsize = 12;
        String align = "JUSTIFY";

        pwsFont = new PWSFonts( font,  italic,  bold,  underline,  textsize, align);

    }

    @Test
    public void getPwsFontTest() {
        assertEquals(pwsFont.getPwsFont(), "Arial");
    }

    @Test
    public void getPwsItalicTest() {
        assertEquals(pwsFont.getPwsItalic(), true);
    }

    @Test
    public void getItalicTest() {
        assertEquals(pwsFont.getItalic(), FontPosture.ITALIC);
    }

    @Test
    public void getPwsBoldTest() {
        assertEquals(pwsFont.getPwsBold(), true);
    }

    @Test
    public void getBoldTest() {
        assertEquals(pwsFont.getBold(), FontWeight.BOLD);
    }

    @Test
    public void getPwsUnderlineTest() {
        assertEquals(pwsFont.getPwsUnderline(), true);
    }

    @Test
    public void getPwsTextsizeTest() {
        assertEquals(pwsFont.getPwsTextsize(), 12);
    }

    @Test
    public void getLQAlignTest()  {assertEquals(pwsFont.getLQAlign(), "JUSTIFY"); }

    @Test
    public void getAlignTest() {assertEquals(pwsFont.getAlign(), TextAlignment.JUSTIFY); }

    @Test
    public void toStringTest() {
        assertEquals( pwsFont.toString() ,"PWSFonts: font = " + pwsFont.getPwsFont() + ", italic = " + pwsFont.getPwsItalic() + ", bold = " + pwsFont.getPwsBold() + ", textsize = " + pwsFont.getPwsTextsize() + ", underline = " + pwsFont.getPwsUnderline() + ", alignment = " + pwsFont.getAlign());

    }
}