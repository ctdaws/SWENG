import javafx.application.Application;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.application.Platform;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import javafx.scene.image.*;
import javafx.scene.text.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;

public class LectureQuest extends Application {

    public Slide currentSlide;
    public Pane pane;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("Lecture Quest Alpha");
        primaryStage.getIcons().add(new Image("file:../resources/4learning_icon_32.png"));

        pane = new Pane();
        
        Slide s1 = new Slide("1");
        s1.add(new FLText("Slide 1", 50, 50, 0, 10));
        s1.add(new FLImage("../resources/4learning_icon_32.png", new Position(0, 0), 0, 200, 200));
        
        Slide s2 = new Slide("2");
        s2.add(new FLText("Slide 2", 50, 50, 0, 10));
        s2.add(new FLImage("../resources/sampleImg.jpg", new Position(0, 0), 0, 200, 200));

        //pane.getChildren().add(s1.textList.get(0).getText());

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

        // Render the objects, need to base it on some sort of layer system??
        // Alternatively, could just render the objects in the order that they were read from the XML file
        // The problem with that approach is that different visual medias will be incorrectly layed over one another probably
        // Possible solution, have every visual media object be defined with a layer that it should reside on. Then
        // render all the objects layer by layer
        
        // NOTE (chris): Have an FLMedia super class which contains all the basic commonalities and the layer of each object, 
        // then create a list of FLMedia objects and sort the list based on the layer. Then run through the list rendering 
        // all the objects.
        ArrayList<FLMedia> mediaObjects = currentSlide.getSortedMediaList();
        for(FLMedia media : mediaObjects) {
            // Render them
            pane.getChildren().add((Node)media.getMedia());
        }


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

    public void setSlide(Slide nextSlide) {
        // if(currentSlide != null) {
        //     pane.getChildren().remove(currentSlide.mList.get(0).getText());
        //     pane.getChildren().remove(currentSlide.imageList.get(0).iView);
        // }

        // currentSlide = nextSlide;
        // pane.getChildren().add(currentSlide.textList.get(0).getText());
        // pane.getChildren().add(currentSlide.imageList.get(0).iView);

    }

      @Override
      public void stop() {
          Platform.exit();
      }
}
