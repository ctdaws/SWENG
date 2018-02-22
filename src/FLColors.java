import javafx.scene.paint.Color;

public class FLColors {
	
	Color color;
	Color backgroundColor;
	
	public setColor(String color) {
		this.color = valueOf(color);
	}
	
	public getColor() {
		return this.color;
	}
	
	public setFill(String color) {
		this.backgroundColor = valueOf(color);
	}
	
	public getFill() {
		retunr this.backgroundColor;
	}
	
}
