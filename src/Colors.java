import javafx.scene.paint.*;

public class FLColors {

	Color color;
	Color backgroundColor;

	public FLColors(String color, String backgroundColor) {
		this.color = Color.valueOf(color);
		this.backgroundColor = Color.valueOf(backgroundColor);
	}

	public void setColor(String color) {
		this.color = Color.valueOf(color);
	}

	public Color getColor() {
		return this.color;
	}

	public void setFill(String color) {
		this.backgroundColor = Color.valueOf(color);
	}

	public Color getFill() {
		return this.backgroundColor;
	}

}
