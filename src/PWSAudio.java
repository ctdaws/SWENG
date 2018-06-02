import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class PWSAudio extends PWSMedia<MediaPlayer> {

    private MediaPlayer mediaPlayer;

    private String fileName;

    @Override
    public MediaPlayer getPwsMedia() { return this.mediaPlayer; }

    public PWSAudio(String id, PWSPosition pwsPosition, PWSTransitions pwsTransitions, String audioFile) {
        this.setId(id);
        this.setPwsPosition(pwsPosition);
        this.setPwsTransitions(pwsTransitions);
        this.fileName = audioFile;
        this.mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource(audioFile).toExternalForm()));
        this.mediaPlayer.setOnEndOfMedia(() -> { this.mediaPlayer.stop(); });
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

    public void play() {
        this.mediaPlayer.stop();
        this.mediaPlayer.play();
    }

    public void stop() {
        this.mediaPlayer.stop();
    }

    public void mute(boolean mute) {
        this.mediaPlayer.setMute(mute);
    }

    @Override
    public String toString() {
        return "PWSAudio:\nid = " + this.getId() + "\nfileName = " + this.fileName + "\n" + this.getPwsPosition();
    }
}
