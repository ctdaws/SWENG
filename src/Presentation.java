import java.util.ArrayList;

public class Presentation {

  private ArrayList<Slide> slideList;
  private ArrayList<Meta> metaList;
  private Defaults presentationDefault;
  private Position slideSize;

  private String currentID;
  //private Navigator navigator;

  public Presentation(Defaults programDefaults, Position slideSize) {
    this.slideList = new ArrayList<Slide>();
    this.metaList = new ArrayList<Meta>();
    this.presentationDefault = programDefaults;
    this.slideSize = slideSize;
    this.currentID = "Q";
    //this.navigator = new Navigator();
    //navigator.setID("Q");
  }

  public double getWidth() {
    return this.slideSize.getX();
  }

  public double getHeight() {
    return this.slideSize.getY();
  }

  private void getNextID() {
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

  public Slide getNextSlide() {
    this.getNextID();
    return this.getSlideByID(currentID);
  }












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
