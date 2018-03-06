<<<<<<< HEAD
=======
// Supported image filetypes are:
// - BMP
// - GIF
// - JPEG
// - PNG

>>>>>>> master
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.Point;
import java.io.File;

<<<<<<< HEAD
public class FLImage {

	private Position position;
	private double width;
	private double height;

	public ImageView iView;

	public FLImage(String imagePath, Position position) {
		this.iView = new ImageView(new Image(new File(imagePath).toURI().toString()));
		this.position = position;

		this.iView.setFitWidth(this.position.getPos2().getX() - this.position.getPos1().getX());
		this.iView.setFitHeight(this.position.getPos2().getY() - this.position.getPos1().getY());

		//this.width = this.position.getPos2().getX() - this.position.getPos1().getX();
		//this.height = this.position.getPos2().getY() - this.position.getPos1().getY();
	}

	public void setPosition(double x, double y, double x2, double y2) {
		this.position = new Position(x, y, x2, y2);
		this.width = this.position.getWidth();
		this.height = this.position.getHeight();
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
=======
public class FLImage extends FLMedia<ImageView> {

	private String fileName;
	private Position position;
	private double width;
	private double height;
	public ImageView iView;

	public FLImage(String imageFile, Position position, int layer, double width, double height) {
		this.fileName = imageFile;
		this.position = position;
		this.width = width;
		this.height = height;
		this.iView = new ImageView(new Image(this.getClass().getResource(imageFile).toExternalForm()));
		this.iView.setFitWidth(width);
		this.iView.setFitHeight(height);

		//printProperties(this);
	}

	@Override
	public ImageView getMedia() {
		return this.iView;
	}

	public void setPosition(double x, double y) {
		this.position = new Position(x, y);

		printProperties(this);
	}

	public String getFileName() { return this.fileName; }

	// Retuns the top-left point
	public Position getPositition() {
		return this.position;
	}

	public double getWidth() { return this.iView.getFitWidth(); }

	public double getHeight() { return this.iView.getFitHeight(); }

	public void printProperties(FLImage image) {
		System.out.println("");
		System.out.println("New FLImage Created. Listing properties:");
		System.out.println("	File Name: " + image.getFileName());
		System.out.println("	Position: x = " + image.position.getX() + ", y = " + image.position.getY());
		System.out.println("	Width: " + image.getWidth());
		System.out.println("	Height: " + image.getHeight());
>>>>>>> master
	}

}
