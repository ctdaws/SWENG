import javafx.scene.text.*;
import javafx.scene.paint.*;

// NOTE bold text FontWeight.BOLD
// NOTE italic text FontPosture.ITALIC

public class FLText2 extends FLMedia<TextFlow> {

  private TextFlow textFlow;
  private Position position;
  private double width;
  private Colors defaultColor;
  private Font defaultFont;
  private Transitions transition;

	@Override
	public TextFlow getMedia() { return this.textFlow; }

  public FLText2(Position position, double width, Defaults slideDefault, Colors color, Font font, Transitions transition) {
    this.textFlow = new TextFlow();
    this.position = position;
    this.width = width;
    this.textFlow.setLayoutX(this.position.getX());
    this.textFlow.setLayoutY(this.position.getY());
    this.textFlow.setMaxWidth(this.width);
    this.defaultColor = color;
    this.defaultFont = font;
    this.transition = transition;
  }

  public void addSnippet(TextSnippet text) {
    this.textFlow.add(text.getText());
  }

  public TextSnippet newSnippet(String textString, Color color, Font font) {
    return new TextSnippet(textString, color, font);
  }

  private class TextSnippet {
    private Text text;
    private Colors color;
    private Font font;

    public TextSnippet(String textString, Colors color, Font font) {
      this.text = new Text(textString);
    }

    public Text getText() {
      return this.text;
    }

  }

}
