import java.util.ArrayList;

class Presentation {
  ArrayList<Topic> tArray;

  public Presentation() {
    tArray = new ArrayList<Topic>();

  }

  public void add(Topic newTopic) { this.tArray.add(newTopic); }

}
