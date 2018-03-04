
// NOTE (chris): This currently isnt part of the whole render cycle
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class FLAudio {
	private Position position;
	private Clip audioClip;

	private String path;
	private MediaPlayer mediaPlayer;

	public FLAudio(String audioPath, Position position) {
		this.path = audioPath;
		this.mediaPlayer = new MediaPlayer(new Media(new File(this.path).toURI().toString()));
		this.position = position;
		this.mediaPlayer.setOnEndOfMedia(() -> this.mediaPlayer.stop());	// Resets audio when finished playing

		printProperties(this);
	}

	public void play() {
		//this.mediaPlayer.stop();	// Resets audio before playing
		this.mediaPlayer.play();
	}

	public void setPosition(double x, double y) {
		this.position = new Position(x, y);
	}

	public Position getPositition() { return this.position; }

	public String getPath() { return this.path; }

	public void printProperties(FLAudio audio) {
		System.out.println("");
		System.out.println("New FLAudio Created. Listing properties:");
		System.out.println("	Path: " + audio.getPath());
		System.out.println("	Position: x = " + audio.position.getPos1().getX() + ", y = " + audio.position.getPos1().getY());
	}

}
