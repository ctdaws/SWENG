import javafx.scene.text.*;
import javafx.scene.paint.*;

public class FLText extends FLMedia<Text> {

// TODO Allow for mid-text formatting
// NOTE StackOverflow you beautiful son of a bitch
// IDEA Use javafx TextFlow to solve formatting problems
// QUESTION Why did I not try Google this before?

	private Text text;
	private Position position;
	private double width;
	private int layer;
	private Colors color;
	private Fonts font;
	private Transitions transition;

	public FLText(String textContent, int layer, double width) {
		this.text = new Text(textContent);
		this.width = width;
		this.layer = layer;
	}

	public FLText(String textContent, Position position, int layer, double width) {
		this.position = position;
		this.layer = layer;
		this.width = width;
		this.text = new Text(this.position.x, this.position.y, textContent);
	}

	public FLText(String textContent, double xPos, double yPos, int layer, double width) { 
		this.text = new Text(xPos, yPos, textContent); 
		this.layer = layer;
		this.width = width;
	}

	public FLText(String textContent, Position pos, int layer, double width, Colors color, Fonts font) {
		this.position = pos;
		this.layer = layer;
		this.width = width;
		this.text = new Text(this.position.x, this.position.y, textContent);
		this.color = color;
		this.font = font;
		this.propertiesToText();
	}

	public FLText(String textContent, Position pos, int layer, double width, Colors color, Fonts font, Transitions transition) {
		this.position = position;
		this.layer = layer;
		this.width = width;
		this.text = new Text(this.position.x, this.position.y, textContent);
		this.color = color;
		this.font = font;
		this.transition = transition;
		this.propertiesToText();
	}

	@Override
	public Text getMedia() { return this.text; }

	public void propertiesToText() {
		if(this.font != null) {
			String css = new String();
			this.setFont(this.font.getFontFamily(), this.font.getSize());
			this.text.setUnderline(this.font.getUnderlined());
			if(this.font.getBold()) { css += "-fx-font-weight: bold;"; }
			if(this.font.getItalic()) { css += "-fx-font-style: italic;"; }
			this.text.setStyle(css);
		}
		if(this.color != null) {
			this.setColor(this.color.getColor());
		}
		if(this.width != 0) {
			this.text.setWrappingWidth(this.width);
		}
	}

	public Text getText() { return this.text; }

	public void setFont(String typeface, int size) { this.text.setFont(new Font(typeface, size)); }

	

	public void setText(String textContent) {
		this.text.setText(textContent);
	}

	public void setColor(String color) { this.text.setFill(Color.valueOf(color)); }

	public void setColor(Color color) { this.text.setFill(color); }

}
