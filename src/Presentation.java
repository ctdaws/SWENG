import java.util.ArrayList;

public class Presentation {

  private ArrayList<Slide> slideList;
  private ArrayList<Meta> metaList;

  public Presentation() {
    this.slideList = new ArrayList<Slide>();
    this.metaList = new ArrayList<Meta>();
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

}
