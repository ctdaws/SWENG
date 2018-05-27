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

  //protected String currentID;
  protected String nextSlideID;
  protected Slide feedback, end, menu;
  protected int currentSlideNum;
  protected int currentQuestionNum;
  protected int currentLevelNum;
  protected int n = 0;
  protected int aVal = 1;
  protected int fVal = 0;


  private FLText feedbackText;
  //public Navigator navigator;

  public Presentation(Defaults programDefaults, Position slideSize) {
    // this.topicList = new ArrayList<Topic>();
    // this.levelList = new ArrayList<Level>();
    // this.questionList = new ArrayList<Question>();
    // this.slideList = new ArrayList<Slide>();
    this.presentationDefault = programDefaults;
    this.lArray = new ArrayList<Level>();
    this.lProgress = new ArrayList<Integer>();
    this.metaList = new ArrayList<Meta>();

    //navigator = new Navigator();

    createDefaultSlides();
    //this.end = new Slide("end", "E");
    //this.menu = new Slide("menu", "M");

    //this.prevID.add("menu");


    // this.presentationDefaultColor = new Colors("#000000", "#000000");
    // this.presentationDefaultStyle = new TextStyle("Arial", 20, false, false, false);
    // this.setDefaults(this.presentationDefaultColor, this.presentationDefaultStyle);
    this.slideSize = slideSize;
    //this.currentID = "1/1/1";
    //this.currentID = "menu";
    this.pane = new Pane();
    this.pane.setMinWidth(getWidth()-225);
    this.pane.setMinHeight(getHeight()-165);
  }

  public double getWidth() {
    return this.slideSize.getX();
  }

  public double getHeight() {
    return this.slideSize.getY();
  }

  public Defaults getPresentationDefaults() { return this.presentationDefault; }





  public void add(Level newLevel) {
    this.lArray.add(newLevel);
    this.lProgress.add(0);
  }

  //public void addTopic(Topic newTopic) { this.topicList.add(newTopic); }

  //public void addLevel(Level newLevel) { this.levelList.add(newLevel); }

  //public void addExample(Example newExample) { this.questionList.add(newExample); }

  //public void addQuestion(Question newQuestion) { this.questionList.add(newQuestion); }

  //public void addSlide(Slide newSlide) { this.slideList.add(newSlide); }

   public Slide getSlideByID(String id) {
     Slide currentSlide;
     //AnswerSlide currentAnswerSlide;
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
//       case "A":
//         String idArray[] = id.split("/");
//         currentAnswerSlide = this.lArray.get(Integer.parseInt(idArray[0])-1).qArray.get(Integer.parseInt(idArray[1])).slideArray.get(Integer.parseInt(idArray[2])-1);
//         return currentAnswerSlide;
//          break;
       default:
         String idArray[] = id.split("/");
         currentSlide = this.lArray.get(Integer.parseInt(idArray[0])-1).qArray.get(Integer.parseInt(idArray[1])).slideArray.get(Integer.parseInt(idArray[2])-1);
         break;
     }
     return currentSlide;
   }

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
