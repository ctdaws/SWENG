import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Point;

class ImageTest {

	private BufferedImage image;
	private Point position;
	private int layer;

	public ImageTest(String image, Point position, int layer) {
		try { 
			this.image = ImageIO.read(new File(image)); 
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.position = position;
		this.layer = layer;
	}

	public BufferedImage getImage() { return this.image; }
	public void setImage(BufferedImage newImage) { this.image = newImage; }
	public void setImage(String newImage) {
		try { 
			this.image = ImageIO.read(new File(newImage)); 
		} catch (IOException e) {
			System.out.println("IOException on reading image" + image);
		}
	}
	

	public Point getPosition() { return this.position; }
	public void setPosition(Point position) { this.position = position; } 
	
	public int getLayer() { return this.layer; }
	public void setLayer(int layer) { this.layer = layer; }
	
}