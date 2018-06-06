import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

/**
 * Class for creating shapes. Uses code taken from the Graphics module supplied by group 5.
 */
public class PWSShape extends PWSMedia<Shape> {

//    JavaFX Shape
    private Shape shape;

//    Type of shape (rectangle, ellipse, line)
    private String type;
//    Thickness of shape outline
    private double stroke;

//    Colour of shape; color:shape outline, fill:shape fill
    private PWSColors pwsColors;

    /**
     * Implementation of abstract method inherited from PWSMedia
     * @return JavaFX Shape created by this class
     */
    @Override
    public Shape getPwsMedia() { return this.shape; }

    /**
     * Constructor for PWSShape
     * @param id ID for identifying this object
     * @param pwsPosition required by PWSMedia, defines size and location of the shape
     * @param pwsTransitions required by PWSMedia, contains animation timings
     * @param pwsColors contains color information
     * @param type string describing the type of shape to create
     * @param stroke thickness of shape outline
     */
    PWSShape(String id, PWSPosition pwsPosition, PWSTransitions pwsTransitions, PWSColors pwsColors, String type, double stroke) {
//        Set variable inherited from PWSMedia
        this.setId(id);
        this.setPwsPosition(pwsPosition);
        this.setPwsTransitions(pwsTransitions);
//        Convert type string to lowercase
        this.type = type.toLowerCase();
//        Store shape information
        this.stroke = stroke;
        this.pwsColors = pwsColors;
//        Generate shape based on type
        switch(this.type) {
//            Ellipse
            case "ellipse": {
                this.shape = new Ellipse(pwsPosition.getCenterX(), pwsPosition.getCenterY(), pwsPosition.getWidth() / 2, pwsPosition.getHeight() / 2);
                break;
            }
//            Rectangle
            case "rectangle": {
                this.shape = new Rectangle(pwsPosition.getX(), pwsPosition.getY(), pwsPosition.getWidth(), pwsPosition.getHeight());
                break;
            }
//            Line
            case "line": {
                this.shape = new Line(pwsPosition.getX(), pwsPosition.getY(), pwsPosition.getX2(), pwsPosition.getY2());
                break;
            }
        }
//        Set fill/outine colours
        this.shape.setFill(pwsColors.getFill());
        this.shape.setStroke(pwsColors.getColor());
//        Set outline thickness
        this.shape.setStrokeWidth(stroke);
//        Generate animations from timing information
        this.setTransition(pwsTransitions);
    }

    /**
     * Generates timeline for showing/hiding shape
     * @param pwsTransitions Timing information for timeline
     */
    public void setTransition(PWSTransitions pwsTransitions) {
//        Create new timeline
        Timeline timeline = new Timeline();
//        Is the shape set to be triggered manually
        if(pwsTransitions.isTriggered()) {
            timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, "auto", (ActionEvent event) -> {
//                Hide the shape
                this.getPwsMedia().setVisible(false);
//                Stop the timeline from continuing
                this.getTimeline().stop();
            }));
        }
//        Shape is automatic
        else {
            timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, "auto", (ActionEvent event) -> {
//                Hide the shape
                this.getPwsMedia().setVisible(false);
            }));
        }
//        Set shape to appear after 'start' number of seconds
        timeline.getKeyFrames().add(new KeyFrame(this.getPwsTransitions().getStart(), "trigger", (ActionEvent event) -> {
            this.getPwsMedia().setVisible(true);
        }));
//        If the duration is set to a positive number of seconds
        if(this.getPwsTransitions().getPwsDuration() >= 0) {
//            Hide the image after 'duration' numnber of seconds
            timeline.getKeyFrames().add(new KeyFrame(this.getPwsTransitions().getDuration(), "trigger", (ActionEvent event) -> {
                this.getPwsMedia().setVisible(false);
            }));
        }
//        Add timeline to media
        this.setTimeline(timeline);
    }

    /**
     * Override of toString method
     * @return String containing information about this shape
     */
    @Override
    public String toString() {
        return "PWSShape:\nid = " + this.getId() + "\n" + this.getPwsPosition() + "\n" + this.shape;
    }

}
