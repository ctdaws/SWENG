// import static org.junit.Assert.*;

// import java.util.List;
// import org.junit.Before;
// import org.junit.Test;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class ParserTest extends Application{

	XMLParser xmlReader;

	//@Before
	public ParserTest(){
			System.out.println("Starting to build XML Parser.");
			xmlReader = new XMLParser("../resources/example.xml");
			System.out.println("Finished building XML Parser.");
		}

	public static void main(String[] args){	launch(args); }

	@Override
	public void start(Stage PrimaryStage) {

		new ParserTest();

		System.out.println("--------------------");

		stop();

	}

	@Override
	public void stop() {
		Platform.exit();
	}


}
