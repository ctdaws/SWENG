import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.ProgressBar;
// import javafx.scene.control.Slider;
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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.effect.Effect;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.Group;


public class LectureQuest extends Application {

  private Colors programDefaultColor = new Colors("#000000", "#000000");
  private TextStyle programDefaultStyle = new TextStyle("Arial", 20, false, false, false);
  private File FLprogressStyleSheet = new File("../resources/style.css");

  private Defaults programDefault = new Defaults(programDefaultStyle, programDefaultColor);

  private Presentation presentation;

  private BorderPane borderLayout = new BorderPane();
  private FLProgress FLprogress;

  private int levelNum = 0, qNum, i, j = 0;

  private FLButton nextBtn, QuestionBtn, ExampleBtn, SolutionBtn, prevBtn;
  //private Button muteBtn = new Button("Mute");
  private Boolean soundEnabled = true;
  //private ProgressBar progress, questionsProgress;
  private Navigator navigator;
  private Pane sizePane;

  private double contrast;
  private ColorAdjust colorAdjust = new ColorAdjust();

  public static void main(String[] args) { launch(args); }

  @Override
  public void start(Stage primaryStage) {


    primaryStage.setTitle("Lecture Quest Alpha");
    //primaryStage.getIcons().add(new Image(this.getClass().getResource("LQ_logo_2_32.png").toExternalForm()));
    primaryStage.getIcons().add(new Image("file:../resources/LQ_logo_2_32.png"));

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

      //Font.loadFont(this.getClass().getResource("fonts/BebasNeue-Regular.ttf").toExternalForm(), 20);

      createGUI();

      //Scene scene = new Scene(this.presentation.pane, presentation.getWidth(), presentation.getHeight());
      // this.sizePane = new Pane();
      // this.sizePane.setMinWidth(presentation.getWidth());
      // this.sizePane.setMinHeight(presentation.getHeight()-this.borderLayout.getLayoutComponent(BorderLayout.NORTH).getHeight()-this.borderLayout.getLayoutComponent(BorderLayout.SOUTH).getHeight());
      //borderLayout.setCenter(this.sizePane);
      borderLayout.setPrefSize(presentation.getWidth(), presentation.getHeight());
      Group root = new Group();
      root.getChildren().add(borderLayout);
      Scene scene = new Scene(root, presentation.getWidth(), presentation.getHeight());

      //scene.getStylesheets().add(getClass().getResource("presentationStyle.css").toExternalForm());
      scene.getStylesheets().add("file:///" + FLprogressStyleSheet.getAbsolutePath().replace("\\","/"));

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

      root.setEffect(this.colorAdjust);

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
    //ImageView resizedImageView = new ImageView(new Image(this.getClass().getResource(imageLocation).toExternalForm()));
    ImageView resizedImageView = new ImageView(new Image("file:../resources/"+imageLocation));
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
   //this.progress.setProgress((double)(this.navigator.getLevelNum()/presentation.lArray.size()));
   //this.fLprogress.setLevelProgress(this.levelNum);
   // setQuestionsProgress();
   this.FLprogress.setLevelProgress(this.navigator.getLevelNum());
  }

  // private void setQuestionsProgress(){
  //     int numberOfQuestions = 0;
  //     for (i = 0; i < presentation.lArray.size(); i++) {
  //         //TODO questionProgress slider
  //         numberOfQuestions += presentation.lArray.get(i).qArray.size();
  //     }
  //     this.questionsProgress.setProgress((double) (this.navigator.getLevelNum() / numberOfQuestions));
  // }

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
    this.QuestionBtn.getButton().setDisable(Q);
    this.ExampleBtn.getButton().setDisable(X);
    this.SolutionBtn.getButton().setDisable(S);
  }

  private HBox getMenuHbox() {
        //TODO Refactor into a new method
        //create Bottom Pane
        this.prevBtn = new FLButton("Previous", new Position(220, 0), 150, 50, "file:../resources/previous_button.png");
        this.QuestionBtn = new FLButton("Question", new Position(393, 0), 150, 50, "file:../resources/question_button.png");
        this.ExampleBtn = new FLButton("Example", new Position(566, 0), 150, 50, "file:../resources/example_button.png");
        this.SolutionBtn = new FLButton("Solution", new Position(739, 0), 150, 50, "file:../resources/solution_button.png");
        this.nextBtn = new FLButton("Next", new Position(902, 0), 150, 50, "file:../resources/next_button.png");

        prevBtn.getButton().setDisable(true);

        nextBtn.getButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                navigator.moveNextSlide();
                prevBtn.getButton().setDisable(false);
                checkButtonStatus();
                setLevelProgress();
                // FLProgress.setLevelProgress();

            }
        });
        QuestionBtn.getButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                navigator.moveSlide(navigator.GetQuestionID());//TODO
                prevBtn.getButton().setDisable(false);
                checkButtonStatus();
                setLevelProgress();

            }
        });
        ExampleBtn.getButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                navigator.moveSlide(navigator.GetExampleID());//TODO
                prevBtn.getButton().setDisable(false);
                checkButtonStatus();
                setLevelProgress();

            }
        });
        SolutionBtn.getButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                navigator.moveSlide(navigator.GetSolutionID());  //TODO
                prevBtn.getButton().setDisable(false);
                checkButtonStatus();
                setLevelProgress();

            }
        });

        prevBtn.getButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                navigator.moveBackSlide();
                if(0 == 1) {//TODO if MENU
                    prevBtn.getButton().setDisable(true);
                }
                checkButtonStatus();
                //setLevelProgress();
            }
        });


        HBox menu = new HBox();
        menu.getChildren().add(new ImageView(new Image("file:../resources/4learning_logo.png")));
        menu.getChildren().add(prevBtn.getMedia());
        menu.getChildren().add(QuestionBtn.getMedia());
        menu.getChildren().add(ExampleBtn.getMedia());
        menu.getChildren().add(SolutionBtn.getMedia());
        menu.getChildren().add(nextBtn.getMedia());
        menu.getChildren().add(new ImageView(new Image("file:../resources/LQ_shield.png")));
        menu.setSpacing(23);
        menu.setMargin(prevBtn.getButton(), new Insets(37, 0, 0, 72));
        menu.setMargin(QuestionBtn.getButton(), new Insets(37, 0, 0, 0));
        menu.setMargin(ExampleBtn.getButton(), new Insets(37, 0, 0, 0));
        menu.setMargin(SolutionBtn.getButton(), new Insets(37, 0, 0, 0));
        menu.setMargin(nextBtn.getButton(), new Insets(37, 72, 0, 0));
        return menu;
    }

  private MenuBar getSettingsBar() {
        MenuBar settingsBar = new MenuBar();
        settingsBar.setMinWidth(125);
        Menu settings = new Menu("");
        settings.setGraphic(resizedImageView("settings.png", 15, 15));

        MenuItem muteItem = new MenuItem("Mute");
        if (this.soundEnabled == true) {
            muteItem.setGraphic(resizedImageView("mute_icon.png", 15, 15));
        }
        else if (this.soundEnabled == false){
            muteItem.setGraphic(resizedImageView("sound_icon.png", 15, 15));
        }

         muteItem.setOnAction(new EventHandler<ActionEvent>() {//TODO change button name
             @Override
                public void handle(ActionEvent event) {
                    if (soundEnabled == true){
                        //mute = true
                        //TODO set sound to mute
                        soundEnabled = false;
                        muteItem.setGraphic(resizedImageView("sound_icon.png", 15, 15));
                        muteItem.setText("Unmute");
                    }
                    else {
                        //mute = false
                        //TODO set to unmute
                        soundEnabled = true;
                        muteItem.setGraphic(resizedImageView("mute_icon.png", 15, 15));
                        muteItem.setText("Mute");
                    }

                 //navigator.moveNextSlide();
                 //prevBtn.setDisable(false);
                 //checkButtonStatus();
                 //setLevelProgress();
                 // FLProgress.setLevelProgress();

             }
         });


        //Menu contrast = new Menu("Contrast");

        // MenuItem highContrast = new MenuItem("High Contrast");
        // MenuItem mediumContrast = new MenuItem("Medium Contrast");
        // MenuItem lowContrast = new MenuItem("Low Contrast");
        Slider contrastSlider = new Slider(-1, 1, 0.0);
        CustomMenuItem contrastItem = new CustomMenuItem(contrastSlider);
        contrastItem.setHideOnClick(false);

        /*Change contrast based on slider value - Added*/
        contrastSlider.valueProperty().addListener(new ChangeListener<Number>(){
          public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val){
            contrast = contrastSlider.getValue();
            colorAdjust.setContrast(contrastSlider.getValue());
            System.out.println("Contrast: " + Double.toString(contrast));
          }
        });

        //contrast.getItems().addAll(lowContrast, mediumContrast, highContrast);

        settings.getItems().addAll(muteItem, contrastItem);

        settingsBar.getMenus().add(settings);
        return settingsBar;
  }

  private MenuBar getMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.setBackground(new Background(new BackgroundFill(Color.web("#FF0000"), CornerRadii.EMPTY, Insets.EMPTY)));
        menuBar.setMinWidth(125);

        Menu levels = new Menu("Level Select");

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

                if (false){//TODO questionComplete  == true && questionCorrect == true) {
                    levelItems.get(i).setGraphic(resizedImageView("correct.png", 15, 15));
                }
                else if (false){//TODO questionComplete  == true && questionCorrect == false) {
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



        //progress = new ProgressBar(this.levelNum / (double) presentation.lArray.size());

        //questionsProgress = new ProgressBar(0);
        //questionsProgress.setDisable(false);

        this.FLprogress = new FLProgress(960, this.levelNum, this.presentation.lArray.size());

        HBox menuBarBox = new HBox();
        //menuBarBox.setAlignment(Pos.CENTER);
        menuBarBox.getChildren().add(menuBar);
        menuBarBox.getChildren().add(this.FLprogress.getStackPane());
        //menuBarBox.getChildren().add(questionsProgress);
        //menuBarBox.getChildren().add(fLprogress.getStackPane());
        menuBarBox.getChildren().add(settingsBar);
        menuBarBox.setMargin(settingsBar, new Insets(25, 0, 0, 110));


        //BorderPane borderLayout = new BorderPane();
        this.borderLayout.setTop(menuBarBox);
        borderLayout.setCenter(this.presentation.pane);


        HBox menu = getMenuHbox();

        //menu.setAlignment(Pos.CENTER);
        //menu.setMaxHeight(95);

        this.borderLayout.setBottom(menu);
        //menu.setBackground(new Background(new BackgroundFill(Color.web("#FF0000"), CornerRadii.EMPTY, Insets.EMPTY)));

    }

  @Override
  public void stop() { Platform.exit(); }
}
