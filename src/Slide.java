import javafx.scene.text.Text;
import java.util.ArrayList;

public class Slide {

	private String id;
	private Defaults slideDefaults;
	public ArrayList<FLAudio> audioList;
	public ArrayList<FLImage> imageList;
	public ArrayList<FLText> textList;
	private Interaction interaction;


	public Slide(String id) {
		this.id = id;
		this.textList = new ArrayList<FLText>();
		this.imageList = new ArrayList<FLImage>();
		this.audioList = new ArrayList<FLAudio>();
	 }

	public String getId() { return this.id; }

	public void setSlideDefaults(Defaults newDefault) { this.slideDefaults = newDefault; }

	public Defaults getSlideDefaults() { return this.slideDefaults; }

	public void add(FLAudio newAudio) { this.audioList.add(newAudio); }

	public void add(FLImage newImage) { this.imageList.add(newImage); }

	public void add(FLText newText) { this.textList.add(newText); }

	public void add(Interaction newInteraction) { this.interaction = newInteraction; }

}
