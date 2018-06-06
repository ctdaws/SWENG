import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.*;


public class GUITest extends Application {
int slideNum;

  @Override
  public void start(Stage primaryStage) {
    slideNum = 1;
    Label presentation = new Label("Slide: " + Integer.toString(slideNum));
    System.out.println("Slide: " + Integer.toString(slideNum));

    Button nextBtn = new Button("Next");
    Button prevBtn = new Button("Previous");
    prevBtn.setDisable(true);

    nextBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        nextSlideNum();
        presentation.setText("Slide: " + Integer.toString(slideNum));
        System.out.println("Slide: " + Integer.toString(slideNum));
        prevBtn.setDisable(false);
      }
    });
    prevBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        prevSlideNum();
        presentation.setText("Slide: " + Integer.toString(slideNum));
        System.out.println("Slide: " + Integer.toString(slideNum));
        if(slideNum == 1) {
          prevBtn.setDisable(true);
        }
      }
    });

    StackPane root = new StackPane();
    //Pane presentation = new Pane();
    //Pane menu = new Pane();
    HBox menu = new HBox();
    menu.setSpacing(10);
    menu.setMargin(prevBtn, new Insets(20, 20, 20, 20));
    menu.setMargin(nextBtn, new Insets(20, 20, 20, 20));
    BorderPane borderLayout = new BorderPane();

    //LevelMenu levelMenu = new LevelMenu();

  //  borderLayout.setTop(levelMenu.showMenu());
    borderLayout.setCenter(presentation);
    borderLayout.setBottom(menu);

    //BorderPane borderLayout = new BorderPane();

    // borderLayout.setCenter(presentation);
    // borderLayout.setBottom(menu);

    root.getChildren().add(borderLayout);

    menu.setBackground(new Background(new BackgroundFill(Color.web("#FF0000"), CornerRadii.EMPTY, Insets.EMPTY)));
    presentation.setBackground(new Background(new BackgroundFill(Color.web("#FFFF00"), CornerRadii.EMPTY, Insets.EMPTY)));

    menu.getChildren().add(prevBtn);
    menu.getChildren().add(nextBtn);

    Scene scene = new Scene(root, 500, 500);

    primaryStage.setTitle("GUI Testing");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  public static void main(String[] args) {
    launch(args);
  }

  public void nextSlideNum() {
    slideNum ++;
  }

  public void prevSlideNum() {
    slideNum --;
  }
}
