import javafx.scene.text.*;
import java.awt.Point;
import javafx.scene.paint.*;

public class FLText {

	private Text text;
	private Position position;
	private Colors color;
	private Fonts font;
	private Transitions transition;

	public FLText(String textContent, Position pos, Colors color, Fonts font, Transitions transition) {
		this.text.setText(textContent);
		this.position = pos;
		this.color = color;
		this.font = font;
		this.transition = transition;
	}

	public void setText(String textContent) {
		this.text.setText(textContent);
	}

	public String getText() {
		return this.text.getText();
	}

	public void setFont(String typeface, int size) {
		this.text.setFont(new Font(typeface, size));
	}

	public String getFontFamily() {
		return this.text.getFont().getFamily();
	}

	public double getFontSize() {
		return this.text.getFont().getSize();
	}

	public void setPosition(double x, double y) {
		this.text.setX(x);
		this.text.setY(y);
	}

	public Point getPosition() {
		return (new Point((int)this.text.getX(), (int)this.text.getY()));
	}

	public void setColor(String color) {
		this.text.setFill(Color.valueOf(color));
	}

	// public Color getColor() {
	// 	return this.text.getFill();
	// }

}
