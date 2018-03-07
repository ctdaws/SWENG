import java.io.File;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.*;

public class LectureQuest extends Application {

  public Colors programDefaultColor = new Colors("#000000", "#000000");
  public TextStyle programDefaultStyle = new TextStyle("Arial", 20, false, false, false);

  public Defaults programDefault = new Defaults(programDefaultStyle, programDefaultColor);

  public Slide currentSlide;
  public Pane pane;

  public static void main(String[] args) { launch(args); }

  @Override
  public void start(Stage primaryStage) {

    primaryStage.setTitle("Lecture Quest Alpha");
    primaryStage.getIcons().add(new Image(this.getClass().getResource("4learning_icon_32.png").toExternalForm()));

    pane = new Pane();

    Slide s1 = new Slide("1");
    s1.add(new FLImage("4learning_icon_32.png", new Position(0, 0), 200, 200));
    s1.add(new FLAudio("sampleAudio.wav", new Position(0, 0)));

// -----------------------------------------------------------------------------

    TextStyle defaultFontStyle = new TextStyle("Arial", 20, true, true, true);
    Defaults slideDefaults = new Defaults(defaultFontStyle, new Colors("#0000FF", "#00FF00"));

    TextStyle style1 = new TextStyle("Arial", 40, true, true, true);
    TextStyle style2 = new TextStyle("Tahoma", 30, false, false, false);

    FLText textFlow = new FLText(new Position(150, 50), 200, slideDefaults, new Transitions("trigger", 0, 0));
    textFlow.add("Text 1", new Colors("#FF0000"), style1);
    textFlow.add("Text 2", style2);
    textFlow.add("Text 3");

    s1.add(textFlow);

// -----------------------------------------------------------------------------

    Slide s2 = new Slide("2");
    s2.add(new FLImage("sampleImg.jpg", new Position(0, 0), 200, 200));
    s2.add(new FLAudio("sampleAudio.mp3", new Position(0, 0)));

    currentSlide = s1;

    Scene scene = new Scene(pane, 500, 400);

    scene.setOnKeyPressed((keyEvent) -> {
      switch(keyEvent.getCode()) {
        case ESCAPE:
          stop();
          break;
        case RIGHT:
          setSlide(s2);
          break;
        case LEFT:
          setSlide(s1);
          break;
      }
    });

    renderSlide();

    // Create a simple combo box to display the available slides
    // ObservableList<String> options = FXCollections.observableArrayList(s1.ID, s2.ID);

    // ComboBox comboBox = new ComboBox<String>(options);
    // comboBox.setLayoutX(100);
    // comboBox.setOnAction((event) -> {
    //     String selectedStiring = comboBox.getSelectionModel().getSelectedItem().toString();
    //     if(selectedStiring == "1") {
    //         setSlide(s1);
    //     }
    //     else if(selectedStiring == "2") {
    //         setSlide(s2);
    //     }
    //     System.out.println(selectedStiring);
    // });

    //pane.getChildren().add(comboBox);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public void renderSlide() {
    ArrayList<FLMedia> mediaObjects = currentSlide.getMediaList();
    for(FLMedia media : mediaObjects) {
      // Render them
      if(media.isRendered()) {
        pane.getChildren().add((Node)media.getMedia());
      }
    }
  }

  public void unrenderSlide() {
    ArrayList<FLMedia> mediaObjects = currentSlide.getMediaList();
    for(FLMedia media : mediaObjects) {
      // Render them
      if(media.isRendered()) {
        pane.getChildren().remove(media.getMedia());
      }
    }
  }

  public void setSlide(Slide nextSlide) {
    if(currentSlide != null) {
      unrenderSlide();
    }

    currentSlide = nextSlide;

    renderSlide();
  }

    @Override
    public void stop() {
        Platform.exit();
    }
}
