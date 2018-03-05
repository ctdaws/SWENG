import javafx.scene.text.*;
import javafx.scene.paint.*;
import java.util.ArrayList;

// NOTE bold text FontWeight.BOLD
// NOTE italic text FontPosture.ITALIC

public class FLText extends FLMedia<TextFlow> {

  private ArrayList<TextSnippet> snippetList;
  private TextFlow textFlow;
  private Position position;
  private double width;
  private Colors defaultColor;
  private TextStyle defaultStyle;
  private Transitions transition;

	@Override
	public TextFlow getMedia() { return this.textFlow; }

  public FLText(Position position, double width, Defaults slideDefault, Transitions transition) {
    this.textFlow = new TextFlow();
    this.position = position;
    this.width = width;
    this.textFlow.setLayoutX(this.position.getX());
    this.textFlow.setLayoutY(this.position.getY());
    this.textFlow.setMaxWidth(this.width);
    this.defaultColor = slideDefault.getDefaultColors();
    this.defaultStyle = slideDefault.getDefaultFonts();
    this.transition = transition;
  }

  public void addSnippet(TextSnippet text) {
    this.textFlow.getChildren().add(text.getText());
  }

  public void flowText() {
    for(TextSnippet text : snippetList) {
      this.textFlow.getChildren().add(text.getText());
    }
  }

  public void add(String textString, Colors color, TextStyle style) {
     this.addSnippet(new TextSnippet(textString, color, style));
  }

  public void add(String textString, Colors color) {
    this.addSnippet(new TextSnippet(textString, color, this.defaultStyle));
  }

  public void add(String textString, TextStyle style) {
    this.addSnippet(new TextSnippet(textString, this.defaultColor, style));
  }

  public void add(String textString) {
    this.addSnippet(new TextSnippet(textString, this.defaultColor, this.defaultStyle));
  }

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

    public Text getText() {
      return this.text;
    }

    public void propertiesToText() {
      if(this.style != null) {
        String css = new String();
        this.setFont(this.style.getFontFamily(), this.style.getSize());
        this.text.setUnderline(this.style.getUnderlined());
        if(this.style.getBold()) { css += "-fx-font-weight: bold;"; }
        if(this.style.getItalic()) { css += "-fx-font-style: italic;"; }
        this.text.setStyle(css);
      }
      if(this.color != null) {
        this.setColor(this.color.getColor());
      }
    }

    public void setFont(String typeface, int size) { this.text.setFont(new Font(typeface, size)); }

    public void setColor(Color color) { this.text.setFill(color); }

  }

}
