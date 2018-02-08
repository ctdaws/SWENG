import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

class Image {

	private BufferedImage image;
	private Position position;
	private int layer;

	public Image(String image, Position position, int layer) {
		try { 
			this.image = ImageIO.read(new File(image)); 
		} catch (IOException e) {
			System.out.println("IOException on reading image" + image);
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
	

	public Position getPosition() { return this.position; }
	public void setPosition(Position position) { this.position = position; } 
	
	public int getLayer() { return this.layer; }
	public void setLayer(int layer) { this.layer = layer; }
	
}