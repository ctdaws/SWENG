import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Media handler for audio. Wrapper for a JavaFX mediaPLayer.
 *
 * @author Samuel Broughton, Chris Dawson
 * @version 1.5
 */
public class PWSAudio extends PWSMedia<MediaPlayer> {

//    JavaFX mediaPlayer
    private MediaPlayer mediaPlayer;
//    filename of audio
    private String fileName;

    /**
     * Implementation of abstract method inherited from PWSMedia
     * @return JavaFX MediaPlayer created by this class
     */
    @Override
    public MediaPlayer getPwsMedia() { return this.mediaPlayer; }

    /**
     * Constructor for PWSAudio
     * @param id ID for identifying this object
     * @param pwsPosition required by PWSMedia, but not used by PWSAudio as no visual aspect is utilised
     * @param pwsTransitions Contains timing information for automatically/manually playeing audio
     * @param audioFile String of filepath for audio
     */
    PWSAudio(String id, PWSPosition pwsPosition, PWSTransitions pwsTransitions, String audioFile) {
//        Set variables inherited from PWSMedia
        this.setId(id);
        this.setPwsPosition(pwsPosition);
        this.setPwsTransitions(pwsTransitions);
//        Store filename
        this.fileName = audioFile;
//        Create new mediaPlayer
        this.mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource(audioFile).toExternalForm()));
//        Stop media when it reaches the end
        this.mediaPlayer.setOnEndOfMedia(() -> { this.mediaPlayer.stop(); });
//        Generate animations from timing information
        this.setTransition(pwsTransitions);
    }

    /**
     * Generates timeline for playing audio automatically
     * @param pwsTransitions Timing information for timeline
     */
    public void setTransition(PWSTransitions pwsTransitions) {
//        Create new timeline
        Timeline timeline = new Timeline();
//        Is the audio set to be triggered manually
        if(pwsTransitions.isTriggered()) {
            timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, "auto", (ActionEvent event) -> {
//                Stop the audio playing
                this.stop();
//                Stop the timeline from continuing
                this.getTimeline().stop();
            }));
        }
//        Audio is automatic
        else {
            timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, "auto", (ActionEvent event) -> {
//                Stop the audio playing
                this.stop();
            }));
        }
//        Set audio to play after 'start' number of seconds
        timeline.getKeyFrames().add(new KeyFrame(this.getPwsTransitions().getStart(), "trigger", (ActionEvent event) -> {
            this.play();
        }));
//        If the duration is set at a positive integer of seconds
        if(this.getPwsTransitions().getPwsDuration() >= 0) {
//            Stop playing audio after 'duration' number of seconds
            timeline.getKeyFrames().add(new KeyFrame(this.getPwsTransitions().getDuration(), "trigger", (ActionEvent event) -> {
                this.stop();
            }));
        }
//        Add timeline to media
        this.setTimeline(timeline);
    }

    /**
     * Method for playing audio from mediaPlayer
     */
    public void play() {
//        Stops audio before playing, ensures media plays from beginning
        this.mediaPlayer.stop();
        this.mediaPlayer.play();
    }

    /**
     * Method for stopping audio playing from mediaPlayer
     */
    public void stop() { this.mediaPlayer.stop(); }

    /**
     * Method for muting the mediaPlayer
     * @param mute true:mute, false:audible
     */
    public void mute(boolean mute) { this.mediaPlayer.setMute(mute); }

    /**
     * Override of toString method
     * @return String containing information about this audio object
     */
    @Override
    public String toString() {
        return "PWSAudio:\nid = " + this.getId() + "\nfileName = " + this.fileName + "\n" + this.getPwsPosition();
    }
}
