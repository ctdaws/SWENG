import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 * Class containing information about the font formatting attributes described in xml
 *
 * @author Samuel Broughton
 * @version 1.6
 */
public class PWSFonts {
//    Font formatting properties from PWS
    private String pwsFont;
    private boolean pwsItalic;
    private boolean pwsBold;
    private boolean pwsUnderline;
    private int pwsTextsize;
//    Additional formatting properties
//    Alignment of text (left, center, right, justified)
    private String align;
//    Vertical position of text (normal, subscript, superscript)
    private String position;

    /**
     * Constructor for PWS font formatting
     * @param font String of font family
     * @param italic boolean if text is italic
     * @param bold boolean if text is bold
     * @param underline boolean if text is underlined
     * @param textsize integer of text size
     */
    public PWSFonts(String font, boolean italic, boolean bold, boolean underline, int textsize) {
        this.pwsFont = font;
        this.pwsItalic = italic;
        this.pwsBold = bold;
        this.pwsUnderline = underline;
        this.pwsTextsize = textsize;
//        Sets alignment and position to PWS default
        this.align = "left";
        this.position = "normal";
    }

    /**
     * Constructor for PWS formatting + text alignment
     * @param font String of font family
     * @param italic boolean if text is italic
     * @param bold boolean if text is bold
     * @param underline boolean if text is underlined
     * @param textsize integer of text size
     * @param align alignment of text within the encapsulating node
     */
    public PWSFonts(String font, boolean italic, boolean bold, boolean underline, int textsize, String align) {
        this.pwsFont = font;
        this.pwsItalic = italic;
        this.pwsBold = bold;
        this.pwsUnderline = underline;
        this.pwsTextsize = textsize;
        this.align = align;
//        Default vertical position
        this.position = "normal";
    }

    /**
     * Constructor for PWS formatting + text alignment and vertical position
     * @param font String of font family
     * @param italic boolean if text is italic
     * @param bold boolean if text is bold
     * @param underline boolean if text is underlined
     * @param textsize integer of text size
     * @param align alignment of text within the encapsulating node
     * @param position vertical position of the text (normal, superscript, subscript)
     */
    public PWSFonts(String font, boolean italic, boolean bold, boolean underline, int textsize, String align, String position) {
        this.pwsFont = font;
        this.pwsItalic = italic;
        this.pwsBold = bold;
        this.pwsUnderline = underline;
        this.pwsTextsize = textsize;
        this.align = align;
        this.position = position;
    }

    /**
     * Getter for font family
     * @return String name of font family
     */
    public String getPwsFont() { return this.pwsFont; }

    /**
     * Getter for italics status
     * @return  boolean status of italics
     */
    public boolean getPwsItalic() { return this.pwsItalic; }

    /**
     * Getter for italic property
     * @return FontPosture for regular or italic text
     */
    public FontPosture getItalic() {
        if(this.pwsItalic) { return FontPosture.ITALIC; }
        else { return FontPosture.REGULAR; }
    }

    /**
     * Getter for bold ststus
     * @return boolean status of boldness
     */
    public boolean getPwsBold() { return this.pwsBold; }

    /**
     * Getter for bold property
     * @return FontWeight for normal or bold text
     */
    public FontWeight getBold() {
        if(this.pwsBold) { return FontWeight.BOLD; }
        else { return FontWeight.NORMAL; }
    }

    /**
     * Getter for underline ststus
     * @return boolean status of underline
     */
    public boolean getPwsUnderline() { return this.pwsUnderline; }

    /**
     * Getter for text size
     * @return integer size of text in pixels
     */
    public int getPwsTextsize() { return this.pwsTextsize; }

    /**
     * Getter for text alignment
     * @return string describing text alignment
     */
    public String getLQAlign() { return this.align; }

    /**
     * Getter for alignment property
     * @return TextAlignment for text
     */
    public TextAlignment getAlign() {
        switch(this.align.toLowerCase()) {
//            Left aligned
            case "left":
                return TextAlignment.LEFT;
//                Centered
            case "center":
                return TextAlignment.CENTER;
//                Right aligned
            case "right":
                return TextAlignment.RIGHT;
//                Justified
            case "justify":
                return TextAlignment.JUSTIFY;
//                Defaults to left aligned
            default:
                return TextAlignment.LEFT;
        }
    }

    /**
     * Getter for vertical position
     * @return string describing position
     */
    public String getLQPosition() { return this.position; }

    /**
     * Override of toString method
     * @return String describing formatting properties
     */
    @Override
    public String toString() {
        return "PWSFonts: font = " + this.pwsFont + ", italic = " + this.pwsItalic + ", bold = " + this.pwsBold + ", textsize = " + this.pwsTextsize + ", underline = " + this.pwsUnderline + ", alignment = " + this.align + ", position = " + this.position;
    }
}
