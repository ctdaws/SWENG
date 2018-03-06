<<<<<<< HEAD
import java.awt.Point;

public class Position {

	private Point pos1;
	private Point pos2;

// TODO fix int casts in Point()

	public Position(double x1, double y1, double x2, double y2) {
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

	public double getWidth() {
		return (this.pos2.getX() - this.pos1.getX());
	}

	public double getHeight() {
		return (this.pos2.getY() - this.pos1.getY());
	}

=======
// Does this class even need to exist?
public class Position {

	public double x;
	public double y;

	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}
>>>>>>> master
}
