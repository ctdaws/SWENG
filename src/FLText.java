import javafx.scene.text.*;
import javafx.scene.paint.*;

public class FLText extends FLMedia<Text> {

// TODO Allow for mid-text formatting
// NOTE StackOverflow you beautiful son of a bitch
// IDEA Use javafx TextFlow to solve formatting problems
// QUESTION Why did I not try Google this before?

	private Text text;
	private Position position;
	private int layer;
	private Colors color;
	private Fonts font;
	private Transitions transition;

	public FLText(String textContent, int layer) {
		this.text = new Text(textContent);
		this.layer = layer;
	}

	public FLText(String textContent, Position position, int layer) {
		this.position = position;
		this.layer = layer;
		this.text = new Text(this.position.getPos1().getX(), this.position.getPos1().getY(), textContent);
	}

	public FLText(String textContent, double xPos, double yPos, int layer) { 
		this.text = new Text(xPos, yPos, textContent); 
		this.layer = layer;
	}

	public FLText(String textContent, Position pos, int layer, Colors color, Fonts font) {
		this.position = pos;
		this.layer = layer;
		this.text = new Text(this.position.getPos1().getX(), this.position.getPos1().getY(), textContent);
		this.color = color;
		this.font = font;
		this.propertiesToText();
	}

	public FLText(String textContent, Position pos, int layer, Colors color, Fonts font, Transitions transition) {
		this.position = position;
		this.layer = layer;
		this.text = new Text(this.position.getPos1().getX(), this.position.getPos1().getY(), textContent);
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
		if(this.position.getWidth() != 0) {
			this.text.setWrappingWidth(this.position.getWidth());
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
