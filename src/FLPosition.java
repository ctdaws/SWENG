public class FLPosition {
	private Point pos1;
	private Point pos2;
	
	public void setPos1(int x, int y) {
		this.pos1 = new Point(x, y);
	}
	
	public void setPos2(int x2, int y2) {
		this.pos2 = new Point(x2, y2);
	}
	
	public Point getPos1() {
		return this.pos1;
	}
	
	public Point getPos2() {
		return this.pos2;
	}
}