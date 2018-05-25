import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
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

  private BorderPane borderLayout = new BorderPane();
  private FLProgress fLprogress;

  private int levelNum, qNum, i, j = 0;

  private Button nextBtn = new Button("Next");
  private Button QuestionBtn = new Button("Question");
  private Button ExampleBtn = new Button("Example");
  private Button SolutionBtn = new Button("Solution");
  private Button prevBtn = new Button("Previous");
  private Button muteBtn = new Button("Mute");
  private ProgressBar progress, questionsProgress;
  private Navigator navigator;

  public static void main(String[] args) { launch(args); }

  @Override
  public void start(Stage primaryStage) {


    primaryStage.setTitle("Lecture Quest Alpha");
    primaryStage.getIcons().add(new Image(this.getClass().getResource("LQ_logo_2_32.png").toExternalForm()));

    this.navigator = new Navigator();
    File questXml = openFile(primaryStage);

    if(questXml == null) {
      System.out.println("null or invalid file chosen. Exiting.");
      stop();
    }
    else {
      XMLParserNew xmlReader = new XMLParserNew(questXml, programDefault);
      presentation = xmlReader.getPresentation();
      this.navigator.setPresentation(this.presentation);
      this.navigator.renderSlide();

      Font.loadFont(this.getClass().getResource("fonts/BebasNeue-Regular.ttf").toExternalForm(), 20);

      createGUI();

      //Scene scene = new Scene(this.presentation.pane, presentation.getWidth(), presentation.getHeight());
      Scene scene = new Scene(this.borderLayout, presentation.getWidth(), presentation.getHeight());

      scene.getStylesheets().add(getClass().getResource("presentationStyle.css").toExternalForm());

      scene.setOnKeyPressed((keyEvent) -> {
            switch (keyEvent.getCode()) {
                case ESCAPE:
                    stop();
                    break;
                case RIGHT:
                    navigator.moveNextSlide();
                    setLevelProgress();
                    break;
                case DOWN:
                    navigator.moveNextSlide();
                    setLevelProgress();
                    break;
                case LEFT:
                    navigator.moveBackSlide();
                    setLevelProgress();
                    break;
                case UP:
                    navigator.moveBackSlide();
                    setLevelProgress();
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

  private ImageView resizedImageView(String imageLocation, int sizeX, int sizeY) {
    ImageView resizedImageView = new ImageView(new Image(this.getClass().getResource(imageLocation).toExternalForm()));
    resizedImageView.setFitWidth(sizeX);
    resizedImageView.setFitHeight(sizeY);
    return resizedImageView;
  }

  private void setSlide(int newLevel, int newQuestion) {
    this.levelNum = newLevel + 1;
    this.qNum = newQuestion;
    System.out.println("Level: " + Integer.toString(levelNum) + " Question: " + Integer.toString(qNum));

    this.navigator.moveSlide(CombineMenuID(levelNum, qNum));
  }

  private void setLevelProgress(){
    this.progress.setProgress((double)(this.navigator.getLevelNum()/presentation.lArray.size()));
    setQuestionsProgress();
    //fLprogress.setLevelProgress(navigator.getLevelNum());
  }

  private void setQuestionsProgress(){
      int numberOfQuestions = 0;
      for (i = 0; i < presentation.lArray.size(); i++) {
          //TODO questionProgress slider
          numberOfQuestions += presentation.lArray.get(i).qArray.size();
      }
      this.questionsProgress.setProgress((double) (this.navigator.getLevelNum() / numberOfQuestions));
  }

  private String CombineMenuID(int newLevel, int newQuestion){
    return (newLevel + "/" + newQuestion + "/" + 1);
  }

  private void checkButtonStatus() {
    switch(this.navigator.getPresentation().getSlideByID(this.navigator.getCurrentID()).getType()){
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

  private void setButtonStatus(boolean Q, boolean X, boolean S) {
    this.QuestionBtn.setDisable(Q);
    this.ExampleBtn.setDisable(X);
    this.SolutionBtn.setDisable(S);
  }

  private HBox getMenuHbox() {
        //TODO Refactor into a new method
        //create Bottom Pane

        prevBtn.setDisable(true);

        nextBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                navigator.moveNextSlide();
                prevBtn.setDisable(false);
                checkButtonStatus();
                setLevelProgress();
                // FLProgress.setLevelProgress();

            }
        });
        QuestionBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                navigator.moveSlide(navigator.GetQuestionID());//TODO
                prevBtn.setDisable(false);
                checkButtonStatus();
                setLevelProgress();

            }
        });
        ExampleBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                navigator.moveSlide(navigator.GetExampleID());//TODO
                prevBtn.setDisable(false);
                checkButtonStatus();
                setLevelProgress();

            }
        });
        SolutionBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                navigator.moveSlide(navigator.GetSolutionID());  //TODO
                prevBtn.setDisable(false);
                checkButtonStatus();
                setLevelProgress();

            }
        });

        prevBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                navigator.moveBackSlide();
                if(0 == 1) {//TODO if MENU
                    prevBtn.setDisable(true);
                }
                checkButtonStatus();
                //setLevelProgress();
            }
        });


        HBox menu = new HBox();
        menu.setSpacing(10);
        menu.setMargin(prevBtn, new Insets(10, 10, 10, 10));
        menu.setMargin(QuestionBtn, new Insets(10, 10, 10, 10));
        menu.setMargin(ExampleBtn, new Insets(10, 10, 10, 10));
        menu.setMargin(SolutionBtn, new Insets(10, 10, 10, 10));
        menu.setMargin(nextBtn, new Insets(10, 10, 10, 10));
        return menu;
    }

  private MenuBar getSettingsBar() {
        MenuBar settingsBar = new MenuBar();
        Menu settings = new Menu("");
        settings.setGraphic(resizedImageView("confused.png", 15, 15));

        MenuItem muteItem = new MenuItem("mute");
        if (/*TODO sound*/true) {
            muteItem.setGraphic(resizedImageView("mute_icon.png", 15, 15));
        }
        else if (/*TODO mute*/ false){
            muteItem.setGraphic(resizedImageView("sound_icon.png", 15, 15));
        }

         muteBtn.setOnAction(new EventHandler<ActionEvent>() {//TODO change button name
             @Override
                public void handle(ActionEvent event) {
                    if (/*TODO mute = false*/ true){
                        //mute = true
                        //TODO set sound to mute
                        muteItem.setGraphic(resizedImageView("sound_icon.png", 15, 15));
                    }
                    else {
                        //mute = false
                        //TODO set to unmute
                        muteItem.setGraphic(resizedImageView("mute_icon.png", 15, 15));
                    }

                 navigator.moveNextSlide();
                 prevBtn.setDisable(false);
                 checkButtonStatus();
                 setLevelProgress();
                 // FLProgress.setLevelProgress();

             }
         });


        Menu contrast = new Menu("Contrast");

        MenuItem highContrast = new MenuItem("High Contrast");
        MenuItem mediumContrast = new MenuItem("Medium Contrast");
        MenuItem lowContrast = new MenuItem("Low Contrast");

        contrast.getItems().addAll(lowContrast, mediumContrast, highContrast);

        settings.getItems().addAll(muteItem, contrast);

        settingsBar.getMenus().add(settings);
        return settingsBar;
  }

  private MenuBar getMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.setBackground(new Background(new BackgroundFill(Color.web("#FF0000"), CornerRadii.EMPTY, Insets.EMPTY)));

        Menu levels = new Menu("Level sel.");
        ArrayList<Menu> levelItems = new ArrayList<Menu>(); //Levels array
        ArrayList<ArrayList<MenuItem>> levelQuestions = new ArrayList<ArrayList<MenuItem>>(); //Array of the questions array for each level


        for (i = 0; i < presentation.lArray.size(); i++) {
            levelItems.add(new Menu("Level " + (i + 1)));

            if (/*levelComplete*/false == true) {
                levelItems.get(i).setGraphic(resizedImageView("correct.png", 15, 15));
            }

            ArrayList<MenuItem> questions = new ArrayList<MenuItem>();  //Array of questions in current level.

            for (j = 0; j < presentation.lArray.get(i).qArray.size(); j++) {
                //lArray.get(i-1).qArray.get(j).slideArray.size()
                if (j == 0) {
                    questions.add(new MenuItem(" Example"));
                } else {
                    questions.add(new MenuItem("Question: " + j));
                }

                if (false){//questionComplete  == true && questionCorrect == true) {
                    levelItems.get(i).setGraphic(resizedImageView("correct.png", 15, 15));
                }
                else if (false){//questionComplete  == true && questionCorrect == false) {
                    questions.get(j).setGraphic(resizedImageView("incorrect.png",15,15));
                }


                questions.get(j).setId(i + "/" + j);
                System.out.println(questions.get(j).getId());
                levelItems.get(i).getItems().add(questions.get(j));
                questions.get(j).setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Object o = event.getSource();
                        for (int k = 0; k < questions.size(); k++) {
                            if (o == questions.get(k)) {

                                String menuID = questions.get(k).getId();
                                String menuIDArray[] = menuID.split("/");
                                int levelNum = Integer.parseInt(menuIDArray[0]);
                                int questionNum = Integer.parseInt(menuIDArray[1]);
                                setSlide(levelNum, questionNum);
                                setLevelProgress();
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
        return menuBar;
    }

  private void createGUI() {
        MenuBar settingsBar = getSettingsBar();
        MenuBar menuBar = getMenuBar();

        progress = new ProgressBar(this.levelNum / (double) presentation.lArray.size());

        questionsProgress = new ProgressBar(0);
        questionsProgress.setDisable(false);

        FLProgress fLprogress = new FLProgress(500, this.levelNum, this.presentation.lArray.size());

        HBox menuBarBox = new HBox();
        menuBarBox.getChildren().add(menuBar);
        menuBarBox.getChildren().add(progress);
        menuBarBox.getChildren().add(questionsProgress);
        menuBarBox.getChildren().add(fLprogress.getStackPane());
        menuBarBox.getChildren().add(settingsBar);


        //BorderPane borderLayout = new BorderPane();
        this.borderLayout.setTop(menuBarBox);
        borderLayout.setCenter(this.presentation.pane);


        HBox menu = getMenuHbox();


        this.borderLayout.setBottom(menu);
        //menu.setBackground(new Background(new BackgroundFill(Color.web("#FF0000"), CornerRadii.EMPTY, Insets.EMPTY)));
        menu.getChildren().add(prevBtn);
        menu.getChildren().add(QuestionBtn);
        menu.getChildren().add(ExampleBtn);
        menu.getChildren().add(SolutionBtn);
        menu.getChildren().add(nextBtn);
    }

  @Override
  public void stop() { Platform.exit(); }
}