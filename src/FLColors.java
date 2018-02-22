import javafx.scene.paint.Color;

public class FLColors {
	
	Color color;
	Color backgroundColor;
	
	public void setColor(String color) {
		this.color = valueOf(color);
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setFill(String color) {
		this.backgroundColor = valueOf(color);
	}
	
	public Color getFill() {
		return this.backgroundColor;
	}
	
}
