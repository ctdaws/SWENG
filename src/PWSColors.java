import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;

/**
 * Class containing information about the color and fill attributes described in xml.
 *
 * @author Samuel Broughton
 * @version 1.2
 */
public class PWSColors {
//    String representation of colours from pws xml
    private String pwsColor;
    private String pwsFill;
//    JavaFX paint objects
    private Paint color;
    private Paint fill;

    /**
     * Constructor for pwsColours object
     * @param color String for color
     * @param fill String for fill
     */
    public PWSColors(String color, String fill) {
//        Store color and fill strings
        this.pwsColor = color;
        this.pwsFill = fill;

//        Generate Paint objects from strings
//        Color
//        Is the color a gradient as defined in the PWS (checks for string 'gradient' in colour string)
        if(color.toLowerCase().contains("gradient".toLowerCase())) {
//            Breaks string at '(', ',' and ')' to seperate string to form ['gradient'], ['color1'], ['color2']
            String array[] = color.split("[\\(||,||\\)]");
//            Remove whitespace from colour strings
            String color1 = array[1].trim();
            String color2 = array[2].trim();
//            Generate linear gradient from colours
            this.color = LinearGradient.valueOf("from 50% 0% to 50% 100%, " + color1 + " 0%, " + color2 + " 100%");
        }
//        Colour is not a gradient
        else {
//            Create colour from string
            this.color = Color.valueOf(color);
        }

//        Fill
//        Is the fill a gradient as defined in the PWS (checks for string 'gradient' in the fill string)
        if(fill.toLowerCase().contains("gradient".toLowerCase())) {
//            Breaks string at '(', ',' and ')' to seperate string to form ['gradient'], ['color1'], ['color2']
            String array[] = fill.split("[\\(||,||\\)]");
//            Remove whitespace from colour strings
            String color1 = array[1].trim();
            String color2 = array[2].trim();
//            Generate linear gradient from colours
            this.fill = LinearGradient.valueOf("from 50% 0% to 50% 100%, " + color1 + " 0%, " + color2 + " 100%");
        }
//            Fill is not a gradient
        else {
//            Create colour from string
            this.fill = Color.valueOf(fill);
        }
    }

    /**
     * Get colour string
     * @return colour represented in string form as given to constructor
     */
    public String getPwsColor() { return this.pwsColor; }

    /**
     * Get fill string
     * @return fill represented in string form as given to the constructor
     */
    public String getPwsFill() { return this.pwsFill; }

    /**
     * Get colour
     * @return colour as a JavaFX Paint object
     */
    public Paint getColor() { return this.color; }

    /**
     * Get fill
     * @return fill as a JavaFX Paint object
     */
    public Paint getFill() { return this.fill; }

    /**
     * Overrine of toString method
     * @return String describing this pwsColour object
     */
    @Override
    public String toString() {
        return "PWSColors: color = " + color + ", fill = " + fill;
    }
}
