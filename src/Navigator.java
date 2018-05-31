import javafx.scene.Node;

import java.lang.String;
import java.util.ArrayList;

class Navigator {
	private LQPresentation lqPresentation;
	public String id;
	public int currentSlideNum;
	public int currentQuestionNum;
	public int currentLevelNum;
	public int n = 0;
	public int aVal = 0;
	public LQSlide currentSlide;
	private String currentID, nextID;
	private ArrayList<String> prevID;

	public Navigator() {
		this.prevID = new ArrayList<String>();
		this.currentID = "menu";
	}

	public void setPresentation(LQPresentation presentation){
		this.lqPresentation = presentation;
	}

	public String getCurrentID() { return this.currentID; } //TODO move from presentor

	public void setCurrentID(String newID) { this.currentID = newID;} //TODO move from presentor

	public String GetNextID() {
		this.nextID = this.currentID;
		switch(this.currentID){
			case "menu":
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
		SetQuestionNum();
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
	}

	public void unloadSlide() {
		this.lqPresentation.pane.getChildren().remove(this.lqPresentation.getSlideByID(currentID).getSlidePane());
	}

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
