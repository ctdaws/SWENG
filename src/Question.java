import java.util.ArrayList;

class Question {
  ArrayList<Slide> slideArray;
  public Question() {
    slideArray = new ArrayList<Slide>();
  }

  public void add(Slide newSlide) { this.slideArray.add(newSlide); }

}
