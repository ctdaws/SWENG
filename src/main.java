public class Main {
	public static void main(String[] args) {		

		Image testImage = new Image("../resources/sampleImg.jpg", new Position(0.0f, 0.0f), 0);

		Text testText = new Text("Sample", new Position(0.0f, 0.0f), 0);

		Audio testAudio = new Audio("../resources/sampleAudio.wav");
		testAudio.play();				//play audio
	}
}