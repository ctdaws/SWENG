import java.util.ArrayList;

public class LQTopic {

    private ArrayList<LQLevel> lqLevelArrayList;
    private String lqTopicId;

    public LQTopic(String lqTopicId) {
        this.lqTopicId = lqTopicId;
        this.lqLevelArrayList = new ArrayList<>();
    }

    public String getID() { return this.lqTopicId; }

    public void add(LQLevel lqLevel) { this.lqLevelArrayList.add(lqLevel); }
}
