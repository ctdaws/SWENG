package Old;

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
	private ImageView iView;

	public FLImage(String id, String imageFile, Position position, double width, double height, boolean visibleOnLoad) {
		this.id = id;
		this.fileName = imageFile;
		this.position = position;
		this.iView = new ImageView(new Image(this.getClass().getResource(imageFile).toExternalForm()));
		this.iView.setX(position.getX());
		this.iView.setY(position.getY());
		this.iView.setFitWidth(width);
		this.iView.setFitHeight(height);
		this.isVisible = visibleOnLoad;

		this.setVisibility();

		//printProperties(this);
	}

	@Override
	public ImageView getMedia() { return this.iView; }

	public void setPosition(double x, double y) { this.position = new Position(x, y); }

	private String getFileName() { return this.fileName; }

	// Returns the top-left point
	public Position getPosition() { return this.position; }

	private double getWidth() { return this.iView.getFitWidth(); }

	private double getHeight() { return this.iView.getFitHeight(); }

	private void setVisibility() {
		if(this.isVisible) {
			this.iView.setVisible(true);
		} else {
			this.iView.setVisible(false);
		}
	}

	public void setVisible() {
		this.isVisible = true;
		this.setVisibility();
	}

	public void printProperties(FLImage image) {
		System.out.println("");
		System.out.println("New FLImage Created. Listing properties:");
		System.out.println("	File Name: " + image.getFileName());
		System.out.println("	Position: x = " + image.position.getX() + ", y = " + image.position.getY());
		System.out.println("	Width: " + image.getWidth());
		System.out.println("	Height: " + image.getHeight());
	}

}
