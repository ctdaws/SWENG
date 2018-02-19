import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Platform;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

import javafx.scene.image.*;

import javafx.scene.text.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class Presentor extends Application {
    
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("Lecture Quest Alpha");
        primaryStage.getIcons().add(new Image("file:../resources/4learning_icon_32.png"));
        
        StackPane slide = new StackPane();

        Scene scene1 = new Scene(slide, 300, 200);

        scene1.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                switch(ke.getCode()) {
                    case ESCAPE:
                        System.out.println("Ecs pressed");
                        stop();
                    break;
                    case RIGHT:
                        System.out.println("Right pressed");                        
                    break; 
                    case LEFT:
                        System.out.println("Left pressed");                        
                    break;
                }
            }
        });

        // Display text
        Text t = new Text("Test");
        t.setFont(new Font(20));

        // Display an image
        Image image = new Image("file:../resources/sampleImg.jpg");
        ImageView imageView = new ImageView(image);
        // Play some audio
        // This approach also doesnt work with mp3
        Media sound = new Media(new File("../resources/sampleAudio.wav").toURI().toString());
        MediaPlayer player = new MediaPlayer(sound);
        player.play();

        Button btn1 = new Button();
        btn1.setText("This is Slide 1");
        
        slide.getChildren().add(btn1);
        slide.getChildren().add(t);
        slide.getChildren().add(imageView);

        primaryStage.setScene(scene1);
        scene1.getStylesheets().add("ButtonTest.css");        
        primaryStage.show();
    }

    @Override
    public void stop() {
        System.out.println("Stopping");
        Platform.exit();
    }
}