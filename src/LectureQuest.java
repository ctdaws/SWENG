import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.*;  //TODO makes 3 above redundant?
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.*;   //TODO makes above 3 redundant?
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.geometry.*;

import java.io.File;


public class LectureQuest extends Application {

  private Colors programDefaultColor = new Colors("#000000", "#000000");
  private TextStyle programDefaultStyle = new TextStyle("Arial", 20, false, false, false);

  private Defaults programDefault = new Defaults(programDefaultStyle, programDefaultColor);

  private Presentation presentation;
  BorderPane borderLayout = new BorderPane();

  int levelNum, qNum, i, j;
  Label presentationLabel;

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



































      //createAndShowGUI(primaryStage, presentation);
      //Label presentationLabel = new Label("Slide: ");// + Integer.toString(slideNum));

    //createContentPane(primaryStage); 
    //createBottomPane(primaryStage, presentation);

      //TODO Refactor into a new method
      //create Menu Pane
    {
      MenuBar menuBar = new MenuBar();
      Menu levels = new Menu("Levels");
      ArrayList<Menu> levelItems = new ArrayList<Menu>(); //Levels array
      ArrayList<ArrayList<MenuItem>> levelQuestions = new ArrayList<ArrayList<MenuItem>>(); //Array of the questions array for each level

      //this.presentationLabel = new Label("Level: " + Integer.toString(levelNum) + " Question: " + Integer.toString(qNum));

      //TODO Sort this out fot getting number of levels and questions
      for(i=0; i<5; i++) {
        levelItems.add(new Menu("Level " + (i+1)));
        ArrayList<MenuItem> questions = new ArrayList<MenuItem>();  //Array of questions in current level.
        for(j=0; j<5; j++) {
          questions.add(new MenuItem("Question: " + (j+1)));
          questions.get(j).setId(i+"/"+j);
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
        }
        levels.getItems().add(levelItems.get(i));
        levelQuestions.add(questions);
      }
      menuBar.getMenus().add(levels);
      //VBox vBox = new VBox(menuBar);

      VBox vBox = new VBox(menuBar);
      //BorderPane borderLayout = new BorderPane();
      this.borderLayout.setTop(vBox);
      borderLayout.setCenter(this.presentation.pane);
    }


      //TODO Refactor into a new method
      //create Bottom Pane
     {

        Button nextBtn = new Button("Next");
        Button prevBtn = new Button("Previous");
        prevBtn.setDisable(true);

        nextBtn.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            presentation.moveNextSlide();
            prevBtn.setDisable(false);
          }
        });

        prevBtn.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            presentation.moveBackSlide();
            if(0 == 1) {//TODO if MENU
              prevBtn.setDisable(true);
            }
          }
        });

        //StackPane root = new StackPane();                                           //THIS ONE
       
        HBox menu = new HBox();
        menu.setSpacing(10);
        menu.setMargin(prevBtn, new Insets(20, 20, 20, 20));
        menu.setMargin(nextBtn, new Insets(20, 20, 20, 20));

        

        //BorderPane borderLayout = new BorderPane();

        //borderLayout.setTop(levelMenu.showMenu());
        //borderLayout.setTop(vBox);
        //Pane pane = new Pane();
        //pane.getChildren().add(presentation);
        //this.borderLayout.setCenter(presentation.pane); //TODO fix
        this.borderLayout.setBottom(menu);

        //root.getChildren().add(borderLayout);
        //root.getChildren().add(borderLayout);                                           //THIS ONE

        menu.setBackground(new Background(new BackgroundFill(Color.web("#FF0000"), CornerRadii.EMPTY, Insets.EMPTY)));
        //presentationLabel.setBackground(new Background(new BackgroundFill(Color.web("#FFFF00"), CornerRadii.EMPTY, Insets.EMPTY)));

        menu.getChildren().add(prevBtn);
        menu.getChildren().add(nextBtn);
     }




      //Scene scene = new Scene(this.presentation.pane, presentation.getWidth(), presentation.getHeight());
      Scene scene = new Scene(this.borderLayout, presentation.getWidth(), presentation.getHeight()); 
        
      
      //scene.getStylesheets().add(getClass().getResource("../resources/presentationStyle.css").toExternalForm());
//    scene.getStylesheets().add("https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css");
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
    this.qNum = (newQuestion + 1);
    //this.presentationLabel.setText("Level: " + Integer.toString(levelNum) + " Question: " + Integer.toString(qNum));
    System.out.println("Level: " + Integer.toString(levelNum) + " Question: " + Integer.toString(qNum));
    this.presentation.moveSlide(CombineMenuID(levelNum, qNum));
  }

  public String CombineMenuID(int newLevel, int newQuestion){
    String newMenuID = (newLevel + "/" + newQuestion + "/" + 1);
    return newMenuID;
  }

  // public void createContentPane(Stage primaryStage) {
  //   {
  //     MenuBar menuBar = new MenuBar();
  //     Menu levels = new Menu("Levels");
  //     ArrayList<Menu> levelItems = new ArrayList<Menu>(); //Levels array
  //     ArrayList<ArrayList<MenuItem>> levelQuestions = new ArrayList<ArrayList<MenuItem>>(); //Array of the questions array for each level

  //     this.presentationLabel = new Label("Level: " + Integer.toString(levelNum) + " Question: " + Integer.toString(qNum));

  //     //TODO Sort this out fot getting number of levels and questions
  //     for(i=0; i<5; i++) {
  //       levelItems.add(new Menu("Level " + (i+1)));
  //       ArrayList<MenuItem> questions = new ArrayList<MenuItem>();  //Array of questions in current level.
  //       for(j=0; j<5; j++) {
  //         questions.add(new MenuItem("Question: " + (j+1)));
  //         questions.get(j).setId(i+"/"+j);
  //         levelItems.get(i).getItems().add(questions.get(j));
  //         questions.get(j).setOnAction(new EventHandler<ActionEvent>() {
  //           @Override
  //           public void handle(ActionEvent event) {
  //             Object o = event.getSource();
  //             for (int k=0; k<questions.size(); k++) {
  //               if(o == questions.get(k)) {

  //                 String menuID = questions.get(k).getId();
  //                 String menuIDArray[] = menuID.split("/");
  //                 int levelNum = Integer.parseInt(menuIDArray[0]);
  //                 int questionNum = Integer.parseInt(menuIDArray[1]);
  //                 setSlide(levelNum, questionNum);
  //                 //
  //               }
  //             }
  //           }
  //         });
  //       }
  //       levels.getItems().add(levelItems.get(i));
  //       levelQuestions.add(questions);
  //     }
  //     menuBar.getMenus().add(levels);

  //     VBox vBox = new VBox(menuBar);
  //     BorderPane borderLayout = new BorderPane();
  //     borderLayout.setTop(vBox);
  //     borderLayout.setCenter(presentationLabel);
  //   }
  // }

  
  // public void createAndShowGUI(Stage primaryStage, Presentation presentation) { 
  //    // Label presentationLabel = new Label("Slide: ");// + Integer.toString(slideNum));

  //   //createContentPane(primaryStage); 
  //   //createBottomPane(primaryStage, presentation);

  //     //TODO Refactor into a new method
  //     //create Menu Pane
  //   {
  //     MenuBar menuBar = new MenuBar();
  //     Menu levels = new Menu("Levels");
  //     ArrayList<Menu> levelItems = new ArrayList<Menu>(); //Levels array
  //     ArrayList<ArrayList<MenuItem>> levelQuestions = new ArrayList<ArrayList<MenuItem>>(); //Array of the questions array for each level

  //     //this.presentationLabel = new Label("Level: " + Integer.toString(levelNum) + " Question: " + Integer.toString(qNum));

  //     //TODO Sort this out fot getting number of levels and questions
  //     for(i=0; i<5; i++) {
  //       levelItems.add(new Menu("Level " + (i+1)));
  //       ArrayList<MenuItem> questions = new ArrayList<MenuItem>();  //Array of questions in current level.
  //       for(j=0; j<5; j++) {
  //         questions.add(new MenuItem("Question: " + (j+1)));
  //         questions.get(j).setId(i+"/"+j);
  //         levelItems.get(i).getItems().add(questions.get(j));
  //         questions.get(j).setOnAction(new EventHandler<ActionEvent>() {
  //           @Override
  //           public void handle(ActionEvent event) {
  //             Object o = event.getSource();
  //             for (int k=0; k<questions.size(); k++) {
  //               if(o == questions.get(k)) {

  //                 String menuID = questions.get(k).getId();
  //                 String menuIDArray[] = menuID.split("/");
  //                 int levelNum = Integer.parseInt(menuIDArray[0]);
  //                 int questionNum = Integer.parseInt(menuIDArray[1]);
  //                 setSlide(levelNum, questionNum);
  //                 //
  //               }
  //             }
  //           }
  //         });
  //       }
  //       levels.getItems().add(levelItems.get(i));
  //       levelQuestions.add(questions);
  //     }
  //     menuBar.getMenus().add(levels);

  //     // VBox vBox = new VBox(menuBar);
  //     // BorderPane borderLayout = new BorderPane();
  //     // borderLayout.setTop(vBox);
  //     // borderLayout.setCenter(presentationLabel);
  //   }


  //     //TODO Refactor into a new method
  //     //create Bottom Pane
  //   {

  //       Button nextBtn = new Button("Next");
  //       Button prevBtn = new Button("Previous");
  //       prevBtn.setDisable(true);

  //       nextBtn.setOnAction(new EventHandler<ActionEvent>() {
  //         @Override
  //         public void handle(ActionEvent event) {
  //           this.presentation.moveNextSlide();
  //           prevBtn.setDisable(false);
  //         }
  //       });

  //       prevBtn.setOnAction(new EventHandler<ActionEvent>() {
  //         @Override
  //         public void handle(ActionEvent event) {
  //           this.presentation.moveBackSlide();
  //           if(0 == 1) {//TODO if MENU
  //             prevBtn.setDisable(true);
  //           }
  //         }
  //       });

  //       StackPane root = new StackPane();
       
  //       HBox menu = new HBox();
  //       menu.setSpacing(10);
  //       menu.setMargin(prevBtn, new Insets(20, 20, 20, 20));
  //       menu.setMargin(nextBtn, new Insets(20, 20, 20, 20));

  //       VBox vBox = new VBox(menuBar);
      

  //       BorderPane borderLayout = new BorderPane();

  //       //borderLayout.setTop(levelMenu.showMenu());
  //       borderLayout.setTop(vBox);
  //       borderLayout.setCenter(presentation);
  //       borderLayout.setBottom(menu);

  //       root.getChildren().add(borderLayout);

  //       menu.setBackground(new Background(new BackgroundFill(Color.web("#FF0000"), CornerRadii.EMPTY, Insets.EMPTY)));
  //       //presentationLabel.setBackground(new Background(new BackgroundFill(Color.web("#FFFF00"), CornerRadii.EMPTY, Insets.EMPTY)));

  //       menu.getChildren().add(prevBtn);
  //       menu.getChildren().add(nextBtn);
  //   }

  // }

  // public void createBottomPane(Stage primaryStage, Presentation presentation) {

  //   Label presentationLabel = new Label("Slide: ");// + Integer.toString(slideNum));

  //   Button nextBtn = new Button("Next");
  //   Button prevBtn = new Button("Previous");
  //   prevBtn.setDisable(true);

  //   nextBtn.setOnAction(new EventHandler<ActionEvent>() {
  //     @Override
  //     public void handle(ActionEvent event) {
  //       this.presentation.moveNextSlide();
  //       prevBtn.setDisable(false);
  //     }
  //   });

  //   prevBtn.setOnAction(new EventHandler<ActionEvent>() {
  //     @Override
  //     public void handle(ActionEvent event) {
  //       this.presentation.moveBackSlide();
  //       if(0 == 1) {//TODO if MENU
  //         prevBtn.setDisable(true);
  //       }
  //     }
  //   });

  //   StackPane root = new StackPane();
   
  //   HBox menu = new HBox();
  //   menu.setSpacing(10);
  //   menu.setMargin(prevBtn, new Insets(20, 20, 20, 20));
  //   menu.setMargin(nextBtn, new Insets(20, 20, 20, 20));

  //   BorderPane borderLayout = new BorderPane();

  //   borderLayout.setTop(levelMenu.showMenu());
  //   borderLayout.setCenter(presentation);
  //   borderLayout.setBottom(menu);

  //   root.getChildren().add(borderLayout);

  //   menu.setBackground(new Background(new BackgroundFill(Color.web("#FF0000"), CornerRadii.EMPTY, Insets.EMPTY)));
  //   presentationLabel.setBackground(new Background(new BackgroundFill(Color.web("#FFFF00"), CornerRadii.EMPTY, Insets.EMPTY)));

  //   menu.getChildren().add(prevBtn);
  //   menu.getChildren().add(nextBtn);
  // }

  @Override
  public void stop() { Platform.exit(); }
}
