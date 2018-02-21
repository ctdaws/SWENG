import javafx.scene.text.Text;

import javafx.scene.layout.AnchorPane;

class Slide extends AnchorPane{
	// TEST: some sample text;
	public Text text;

	public String slideID;

	public Slide(String ID) {
		this.slideID = ID;
		text = new Text("Slide:" + ID);
	} 


}