import javafx.scene.paint.*;

public class Colors {

	Color color;
	Color backgroundColor;

<<<<<<< HEAD
	public Colors(String color) {
		this.color = Color.valueOf(color);
	}
=======
	public Colors(String color) { this.color = Color.valueOf(color); }
>>>>>>> master

	public Colors(String color, String backgroundColor) {
		this.color = Color.valueOf(color);
		this.backgroundColor = Color.valueOf(backgroundColor);
	}

<<<<<<< HEAD
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
=======
	public void setColor(String color) { this.color = Color.valueOf(color); }

	public Color getColor() { return this.color; }

	public void setFill(String color) { this.backgroundColor = Color.valueOf(color); }

	public Color getFill() { return this.backgroundColor; }
>>>>>>> master

}
