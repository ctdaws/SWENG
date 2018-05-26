import java.util.ArrayList;

public class LQExample extends LQQuestion{

    private ArrayList<LQSlide> lqSlideArrayList;

    public LQExample(String lqExampleId) {
        super(lqExampleId);
        lqSlideArrayList = new ArrayList<>();
    }
}
