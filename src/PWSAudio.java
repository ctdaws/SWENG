import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PWSAudio extends PWSMedia<MediaPlayer> {

    private String fileName;
    private MediaPlayer mediaPlayer;

    @Override
    public MediaPlayer getPwsMedia() { return this.mediaPlayer; }

    public PWSAudio(String id, String audioFile, PWSPosition pwsPosition) {
        this.setId(id);
        this.setPwsPosition(pwsPosition);
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
}
