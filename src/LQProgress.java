import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;

/**
 * This class is used to create the progress bar which displays the user's progress through the quest.
 * @author Matt Holt, Oscar Thorpe
 */
public class LQProgress {
    private float levelNum;
    private float totalLevels;
    private StackPane stack;
    private int width;
    private ProgressBar pb;
    private Slider slider;

    /**
     * Constructor for the progress bar
     * @param width the width of the progress bar
     * @param levelNum the current level value
     * @param totalLevels the total number of levels in the quest
     */
    public LQProgress(int width, int levelNum, int totalLevels){
        this.levelNum = (float)levelNum;
        this.totalLevels = (float)totalLevels;
        this.width = width;

        //Initialise stack
        this.stack = new StackPane();
        this.stack.getStylesheets().add(this.getClass().getResource("progress.css").toExternalForm());

        //Initialise and set progress bar width  - this.levelNum-1
        this.pb = new ProgressBar((this.levelNum)/this.totalLevels);
        pb.setMinWidth(this.width);
        pb.setMaxWidth(this.width);

        //Initialise and set slider width
        this.slider = new Slider(this.levelNum/this.totalLevels, 1, this.levelNum/this.totalLevels);
        slider.setDisable(true);  //Disable
        slider.setMinWidth(this.width+25);
        slider.setMaxWidth(this.width+25);

        //Add progress bar and slider to stack
        stack.getChildren().addAll(pb, slider);
    }

    /**
     * Set position in progress bar
     * @param level the current level value
     */
    public void setLevelProgress(int level) {
        if(level == 0) {
            this.pb.setProgress((float)level/(this.totalLevels)); //level-1 totalLevels-1
            this.slider.setValue((float)level/this.totalLevels);
        } else {
            this.pb.setProgress((float)(level - 1)/(this.totalLevels)); //level-1 totalLevels-1
            this.slider.setValue((float)(level - 1)/this.totalLevels);
        }
        this.levelNum = (float)level;
    }

    /**
     * Gets the slider object
     * @return slider
     */
    public Slider getSlider() { return this.slider; }

    /**
     * Gets the progress bar object
     * @return pb
     */
    public ProgressBar getProgressBar() { return this.pb; }

    /**
     * Gets the stack pane to add it to GUI layout
     * @return stack
     */
    public StackPane getStackPane() { return this.stack; }
}
