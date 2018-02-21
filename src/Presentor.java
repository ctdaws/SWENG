import javafx.application.Application;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Platform;



import javafx.scene.image.*;

import javafx.scene.text.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class Presentor extends Application {
    
    private Slide currentSlide;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("Lecture Quest Alpha");
        primaryStage.getIcons().add(new Image("file:../resources/4learning_icon_32.png"));
        
        
        // NOTE (chris): this current approach isnt really working, redo it so the scene has the event handler on it and 
        // and keep thinking about how to deal with the slides


        //StackPane stackPane = new StackPane();
        Presentation pres = new Presentation();
        Scene scene = new Scene(pres, 300, 200);

        // // Create 2 new slides
        // Slide s1 = new Slide("1");
        // Slide s2 = new Slide("2");
        // s2.text.setVisible(false);

        // currentSlide = s1;

        // scene1.setOnKeyPressed(new EventHandler<KeyEvent>() {
        //     @Override
        //     public void handle(KeyEvent ke) {
        //         switch(ke.getCode()) {
        //             case ESCAPE:
        //                 System.out.println("Esc pressed");
        //                 stop();
        //             break;
        //             case RIGHT:
        //                 System.out.println("Right pressed");
        //                 // Display slide 2                    
        //                 s2.text.setVisible(true);
        //                 s1.text.setVisible(false);
        //             break; 
        //             case LEFT:
        //                 System.out.println("Left pressed");
        //                 // Display slide 1
        //                 s1.text.setVisible(true);
        //                 s2.text.setVisible(false);
        //             break;
        //         }
        //     }
        // });

        /*
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
        
        slide.getChildren().add(imageView);
        slide.getChildren().add(btn1);
        slide.getChildren().add(t);
        */

        // stackPane.getChildren().add(s1.text);
        // stackPane.getChildren().add(s2.text);

        primaryStage.setScene(scene);
        //scene1.getStylesheets().add("ButtonTest.css");        
        primaryStage.show();
        
        // Display the current slide
        // In this simple case that just means displaying the text
        
    }

    @Override
    public void stop() {
        System.out.println("Stopping");
        Platform.exit();
    }


}