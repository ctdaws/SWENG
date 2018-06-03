import java.util.ArrayList;

public class LQQuestion{

    private ArrayList<LQSlide> lqSlideArrayList;
    private String lqQuestionId;

    public LQQuestion(String lqQuestionId) {
        this.lqQuestionId = lqQuestionId;
        this.lqSlideArrayList = new ArrayList<>();
    }

    public String getId() {
        return this.lqQuestionId;
    }

    public void add(LQSlide lqSlide) {
        this.lqSlideArrayList.add(lqSlide);
    }

    public ArrayList<LQSlide> getLqSlideArray() { return this.lqSlideArrayList; }
}
