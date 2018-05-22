import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class LectureQuest extends Application {

  private Colors programDefaultColor = new Colors("#000000", "#000000");
  private TextStyle programDefaultStyle = new TextStyle("Arial", 20, false, false, false);

  private Defaults programDefault = new Defaults(programDefaultStyle, programDefaultColor);

  private Presentation presentation;

  public static void main(String[] args) { launch(args); }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Lecture Quest Alpha");
    //primaryStage.getIcons().add(new Image(this.getClass().getResource("../resources/LQ_logo_2_32.png").toExternalForm()));

    File questXml = openFile(primaryStage);

    if(questXml == null) {
      System.out.println("null or invalid file chosen. Exiting.");
      stop();
    }
    else {
      XMLParserNew xmlReader = new XMLParserNew(questXml, programDefault);
      presentation = xmlReader.getPresentation();

      //Font.loadFont(this.getClass().getResource("../resources/fonts/BebasNeue-Regular.ttf").toExternalForm(), 20);

      Scene scene = new Scene(this.presentation.pane, presentation.getWidth(), presentation.getHeight());

      //scene.getStylesheets().add(getClass().getResource("../resources/presentationStyle.css").toExternalForm());
//    scene.getStylesheets().add("https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css");

      scene.setOnKeyPressed((keyEvent) -> {
        switch(keyEvent.getCode()) {
          case ESCAPE:
            stop();
            break;
          case RIGHT:
            this.presentation.moveNextSlide();
            break;
          case LEFT:
            // TODO: go to previous slide
            break;
        }
      });

      primaryStage.setScene(scene);
      primaryStage.show();
    }
  }

  private File openFile (Stage stage) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open Image");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Quest", "*.4l"),
            new FileChooser.ExtensionFilter("PWS", "*.pws"),
            new FileChooser.ExtensionFilter("All", "*.*")
    );
    return fileChooser.showOpenDialog(stage);
  }

  @Override
  public void stop() { Platform.exit(); }
}
