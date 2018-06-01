import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class PWSFonts {
    private String pwsFont;
    private boolean pwsItalic;
    private boolean pwsBold;
    private boolean pwsUnderline;
    private int pwsTextsize;

    private String align;
    private String position;

    public PWSFonts(String font, boolean italic, boolean bold, boolean underline, int textsize) {
        this.pwsFont = font;
        this.pwsItalic = italic;
        this.pwsBold = bold;
        this.pwsUnderline = underline;
        this.pwsTextsize = textsize;
        this.align = "left";
        this.position = "normal";
    }

    public PWSFonts(String font, boolean italic, boolean bold, boolean underline, int textsize, String align) {
        this.pwsFont = font;
        this.pwsItalic = italic;
        this.pwsBold = bold;
        this.pwsUnderline = underline;
        this.pwsTextsize = textsize;
        this.align = align;
        this.position = "normal";
    }

    public PWSFonts(String font, boolean italic, boolean bold, boolean underline, int textsize, String align, String position) {
        this.pwsFont = font;
        this.pwsItalic = italic;
        this.pwsBold = bold;
        this.pwsUnderline = underline;
        this.pwsTextsize = textsize;
        this.align = align;
        this.position = position;
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

    public String getLQAlign() { return this.align; }

    public TextAlignment getAlign() {
        switch(this.align.toLowerCase()) {
            case "left":
                return TextAlignment.LEFT;
            case "center":
                return TextAlignment.CENTER;
            case "right":
                return TextAlignment.RIGHT;
            case "justify":
                return TextAlignment.JUSTIFY;
            default:
                return TextAlignment.LEFT;
        }
    }

    public String getLQPosition() {
        return this.position;
    }

    @Override
    public String toString() {
        return "PWSFonts: font = " + this.pwsFont + ", italic = " + this.pwsItalic + ", bold = " + this.pwsBold + ", textsize = " + this.pwsTextsize + ", underline = " + this.pwsUnderline + ", alignment = " + this.align + ", position = " + this.position;
    }
}
