import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class LQButton extends LQMedia<Button>{

    private Button button;
    private String path;

    private PWSImage pwsImage;
    private PWSAudio pwsAudio;

    @Override
    public Button getLQMedia() { return this.button; }

    public LQButton(String id, PWSPosition pwsPosition, PWSTransitions pwsTransitions) {
        this.setId(id);
        this.setPwsPosition(pwsPosition);
        this.setPwsTransitions(pwsTransitions);
        this.button = new Button();
        this.button.setLayoutX(pwsPosition.getX());
        this.button.setLayoutY(pwsPosition.getY());
        this.button.setStyle("-fx-min-width: " + pwsPosition.getWidth() + "px; " + "-fx-min-height: " + pwsPosition.getHeight() + "px; ");
        this.setTransition(pwsTransitions);
    }

    public LQButton(String id, PWSPosition pwsPosition, PWSTransitions pwsTransitions, Node graphic) {
        this.setId(id);
        this.setPwsPosition(pwsPosition);
        this.setPwsTransitions(pwsTransitions);
        this.button = new Button("", graphic);
        this.button.setLayoutX(pwsPosition.getX());
        this.button.setLayoutY(pwsPosition.getY());
        this.button.setStyle("-fx-min-width: " + pwsPosition.getWidth() + "px; " + "-fx-min-height: " + pwsPosition.getHeight() + "px; ");
        this.setTransition(pwsTransitions);
    }

    public LQButton(String id, PWSPosition pwsPosition, PWSTransitions pwsTransitions, String path) {
        this.setId(id);
        this.setPwsPosition(pwsPosition);
        this.setPwsTransitions(pwsTransitions);
        this.button = new Button();
        this.button.setLayoutX(pwsPosition.getX());
        this.button.setLayoutY(pwsPosition.getY());
        this.button.setStyle("-fx-min-width: " + pwsPosition.getWidth() + "px; " + "-fx-min-height: " + pwsPosition.getHeight() + "px; " + "-fx-border: none; " + "-fx-background-color: transparent; " + "-fx-background-image: url('" + path + "'); " + "-fx-background-size: " + pwsPosition.getWidth() + "px " + pwsPosition.getHeight() + "px;");
        this.path = path;
        this.setTransition(pwsTransitions);
    }

    public void setTransition(PWSTransitions pwsTransitions) {
        Timeline timeline = new Timeline();
        if(pwsTransitions.isTriggered()) {
            timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, "auto", (ActionEvent event) -> {
                this.getLQMedia().setVisible(false);
                this.getTimeline().stop();
            }));
        }
        else {
            timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, "auto", (ActionEvent event) -> {
                this.getLQMedia().setVisible(false);
            }));
        }
        timeline.getKeyFrames().add(new KeyFrame(this.getPwsTransitions().getStart(), "trigger", (ActionEvent event) -> {
            this.getLQMedia().setVisible(true);
        }));
        if(this.getPwsTransitions().getPwsDuration() >= 0) {
            timeline.getKeyFrames().add(new KeyFrame(this.getPwsTransitions().getDuration(), "trigger", (ActionEvent event) -> {
                this.getLQMedia().setVisible(false);
            }));
        }
        this.setTimeline(timeline);
    }

    public void add(String buttonText) {
        this.button.setText(buttonText);
    }

    public void add(PWSImage pwsImage) {
        this.pwsImage = pwsImage;
    }

    public void add(PWSAudio pwsAudio) {
        this.pwsAudio = pwsAudio;
    }

    public Button getLQButton() { return this.button; }

    public PWSAudio getButtonTriggerAudio() { return this.pwsAudio; }

    public PWSImage getButtonTriggerImage() { return this.pwsImage; }

    public String toString() {
        return "LQButton:\nid = " + this.getId() + "\nbackground image = " + this.path + "\n" + this.getPwsPosition();
    }
}
