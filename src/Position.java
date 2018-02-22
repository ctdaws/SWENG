public class FLPosition {
	private Point pos1;
	private Point pos2;
	
	public setPos1(int x, int y) {
		this.pos1 = new Point(x, y);
	}
	
	public setPos2(int x2, int y2) {
		this.pos2 = new Point(x2, y2);
	}
	
	public getPos1() {
		return this.pos1;
	}
	
	public getPos2() {
		return this.pos2;
	}
}