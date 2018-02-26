import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Point;	

public class FLImage {

	private Position position;
	private double width;
	private double height;

	private BufferedImage image;

	public FLImage(String imagePath, Position position) {
		try {
			this.image = ImageIO.read(new File(imagePath));
			this.position = position;
			this.width = this.position.getPos2().getX() - this.position.getPos1().getX();
			this.height = this.position.getPos2().getY() - this.position.getPos1().getY();
		} catch(IOException e) {
			e.printStackTrace();
		}		
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
