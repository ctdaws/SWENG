import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;

public class PWSColors {
    private String pwsColor;
    private String pwsFill;

    private Paint color;
    private Paint fill;

    public PWSColors(String color, String fill) {
        this.pwsColor = color;
        this.pwsFill = fill;

        if(color.toLowerCase().contains("gradient".toLowerCase())) {
            String array[] = color.split("[\\(||,||\\)]");
            String color1 = array[1].trim();
            String color2 = array[2].trim();
            this.color = LinearGradient.valueOf("from 50% 0% to 50% 100%, " + color1 + " 0%, " + color2 + " 100%");
        }
        else {
            this.color = Color.valueOf(color);
        }

        if(fill.toLowerCase().contains("gradient".toLowerCase())) {
            String array[] = fill.split("[\\(||,||\\)]");
            String color1 = array[1].trim();
            String color2 = array[2].trim();
            this.fill = LinearGradient.valueOf("from 50% 0% to 50% 100%, " + color1 + " 0%, " + color2 + " 100%");
        }
        else {
            this.fill = Color.valueOf(fill);
        }
    }

    public String getPwsColor() { return this.pwsColor; }

    public String getPwsFill() { return this.pwsFill; }

    public Paint getColor() { return this.color; }

    public Paint getFill() { return this.fill; }

    @Override
    public String toString() {
        return "PWSColors: color = " + color + ", fill = " + fill;
    }
}
