import javafx.scene.text.Text;
import java.util.ArrayList;

class Slide {
//	TEST: some sample text;
//	public Text text;

	private String id;

	private FLDefaults slideDefaults;

	private ArrayList<FLAudio> audioList;
	private ArrayList<FLImage> imageList;
	private ArrayList<FLText> textList;
//	private ArrayList<FLVideo> videoList;

	private FLInteraction interaction;

// Old constructor
	// public Slide(String ID) {
	// 	this.ID = ID;
	// 	text = new Text("Slide:" + ID);
	// 	text.setLayoutX(50);
	// 	text.setLayoutY(50);
	// }

	public Slide(String id) {
		this.id = id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setSlideDefaults(FLDefaults newDefault) {
		this.slideDefaults = newDefault;
	}

	private void addAudioToSlide(FLAudio newAudio) {
		this.audioList.add(newAudio);
	}

	private void addImageToSlide(FLImage newImage) {
		this.imageList.add(newImage);
	}

	private void addTextToSlide(FLText newText) {
		this.textList.add(newText);
	}

	// private void addVideoToSlide(FLVideo newVideo) {
	// 	this.videoList.add(newVideo);
	// }

	private void addInteractionToSlide(FLInteraction newInteraction) {
		this.interaction = newInteraction;
	}

}
