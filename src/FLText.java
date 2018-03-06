<<<<<<< HEAD
//TODO Implement support for BOLD and ITALIC formatting
//NOTE May have to be achieved via CSS

import javafx.scene.text.*;
import java.awt.Point;
import javafx.scene.paint.*;

public class FLText {

	private Text text;
	private Position position;
	private Colors color;
	private Fonts font;
	private Transitions transition;

	public FLText() {
		this.text = new Text("TEST.");
	}

	public FLText(String textContent) {
		this.text = new Text(textContent);
	}

	public FLText(String textContent, Position position) {
		this.position = position;
		this.text = new Text(this.position.getPos1().getX(), this.position.getPos1().getY(), textContent);
	}

	public FLText(String textContent, double xPos, double yPos) {
		this.text = new Text(xPos, yPos, textContent);
	}

	public FLText(String textContent, Position pos, Colors color, Fonts font) {
		this.position = pos;
		this.text = new Text(this.position.getPos1().getX(), this.position.getPos1().getY(), textContent);
		this.color = color;
		this.font = font;
		this.propertiesToText();
	}

	public FLText(String textContent, Position pos, Colors color, Fonts font, Transitions transition) {
		this.position = position;
		this.text = new Text(this.position.getPos1().getX(), this.position.getPos1().getY(), textContent);
		this.color = color;
		this.font = font;
		this.transition = transition;
		this.propertiesToText();
	}

	public void propertiesToText() {
		if(this.font != null) {
			this.setFont(this.font.getFontFamily(), this.font.getSize());
			this.text.setUnderline(this.font.getUnderlined());
			if(this.font.getBold() && this.font.getItalic()) { this.text.setStyle("-fx-font-weight: bold; -fx-font-style: italic;"); }
			else if(this.font.getBold()) { this.text.setStyle("-fx-font-weight: bold;"); }
			else if(this.font.getItalic()) { this.text.setStyle("-fx-font-style: italic;"); }
		}
		if(this.color != null) {
			this.setColor(this.color.getColor());
		}
		if(this.position.getWidth() != 0) {
			this.text.setWrappingWidth(this.position.getWidth());
		}
	}

	public Text getText() {
		return this.text;
	}

	public void setFont(String typeface, int size) {
		this.text.setFont(new Font(typeface, size));
	}

	public void setColor(String color) {
		this.text.setFill(Color.valueOf(color));
	}

	public void setColor(Color color) {
		this.text.setFill(color);
	}
=======
import javafx.scene.text.*;
import javafx.scene.paint.*;
import java.util.ArrayList;

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
    this.defaultStyle = slideDefault.getDefaultStyle();
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
      this.setStyle(this.style.getFontFamily(), this.style.getSize(), this.style.getBold(), this.style.getItalic());
      this.text.setUnderline(this.style.getUnderlined());
      this.setColor(this.color.getColor());
    }

    public void setStyle(String typeface, int size, FontWeight bold, FontPosture italic) { this.text.setFont(Font.font(typeface, bold, italic, size)); }

    public void setColor(Color color) { this.text.setFill(color); }

  }
>>>>>>> master

}
