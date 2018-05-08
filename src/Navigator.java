class Navigator {
  public int currentSlideNum;
  public int currentQuestionNum;
  public int currentLevelNum;
  public int currentTopicNum;
  public int n, aVal, fVal;
  public int maxTopic = 3;
  public int maxLevel = 2;
  public int maxQuestion = 2;
  public String id;

  Presentation p;
  Topic t0;
  Topic t1;
  Level l1;
  Level l2;
  Question q1;
  Slide s1;
  Slide s2;
  Slide s3;
  Question q2;
  Slide s4;
  Slide s5;
  Slide s6;
  Example l1X;
  Slide l1Xs1;

  Question l2q1;
  Slide l2q1Q;
  Slide l2q1A;
  Slide l2q1S;
  Question l2q2;
  Slide l2q2Q;
  Slide l2q2A;
  Slide l2q2S;
  Example l2X;
  Slide l2Xs1;

  Slide feedback, end, menu;

  Slide currentSlide;
  /*int numOfLevels = 2;
  int numOfQuestions = 2;*/
  public Navigator() {
    /*

    for(int i=0;i<numOfLevels+1;i++) {
      this.t1.add(new Level());
      for(int j=0;j<numOfQuestions+2;j++){
        if(j==0) {
          this.t1.lArray.get(i).add(new Example());
          this.t1.lArray.get(i).get(j).add(new Slide())
        } else {
          this.t1.lArray.get(i).add(new Question());
        }
      }
    }*/

    this.feedback = new Slide(15, "Feedback Slide");
    this.end = new Slide(16, "End Slide");
    this.menu = new Slide(17, "Menu Slide");

    this.p = new Presentation();

    this.currentSlide = new Slide(1, "Current Slide");

    CreateObjects();
    BuildPresentation();

    // e.g. 1/1/1/1, menu, feedback, end
    id = "1/1/0/1";
    /*
    currentTopicNum = 1;
    currentLevelNum = 1;
    currentQuestionNum = 0;
    currentSlideNum = 1;
    */
    //SetCurrentSlide(currentTopicNum, currentLevelNum, currentQuestionNum, currentSlideNum);
    SetCurrentSlide(id);
    id = GetNextSlide(id);
    SetCurrentSlide(id);
  }

public static void main(String[] args) {
  Navigator navigator = new Navigator();
}

//add functions for building and splitting ID

//public void SetCurrentSlide(int topic, int level, int question, int slide){
  public void SetCurrentSlide(String id){
  //check for specific IDs for feedback, end, menu
  switch(id){
    case "menu":
      this.currentSlide = menu;
      break;
    case "feedback":
      this.currentSlide = feedback;
      break;
    case "end":
      this.currentSlide = end;
      break;
    default:
      /*
      //splits ID into individual values
      String idArray[] = id.split("/");
      int topic = Integer.parseInt(idArray[0]);
      int level = Integer.parseInt(idArray[1]);
      int question = Integer.parseInt(idArray[2]);
      int slide = Integer.parseInt(idArray[3]);
      */
      SplitID(id);

      //System.out.println("Topic: " + topic + " Level: " + level +" Question: "+question+" Slide: "+slide);
      System.out.println("Topic: " + currentTopicNum
                        + " Level: " + currentLevelNum
                        + " Question: " + currentQuestionNum
                        + " Slide: " + currentSlideNum);

      //this.currentSlide = this.p.tArray.get(topic-1).lArray.get(level-1).qArray.get(question).slideArray.get(slide-1);
      this.currentSlide = this.p.tArray.get(currentTopicNum-1).lArray.get(currentLevelNum-1).qArray.get(currentQuestionNum).slideArray.get(currentSlideNum-1);
      break;
  }

  System.out.println("Current slide text: " + this.currentSlide.text);
}

public String GetNextSlide(String id) {
  switch(id){
    case "menu":
      //choose next slide
      break;
    case "feedback":
      //split previous id
      n = aVal + fVal;
      currentLevelNum += n;
      //TODO change this to account for which questions have already been completed
      currentQuestionNum = 1;
      currentSlideNum = 1;
      if (currentLevelNum < 1) {
        currentLevelNum = 1;
        id = CombineID();
      }
      else if (currentLevelNum > 10) {
        id = "end";
      }
      break;
    case "end":
      id = "menu";
      break;
    default:
      SplitID(id);
      //if question number > 0, it is a question
      if (currentQuestionNum > 0) {
        if (currentSlideNum == 1) {
          currentSlideNum++;
          id = CombineID();
        }
        else if (currentSlideNum == 2 || currentSlideNum == 3) {
          id = "feedback";
        }
      }
      //if question number = 0, it is an example
      else if (currentQuestionNum == 0) {
        currentQuestionNum++;
        currentSlideNum = 1;
        id = CombineID();
      }
      break;
  }
  return id;

  /*
  if (currentQuestionNum > 0) {
    if (currentSlideNum == 1) {
      currentSlideNum++;
    }
  }

  if (currentQuestionNum == 0) {
    currentQuestionNum++;
    currentSlideNum = 1;
  }

  if (currentSlideNum == 2 || currentSlideNum == 3) {
    id = "feedback";
  }

  if (currentSlide == feedback) {
    n = aVal + fVal;
    currentLevelNum += n;
    if (currentLevelNum < 1) {
      currentLevelNum = 1;
    }
    else if (currentLevelNum > 10) {
      id = "end";
    }
  }

  if (currentSlide == end) {
    id = "menu";
  }
  */

}

public void SplitID(String id){
  //splits ID into individual values
  String idArray[] = id.split("/");
  currentTopicNum = Integer.parseInt(idArray[0]);
  currentLevelNum = Integer.parseInt(idArray[1]);
  currentQuestionNum = Integer.parseInt(idArray[2]);
  currentSlideNum = Integer.parseInt(idArray[3]);
}

public String CombineID(){
  //combines values into string ID
  String newID = (currentTopicNum + "/"
                  + currentLevelNum + "/"
                  + currentQuestionNum + "/"
                  + currentSlideNum);
	return newID;
}

public void CreateObjects(){
  this.t0 = new Topic();
  this.t1 = new Topic();

  this.l1 = new Level();
  this.l1X = new Example();
  this.l1Xs1 = new Slide(7, "L1 X s1");
  this.q1 = new Question();
  this.s1 = new Slide(1, "L1 Q1");
  this.s2 = new Slide(2, "L1 A1");
  this.s3 = new Slide(3, "L1 S1");
  this.q2 = new Question();
  this.s4 = new Slide(4, "L1 Q2");
  this.s5 = new Slide(5, "L1 A2");
  this.s6 = new Slide(6, "L1 S2");


  this.l2 = new Level();
  this.l2X = new Example();
  this.l2Xs1 = new Slide(8, "L2 X s1");
  this.l2q1 = new Question();
  this.l2q1Q = new Slide(9, "L2 Q1");
  this.l2q1A = new Slide(10, "L2 A1");
  this.l2q1S = new Slide(11, "L2 S1");
  this.l2q2 = new Question();
  this.l2q2Q = new Slide(12, "L2 Q2");
  this.l2q2A = new Slide(13, "L2 A2");
  this.l2q2S = new Slide(14, "L2 S2");
}

public void BuildPresentation(){
  this.l1X.add(l1Xs1);

  this.q1.add(s1);
  this.q1.add(s2);
  this.q1.add(s3);

  this.q2.add(s4);
  this.q2.add(s5);
  this.q2.add(s6);

  this.l2X.add(l2Xs1);

  this.l2q1.add(l2q1Q);
  this.l2q1.add(l2q1A);
  this.l2q1.add(l2q1S);

  this.l2q2.add(l2q2Q);
  this.l2q2.add(l2q2A);
  this.l2q2.add(l2q2S);

  this.l1.add(l1X);
  this.l1.add(q1);
  this.l1.add(q2);

  this.l2.add(l2X);
  this.l2.add(l2q1);
  this.l2.add(l2q2);

  this.t1.add(l1);
  this.t1.add(l2);

  this.p.add(t1);
}

//TODO add manual navigation function
//TODO add a way to save previous id
//TODO add 'back' functionality using previous id
//TODO add a check to make sure the max number of questions isn't exceeded

}
