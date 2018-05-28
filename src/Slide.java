import javafx.scene.control.Button;
import java.util.ArrayList;

public class Slide {

	// Have a super list of all the media objects
	protected ArrayList<FLMedia> mediaList;
	protected String id;
	protected String type;
	private Defaults slideDefaults;
	protected String answerNum;
    protected Integer answerNumInt = 0;
	protected Boolean correct;
	protected Boolean answered = false;//, correct = false;
	protected Boolean[] correctArray;

	//private Interaction interaction;

	public Slide(String id, String type) {
		this.id = id;
		this.type = type;
		this.mediaList = new ArrayList<FLMedia>();
		this.correctArray = new Boolean[4];
	 }

	public String getId() { return this.id; }

	public String getType() { return this.type; }

	public void setSlideDefaults(Defaults newDefault) { this.slideDefaults = newDefault; }

	public Defaults getSlideDefaults() { return this.slideDefaults; }

	public void add(FLAudio newAudio) { this.mediaList.add(newAudio); }

	public void add(FLImage newImage) { this.mediaList.add(newImage); }

	public void add(FLText newText) { this.mediaList.add(newText); }

	//public void add(Interaction newInteraction) { this.interaction = newInteraction; }

	public void add(FLButton newButton) {this.mediaList.add(newButton); }

	public ArrayList<FLMedia> getMediaList() { return this.mediaList; }

	public FLAudio getAudio(String audioID) {
		for(FLMedia media : this.mediaList) {
			if(media.getId().equals(audioID)) {
				return (FLAudio)media;
			}
		}
		return null;
	}

	public FLImage getImage(String imageID) {
		for(FLMedia media : this.mediaList) {
			if(media.getId().equals(imageID)) {
				return (FLImage)media;
			}
		}
		return null;
	}

	public void setCorrect(Boolean correct){
		this.correct = correct;
	}

	public void setAnswered(Boolean answered){
		this.answered = answered;
	}

	public void setAnswerNum(String answernum){
		this.answerNum = answernum;
	}

	public String getAnswerNum(){
		return this.answerNum;
	}

    public void setAnswerNumInt(Integer answernumInt){
        this.answerNumInt = answernumInt;
    }

    public Integer getAnswerNumInt(){
        return this.answerNumInt;
    }

	public Boolean getCorrect(){
		return this.correct;
	}

	public Boolean getAnswered(){
		return this.answered;
	}

	public void setCorrectArray(Boolean correct, Integer answerNum){
		this.correctArray[answerNum] = correct;
		System.out.println(correctArray[0] + ", " + correctArray[1] + ", " + correctArray[2] + ", " + correctArray[3]);
	}

    public Boolean[] getCorrectArray() {
        return correctArray;
    }
}
