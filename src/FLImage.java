// Supported image filetypes are:
// - BMP
// - GIF
// - JPEG
// - PNG

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.Point;
import java.io.File;

public class FLImage {

	private String path;	// Stores path here as it doesn't appear to be retrievable from ImageView/Image
	private Position position;
	public ImageView iView;

	public FLImage(String imagePath, Position position) {
		this.path = imagePath;
		this.iView = new ImageView(new Image(new File(this.path).toURI().toString()));
		this.position = position;

		this.iView.setFitWidth(this.position.getPos2().getX() - this.position.getPos1().getX());
		this.iView.setFitHeight(this.position.getPos2().getY() - this.position.getPos1().getY());

		printProperties(this);
	}

	public void setPosition(double x, double y, double x2, double y2) {
		this.position = new Position(x, y, x2, y2);
		this.iView.setFitWidth(this.position.getPos2().getX() - this.position.getPos1().getX());
		this.iView.setFitHeight(this.position.getPos2().getY() - this.position.getPos1().getY());
	}

	public String getPath() {
		return this.path;
	}

	// Retuns the top-left point
	public Point getPositition() {
		return this.position.getPos1();
	}

	public double getWidth() {
		return this.iView.getFitWidth();
	}

	public double getHeight() {
		return this.iView.getFitHeight();
	}

	public void printProperties(FLImage image) {
		System.out.println("");
		System.out.println("New FLImage Created. Listing properties:");
		System.out.println("	Path: " + image.getPath());
		System.out.println("	Position: x = " + image.position.getPos1().getX() + ", y = " + image.position.getPos1().getY());
		System.out.println("	Width: " + image.getWidth());
		System.out.println("	Height: " + image.getHeight());
	}

}
