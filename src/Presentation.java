import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Presentation {

  private ArrayList<Topic> topicList;
  private ArrayList<Level> levelList;
  private ArrayList<Question> questionList;
  private ArrayList<Slide> slideList;
  private ArrayList<Meta> metaList;
  private Defaults presentationDefault;
  private Position slideSize;

  public Pane pane;

  public FLAudio currentAudio;

  private String currentID;
  private String nextSlideID;

  public Presentation(Defaults programDefaults, Position slideSize) {
    this.topicList = new ArrayList<Topic>();
    this.levelList = new ArrayList<Level>();
    this.questionList = new ArrayList<Question>();
    this.slideList = new ArrayList<Slide>();
    this.metaList = new ArrayList<Meta>();
    this.presentationDefault = programDefaults;
    this.slideSize = slideSize;
    this.currentID = "Q";
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

  public void getNextID() {
    switch(this.currentID) {
      case "Q":
        this.currentID = "A";
        break;
      case "X":
        this.currentID = "Q";
        break;
      case "A":
        this.currentID = "F";
        break;
      case "S":
        this.currentID = "F";
        break;
      case "F":
        this.currentID = "E";
        break;
      case "E":
        break;
      default:
        break;
    }
  }

  public void moveSlide(String slideID) {
    this.unloadSlide();
    this.setCurrentID(slideID);
    this.renderSlide();
  }

  public void moveNextSlide() {
    this.unloadSlide();
    this.getNextID();
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

  public void addTopic(Topic newTopic) { this.topicList.add(newTopic); }

  public void addLevel(Level newLevel) { this.levelList.add(newLevel); }

  public void addExample(Example newExample) { this.questionList.add(newExample); }

  public void addQuestion(Question newQuestion) { this.questionList.add(newQuestion); }

  public void addSlide(Slide newSlide) { this.slideList.add(newSlide); }

  public Slide getSlideByID(String id) {
    for (Slide currentSlide : slideList) {
      if (currentSlide.getId().equals(id)) {
        return currentSlide;
      }
    }

    return null;
  }

  public boolean checkForSlide(String id) {
    for (Slide currentSlide : slideList) {
      if (currentSlide.getId().equals(id)) {
        return true;
      }
    }

    return false;
  }

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
}
