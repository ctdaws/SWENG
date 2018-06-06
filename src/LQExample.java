import java.util.ArrayList;

/**
 * This class is used to create example objects in the presentation, it extends LQQuestion.
 * It contains a set of slide objects.
 * @author Oscar Thorpe, Matt Holt, Ben Grainger
 */
public class LQExample extends LQQuestion{

    //  Array containing slide objects in this example
    private ArrayList<LQSlide> lqSlideArrayList;

    /**
     * Constructor for example object
     * @param lqExampleId ID for the examploe
     */
    public LQExample(String lqExampleId) {
        super(lqExampleId);
        lqSlideArrayList = new ArrayList<>();
    }
}
