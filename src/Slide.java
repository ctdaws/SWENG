import javafx.scene.control.Button;
import java.util.ArrayList;

public class Slide {

	// Have a super list of all the media objects
	private ArrayList<FLMedia> mediaList;
	protected String id;
    private String type;
	private Defaults slideDefaults;

    //protected Integer answerNumInt = 0;

	//TODO answerslide
	private Boolean answered = false;//, correct = false; TODO answer slide?
	private String answerNum;
	private Boolean gotAnswerCorrect;
	private Boolean[] correctArray;
    private FLAudio incorrectAudio, correctAudio;
	protected FLImage correctImage, incorrectImage0, incorrectImage1, incorrectImage2, incorrectImage3;


	//private Interaction interaction;

//	public Slide(String id, String type) {
//		this.id = id;
//		this.type = type;
//		this.mediaList = new ArrayList<FLMedia>();
//
//	}

	public Slide(String id, String type){
		this.id = id;
		this.type = type;
		this.mediaList = new ArrayList<FLMedia>();

		//answer slide
		this.correctArray = new Boolean[4];
		this.answerNum = answerNum;


		this.correctAudio = new FLAudio(" correct jingle", "CORRECT.mp3", new Position(100, 100));
		this.add(this.correctAudio);

		this.incorrectAudio = new FLAudio(" incorrect jingle", "INCORRECT.wav", new Position(100, 100));
		this.add(this.incorrectAudio);
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



	//TODO probably redundant
	private void showImage(){
		if(mediaList.get(this.mediaList.size()-8) instanceof FLImage) {
			FLImage flImage;// = new FLImage();

			flImage = (FLImage) mediaList.get(this.mediaList.size() - 8);
			flImage.setVisible();

		}
	}

	//TODO Answer methods
	public String getAnswerNum(){
		return this.answerNum;
	}

	public void setCorrectArray(Boolean correct, Integer answerNum){
		this.correctArray[answerNum] = correct;
		System.out.println(correctArray[0] + ", " + correctArray[1] + ", " + correctArray[2] + ", " + correctArray[3]);
	}

	public Boolean[] getCorrectArray() {
		return correctArray;
	}

	protected void setActionListeners(){
		System.out.println("media list size = " + this.mediaList.size() + "id: " + id + " type: " + type);
		System.out.println(this.getAnswered());
		FLButton button1;
		FLButton button2;
		FLButton button3;
		FLButton button4;
		FLButton array[] = new FLButton[4];
		int numberOfButtons = 0;

		for (int i = 1 ; i < this.mediaList.size(); i++) {
			if (mediaList.get(i) instanceof FLButton){
				array[numberOfButtons] = (FLButton)mediaList.get(i);
				numberOfButtons++;
			}
		}

		button1 = array[0];
		button1.getButton().setOnMouseClicked((clickEvent) -> {
			if(!this.getAnswered()) {
				this.setAnswered(true);
				checkCorrect(this.getCorrectArray(), 0);
				System.out.println("pressed answer 1");
			} else{System.out.println("Already Answered!!");}
		});

		button2 = array[1];
		button2.getButton().setOnMouseClicked((clickEvent) -> {
			if(!this.getAnswered()) {
				this.setAnswered(true);
				checkCorrect(this.getCorrectArray(), 1);
				System.out.println("pressed answer 2");
			} else{System.out.println("Already Answered!!");}
		});

		button3 = array[2];
		button3.getButton().setOnMouseClicked((clickEvent) -> {
			if(!this.getAnswered()) {
				this.setAnswered(true);
				checkCorrect(this.getCorrectArray(), 2);
				System.out.println("pressed answer 3");
			} else{System.out.println("Already Answered!!");}
		});

		button4 = array[3];
		button4.getMedia().setOnMouseClicked((clickEvent) -> {
			if(!this.getAnswered()) {
				this.setAnswered(true);
				checkCorrect(this.getCorrectArray(), 3);
				System.out.println("pressed answer 4");
			} else{System.out.println("Already Answered!!");}
		});


	}

	private void setGotAnswerCorrect(Boolean correct) {
		this.gotAnswerCorrect = correct;
	}

	private void setAnswered(Boolean answered) {
		this.answered = answered;
	}

	public void setAnswerNum(String answerNum) {
		this.answerNum = answerNum;
	}

	private Boolean getGotAnswerCorrect() {
		return this.gotAnswerCorrect;
	}

	public Boolean getAnswered() {
		return this.answered;
	}
	public void resetAnswer(){
		setAnswered(false);
		setGotAnswerCorrect(false);
	}


	private void checkCorrect(Boolean[] correctArray, Integer answerNumInt) {
		System.out.println("answerNumInt = " + answerNumInt);
		if (correctArray[answerNumInt]) {
			this.correctAudio.play();
			//showImage();
			this.setGotAnswerCorrect(true);
		}
		else{
			//this.incorrectAudio.play();
			this.setGotAnswerCorrect(false);
			switch (answerNumInt) {
				case 0:
					this.incorrectAudio.play();
					//showImage();
					//this.incorrectImage0.setVisible();
					break;
				case 1:
					this.incorrectAudio.play();
					//showImage();
					//this.incorrectImage1.setVisible();
					break;
				case 2:
					this.incorrectAudio.play();
					//showImage();
					//this.incorrectImage2.setVisible();
					break;
				case 3:
					this.incorrectAudio.play();
					//showImage();
					//this.incorrectImage3.setVisible();
					break;
				default:
					break;
			}
		}
		System.out.println(this.getGotAnswerCorrect());
	}

    //	public void setCorrect(Boolean correct){
//		this.correct = correct;
//	}
//
//	public void setAnswered(Boolean answered){
//		this.answered = answered;
//	}
//
//
//	public Boolean getCorrect(){
//		return this.correct;
//	}
//
//	public Boolean getAnswered(){
//		return this.answered;
//	}
//
//    public void setAnswerNumInt(Integer answernumInt){
//        this.answerNumInt = answernumInt;
//    }
//
//    public Integer getAnswerNumInt(){
//        return this.answerNumInt;
//    }
//
//
//
//	public void setAnswerNum(String answernum){
//		this.answerNum = answernum;
//	}

}

