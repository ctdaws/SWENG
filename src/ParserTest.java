// import static org.junit.Assert.*;

// import java.util.List;
// import org.junit.Before;
// import org.junit.Test;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class ParserTest extends Application{

	//public Colors programDefaultColor = new Colors("#000000", "#000000");
	//public TextStyle programDefaultStyle = new TextStyle("Arial", 20, false, false, false);

	//public Defaults programDefault = new Defaults(programDefaultStyle, programDefaultColor);

	//XMLParser xmlReader;
	Presentation presentation;

	//@Before
	public void parserTest(){
			System.out.println("Starting to build XML Parser.");

			Colors programDefaultColor = new Colors("#000000", "#000000");
			TextStyle programDefaultStyle = new TextStyle("Arial", 20, false, false, false);
			Defaults programDefault = new Defaults(programDefaultStyle, programDefaultColor);

			// NOTE (chris): Not loading the resource in the correct way currently
//			XMLParser xmlReader = new XMLParser("resources/example_mod.pws", programDefault);
//			presentation = xmlReader.getPresentation();
			System.out.println("Finished building XML Parser.");
	}

	public static void main(String[] args){	launch(args); }

	@Override
	public void start(Stage PrimaryStage) {

//		parserTest();


		System.out.println("--------------------");

		stop();

	}

	@Override
	public void stop() {
		Platform.exit();
	}


}
