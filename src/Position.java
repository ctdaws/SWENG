import java.awt.Point;

public class FLPosition {

	private Point pos1;
	private Point pos2;

// TODO fix int casts in Point()

	public FLPosition(double x1, double y1, double x2, double y2) {
		this.pos1 = new Point((int)x1, (int)y1);
		this.pos2 = new Point((int)x2, (int)y2);
	}

	public void setPos1(double x, double y) {
		this.pos1 = new Point((int)x, (int)y);
	}

	public void setPos2(double x2, double y2) {
		this.pos2 = new Point((int)x2, (int)y2);
	}

	public Point getPos1() {
		return this.pos1;
	}

	public Point getPos2() {
		return this.pos2;
	}

}
