import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class LectureQuest extends Application {

  private Colors programDefaultColor = new Colors("#000000", "#000000");
  private TextStyle programDefaultStyle = new TextStyle("Arial", 20, false, false, false);

  private Defaults programDefault = new Defaults(programDefaultStyle, programDefaultColor);

  private Presentation presentation;

  public static void main(String[] args) { launch(args); }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Lecture Quest Alpha");
    primaryStage.getIcons().add(new Image(this.getClass().getResource("LQ_logo_2_32.png").toExternalForm()));

    XMLParser xmlReader = new XMLParser("resources/FLExample.pws", programDefault);
    presentation = xmlReader.getPresentation();

    Scene scene = new Scene(this.presentation.pane, presentation.getWidth(), presentation.getHeight());
    scene.setOnKeyPressed((keyEvent) -> {
      switch(keyEvent.getCode()) {
        case ESCAPE:
          stop();
          break;
        case RIGHT:
          this.presentation.moveNextSlide();
          break;
      }
    });

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  @Override
  public void stop() { Platform.exit(); }
}
