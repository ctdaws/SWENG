import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.util.Duration;

/**
 * LQButton
 * The LQButton class is a wrapper and provides constructor methods for the JavaFX Button. Also allows for adding an
 * image for the background.
 *
 * @author Samuel Broughton
 * @version 2.3
 */

public class LQButton extends LQMedia<Button>{

    private Button button;
    private String path;

//    References for image/audio to be triggered by button press
    private PWSImage pwsImage;
    private PWSAudio pwsAudio;

    /**
     * Provide required implementation of getLQMedia as specified by LQMedia
     * @return Button
     */
    @Override
    public Button getLQMedia() { return this.button; }

    /**
     * Constructor for LQButton
     * @param id String id for identifying the button
     * @param pwsPosition PWSPosition for setting location and dimensions of the created button
     * @param pwsTransitions PWSTransitions for setting the appearance timings of the created button
     */
    LQButton(String id, PWSPosition pwsPosition, PWSTransitions pwsTransitions) {
//        Copy args
        this.setId(id);
        this.setPwsPosition(pwsPosition);
        this.setPwsTransitions(pwsTransitions);
//        Create new JavaFX button
        this.button = new Button();
//        Set location (top-left) of the button
        this.button.setLayoutX(pwsPosition.getX());
        this.button.setLayoutY(pwsPosition.getY());
//        Set width/height of the button using CSS
        this.button.setStyle("-fx-min-width: " + pwsPosition.getWidth() + "px; " + "-fx-min-height: " + pwsPosition.getHeight() + "px; ");
//        Add appearance animations to the button
        this.setTransition(pwsTransitions);
    }

    /**
     * Constructor for LQButton, with background image
     * @param id String id for identifying the button
     * @param pwsPosition PWSPosition for setting location and dimensions of the created button
     * @param pwsTransitions PWSTransitions for setting the appearance timings of the created button
     * @param path String path to location of background image
     */
    LQButton(String id, PWSPosition pwsPosition, PWSTransitions pwsTransitions, String path) {
//        Copy args
        this.setId(id);
        this.setPwsPosition(pwsPosition);
        this.setPwsTransitions(pwsTransitions);
        this.path = path;
//        Create new JavaFX button
        this.button = new Button();
//        Set location (top-left) of the button
        this.button.setLayoutX(pwsPosition.getX());
        this.button.setLayoutY(pwsPosition.getY());
//        Set width/height and background image of button using CSS
        this.button.setStyle("-fx-min-width: " + pwsPosition.getWidth() + "px; " + "-fx-min-height: " + pwsPosition.getHeight() + "px; " + "-fx-border: none; " + "-fx-background-color: transparent; " + "-fx-background-image: url('" + path + "'); " + "-fx-background-size: " + pwsPosition.getWidth() + "px " + pwsPosition.getHeight() + "px;");
//        Add appearance animations to the button
        this.setTransition(pwsTransitions);
    }

    /**
     * setTransition applies appearance animations to the button
     * @param pwsTransitions PWSTransitions containing animation timings
     */
    public void setTransition(PWSTransitions pwsTransitions) {
//        Create new Timeline
        Timeline timeline = new Timeline();
//        If animation is triggered manually
        if(pwsTransitions.isTriggered()) {
//            Create keyframe to play when slide loads
            timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, "auto", (ActionEvent event) -> {
//                Hide button
                this.getLQMedia().setVisible(false);
//                Stop Timeline
                this.getTimeline().stop();
            }));
        }
//        If animation plays automatically
        else {
//            Create new Timeline to play when slide loads
            timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, "auto", (ActionEvent event) -> {
//                Hide button
                this.getLQMedia().setVisible(false);
            }));
        }
//        Create keyframe to play after 'start' number of seconds has passed
        timeline.getKeyFrames().add(new KeyFrame(this.getPwsTransitions().getStart(), "trigger", (ActionEvent event) -> {
//            Show button
            this.getLQMedia().setVisible(true);
        }));
//        Is a 'duration' set?
        if(this.getPwsTransitions().getPwsDuration() >= 0) {
//            Create keyframe to play after 'duration' number of seconds has passed
            timeline.getKeyFrames().add(new KeyFrame(this.getPwsTransitions().getDuration(), "trigger", (ActionEvent event) -> {
//                Hide button
                this.getLQMedia().setVisible(false);
            }));
        }
//        Apply Timeline to button
        this.setTimeline(timeline);
    }

    /**
     * Adds text to button
     * @param buttonText String of text to add to the button
     */
    public void add(String buttonText) { this.button.setText(buttonText); }

    /**
     * Adds an image for reference when creating EventListener for button
     * @param pwsImage PWSImage to be triggered when button pressed
     */
    public void add(PWSImage pwsImage) { this.pwsImage = pwsImage; }

    /**
     * Adds audio for reference when creating EventListener for button
     * @param pwsAudio PWSAudio to be triggered when button pressed
     */
    public void add(PWSAudio pwsAudio) { this.pwsAudio = pwsAudio; }

    /**
     * returns JavaFX button
     * @return Button created by this wrapper
     */
    public Button getLQButton() { return this.button; }

    /**
     * returns audio held for reference
     * @return PWSAudio to be triggered by button press
     */
    public PWSAudio getButtonTriggerAudio() { return this.pwsAudio; }

    /**
     * returns image held for reference
     * @return PWSImage to be triggered by button press
     */
    public PWSImage getButtonTriggerImage() { return this.pwsImage; }

    /**
     * Override of default method toString
     * @return Information about this LQButton
     */
    public String toString() {
        return "LQButton:\nid = " + this.getId() + "\nbackground image = " + this.path + "\n" + this.getPwsPosition();
    }
}
