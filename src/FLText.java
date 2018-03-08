import javafx.scene.text.*;
import javafx.scene.paint.*;
import java.util.ArrayList;

public class FLText extends FLMedia<TextFlow> {

  private TextFlow textFlow;
  private Colors defaultColor;
  private TextStyle defaultStyle;
  private Transitions transition;

	@Override
	public TextFlow getMedia() { return this.textFlow; }

  public FLText(Position position, double width, Defaults slideDefault, Transitions transition) {
    this.textFlow = new TextFlow();
    this.textFlow.setLayoutX(position.getX());
    this.textFlow.setLayoutY(position.getY());
    this.textFlow.setMaxWidth(width);
    this.defaultColor = slideDefault.getDefaultColors();
    this.defaultStyle = slideDefault.getDefaultStyle();
    this.transition = transition;
  }

  public void setColor(Colors color) { this.defaultColor = color; }
  public Colors getColor() { return this.defaultColor; }

  public TextStyle getStyle() { return this.defaultStyle; }

  public void addSnippet(TextSnippet text) { this.textFlow.getChildren().add(text.getText()); }

  public void add(String textString, Colors color, TextStyle style) { this.addSnippet(new TextSnippet(textString, color, style)); }

  public void add(String textString, Colors color) { this.addSnippet(new TextSnippet(textString, color, this.defaultStyle)); }

  public void add(String textString, TextStyle style) { this.addSnippet(new TextSnippet(textString, this.defaultColor, style)); }

  public void add(String textString) { this.addSnippet(new TextSnippet(textString, this.defaultColor, this.defaultStyle)); }

  public class TextSnippet {
    
    private Text text;
    private Colors color;
    private TextStyle style;

    public TextSnippet(String textString, Colors color, TextStyle style) {
      this.text = new Text(textString);
      this.color = color;
      this.style = style;
      this.propertiesToText();
    }

    public Text getText() { return this.text; }

    public void propertiesToText() {
      this.setStyle(this.style.getFontFamily(), this.style.getSize(), this.style.getBold(), this.style.getItalic());
      this.text.setUnderline(this.style.getUnderlined());
      this.setColor(this.color.getColor());
    }

    public void setStyle(String typeface, int size, FontWeight bold, FontPosture italic) { this.text.setFont(Font.font(typeface, bold, italic, size)); }

    public void setColor(Color color) { this.text.setFill(color); }

  }

}
