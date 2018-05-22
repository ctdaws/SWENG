/*
class Navigator {

	//public boolean Qbutton;
	//public boolean Xbutton;
	//public boolean Sbutton;
	public String id;
	public int currentSlideNum;
  public int currentQuestionNum;
  public int currentLevelNum;
  public int n = 0;
  public int aVal = 0;
  public int fVal = 0;
	public ArrayList<String> prevID;
	//Slide feedback, end, menu;
	//Slide currentSlide;

	public Navigator() {
		//this.setButtonStatus(true, false, true);
		this.prevID = new ArrayList<String>();
    this.prevID.add("menu");

    // this.feedback = new Slide(15, "Feedback Slide");
    // this.end = new Slide(16, "End Slide");
    // this.menu = new Slide(17, "Menu Slide");
		// this.currentSlide = new Slide(1, "Current Slide");
	}

  // public Slide getSlideByID(String id){
  // //check for specific IDs for feedback, end, menu
	// Slide currentSlide;
	// switch(id){
  //   case "menu":
  //     currentSlide = menu;
  //     break;
  //   case "feedback":
  //     currentSlide = feedback;
  //     break;
  //   case "end":
  //     currentSlide = end;
  //     break;
  //   default:
  //     SplitID(id);
  //     System.out.println(" Level: " + currentLevelNum
  //                       + " Question: " + currentQuestionNum
  //                       + " Slide: " + currentSlideNum);
	//
	// 		currentSlide = this.p.lArray.get(currentLevelNum-1).qArray.get(currentQuestionNum).slideArray.get(currentSlideNum-1);
  //     break;
  // }

//   System.out.println("Current slide text: " + this.currentSlide.text);
//   System.out.println("Previous slide ID: " + this.prevID.get(this.prevID.size()-1));
// 	return currentSlide;
// }

public String GetNextID() {
  String nextID = this.id;
  switch(this.id){
    case "menu":
      //choose next slide
      nextID = "menu";
      break;
    case "feedback":
      SplitID(this.prevID.get(this.prevID.size()-1));
      n = aVal + fVal;
      currentLevelNum += n;
      //TODO change this to account for which questions have already been completed -DONE
      currentSlideNum = 1;
      if (currentLevelNum < 1) {
        currentLevelNum = 1;
        currentQuestionNum = SetQuestionNum();
        //if (currentQuestionNum > this.p.tArray.get(currentTopicNum-1).lArray.get(currentLevelNum-1).qArray.size() - 1) {
        if (currentQuestionNum > this.p.lArray.get(currentLevelNum-1).qArray.size() - 1) {
          currentLevelNum ++;
        }
        nextID = CombineID();
      }
      //else if (currentLevelNum > this.p.tArray.get(currentTopicNum-1).lArray.size()) {
      else if (currentLevelNum > this.p.lArray.size()) {
        nextID = "end";
      }
      else {
        do {
          currentQuestionNum = SetQuestionNum();
          //if (currentQuestionNum > this.p.tArray.get(currentTopicNum-1).lArray.get(currentLevelNum-1).qArray.size() - 1) {
          if (currentQuestionNum > this.p.lArray.get(currentLevelNum-1).qArray.size() - 1) {
            currentLevelNum ++;
            //if (currentLevelNum > this.p.tArray.get(currentTopicNum-1).lArray.size()) {
            if (currentLevelNum > this.p.lArray.size()) {
              nextID = "end";
              break;
            }
            else {
              currentQuestionNum = SetQuestionNum();
            }
          }
        //}  while (currentQuestionNum > this.p.tArray.get(currentTopicNum-1).lArray.get(currentLevelNum-1).qArray.size() - 1);
        }  while (currentQuestionNum > this.p.lArray.get(currentLevelNum-1).qArray.size() - 1);
        if (nextID != "end") {
          nextID = CombineID();
        }
      }
      break;
    case "end":
      nextID = "menu";
      break;
    default:
      SplitID(id);
      //if question number > 0, it is a question
      if (currentQuestionNum > 0) {
        if (currentSlideNum == 1) {
          currentSlideNum++;
          nextID = CombineID();
        }
        else if (currentSlideNum == 2 || currentSlideNum == 3) {
          nextID = "feedback";
          //if (currentQuestionNum > this.p.tArray.get(currentTopicNum-1).lProgress.get(currentLevelNum-1) ) {
          //  this.p.tArray.get(currentTopicNum-1).lProgress.set(currentLevelNum-1, currentQuestionNum);
          //}
          //if (currentQuestionNum > this.p.lProgress.get(currentLevelNum-1) ) {
            this.p.lProgress.set(currentLevelNum-1, currentQuestionNum);
          //}
        }
      }
      //if question number = 0, it is an example
      else if (currentQuestionNum == 0) {
        currentSlideNum++;
        //if (currentSlideNum > this.p.tArray.get(currentTopicNum-1).lArray.get(currentLevelNum-1).qArray.get(currentQuestionNum).slideArray.size() ) {
        if (currentSlideNum > this.p.lArray.get(currentLevelNum-1).qArray.get(currentQuestionNum).slideArray.size() ) {
          //currentLevelNum ++;
          currentQuestionNum = SetQuestionNum(); // Want to go back to question we left - not just question 1.
          currentSlideNum = 1;
          //if (currentLevelNum > this.p.tArray.get(currentTopicNum-1).lArray.size()) {
          //  nextID = "end";
         // }
        }

        nextID = CombineID();
      }
      break;
  }
  this.prevID.add(id);
  return nextID;

}

public String GoToExample(){
  String nextID = this.id;
  SplitID(this.id);
  currentQuestionNum = 0;
  currentSlideNum = 1;
  nextID = CombineID();
  return nextID;
}

public String GoToQuestion(){
  String nextID = this.id;
  SplitID(this.id);
  currentQuestionNum = SetQuestionNum();
  currentSlideNum = 1;
  nextID = CombineID();
  return nextID;
}

public String GoToSolution(){
  String nextID = this.id;
  SplitID(this.id);
  currentSlideNum = 3;
  nextID = CombineID();
  return nextID;
}

public String GetPrevID(){
  String lastID;
  lastID = this.prevID.get(this.prevID.size()-1);
  this.prevID.remove(this.prevID.size()-1);
  return lastID;
}

public void SplitID(String id){
  //splits ID into individual values
  String idArray[] = id.split("/");
  //currentTopicNum = Integer.parseInt(idArray[0]);
  currentLevelNum = Integer.parseInt(idArray[0]);
  currentQuestionNum = Integer.parseInt(idArray[1]);
  currentSlideNum = Integer.parseInt(idArray[2]);
}

public int SetQuestionNum(){
  int QuestionNum;
  //QuestionNum = this.p.tArray.get(currentTopicNum-1).lProgress.get(currentLevelNum-1) + 1;
  QuestionNum = this.p.lProgress.get(currentLevelNum-1) + 1;
  return QuestionNum;
}

public String CombineID(){
  //combines values into string ID
  //(...currentTopicNum + "/" + ...)
  String newID = (currentLevelNum + "/"
                  + currentQuestionNum + "/"
                  + currentSlideNum);
	return newID;
}

public int GetCurrentLevelNum() {
	return this.currentLevelNum;
}

public int GetCurrentQuestionNum() {
	return this.currentQuestionNum;
}

public int GetCurrentSlideNum() {
	return this.currentSlideNum;
}

	/*
	public void checkButtonStatus() {
		switch(this.id){
			case "Q":
				setButtonStatus(false, true, false);
				break;
			case "X":
				setButtonStatus(true, false, false);
				break;
			case "A":
				setButtonStatus(true, true, true);
				break;
			case "S":
				setButtonStatus(false, false, false);
				break;
			case "F":
				setButtonStatus(false, false, false);
				break;
			case "E":
				setButtonStatus(false, false, false);
				break;
			default:
				setButtonStatus(true, false, false);
			break;
		}
	}
	*/

	/*
	public void setButtonStatus(boolean Q, boolean X, boolean S) {
		this.Qbutton = Q;
		this.Xbutton = X;
		this.Sbutton = S;
	}*/

//}
