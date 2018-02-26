// NOTE (Chris): The system so far will NOT play any mp3 files
import java.io.File;
import javax.sound.sampled.*;

public class FLAudio {

	private Position position;	
	private Clip audioClip;

	public FLAudio(String audioPath, Position position) {
		try {
			AudioInputStream stream = AudioSystem.getAudioInputStream(new File(audioPath).getAbsoluteFile());
	        this.audioClip = AudioSystem.getClip();
	        this.audioClip.open(stream);
	        this.position = position;
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void play() {
		this.audioClip.start();
	}

	public void setPosition(double x, double y, double x2, double y2) {
		this.position = new Position(x, y, x2, y2);
	}

	public Position getPositition() {
		return this.position;
	}

}
