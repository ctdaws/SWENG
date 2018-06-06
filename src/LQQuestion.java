import java.util.ArrayList;

/**
 * This class is used to create question objects in the presentation.
 * It contains a set of slide objects.
 * @author Oscar Thorpe, Matt Holt, Ben Grainger
 */
public class LQQuestion{

//  Array containing slide objects in this question
    private ArrayList<LQSlide> lqSlideArrayList;

//  Question ID
    private String lqQuestionId;

    /**
     * Constructor for question objects
     * @param lqQuestionId ID for the question
     */
    public LQQuestion(String lqQuestionId) {
        this.lqQuestionId = lqQuestionId;
        this.lqSlideArrayList = new ArrayList<>();
    }

    /**
     * Gets the question ID
     * @return lqQuestionId
     */
    public String getId() { return this.lqQuestionId; }

    /**
     * Adds a slide object to the question
     * @param lqSlide slide object
     */
    public void add(LQSlide lqSlide) { this.lqSlideArrayList.add(lqSlide); }

    /**
     * Gets the array of slide objects in this question
     * @return lqSlideArrayList
     */
    public ArrayList<LQSlide> getLqSlideArray() { return this.lqSlideArrayList; }
}
