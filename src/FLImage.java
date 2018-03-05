// Supported image filetypes are:
// - BMP
// - GIF
// - JPEG
// - PNG

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.Point;
import java.io.File;

public class FLImage extends FLMedia<ImageView> {

	private String path;
	private Position position;
	private int layer;
	private double width;
	private double height;
	public ImageView iView;

	public FLImage(String imagePath, Position position, int layer, double width, double height) {
		this.path = imagePath;
		this.position = position;
		this.layer = layer;
		this.width = width;
		this.height = height;
		this.iView = new ImageView(new Image(getClass().getResource(imagePath).toExternalForm()));
		this.iView.setFitWidth(width);
		this.iView.setFitHeight(height);
	}

	@Override
	public ImageView getMedia() {
		return this.iView;
	}

	public void setPosition(double x, double y) {
		this.position = new Position(x, y);

		printProperties(this);
	}

	public String getPath() { return this.path; }

	// Retuns the top-left point
	public Position getPositition() {
		return this.position;
	}

	public double getWidth() { return this.iView.getFitWidth(); }

	public double getHeight() { return this.iView.getFitHeight(); }

	public void printProperties(FLImage image) {
		System.out.println("");
		System.out.println("New FLImage Created. Listing properties:");
		System.out.println("	Path: " + image.getPath());
		System.out.println("	Position: x = " + image.position.x + ", y = " + image.position.y);
		System.out.println("	Width: " + image.getWidth());
		System.out.println("	Height: " + image.getHeight());
	}

}
