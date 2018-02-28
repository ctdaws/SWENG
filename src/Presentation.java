import java.util.ArrayList;

public class Presentation {

  private ArrayList<Slide> slideList;
  private ArrayList<Meta> meta;

  public Presentation() {
    this.slideList = new ArrayList<Slide>();
    this.meta = new ArrayList<Meta>();
  }

  public void addSlide(Slide newSlide) {
    this.slideList.add(newSlide);
  }

  public Slide getSlideByID(String id) {
    for (Slide currentSlide : slideList) {
      if (currentSlide.getId().equals(id)) {
        return currentSlide;
      }
    }

    return null;
  }

  public void addMeta(Meta newMeta) {
    this.meta.add(newMeta);
  }

}
