import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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
    }

    public void play() {
        this.mediaPlayer.stop();
        this.mediaPlayer.play();
    }

    public void stop() {
        this.mediaPlayer.stop();
    }

    @Override
    public String toString() {
        return "PWSAudio:\nid = " + this.getId() + "\nfileName = " + this.fileName + "\n" + this.getPwsPosition();
    }
}
