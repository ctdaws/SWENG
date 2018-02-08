class Text {
	
	private String text;
	private Position position;
	private int layer;


	public Text(String text, Position position, int layer) {
		this.text = text;
		this.position = position;
		this.layer = layer; 
	}

	public String getText() { return this.text; }
	public void setText(String text) { this.text = text; }

	public Position getPosition() { return this.position; }
	public void setPosition(Position position) { this.position = position; } 
	
	public int getLayer() { return this.layer; }
	public void setLayer(int layer) { this.layer = layer; }

}