import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Presentation {

  // private ArrayList<Topic> topicList;
  // private ArrayList<Level> levelList;
  // private ArrayList<Question> questionList;
  // private ArrayList<Slide> slideList;
  ArrayList<Level> lArray;
  ArrayList<Integer> lProgress;
  private ArrayList<Meta> metaList;
  private Defaults presentationDefault;
  // private Colors presentationDefaultColor;
  // private TextStyle presentationDefaultStyle;
  private Position slideSize;

  public Pane pane;

  public FLAudio currentAudio;

  private String currentID;
  private String nextSlideID;
  private Slide feedback, end, menu;
  private int currentSlideNum;
  private int currentQuestionNum;
  private int currentLevelNum;
  private int n = 0;
  private int aVal = 1;
  private int fVal = 0;
	private ArrayList<String> prevID;

  private FLText feedbackText;

  public Presentation(Defaults programDefaults, Position slideSize) {
    // this.topicList = new ArrayList<Topic>();
    // this.levelList = new ArrayList<Level>();
    // this.questionList = new ArrayList<Question>();
    // this.slideList = new ArrayList<Slide>();
    this.presentationDefault = programDefaults;
    this.lArray = new ArrayList<Level>();
    this.lProgress = new ArrayList<Integer>();
    this.metaList = new ArrayList<Meta>();

    createDefaultSlides();
    //this.end = new Slide("end", "E");
    //this.menu = new Slide("menu", "M");
    this.prevID = new ArrayList<String>();
    //this.prevID.add("menu");


    // this.presentationDefaultColor = new Colors("#000000", "#000000");
    // this.presentationDefaultStyle = new TextStyle("Arial", 20, false, false, false);
    // this.setDefaults(this.presentationDefaultColor, this.presentationDefaultStyle);
    this.slideSize = slideSize;
    //this.currentID = "1/1/1";
    this.currentID = "menu";
    this.pane = new Pane();
  }

  public String getCurrentID() { return this.currentID; }
  public void setCurrentID(String newID) { this.currentID = newID;}

  public double getWidth() {
    return this.slideSize.getX();
  }

  public double getHeight() {
    return this.slideSize.getY();
  }

  public Defaults getPresentationDefaults() { return this.presentationDefault; }

  public Slide getSlideByID(String id){
  //check for specific IDs for feedback, end, menu
	Slide currentSlide;
	switch(id){
    case "menu":
      currentSlide = menu;
      break;
    case "feedback":
      currentSlide = feedback;
      break;
    case "end":
      currentSlide = end;
      break;
    default:
      SplitID(id);
      // System.out.println(" Level: " + currentLevelNum
      //                   + " Question: " + currentQuestionNum
      //                   + " Slide: " + currentSlideNum);

			currentSlide = this.lArray.get(this.currentLevelNum-1).qArray.get(this.currentQuestionNum).slideArray.get(this.currentSlideNum-1);
      break;
  }
  System.out.println("\nCurrent slide ID: " + this.currentID);
  if (this.prevID.size() > 0){
    System.out.println("Previous slide ID: " + this.prevID.get(this.prevID.size()-1));
  }
  else { System.out.println("No previous slides"); }
  return currentSlide;
}

public String GetNextID() {
  String nextID = this.currentID;
  switch(this.currentID){
    case "menu":
      //choose next slide
      //nextID = "menu";
      nextID = "1/0/1";
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
        if (currentQuestionNum > this.lArray.get(currentLevelNum-1).qArray.size() - 1) {
          currentLevelNum ++;
        }
        nextID = CombineID();
      }
      //else if (currentLevelNum > this.p.tArray.get(currentTopicNum-1).lArray.size()) {
      else if (currentLevelNum > this.lArray.size()) {
        nextID = "end";
      }
      else {
        do {
          currentQuestionNum = SetQuestionNum();
          //if (currentQuestionNum > this.p.tArray.get(currentTopicNum-1).lArray.get(currentLevelNum-1).qArray.size() - 1) {
          if (currentQuestionNum > this.lArray.get(currentLevelNum-1).qArray.size() - 1) {
            currentLevelNum ++;
            //if (currentLevelNum > this.p.tArray.get(currentTopicNum-1).lArray.size()) {
            if (currentLevelNum > this.lArray.size()) {
              nextID = "end";
              break;
            }
            else {
              currentQuestionNum = SetQuestionNum();
            }
          }
        }  while (currentQuestionNum > this.lArray.get(currentLevelNum-1).qArray.size() - 1);
        if (nextID != "end") {
          nextID = CombineID();
        }
      }
      break;
    case "end":
      nextID = "menu";
      break;
    default:
      SplitID(this.currentID);
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
            this.lProgress.set(currentLevelNum-1, currentQuestionNum);
          //}
        }
      }
      //if question number = 0, it is an example
      else if (currentQuestionNum == 0) {
        currentSlideNum++;
        if (currentSlideNum > this.lArray.get(currentLevelNum-1).qArray.get(currentQuestionNum).slideArray.size() ) {
          //currentLevelNum ++;
          currentSlideNum = 1;
          // Want to go back to question we left - not just question 1
          do {
            currentQuestionNum = SetQuestionNum();
            if (currentQuestionNum > this.lArray.get(currentLevelNum-1).qArray.size() - 1) {
              currentLevelNum ++;
              if (currentLevelNum > this.lArray.size()) {
                nextID = "end";
                break;
              }
              else {
                currentQuestionNum = 0; //sets id to example
              }
            }
          }  while (currentQuestionNum > this.lArray.get(currentLevelNum-1).qArray.size() - 1);
        }
        if (nextID != "end") {
          nextID = CombineID();
        }
      }
      break;
  }
  this.prevID.add(this.currentID);
  return nextID;

}

public String GetExampleID(){
  String nextID = this.currentID;
  SplitID(this.currentID);
  currentQuestionNum = 0;
  currentSlideNum = 1;
  nextID = CombineID();
  return nextID;
}

public String GetQuestionID(){
  String nextID = this.currentID;
  SplitID(this.currentID);
  currentQuestionNum = SetQuestionNum();
  currentSlideNum = 1;
  nextID = CombineID();
  return nextID;
}

public String GetSolutionID(){
  String nextID = this.currentID;
  SplitID(this.currentID);
  currentSlideNum = 3;
  nextID = CombineID();
  return nextID;
}

public String GetPrevID(){
  String lastID;
  if (this.prevID.size() > 0){
    lastID = this.prevID.get(this.prevID.size()-1);
    this.prevID.remove(this.prevID.size()-1);
  }
  else { lastID = this.currentID; }
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
  QuestionNum = this.lProgress.get(currentLevelNum-1) + 1;
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

  public void moveSlide(String slideID) {
    this.unloadSlide();
    this.setCurrentID(slideID);
    this.renderSlide();
  }

  public void moveNextSlide() {
    this.unloadSlide();
    this.setCurrentID(this.GetNextID());
    this.renderSlide();
  }

  public void moveBackSlide() {
    this.unloadSlide();
    this.setCurrentID(this.GetPrevID());
    this.renderSlide();
  }

  public void renderSlide() {
    ArrayList<FLMedia> mediaObjects = this.getSlideByID(currentID).getMediaList();

    for(FLMedia media : mediaObjects) {
      if (media.isRendered()) {
        pane.getChildren().add((Node)media.getMedia());
      }
    }
  }

  public void unloadSlide() {
    ArrayList<FLMedia> mediaObjects = this.getSlideByID(currentID).getMediaList();

    for(FLMedia media : mediaObjects) {
      if (media.isRendered()) {
        pane.getChildren().remove(media.getMedia());
      }
    }
  }

  public void playAudio(String slideID, String audioID) {
      if(currentAudio == null) {
          this.currentAudio = getSlideByID(slideID).getAudio(audioID);
          currentAudio.play();
      } else {
          this.currentAudio.stop();
          this.currentAudio = getSlideByID(slideID).getAudio(audioID);
          this.currentAudio.play();
      }
  }

  public void showImage(String slideID, String imageID) {
    getSlideByID(slideID).getImage(imageID).setVisible();
  }

  public void add(Level newLevel) {
    this.lArray.add(newLevel);
    this.lProgress.add(0);
  }

  public int getLevelNum() {
    return this.currentLevelNum;
  }

  //public void addTopic(Topic newTopic) { this.topicList.add(newTopic); }

  //public void addLevel(Level newLevel) { this.levelList.add(newLevel); }

  //public void addExample(Example newExample) { this.questionList.add(newExample); }

  //public void addQuestion(Question newQuestion) { this.questionList.add(newQuestion); }

  //public void addSlide(Slide newSlide) { this.slideList.add(newSlide); }

  // public Slide getSlideByID(String id) {
  //   Slide currentSlide;
  //   switch(id){
  //     case "menu":
  //       currentSlide = menu;
  //       break;
  //     case "feedback":
  //       currentSlide = feedback;
  //       break;
  //     case "end":
  //       currentSlide = end;
  //       break;
  //     default:
  //       SplitID(id);
  //       System.out.println(" Level: " + currentLevelNum
  //                         + " Question: " + currentQuestionNum
  //                         + " Slide: " + currentSlideNum);
  //
  // 			currentSlide = this.p.lArray.get(currentLevelNum-1).qArray.get(currentQuestionNum).slideArray.get(currentSlideNum-1);
  //       break;
  //   }
  //   return currentSlide;
  // }

  // public boolean checkForSlide(String id) {
  //   for (Slide currentSlide : slideList) {
  //     if (currentSlide.getId().equals(id)) {
  //       return true;
  //     }
  //   }
  //
  //   return false;
  // }

  public void addMeta(Meta newMeta) { this.metaList.add(newMeta); }

  public String getMetaByName(String key) {
    for(Meta currentMeta : metaList) {
      if(currentMeta.getKeyword().equals(key)) {
        return currentMeta.getValue();
      }
    }
    return "";
  }

  public void setDefaults(Colors color, TextStyle style) {
    this.presentationDefault.setDefaultStyle(style);
    this.presentationDefault.setDefaultColors(color);
  }

  public void setDefaults(Colors color) { this.presentationDefault.setDefaultColors(color); }

  public void setDefaults(TextStyle style) { this.presentationDefault.setDefaultStyle(style); }


  public void createDefaultSlides(){
    this.feedback = new Slide("feedback", "F");
    FLText feedbackText = new FLText("textF", new Position(50, 25), 1180, this.presentationDefault, new Transitions("trigger", 0, 0));
    feedbackText.add("Feedback");
    this.feedback.add(feedbackText);

    this.end = new Slide("end", "E");
    FLText endText = new FLText("textE", new Position(50, 25), 1180, this.presentationDefault, new Transitions("trigger", 0, 0));
    endText.add("End");
    this.end.add(endText);

    this.menu = new Slide("menu", "M");
    FLText menuText = new FLText("textM", new Position(50, 25), 1180, this.presentationDefault, new Transitions("trigger", 0, 0));
    menuText.add("Menu");
    this.menu.add(menuText);

    // String color = "#000000";
    // String fill = "#000000";
    // String font = "Arial";
    // String textSize = "20";
    // String italic = "false";
    // String bold = "false";
    // String underline = "false";
    //
    // if(color != null) { this.feedback.getSlideDefaults().getDefaultColors().setColor(color); }
    // if(fill != null) { this.feedback.getSlideDefaults().getDefaultColors().setFill(fill); }
    //
    // if(font != null) { this.feedback.getSlideDefaults().getDefaultStyle().setFontFamily(font); }
    // if(textSize != null) { this.feedback.getSlideDefaults().getDefaultStyle().setSize(Integer.parseInt(textSize)); }
    // if(italic != null) { this.feedback.getSlideDefaults().getDefaultStyle().setItalic(Boolean.parseBoolean(italic)); }
    // if(bold != null) { this.feedback.getSlideDefaults().getDefaultStyle().setBold(Boolean.parseBoolean(bold)); }
    // if(underline != null) { this.feedback.getSlideDefaults().getDefaultStyle().setUnderlined(Boolean.parseBoolean(underline)); }
    //


    //this.feedback.setSlideDefaults(this.presentationDefault);

    //this.feedbackText = new FLText("text1", new Position(50, 25), 1180, this.presentationDefault, new Transitions("trigger", 0, 0));

  }
}
