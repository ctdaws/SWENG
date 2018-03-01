// import static org.junit.Assert.*;

// import java.util.List;
// import org.junit.Before;
// import org.junit.Test;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class ParserTest extends Application{

	XMLParser xmlReader;
	Presentation presentation;

	//@Before
	public void parserTest(){
			System.out.println("Starting to build XML Parser.");
			xmlReader = new XMLParser("../resources/example.xml");
			presentation = xmlReader.getPresentation();
			System.out.println("Finished building XML Parser.");
		}

	public static void main(String[] args){	launch(args); }

	@Override
	public void start(Stage PrimaryStage) {

		parserTest();


		System.out.println("--------------------");

		stop();

	}

	@Override
	public void stop() {
		Platform.exit();
	}


}
