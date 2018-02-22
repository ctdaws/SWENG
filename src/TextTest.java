import java.awt.Point;

class TextTest {
	
	private String text;
	private Point position;
	private int layer;


	public TextTest(String text, Point position, int layer) {
		this.text = text;
		this.position = position;
		this.layer = layer; 
	}

	public String getText() { return this.text; }
	public void setText(String text) { this.text = text; }

	public Point getPosition() { return this.position; }
	public void setPosition(Point position) { this.position = position; } 
	
	public int getLayer() { return this.layer; }
	public void setLayer(int layer) { this.layer = layer; }

}