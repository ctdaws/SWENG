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

  private Colors programDefaultColor = new Colors("#000000", "#000000");
  private TextStyle programDefaultStyle = new TextStyle("Arial", 20, false, false, false);

  private Defaults programDefault = new Defaults(programDefaultStyle, programDefaultColor);

  private Presentation presentation;
  private Slide currentSlide;
  private Pane pane;

  private int slideCounter = 0;

  public static void main(String[] args) { launch(args); }

  @Override
  public void start(Stage primaryStage) {

    primaryStage.setTitle("Lecture Quest Alpha");
    primaryStage.getIcons().add(new Image(this.getClass().getResource("4learning_icon_32.png").toExternalForm()));

    pane = new Pane();

    XMLParser xmlReader = new XMLParser("resources/FLExample.pws", programDefault);
    presentation = xmlReader.getPresentation();

    currentSlide = presentation.getSlideByID("Q");
    this.slideCounter = 1;

    Scene scene = new Scene(pane, presentation.getWidth(), presentation.getHeight());

    scene.setOnKeyPressed((keyEvent) -> {
      switch(keyEvent.getCode()) {
        case ESCAPE:
          stop();
          break;
        case RIGHT:
          setSlide(this.presentation.getNextSlide());
          break;
        case LEFT:
          //setSlide(presentation.getSlideByID(Integer.toString(--slideCounter)));
          break;
      }
    });

    renderSlide();

    Image_FL image_FL = new Image_FL("sampleImg.jpg", 25, 25, 75, 75);
    image_FL.toggleGreyscale();
    image_FL.scale(0.5);
    image_FL.moveTo(100, 200);
    pane.getChildren().add(image_FL.getImageView());

    System.out.println(image_FL);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void renderSlide() {
    ArrayList<FLMedia> mediaObjects = currentSlide.getMediaList();
    for(FLMedia media : mediaObjects) {
      // Render them
      if(media.isRendered()) {
        pane.getChildren().add((Node)media.getMedia());
      }
    }
  }

  private void unrenderSlide() {
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
  public void stop() { Platform.exit(); }
}
