import java.util.ArrayList;

public class LQLevel {

    private ArrayList<LQQuestion> lqQuestionArrayList;
    private String lqLevelId;

    public LQLevel(String lqLevelId) {
        this.lqLevelId = lqLevelId;
        this.lqQuestionArrayList = new ArrayList<>();
    }

    public String getId() { return this.lqLevelId; }

    public void add(LQQuestion lqQuestion) { this.lqQuestionArrayList.add(lqQuestion); }

    public ArrayList<LQQuestion> getLqQuestionArray() { return this.lqQuestionArrayList; }
}
