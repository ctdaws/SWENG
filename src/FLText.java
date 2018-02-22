import javafx.scene.text.*;
import java.awt.point;
import javafx.scene.paint.Paint;

public class FLText {
	
	private Text text;
	
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
	
	public void setPosition(int x, int y) {
		this.text.setX(x);
		this.text.setY(y);
	}
	
	public Point getPosition() {
		return (new Point(this.text.getX(), this.text.getY()));
	}
	
	public void setColor(String color) {
		this.text.setFill(valueOf(color));
	}
	
	public Color getColor() {
		return this.text.getFill();
	}
	
}
