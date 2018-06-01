import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class PWSVideo extends PWSMedia<MediaView> {

    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;

    private String fileName;

    @Override
    public MediaView getPwsMedia() { return this.mediaView; }

    public PWSVideo(String id, PWSPosition pwsPosition, PWSTransitions pwsTransitions, String videoFile) {
        this.setId(id);
        this.setPwsPosition(pwsPosition);
        this.setPwsTransitions(pwsTransitions);
        this.fileName = videoFile;
        this.media = new Media(this.getClass().getResource(videoFile).toExternalForm());
        this.mediaPlayer = new MediaPlayer(this.media);
        this.mediaView = new MediaView(this.mediaPlayer);
        this.mediaView.setX(pwsPosition.getX());
        this.mediaView.setY(pwsPosition.getY());
        this.mediaView.setFitHeight(pwsPosition.getHeight());
        this.mediaView.setFitWidth(pwsPosition.getWidth());
        this.setTransition(pwsTransitions);
    }

    public void setTransition(PWSTransitions pwsTransitions) {
        Timeline timeline = new Timeline();
        if(pwsTransitions.isTriggered()) {
            timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, "auto", (ActionEvent event) -> {
                this.stop();
                this.getTimeline().stop();
            }));
        }
        else {
            timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, "auto", (ActionEvent event) -> {
                this.stop();
            }));
        }
        timeline.getKeyFrames().add(new KeyFrame(this.getPwsTransitions().getStart(), "trigger", (ActionEvent event) -> {
            this.play();
        }));
        if(this.getPwsTransitions().getPwsDuration() >= 0) {
            timeline.getKeyFrames().add(new KeyFrame(this.getPwsTransitions().getDuration(), "trigger", (ActionEvent event) -> {
                this.stop();
            }));
        }
        this.setTimeline(timeline);
    }

    public Media getMedia() { return this.media; }

    public MediaPlayer getMediaPlayer() { return this.mediaPlayer; }

    public MediaView getMediaView() { return this.mediaView; }

    public void play() { this.mediaPlayer.play(); }

    public void pause() { this.mediaPlayer.pause(); }

    public void stop() { this.mediaPlayer.stop(); }

    public void mute(boolean mute) {
        this.mediaPlayer.setMute(mute);
    }

    @Override
    public String toString() {
        return "PWSVideo:\nid = " + this.getId() + "\nfilename = " + this.fileName + "\n" + this.mediaView;
    }
}
