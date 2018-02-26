import java.awt.Point;

public class FLImage {

	private FLPosition position;
	private double width;
	private double height;
	private String path;

	public FLImage(FLPosition position, String path) {
		this.position = position;
		this.width = this.position.getPos2().getX() - this.position.getPos1().getX();
		this.height = this.position.getPos2().getY() - this.position.getPos1().getY();
		this.path = path;
	}

	public void setPosition(double x, double y, double x2, double y2) {
		this.position = new FLPosition(x, y, x2, y2);
		this.width = x2 - x;
		this.height = y2 - y;
	}

	public void setPath(String path) {
		this.path = path;
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

	public String getPath() {
		return this.path;
	}

}
