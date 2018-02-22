import java.awt.point;

public class FLImage {
	
	private Point position;
	private double width;
	private double height;
	private String path;
	
	public void setPosition(double x, double y, double x2, double y2) {
		this.position = new Point(x, y);
		this.width = x2 - x;
		this.height = y2 - y;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public Point getPositition() {
		return this.position;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public String getPath() {
		return this.path;
	}
	
}
