import javafx.scene.text.Text;

class Slide {
	// TEST: some sample text;
	public Text text;

	public String ID;

	public Slide(String ID) {
		this.ID = ID;
		text = new Text("Slide:" + ID);
		text.setLayoutX(50);
		text.setLayoutY(50);
	} 
}