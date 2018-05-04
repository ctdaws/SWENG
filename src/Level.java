import java.util.ArrayList;

class Level {
  ArrayList<Question> qArray;

  public Level() {
    qArray = new ArrayList<Question>();

  }

  public void add(Question newQuestion) { this.qArray.add(newQuestion); }

}
