import java.awt.Point;

public class FLAudio {
	
	private Point position;
	private Point position2;
	private String path;
	
	public void setPosition(double x, double y, double x2, double y2) {
		this.position = new Point(x, y);
		this.position2 = new Point(x2, y2);
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public Point getPositition() {
		return this.position;
	}
	
	public Point getPositition2() {
		return this.position2;
	}
	
	public String getPath() {
		return this.path;
	}
	
}
