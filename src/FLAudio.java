// NOTE (Chris): The system so far will NOT play any mp3 files
// NOTE (Samuel): javafx MediaPlayer CAN play mp3 files

import java.io.File;
//import javax.sound.sampled.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class FLAudio {

	private Position position;

//	private Clip audioClip;

	private Media media;
	private MediaPlayer mediaPlayer;

	// public FLAudio(String audioPath, Position position) {
	// 	try {
	// 		AudioInputStream stream = AudioSystem.getAudioInputStream(new File(audioPath).getAbsoluteFile());
	//         this.audioClip = AudioSystem.getClip();
	//         this.audioClip.open(stream);
	//         this.position = position;
	// 	} catch (Exception e) {
	// 		e.printStackTrace();
	// 	}
	// }
	//
	// public void play() {
	// 	this.audioClip.start();
	// }

	public FLAudio(String audioPath, Position position) {
			this.media = new Media(new File(audioPath).toURI().toString());
			this.mediaPlayer = new MediaPlayer(this.media);
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
