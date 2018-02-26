import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.Point;	

public class FLImage {

	private Position position;
	private double width;
	private double height;

	public ImageView iView;

	public FLImage(String imagePath, Position position) {
		this.iView = new ImageView(new Image("file:" + imagePath));
		this.position = position;

		this.iView.setFitWidth(this.position.getPos2().getX() - this.position.getPos1().getX());
		this.iView.setFitHeight(this.position.getPos2().getY() - this.position.getPos1().getY());

		//this.width = this.position.getPos2().getX() - this.position.getPos1().getX();
		//this.height = this.position.getPos2().getY() - this.position.getPos1().getY();
	}

	public void setPosition(double x, double y, double x2, double y2) {
		this.position = new Position(x, y, x2, y2);
		this.width = x2 - x;
		this.height = y2 - y;
	}

	// Retuns the top-left point
	public Point getPositition() {
		return this.position.getPos1();
	}

	public double getWidth() {
		return this.width;
	}

	public double getHeight() {
		return this.height;
	}	

}
