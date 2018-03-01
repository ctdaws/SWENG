import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class FLAudio {

	private Position position;
	private MediaPlayer mediaPlayer;

	public FLAudio(String audioPath, Position position) {
			this.mediaPlayer = new MediaPlayer(new Media(new File(audioPath).toURI().toString()));
			this.position = position;
	}

	public void play() {
		this.mediaPlayer.play();
	}

	public void setPosition(double x, double y, double x2, double y2) {
		this.position = new Position(x, y, x2, y2);
	}

	public Position getPositition() {
		return this.position;
	}

}
