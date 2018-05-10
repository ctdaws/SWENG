import java.util.ArrayList;

class Topic {
    ArrayList<Level> lArray;
    private String id;
    public Topic(String id) {
        lArray = new ArrayList<Level>();
        this.id = id;
    }

    public void add(Level newLevel) { this.lArray.add(newLevel); }

    public String getId() { return this.id; }

}
