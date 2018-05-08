import java.util.ArrayList;

class Level {
    ArrayList<Question> qArray;
    private String id;

    public Level(String id) {
        qArray = new ArrayList<Question>();
        this.id = id;
    }

    public void add(Question newQuestion) { this.qArray.add(newQuestion); }

}