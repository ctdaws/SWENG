import javafx.scene.Node;

import java.lang.String;
import java.util.ArrayList;

class Navigator {

	//public boolean Qbutton;
	//public boolean Xbutton;
	//public boolean Sbutton;
	private LQPresentation lqPresentation;
	public String id;
	public int currentSlideNum;
	public int currentQuestionNum;
	public int currentLevelNum;
	public int n = 0;
	public int aVal = 0;
	//public int fVal = 0;
	//public ArrayList<String> prevID;
	//Slide feedback, end, menu;
	public LQSlide currentSlide;
	private String currentID, nextID;
	private ArrayList<String> prevID;

	public Navigator() {
		//this.setButtonStatus(true, false, true);
		this.prevID = new ArrayList<String>();
		this.currentID = "menu";
		//this.prevID.add("menu");

		// this.feedback = new Slide(15, "Feedback Slide");
		// this.end = new Slide(16, "End Slide");
		// this.menu = new Slide(17, "Menu Slide");
		// this.currentSlide = new Slide(1, "Current Slide");
	}

	public void setPresentation(LQPresentation presentation){
		this.lqPresentation = presentation;
	}

	public String getCurrentID() { return this.currentID; } //TODO move from presentor

	public void setCurrentID(String newID) { this.currentID = newID;} //TODO move from presentor

//        public Slide getSlideByID(String id){
//            //check for specific IDs for feedback, end, menu
//            Slide currentSlide;
//            switch(id){
//                case "menu":
//                    currentSlide = this.presentation.menu;
//                    break;
//                case "feedback":
//                    currentSlide = this.presentation.feedback;
//                    break;
//                case "end":
//                    currentSlide = this.presentation.end;
//                    break;
//                default:
//                    SplitID(id);
//                    // System.out.println(" Level: " + currentLevelNum
//                    //                   + " Question: " + currentQuestionNum
//                    //                   + " Slide: " + currentSlideNum);
//
//                    currentSlide = this.presentation.lArray.get(this.currentLevelNum-1).qArray.get(this.currentQuestionNum).slideArray.get(this.currentSlideNum-1);
//                    break;
//            }
////            System.out.println("\nCurrent slide ID: " + this.currentID);
////            if (this.prevID.size() > 0){
////                System.out.println("Previous slide ID: " + this.prevID.get(this.prevID.size()-1));
////            }
////            else { System.out.println("No previous slides"); }
//            return currentSlide;
//        } //TODO move from presentor

	public String GetNextID() {
		this.nextID = this.currentID;
		switch(this.currentID){
			case "menu":
				//choose next slide
				//nextID = "menu";
				//this.nextID = "1/1/1";
				currentLevelNum = 1;
				currentQuestionNum = 1;
				currentSlideNum = 1;
				SetQuestionNum();
				break;
			case "feedback":
				SplitID(this.prevID.get(this.prevID.size()-1));
				n = this.aVal + lqPresentation.fVal;
				currentLevelNum += n;
				currentSlideNum = 1;
				if (currentLevelNum < 1) {
					currentLevelNum = 1;
					//currentQuestionNum = SetQuestionNum();
					SetQuestionNum();
					//if (currentQuestionNum > this.p.tArray.get(currentTopicNum-1).lArray.get(currentLevelNum-1).qArray.size() - 1) {
					if (currentQuestionNum > this.lqPresentation.getLqLevelArray().get(currentLevelNum-1).getLqQuestionArray().size() - 1) {
						currentLevelNum ++;
					}
					this.nextID = CombineID();
				}
				//else if (currentLevelNum > this.p.tArray.get(currentTopicNum-1).lArray.size()) {
				else if (currentLevelNum > this.lqPresentation.getLqLevelArray().size()) {
					this.nextID = "end";
				}
				else {
//                        do {
//                            currentQuestionNum = SetQuestionNum();
//                            //if (currentQuestionNum > this.p.tArray.get(currentTopicNum-1).lArray.get(currentLevelNum-1).qArray.size() - 1) {
//                            if (currentQuestionNum > this.presentation.lArray.get(currentLevelNum-1).qArray.size() - 1) {
//                                currentLevelNum ++;
//                                //if (currentLevelNum > this.p.tArray.get(currentTopicNum-1).lArray.size()) {
//                                if (currentLevelNum > this.presentation.lArray.size()) {
//                                    nextID = "end";
//                                    break;
//                                }
//                                else {
//                                    currentQuestionNum = SetQuestionNum();
//                                }
//                            }
//                        }  while (currentQuestionNum > this.presentation.lArray.get(currentLevelNum-1).qArray.size() - 1);
//                        if (nextID != "end") {
//                            nextID = CombineID();
//                        }
					SetQuestionNum();
				}
				break;
			case "end":
				this.nextID = "menu";
				break;
			default:
				SplitID(this.currentID);
				//if question number > 0, it is a question
				if (currentQuestionNum > 0) {
					if (currentSlideNum == 1) {
						currentSlideNum++;
						this.nextID = CombineID();
						this.resetAnswer(this.nextID);
					}
					else if(currentSlideNum == 2) {
						if(this.lqPresentation.getSlideByID(this.currentID).getGotAnswerCorrect()){
							this.aVal = 1;
						}
						else {
							this.aVal = 0;
						}
						this.nextID = "feedback";
						this.lqPresentation.getLqProgressArray().set(currentLevelNum-1, currentQuestionNum);
					}
					else if (currentSlideNum == 3) {
						this.nextID = "feedback";
						//if (currentQuestionNum > this.p.tArray.get(currentTopicNum-1).lProgress.get(currentLevelNum-1) ) {
						//  this.p.tArray.get(currentTopicNum-1).lProgress.set(currentLevelNum-1, currentQuestionNum);
						//}
						//if (currentQuestionNum > this.p.lProgress.get(currentLevelNum-1) ) {
						this.lqPresentation.getLqProgressArray().set(currentLevelNum-1, currentQuestionNum);
						//}
					}
				}
				//if question number = 0, it is an example
				else if (currentQuestionNum == 0) {
					currentSlideNum++;
					if (currentSlideNum > this.lqPresentation.getLqLevelArray().get(currentLevelNum-1).getLqQuestionArray().get(currentQuestionNum).getLqSlideArray().size() ) {
						//currentLevelNum ++;
						currentSlideNum = 1;
						// Want to go back to question we left - not just question 1
//                            do {
//                                currentQuestionNum = SetQuestionNum();
//                                if (currentQuestionNum > this.presentation.lArray.get(currentLevelNum-1).qArray.size() - 1) {
//                                    currentLevelNum ++;
//                                    if (currentLevelNum > this.presentation.lArray.size()) {
//                                        nextID = "end";
//                                        break;
//                                    }
//                                    else {
//                                        currentQuestionNum = 0; //sets id to example
//                                    }
//                                }
//                            }  while (currentQuestionNum > this.presentation.lArray.get(currentLevelNum-1).qArray.size() - 1);
						SetQuestionNum();
					}
//                        if (nextID != "end") {
					nextID = CombineID();
//                        }
				}
				break;
		}
		this.prevID.add(this.currentID);
		return this.nextID;

	} //TODO move from presentor

	public String GetExampleID(){
		this.nextID = this.currentID;
		SplitID(this.currentID);
		currentQuestionNum = 0;
		currentSlideNum = 1;
		this.nextID = CombineID();
		return this.nextID;
	} //TODO move from presentor

	public String GetQuestionID(){
		this.nextID = this.currentID;
		SplitID(this.currentID);
//            do {
////                currentQuestionNum = SetQuestionNum();
////                if (currentQuestionNum > this.presentation.lArray.get(currentLevelNum-1).qArray.size() - 1) {
////                    currentLevelNum ++;
////                    if (currentLevelNum > this.presentation.lArray.size()) {
////                        nextID = "end";
////                        break;
////                    }
////                    else {
////                        currentQuestionNum = SetQuestionNum();
////                    }
////                }
////            }  while (currentQuestionNum > this.presentation.lArray.get(currentLevelNum-1).qArray.size() - 1);
////            if (nextID != "end") {
////                nextID = CombineID();
////            }
		SetQuestionNum();
//            currentQuestionNum = SetQuestionNum();
//            currentSlideNum = 1;
//            nextID = CombineID();
		return this.nextID;
	} //TODO move from presentor

	public String GetSolutionID(){
		this.nextID = this.currentID;
		SplitID(this.currentID);
		currentSlideNum = 3;
		this.nextID = CombineID();
		return this.nextID;
	} //TODO move from presentor

	public String GetPrevID(){
		String lastID;
		if (this.prevID.size() > 0){
			lastID = this.prevID.get(this.prevID.size()-1);
			this.prevID.remove(this.prevID.size()-1);
		}
		else { lastID = this.currentID; }
		return lastID;
	} //TODO move from presentor

	public void SplitID(String id){
		//splits ID into individual values
		String idArray[] = id.split("/");
		//currentTopicNum = Integer.parseInt(idArray[0]);
		currentLevelNum = Integer.parseInt(idArray[0]);
		currentQuestionNum = Integer.parseInt(idArray[1]);
		currentSlideNum = Integer.parseInt(idArray[2]);
	} //TODO move from presentor

	public void SetQuestionNum(){
		//int QuestionNum;
		//currentQuestionNum = this.lqPresentation.getLqProgressArray().get(currentLevelNum-1) + 1;

		do {
			currentQuestionNum = this.lqPresentation.getLqProgressArray().get(currentLevelNum-1) + 1;
			if (currentQuestionNum > this.lqPresentation.getLqLevelArray().get(currentLevelNum-1).getLqQuestionArray().size() - 1) {
				currentLevelNum ++;
				if (currentLevelNum > this.lqPresentation.getLqLevelArray().size()) {
					this.nextID = "end";
					break;
				}
				else {
					currentQuestionNum = this.lqPresentation.getLqProgressArray().get(currentLevelNum-1) + 1;
				}
			}
		}  while (currentQuestionNum > this.lqPresentation.getLqLevelArray().get(currentLevelNum-1).getLqQuestionArray().size() - 1);
		if (this.nextID != "end") {
			this.nextID = CombineID();
		}

		//return QuestionNum;
	}

	public String CombineID(){
		//combines values into string ID
		//(...currentTopicNum + "/" + ...)
		String newID = (currentLevelNum + "/"
				+ currentQuestionNum + "/"
				+ currentSlideNum);
		return newID;
	}   //TODO move from presentor

	public void moveSlide(String slideID) {
		this.unloadSlide();
		this.setCurrentID(slideID);
		this.renderSlide();
	} //TODO move from presentor

	public void moveNextSlide() {
		this.unloadSlide();
		this.setCurrentID(this.GetNextID());
		this.renderSlide();
	} //TODO move from presentor

	public void moveBackSlide() {
		this.unloadSlide();
		this.setCurrentID(this.GetPrevID());
		this.renderSlide();
	} //TODO move from presentor

	public void renderSlide() {
		this.lqPresentation.pane.getChildren().add(this.lqPresentation.getSlideByID(currentID).getSlidePane());
//		TODO: Replace with call to draw slide
//		ArrayList<FLMedia> mediaObjects = this.presentation.getSlideByID(currentID).getMediaList();
//
//		for(FLMedia media : mediaObjects) {
//			if (media.isRendered()) {
//				this.presentation.pane.getChildren().add((Node)media.getMedia());
//			}
//		}

		//System.out.println("aVal = " + this.aVal);
		//System.out.println("Answered = " + this.presentation.getSlideByID(currentID).getAnswered() + "\nCorrect = " + this.presentation.getSlideByID(currentID).getCorrect());
	}

	public void unloadSlide() {
		this.lqPresentation.pane.getChildren().remove(this.lqPresentation.getSlideByID(currentID).getSlidePane());
//		TODO: Replace with call to remove slide
//		ArrayList<FLMedia> mediaObjects = this.presentation.getSlideByID(currentID).getMediaList();
//
//		for(FLMedia media : mediaObjects) {
//			if (media.isRendered()) {
//				this.presentation.pane.getChildren().remove(media.getMedia());
//			}
//		}
	}

//	public void playAudio(String slideID, String audioID) {
//		if(this.lqPresentation.currentAudio == null) {
//			this.lqPresentation.currentAudio = this.presentation.getSlideByID(slideID).getAudio(audioID);
//			this.presentation.currentAudio.play();
//		} else {
//			this.presentation.currentAudio.stop();
//			this.presentation.currentAudio = this.presentation.getSlideByID(slideID).getAudio(audioID);
//			this.presentation.currentAudio.play();
//		}
//	}

//	public void showImage(String slideID, String imageID) {
//		this.presentation.getSlideByID(slideID).getImage(imageID).setVisible();
//	}

	public void resetAnswer(String id){
		this.aVal = 0;
		this.lqPresentation.getSlideByID(id).resetAnswer();
	}

	public int getLevelNum() {
		return this.currentLevelNum;
	}

	public int GetCurrentLevelNum() {
		return this.currentLevelNum;
	}   //TODO Needed?

	public int GetCurrentQuestionNum() {
		return this.currentQuestionNum;
	}   //TODO Needed?

	public int GetCurrentSlideNum() {
		return this.currentSlideNum;
	}   //TODO Needed?

	public LQPresentation getPresentation() {
		return lqPresentation;
	}
}
