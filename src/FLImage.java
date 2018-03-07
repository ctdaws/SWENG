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

	private String fileName;
	private Position position;
	private double width;
	private double height;
	public ImageView iView;

	public FLImage(String imageFile, Position position, double width, double height) {
		this.fileName = imageFile;
		this.position = position;
		this.width = width;
		this.height = height;
		this.iView = new ImageView(new Image(this.getClass().getResource(imageFile).toExternalForm()));
		this.iView.setX(position.getX());
		this.iView.setY(position.getY());
		this.iView.setFitWidth(width);
		this.iView.setFitHeight(height);

		//printProperties(this);
	}

	@Override
	public ImageView getMedia() { return this.iView; }

	public void setPosition(double x, double y) { this.position = new Position(x, y); }

	public String getFileName() { return this.fileName; }

	// Retuns the top-left point
	public Position getPositition() { return this.position; }

	public double getWidth() { return this.iView.getFitWidth(); }

	public double getHeight() { return this.iView.getFitHeight(); }

	public void printProperties(FLImage image) {
		System.out.println("");
		System.out.println("New FLImage Created. Listing properties:");
		System.out.println("	File Name: " + image.getFileName());
		System.out.println("	Position: x = " + image.position.getX() + ", y = " + image.position.getY());
		System.out.println("	Width: " + image.getWidth());
		System.out.println("	Height: " + image.getHeight());
	}

}
