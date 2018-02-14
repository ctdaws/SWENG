import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Presentor extends Application {
    
    public static void main(String[] args) {        

        Image testImage = new Image("../resources/sampleImg.jpg", new Position(0.0f, 0.0f), 0);

        Text testText = new Text("Sample", new Position(0.0f, 0.0f), 0);

        Audio testAudio = new Audio("../resources/sampleAudio.wav");

        launch(args);
        
    }

    @Override
    public void start(Stage primaryStage) {
       
        primaryStage.setTitle("Hello World!");
        
        StackPane slide1 = new StackPane();
        StackPane slide2 = new StackPane();

        Scene scene1 = new Scene(slide1, 300, 250);
        Scene scene2 = new Scene(slide2, 300, 250);

        Button btn1 = new Button();
        btn1.setText("Go to Slide 2");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Going to Slide 2");
                primaryStage.setScene(scene2);
            }
        });

        Button btn2 = new Button();
        btn2.setText("Go to Slide 1");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Moving to Slide 1");
                primaryStage.setScene(scene1);
            }
        });
        
        
        
        slide1.getChildren().add(btn1);
        slide2.getChildren().add(btn2);

       

        primaryStage.setScene(scene1);
        
        
        scene1.getStylesheets().add("ButtonTest.css");
        scene2.getStylesheets().add("ButtonTest.css");
        
        primaryStage.show();
    }

    
}