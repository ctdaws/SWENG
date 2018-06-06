import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Media handler for images. Wraper for a FavaFX imegView
 */
public class PWSImage extends PWSMedia<ImageView>{

//    JavaFX imageView
    private ImageView imageView;
//    filename of image
    private String fileName;

    /**
     * Implementation of abstract method inherited from PWSMedia
     * @return JavaFX ImageView created by this class
     */
    @Override
    public ImageView getPwsMedia() { return this.imageView; }

    /**
     * Constructor for PWSImage
     * @param id ID for identifying this object
     * @param pwsPosition required by PWSMedia, describes position and size of image
     * @param pwsTransitions required by PWSMedia, contains animation timings
     * @param path String filename of image
     */
    public PWSImage(String id, PWSPosition pwsPosition, PWSTransitions pwsTransitions, String path) {
//        Set variables inherited from PWSMedia
        this.setId(id);
        this.setPwsPosition(pwsPosition);
        this.setPwsTransitions(pwsTransitions);
//        Store filename
        this.fileName = path;
//        Create new ImageView from image
        this.imageView = new ImageView(new Image(this.getClass().getResource(path).toExternalForm()));
//        Set top-left position of image
        this.imageView.setX(this.getPwsPosition().getX());
        this.imageView.setY(this.getPwsPosition().getY());
//        Set dimensions of image
        this.imageView.setFitWidth(this.getPwsPosition().getWidth());
        this.imageView.setFitHeight(this.getPwsPosition().getHeight());
//        Generate animations from timing information
        this.setTransition(pwsTransitions);
    }

    /**
     * Generates timeline for showing/hiding image
     * @param pwsTransitions Timing information for timeline
     */
    public void setTransition(PWSTransitions pwsTransitions) {
//        Create new tieline
        Timeline timeline = new Timeline();
//        Is the image set to be triggered manually
        if(pwsTransitions.isTriggered()) {
            timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, "auto", (ActionEvent event) -> {
//                Hide the image
                this.getPwsMedia().setVisible(false);
//                Stop the timeline from continuing
                this.getTimeline().stop();
            }));
        }
//        Image is automatic
        else {
            timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, "auto", (ActionEvent event) -> {
//                Hide the image
                this.getPwsMedia().setVisible(false);
            }));
        }
//        Set image to appear after 'start' number of seconds
        timeline.getKeyFrames().add(new KeyFrame(this.getPwsTransitions().getStart(), "trigger", (ActionEvent event) -> {
            this.getPwsMedia().setVisible(true);
        }));
//        If the duration is set at a positive integer of seconds
        if(this.getPwsTransitions().getPwsDuration() >= 0) {
//            Hide the image after 'duration' number of seconds
            timeline.getKeyFrames().add(new KeyFrame(this.getPwsTransitions().getDuration(), "trigger", (ActionEvent event) -> {
                this.getPwsMedia().setVisible(false);
            }));
        }
//        Add timeline to media
        this.setTimeline(timeline);
    }

    /**
     * Override of toString method
     * @return String containing information about this object
     */
    @Override
    public String toString() {
        return "PWSImage:\nid = " + this.getId() + "\nfileName = " + this.fileName + "\n" + this.getPwsPosition();
    }

    /**
     * Replaces the image in the imageView
     * @param imagePath String filename of new image
     */
    public void setImage(String imagePath) {
        this.imageView.setImage(new Image(imagePath));
    }
}
