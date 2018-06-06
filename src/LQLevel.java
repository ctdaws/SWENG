import java.util.ArrayList;

/**
 * This class is used to create level objects in the presentation.
 * It contains a set of question (and example) objects.
 * @author Oscar Thorpe, Matt Holt, Ben Grainger
 */
public class LQLevel {

//  Array containing question (and example) objects in this level
    private ArrayList<LQQuestion> lqQuestionArrayList;

//  Level ID
    private String lqLevelId;

    /**
     * Constructor for level objects
     * @param lqLevelId ID for the level
     */
    public LQLevel(String lqLevelId) {
        this.lqLevelId = lqLevelId;
        this.lqQuestionArrayList = new ArrayList<>();
    }

    /**
     * Gets level ID
     * @return lqLevelId
     */
    public String getId() { return this.lqLevelId; }

    /**
     * Adds a question (or example) object to the level
     * @param lqQuestion question object
     */
    public void add(LQQuestion lqQuestion) { this.lqQuestionArrayList.add(lqQuestion); }

    /**
     * Gets the array of question objects in this level
     * @return lqQuestionArrayList
     */
    public ArrayList<LQQuestion> getLqQuestionArray() { return this.lqQuestionArrayList; }
}
