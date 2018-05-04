import java.util.ArrayList;

class Topic {
  ArrayList<Level> lArray;

  public Topic() {
    lArray = new ArrayList<Level>();

  }

  public void add(Level newLevel) { this.lArray.add(newLevel); }

}
