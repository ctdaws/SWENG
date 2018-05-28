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
	//answerslide
	protected FLAudio incorrectAudio, correctAudio;
	protected FLImage correctImage, incorrectImage0, incorrectImage1, incorrectImage2, incorrectImage3;
	protected FLButton button1;
	protected FLButton button2;
	protected FLButton button3;
	protected FLButton button4;

	//private Interaction interaction;

//	public Slide(String id, String type) {
//		this.id = id;
//		this.type = type;
//		this.mediaList = new ArrayList<FLMedia>();
//		this.correctArray = new Boolean[4];
//
//	}

	public Slide(String id, String type){
		this.id = id;
		this.type = type;
		this.mediaList = new ArrayList<FLMedia>();
		this.correctArray = new Boolean[4];
		//answer slide
		this.answerNum = answerNum;
		this.correct = correct;
		this.answerNumInt = answerNumInt;




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
	protected void displayAnswers() {

//		FLButton button1;
//		FLButton button2;
//		FLButton button3;
//		FLButton button4;


//		Position position2 = new Position(649, 399);
		//Position position3 = new Position(125, 485);
//		Position position4 = new Position(649, 485);

//		setButton1();

//		button2 = new FLButton(id, position2, 500, 75, this.getClass().getResource("answers_2.png").toExternalForm());
//		//button2.addText("Answer 2");
//		this.add(button2);

//		button3 = new FLButton(id, position3, 500, 75, this.getClass().getResource("answers_3.png").toExternalForm());
//		//button3.addText("Answer 3");
//		this.add(button3);

//		button4 = new FLButton(id, position4, 500, 75, this.getClass().getResource("answers_4.png").toExternalForm());
//		//button4.addText("Answer 4");
//		this.add(button4);

//
//		button2.getButton().setOnMouseClicked((clickEvent) -> {
//			this.setAnswered(true);
//			checkCorrect(this.getCorrectArray(), 1);
//		});
//		button3.getButton().setOnMouseClicked((clickEvent) -> {
//			this.setAnswered(true);
//			checkCorrect(this.getCorrectArray(), 2);
//		});
//		button4.getButton().setOnMouseClicked((clickEvent) -> {
//			this.setAnswered(true);
//			checkCorrect(this.getCorrectArray(), 3);
//		});
//
//		System.out.println("printed Answers Buttons");
//
//		correctAudio = new FLAudio(" correct jingle", "CORRECT.mp3", new Position(100, 100));
//		this.add(correctAudio);
//
//		incorrectAudio = new FLAudio(" incorrect jingle", "INCORRECT.wav", new Position(100, 100));
//		this.add(incorrectAudio);


		System.out.println(correctArray[0] + ", " + correctArray[1] + ", " + correctArray[2] + ", " + correctArray[3]);

		//if(this.getCorrectArray()[0] != null && this.getCorrectArray()[0]){
//			correctImage  = new FLImage("correct image",   "correct.png", position, 100, 100, false);
//			this.add(correctImage);

//			incorrectImage1 = new FLImage("correct image", "incorrect.png", position2, 100, 100, false);
//			this.add(incorrectImage1);

//			incorrectImage2 = new FLImage("correct image", "incorrect.png", position3, 100, 100, false);
//			this.add(incorrectImage2);
//
//			incorrectImage3 = new FLImage("correct image", "incorrect.png", position4, 100, 100, false);
//			this.add(incorrectImage3);

//		}else if (this.getCorrectArray()[1] != null && this.getCorrectArray()[1]){
//			correctImage  = new FLImage("correct image",   "correct.png", position2, 100, 100, false);
//			this.add(correctImage);
//
//			incorrectImage1 = new FLImage("correct image", "incorrect.png", position1, 100, 100, false);
//			this.add(incorrectImage1);
//
//			incorrectImage2 = new FLImage("correct image", "incorrect.png", position3, 100, 100, false);
//			this.add(incorrectImage2);
//
//			incorrectImage3 = new FLImage("correct image", "incorrect.png", position4, 100, 100, false);
//			this.add(incorrectImage3);
//		}else if (this.getCorrectArray()[2] != null && this.getCorrectArray()[2]){
//			correctImage  = new FLImage("correct image",   "correct.png", position3, 100, 100, false);
//			this.add(correctImage);
//
//			incorrectImage1 = new FLImage("correct image", "incorrect.png", position2, 100, 100, false);
//			this.add(incorrectImage1);
//
//			incorrectImage2 = new FLImage("correct image", "incorrect.png", position1, 100, 100, false);
//			this.add(incorrectImage2);
//
//			incorrectImage3 = new FLImage("correct image", "incorrect.png", position4, 100, 100, false);
//			this.add(incorrectImage3);
//		}else if (this.getCorrectArray()[3] != null && this.getCorrectArray()[3]){
//			correctImage  = new FLImage("correct image",   "correct.png", position4, 100, 100, false);
//			this.add(correctImage);
//
//			incorrectImage1 = new FLImage("correct image", "incorrect.png", position2, 100, 100, false);
//			this.add(incorrectImage1);
//
//			incorrectImage2 = new FLImage("correct image", "incorrect.png", position3, 100, 100, false);
//			this.add(incorrectImage2);
//
//			incorrectImage3 = new FLImage("correct image", "incorrect.png", position1, 100, 100, false);
//			this.add(incorrectImage3);
//		}

	}

	protected void setActionListeners(){
		System.out.println("media list size = " + this.mediaList.size() + "id: " + id + " type: " + type);
		if(mediaList.get(this.mediaList.size()-7) instanceof FLButton) {
			this.button1 = (FLButton)mediaList.get(this.mediaList.size()-7);

		this.button1.getButton().setOnMouseClicked((clickEvent) -> {
			this.setAnswered(true);
			checkCorrect(this.getCorrectArray(), 0);
			System.out.println("pressed answer 1");
		});}

		if(mediaList.get(this.mediaList.size()-5) instanceof FLButton) {
			this.button2 = (FLButton)mediaList.get(this.mediaList.size()-5);

		this.button2.getButton().setOnMouseClicked((clickEvent) -> {
			this.setAnswered(true);
			checkCorrect(this.getCorrectArray(), 1);
			System.out.println("pressed answer 2");
		});}

		if(mediaList.get(this.mediaList.size()-3) instanceof FLButton) {
			this.button3 = (FLButton)mediaList.get(this.mediaList.size()-3);

		this.button3.getButton().setOnMouseClicked((clickEvent) -> {
			this.setAnswered(true);
			checkCorrect(this.getCorrectArray(), 2);
			System.out.println("pressed answer 3");
		});}

		//this.button4 = mediaList<Button>.getMedia(mediaList.size()-1);
		//this.button4 = mediaList.lastIndexOf(currentButton);
		if(mediaList.get(this.mediaList.size()-1) instanceof FLButton) {
		this.button4 = (FLButton)mediaList.get(this.mediaList.size()-1);

		this.button4.getMedia().setOnMouseClicked((clickEvent) -> {
			this.setAnswered(true);
			checkCorrect(this.getCorrectArray(), 3);
			System.out.println("pressed answer 4");
		});}

	}

	protected void setButton1() {
//		Position position = new Position(125, 399);
//		button1 = new FLButton(id, position, 500, 75, this.getClass().getResource("answer_1.png").toExternalForm());
//		//button1.addText("Answer 1");
//		//button1.getMedia().getStyleClass().add("answer 1");
//		this.add(button1);

		button1.getButton().setOnMouseClicked((clickEvent) -> {
			this.setAnswered(true);
			checkCorrect(this.getCorrectArray(), 0);
		});
//
//		if(this.getCorrectArray()[0] != null && this.getCorrectArray()[0]){
//			correctImage  = new FLImage("correct image",   "correct.png", position, 100, 100, false);
//			this.add(correctImage);
//		} else {
//			incorrectImage0 = new FLImage("incorrect image", "incorrect.png", position, 100, 100, false);
//			this.add(incorrectImage0);
//		}
//
		correctAudio = new FLAudio(" correct jingle", "CORRECT.mp3", new Position(100, 100));
		this.add(correctAudio);

		incorrectAudio = new FLAudio(" incorrect jingle", "INCORRECT.wav", new Position(100, 100));
		this.add(incorrectAudio);

	}

	protected void setButton2(){
		//Position position = new Position(649, 399);
//		button2 = new FLButton(id, position, 500, 75, this.getClass().getResource("answers_2.png").toExternalForm());
//		//button1.addText("Answer 1");
//		//button1.getMedia().getStyleClass().add("answer 1");
//		this.add(button2);

		button2.getButton().setOnMouseClicked((clickEvent) -> {
			this.setAnswered(true);
			checkCorrect(this.getCorrectArray(), 1);
		});

//		if(this.getCorrectArray()[1] != null && this.getCorrectArray()[1]){
//			correctImage  = new FLImage("correct image",   "correct.png", position, 100, 100, false);
//			this.add(correctImage);
//		}else {
//			incorrectImage1 = new FLImage("incorrect image", "incorrect.png", position, 100, 100, false);
//			this.add(incorrectImage1);
//		}
	}

	protected void setButton3(){
//		Position position = new Position(125, 485);
//		button2 = new FLButton(id, position, 500, 75, this.getClass().getResource("answers_3.png").toExternalForm());
//		//button1.addText("Answer 1");
//		//button1.getMedia().getStyleClass().add("answer 1");
//		this.add(button2);

		button2.getButton().setOnMouseClicked((clickEvent) -> {
			this.setAnswered(true);
			checkCorrect(this.getCorrectArray(), 2);
		});

//		if(this.getCorrectArray()[2] != null && this.getCorrectArray()[2]){
//			correctImage  = new FLImage("correct image",   "correct.png", position, 100, 100, false);
//			this.add(correctImage);
//		}else {
//			incorrectImage2 = new FLImage("incorrect image", "incorrect.png", position, 100, 100, false);
//			this.add(incorrectImage2);
//		}
	}

	protected void setButton4(){
//		Position position = new Position(649, 485);
//		button2 = new FLButton(id, position, 500, 75, this.getClass().getResource("answers_4.png").toExternalForm());
//		//button1.addText("Answer 1");
//		//button1.getMedia().getStyleClass().add("answer 1");
//		this.add(button2);

		button2.getButton().setOnMouseClicked((clickEvent) -> {
			this.setAnswered(true);
			checkCorrect(this.getCorrectArray(), 3);
		});

//		if(this.getCorrectArray()[3] != null && this.getCorrectArray()[3]){
//			correctImage  = new FLImage("correct image",   "correct.png", position, 100, 100, false);
//			this.add(correctImage);
//		}else {
//			incorrectImage3 = new FLImage("incorrect image", "incorrect.png", position, 100, 100, false);
//			this.add(incorrectImage3);
//		}
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	public void setAnswered(Boolean answered) {
		this.answered = answered;
	}

	public void setAnswerNum(String answerNum) {
		this.answerNum = answerNum;
	}

	public Boolean getCorrect() {
		return this.correct;
	}

	public Boolean getAnswered() {
		return this.answered;
	}

	public void setAnswerNumInt(Integer answerNumInt){
		this.answerNumInt = answerNumInt;
	}

	public Integer getAnswerNumInt(){
		return this.answerNumInt;
	}

//    public void setCorrectArray(Boolean correct, Integer answerNum){
//        this.correctArray[answerNum] = correct;
//        System.out.println(correctArray[0] + ", " + correctArray[1] + ", " + correctArray[2] + ", " + correctArray[3]);
//    }
//
//    public Boolean[] getCorrectArray() {
//        return correctArray;
//    }

//  public Boolean setCorrectArray(Boolean correct, Integer answernum){
//      this.correctArray[answernum] = correct;
//  }



	public void checkCorrect(Boolean[] correctArray, Integer answerNumInt) {
		if (correctArray[answerNumInt]) {
			this.correctAudio.play();
			//this.correctImage.setVisible();
			this.setCorrect(true);
		}
		else{
			//this.incorrectAudio.play();
			this.setCorrect(false);
			switch (answerNumInt) {
				case 0:
					this.incorrectAudio.play();
					//this.incorrectImage0.setVisible();
					break;
				case 1:
					this.incorrectAudio.play();
					//this.incorrectImage1.setVisible();
					break;
				case 2:
					this.incorrectAudio.play();
					//this.incorrectImage2.setVisible();
					break;
				case 3:
					this.incorrectAudio.play();
					//this.incorrectImage3.setVisible();
					break;
				default:
					break;
			}
		}
		System.out.println(this.getCorrect());
	}



}

