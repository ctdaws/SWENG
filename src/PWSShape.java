import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class PWSShape extends PWSMedia<Shape> {

    private Shape shape;

    private String type;
    private double stroke;

    private PWSColors pwsColors;

    @Override
    public Shape getPwsMedia() { return this.shape; }

    public PWSShape(String id, PWSPosition pwsPosition, PWSTransitions pwsTransitions, PWSColors pwsColors, String type, double stroke) {
        this.setId(id);
        this.setPwsPosition(pwsPosition);
        this.setPwsTransitions(pwsTransitions);
        this.type = type.toLowerCase();
        this.stroke = stroke;
        this.pwsColors = pwsColors;
        switch(this.type) {
            case "ellipse": {
                this.shape = new Ellipse(pwsPosition.getCenterX(), pwsPosition.getCenterY(), pwsPosition.getWidth() / 2, pwsPosition.getHeight() / 2);
                break;
            }
            case "rectangle": {
                this.shape = new Rectangle(pwsPosition.getX(), pwsPosition.getY(), pwsPosition.getWidth(), pwsPosition.getHeight());
                break;
            }
            case "line": {
                this.shape = new Line(pwsPosition.getX(), pwsPosition.getY(), pwsPosition.getX2(), pwsPosition.getY2());
                break;
            }
        }
        this.shape.setFill(pwsColors.getFill());
        this.shape.setStroke(pwsColors.getColor());
        this.shape.setStrokeWidth(stroke);
        this.setTransition(pwsTransitions);
    }

    public void setTransition(PWSTransitions pwsTransitions) {
        Timeline timeline = new Timeline();
        if(pwsTransitions.isTriggered()) {
            timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, "auto", (ActionEvent event) -> {
                this.getPwsMedia().setVisible(false);
                this.getTimeline().stop();
            }));
        }
        else {
            timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, "auto", (ActionEvent event) -> {
                this.getPwsMedia().setVisible(false);
            }));
        }
        timeline.getKeyFrames().add(new KeyFrame(this.getPwsTransitions().getStart(), "trigger", (ActionEvent event) -> {
            this.getPwsMedia().setVisible(true);
        }));
        if(this.getPwsTransitions().getPwsDuration() >= 0) {
            timeline.getKeyFrames().add(new KeyFrame(this.getPwsTransitions().getDuration(), "trigger", (ActionEvent event) -> {
                this.getPwsMedia().setVisible(false);
            }));
        }
        this.setTimeline(timeline);
    }

    @Override
    public String toString() {
        return "PWSShape:\nid = " + this.getId() + "\n" + this.getPwsPosition() + "\n" + this.shape;
    }

}
