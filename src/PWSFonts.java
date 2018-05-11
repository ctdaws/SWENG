import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class PWSFonts {
    private String pwsFont;
    private boolean pwsItalic;
    private boolean pwsBold;
    private boolean pwsUnderline;
    private int pwsTextsize;

    public PWSFonts(String font, boolean italic, boolean bold, boolean underline, int textsize) {
        this.pwsFont = font;
        this.pwsItalic = italic;
        this.pwsBold = bold;
        this.pwsUnderline = underline;
        this.pwsTextsize = textsize;
    }

    public String getPwsFont() {
        return this.pwsFont;
    }

    public boolean getPwsItalic() {
        return this.pwsItalic;
    }

    public FontPosture getItalic() {
        if(this.pwsItalic) { return FontPosture.ITALIC; }
        else { return FontPosture.REGULAR; }
    }

    public boolean getPwsBold() {
        return this.pwsBold;
    }

    public FontWeight getBold() {
        if(this.pwsBold) { return FontWeight.BOLD; }
        else { return FontWeight.NORMAL; }
    }

    public boolean getPwsUnderline() {
        return this.pwsUnderline;
    }

    public int getPwsTextsize() {
        return this.pwsTextsize;
    }

    @Override
    public String toString() {
        return "PWSFonts: font = " + this.pwsFont + ", italic = " + this.pwsItalic + ", bold = " + this.pwsBold + ", textsize = " + this.pwsTextsize + ", underline = " + this.pwsUnderline;
    }
}
