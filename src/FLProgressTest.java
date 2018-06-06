import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.File;

public class FLProgressTest extends Application {
  int levelNum = 1;
  int totalLevels = 5;
  FLProgress progress = new FLProgress(500, levelNum, totalLevels);
  Button nextBtn = new Button("Next");
  Button prevBtn = new Button("Previous");
  File styleSheet = new File("../resources/style.css");

  @Override
  public void start(Stage stage) {
    Group group = new Group();
    Scene scene = new Scene(group, 1000, 500);
    scene.getStylesheets().clear();
    scene.getStylesheets().add("file:///" + styleSheet.getAbsolutePath().replace("\\","/"));

    this.nextBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        nextLevel();
        progress.setLevelProgress(levelNum);
      }
    });

    this.prevBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        prevLevel();
        progress.setLevelProgress(levelNum);
      }
    });

    HBox hb = new HBox();
    hb.setSpacing(5);
    hb.setAlignment(Pos.CENTER);
    hb.getChildren().addAll(nextBtn, prevBtn, progress.getStackPane()); //NOTE the getStackPane() method!
    scene.setRoot(hb);

    stage.setScene(scene);
    stage.setTitle("Progress Bar Object Test");
    stage.show();
  }

  public void nextLevel() {
    if(this.levelNum < this.totalLevels) {
      this.levelNum++;
    }
  }

  public void prevLevel() {
    if(this.levelNum > 1) {
      this.levelNum--;
    }
  }

}
