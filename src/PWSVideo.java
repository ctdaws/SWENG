import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

/**
 * Media handler for video. Wrapper for a JavaFX mediaView. Modified to be used by ContractVideo
 *
 * @author Samuel Broughton
 * @version 1.3
 */
public class PWSVideo extends PWSMedia<MediaView> {
//    Media objects
    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
//    filename of video
    private String fileName;

    /**
     * Implementation of abstract method inherited from PWSMedia
     * @return JavaFX MediaView created by this class
     */
    @Override
    public MediaView getPwsMedia() { return this.mediaView; }

    /**
     * Constructor for PWSVideo
     * @param id ID for identifying this object
     * @param pwsPosition required by PWSMedia, describes position and size of video
     * @param pwsTransitions required by PWSMedis, contains automated playback timings
     * @param videoFile filename of the video source
     */
    PWSVideo(String id, PWSPosition pwsPosition, PWSTransitions pwsTransitions, String videoFile) {
//        Set variables inherited from PWSMedia
        this.setId(id);
        this.setPwsPosition(pwsPosition);
        this.setPwsTransitions(pwsTransitions);
//        Strore filename
        this.fileName = videoFile;
//        Create media
        this.media = new Media(this.getClass().getResource(videoFile).toExternalForm());
        this.mediaPlayer = new MediaPlayer(this.media);
        this.mediaView = new MediaView(this.mediaPlayer);
//        Set video position and size
        this.mediaView.setX(pwsPosition.getX());
        this.mediaView.setY(pwsPosition.getY());
        this.mediaView.setFitHeight(pwsPosition.getHeight());
        this.mediaView.setFitWidth(pwsPosition.getWidth());
//        Generate automatic playback timings
        this.setTransition(pwsTransitions);
    }

    /**
     * Generates timeline for automatic playback of video
     * @param pwsTransitions timing information for timeline
     */
    public void setTransition(PWSTransitions pwsTransitions) {
//        CReate new timeline
        Timeline timeline = new Timeline();
//        Is the video set to be played manually
        if(pwsTransitions.isTriggered()) {
            timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, "auto", (ActionEvent event) -> {
//                Stop the video
                this.stop();
//                Stop the timeline from continuing
                this.getTimeline().stop();
            }));
        }
//        Playback is autoatic
        else {
            timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, "auto", (ActionEvent event) -> {
//                Stop the video
                this.stop();
            }));
        }
//        Set the video to start playing after 'start' number of seconds
        timeline.getKeyFrames().add(new KeyFrame(this.getPwsTransitions().getStart(), "trigger", (ActionEvent event) -> {
            this.play();
        }));
//        If the duration is set to a positive integer of seconds
        if(this.getPwsTransitions().getPwsDuration() >= 0) {
//            Stop playback after 'duration' number of seconds
            timeline.getKeyFrames().add(new KeyFrame(this.getPwsTransitions().getDuration(), "trigger", (ActionEvent event) -> {
                this.stop();
            }));
        }
//        Add timeline to media
        this.setTimeline(timeline);
    }

    /**
     * Getter for media
     * @return returns JavaFX Media
     */
    public Media getMedia() { return this.media; }

    /**
     * Getter for mediaPlayer
     * @return returns JavaFX MediaPlayer
     */
    public MediaPlayer getMediaPlayer() { return this.mediaPlayer; }

    /**
     * Getter for mediaView
     * @return returns JavaFX MediaView
     */
    public MediaView getMediaView() { return this.mediaView; }

    /**
     * Method for playing the video
     */
    public void play() { this.mediaPlayer.play(); }

    /**
     * Method for pausing the video
     */
    public void pause() { this.mediaPlayer.pause(); }

    /**
     * Method for stopping the video
     */
    public void stop() { this.mediaPlayer.stop(); }

    /**
     * Method for muting the videos audio
     * @param mute boolean true:mute, false:audible
     */
    public void mute(boolean mute) { this.mediaPlayer.setMute(mute); }

    /**
     * Override of toString method
     * @return string containing information about the video object
     */
    @Override
    public String toString() {
        return "PWSVideo:\nid = " + this.getId() + "\nfilename = " + this.fileName + "\n" + this.mediaView;
    }
}
