import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

// import javafx.scene.control.Menu;
// import javafx.scene.control.MenuBar;
// import javafx.scene.control.MenuItem;
import javafx.scene.control.*;  //TODO makes 3 above redundant?
// import javafx.scene.layout.VBox;
// import javafx.scene.layout.StackPane;
// import javafx.scene.layout.Pane;
import javafx.scene.layout.*;   //TODO makes above 3 redundant?
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.geometry.*;

import java.io.File;
import java.net.URL;


public class LectureQuest extends Application {

  private Colors programDefaultColor = new Colors("#000000", "#000000");
  private TextStyle programDefaultStyle = new TextStyle("Arial", 20, false, false, false);

  private Defaults programDefault = new Defaults(programDefaultStyle, programDefaultColor);

  private Presentation presentation;
  BorderPane borderLayout = new BorderPane();

  int levelNum, qNum, i, j;
  Label presentationLabel;

  Button nextBtn = new Button("Next");
  Button QuestionBtn = new Button("Question");
  Button ExampleBtn = new Button("Example");
  Button SolutionBtn = new Button("Solution");
  Button prevBtn = new Button("Previous");

  //ArrayList<MenuItem> questions = new ArrayList<MenuItem>();

  public static void main(String[] args) { launch(args); }

  @Override
  public void start(Stage primaryStage) {


    primaryStage.setTitle("Lecture Quest Alpha");
    //primaryStage.getIcons().add(new Image(this.getClass().getResource("../resources/LQ_logo_2_32.png").toExternalForm()));
    //TODO Make sure this does what it's supposed to
    primaryStage.getIcons().add(new Image("file:../resources/LQ_logo_2_32.png"));



    File questXml = openFile(primaryStage);

    if(questXml == null) {
      System.out.println("null or invalid file chosen. Exiting.");
      stop();
    }
    else {
      XMLParserNew xmlReader = new XMLParserNew(questXml, programDefault);
      presentation = xmlReader.getPresentation();

      //TODO Make sure this does what it's supposed to
      //Font.loadFont(this.getClass().getResource("../resources/fonts/BebasNeue-Regular.ttf").toExternalForm(), 20);
      Font.loadFont(this.getClass().getResourceAsStream("../resources/fonts/BebasNeue-Regular.ttf"), 20);
    //  File font = new File("../resources/BebasNeue-Regular.ttf");
      //scene.getStylesheets().clear();
      //Font.loadFont(this.getClass().getResource("file:///" + font.getAbsolutePath().replace("\\", "/")).toExternalForm(),20);

      //TODO Refactor into a new method
      //create Menu Pane
    {
      MenuBar menuBar = new MenuBar();
      menuBar.setBackground(new Background(new BackgroundFill(Color.web("#FF0000"), CornerRadii.EMPTY, Insets.EMPTY)));

      Menu levels = new Menu("Level sel.");
      ArrayList<Menu> levelItems = new ArrayList<Menu>(); //Levels array
      ArrayList<ArrayList<MenuItem>> levelQuestions = new ArrayList<ArrayList<MenuItem>>(); //Array of the questions array for each level

      //this.presentationLabel = new Label("Level: " + Integer.toString(levelNum) + " Question: " + Integer.toString(qNum));

      //TODO Sort this out fot getting number of levels and questions
      //for(i=0; i<5; i++) {
      for(i=0; i<presentation.lArray.size(); i++) {
        levelItems.add(new Menu("Level " + (i+1)));
        ArrayList<MenuItem> questions = new ArrayList<MenuItem>();  //Array of questions in current level.
        //for(j=0; j<5; j++) {
        for(j=0; j<presentation.lArray.get(i).qArray.size(); j++) {//TODO j not j-1
          //lArray.get(i-1).qArray.get(j).slideArray.size()
          if(j==0){
            questions.add(new MenuItem(" Example"));//TODO j not j+1
            questions.get(j).setId(i+"/"+j);
            System.out.println(questions.get(j).getId());
            levelItems.get(i).getItems().add(questions.get(j));
            questions.get(j).setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                Object o = event.getSource();
                for (int k=0; k<questions.size(); k++) {
                  if(o == questions.get(k)) {

                    String menuID = questions.get(k).getId();
                    String menuIDArray[] = menuID.split("/");
                    int levelNum = Integer.parseInt(menuIDArray[0]);
                    int questionNum = Integer.parseInt(menuIDArray[1]);
                    setSlide(levelNum, questionNum);
                    //
                  }
                }
              }
            });
          }//TODO Example Slide
          //createMenuButton(i,j);
          else{
            questions.add(new MenuItem("Question: " + (j)));//TODO j not j+1
            questions.get(j).setId(i+"/"+j);
            System.out.println(questions.get(j).getId());
            levelItems.get(i).getItems().add(questions.get(j));
            questions.get(j).setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                Object o = event.getSource();
                for (int k=0; k<questions.size(); k++) {
                  if(o == questions.get(k)) {

                    String menuID = questions.get(k).getId();
                    String menuIDArray[] = menuID.split("/");
                    int levelNum = Integer.parseInt(menuIDArray[0]);
                    int questionNum = Integer.parseInt(menuIDArray[1]);
                    setSlide(levelNum, (questionNum));
                    //
                  }
                }
              }
            });
          }
        }
        levels.getItems().add(levelItems.get(i));
        levelQuestions.add(questions);
      }
      menuBar.getMenus().add(levels);
      //VBox vBox = new VBox(menuBar);

      VBox menuBarBox = new VBox(menuBar);

      //BorderPane borderLayout = new BorderPane();
      this.borderLayout.setTop(menuBarBox);
      borderLayout.setCenter(this.presentation.pane);
    }


      //TODO Refactor into a new method
      //create Bottom Pane
     {
        // Button nextBtn = new Button("Next");
        // Button QuestionBtn = new Button("Question");
        // Button ExampleBtn = new Button("Example");
        // Button SolutionBtn = new Button("Solution");
        // Button prevBtn = new Button("Previous");
        prevBtn.setDisable(true);

        nextBtn.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            presentation.moveNextSlide();
            prevBtn.setDisable(false);
            checkButtonStatus();
          }
        });
        QuestionBtn.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            presentation.moveSlide(presentation.GetQuestionID());//TODO
            prevBtn.setDisable(false);
            checkButtonStatus();
          }
        });
        ExampleBtn.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            presentation.moveSlide(presentation.GetExampleID());//TODO
            prevBtn.setDisable(false);
            checkButtonStatus();
          }
        });
        SolutionBtn.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            presentation.moveSlide(presentation.GetSolutionID());  //TODO
            prevBtn.setDisable(false);
            checkButtonStatus();
          }
        });

        prevBtn.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            presentation.moveBackSlide();
            if(0 == 1) {//TODO if MENU
              prevBtn.setDisable(true);
            }
            checkButtonStatus();
          }
        });

        //checkButtonStatus();

        HBox menu = new HBox();
        menu.setSpacing(10);
        menu.setMargin(prevBtn, new Insets(10, 10, 10, 10));
        menu.setMargin(QuestionBtn, new Insets(10, 10, 10, 10));
        menu.setMargin(ExampleBtn, new Insets(10, 10, 10, 10));
        menu.setMargin(SolutionBtn, new Insets(10, 10, 10, 10));
        menu.setMargin(nextBtn, new Insets(10, 10, 10, 10));

        this.borderLayout.setBottom(menu);
        //menu.setBackground(new Background(new BackgroundFill(Color.web("#FF0000"), CornerRadii.EMPTY, Insets.EMPTY)));
        menu.getChildren().add(prevBtn);
        menu.getChildren().add(QuestionBtn);
        menu.getChildren().add(ExampleBtn);
        menu.getChildren().add(SolutionBtn);
        menu.getChildren().add(nextBtn);
     }




      //Scene scene = new Scene(this.presentation.pane, presentation.getWidth(), presentation.getHeight());
      Scene scene = new Scene(this.borderLayout, presentation.getWidth(), presentation.getHeight());


      //scene.getStylesheets().add(getClass().getResource("../resources/presentationStyle.css").toExternalForm());
      //scene.getStylesheets().add("https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css");
      //TODO Make sure this does what it's supposed to
      File f = new File("../resources/presentationStyle.css");
      scene.getStylesheets().clear();
      scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

      scene.setOnKeyPressed((keyEvent) -> {
        switch(keyEvent.getCode()) {
          case ESCAPE:
            stop();
            break;
          case RIGHT:
            this.presentation.moveNextSlide();
            break;
          case DOWN:
            this.presentation.moveNextSlide();
            break;
          case LEFT:
            this.presentation.moveBackSlide();
            break;
          case UP:
            this.presentation.moveBackSlide();
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

  public ImageView resizedImageView(String imageLocation, int sizeX, int sizeY) {
    ImageView resizedImageView = new ImageView(new Image(imageLocation));
    resizedImageView.setFitWidth(sizeX);
    resizedImageView.setFitHeight(sizeY);
    return resizedImageView;
  }

  public void setSlide(int newLevel, int newQuestion) {
    this.levelNum = newLevel + 1;
    this.qNum = (newQuestion);
    System.out.println("Level: " + Integer.toString(levelNum) + " Question: " + Integer.toString(qNum));
    this.presentation.moveSlide(CombineMenuID(levelNum, qNum));
  }

  public String CombineMenuID(int newLevel, int newQuestion){
    String newMenuID = (newLevel + "/" + newQuestion + "/" + 1);
    return newMenuID;
  }

  public void checkButtonStatus() {
    switch(this.presentation.getSlideByID(presentation.getCurrentID()).getType()){
    //switch(this.presentation.getSlideByID(slideID){
      case "M":
        setButtonStatus(true, true, true);
        break;
      case "Q":
        setButtonStatus(true, false, false);
        break;
      case "X":
        setButtonStatus(false, true, true);
        break;
      case "A":
        setButtonStatus(false, false, false);
        break;
      case "S":
        setButtonStatus(true, true, true);
        break;
      case "F":
        setButtonStatus(true, true, true);
        break;
      case "E":
        setButtonStatus(true, true, true);
        break;
      default:
        setButtonStatus(true, true, true);
      break;

    }
  }

  public void setButtonStatus(boolean Q, boolean X, boolean S) {
    this.QuestionBtn.setDisable(Q);
    this.ExampleBtn.setDisable(X);
    this.SolutionBtn.setDisable(S);
  }

  // public void createMenuButton(int i, int j){
  //   this.questions.add(new MenuItem("Question: " + (j+1)));//TODO j not j+1
  //   this.questions.get(j).setId(i+"/"+j);
  //   this.levelItems.get(i).getItems().add(questions.get(j));
  //   this.questions.get(j).setOnAction(new EventHandler<ActionEvent>() {
  //     @Override
  //     public void handle(ActionEvent event) {
  //       Object o = event.getSource();
  //       for (int k=0; k<this.questions.size(); k++) {
  //         if(o == questions.get(k)) {
  //
  //           String menuID = this.questions.get(k).getId();
  //           String menuIDArray[] = menuID.split("/");
  //           int levelNum = Integer.parseInt(menuIDArray[0]);
  //           int questionNum = Integer.parseInt(menuIDArray[1]);
  //           setSlide(levelNum, questionNum);
  //           //
  //         }
  //       }
  //     }
  //   });
  // }

  @Override
  public void stop() { Platform.exit(); }
}
