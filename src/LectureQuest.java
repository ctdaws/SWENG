import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class LectureQuest extends Application {

    private PWSPresentation pwsPresentation;
    private LQPresentation lqPresentation;

    private int currentSlideID = 0;
    private PWSSlide currentSlide;

    private BorderPane borderLayout = new BorderPane();
    private LQProgress LQprogress;

    private int levelNum = 0, qNum, i, j = 0;

    private LQButton nextBtn, QuestionBtn, ExampleBtn, SolutionBtn, prevBtn;
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
        primaryStage.getIcons().add(new Image(this.getClass().getResource("LQ_logo_2_32.png").toExternalForm()));

        this.navigator = new Navigator();

        Font.loadFont(this.getClass().getResource("fonts/BebasNeue-Regular.ttf").toExternalForm(), 20);

        Group root = new Group();
        Scene scene = new Scene(root, 1280, 720);

        File questXml = openFile(primaryStage);

        if(questXml == null) {
            System.out.println("null or invalid file chosen. Closing.");
            primaryStage.close();
        }
        else {
            XMLParser xmlParser = new XMLParser();
            xmlParser.parse(questXml);
            switch(xmlParser.getXmlType()) {
                case "4l": {
                    lqPresentation = xmlParser.getParsedLQPresentation();
                    System.out.println(".4l is not currently supported outside of the parser.");
//                    Branch to LQ Navigator
//                    TODO: LQ Navigator

                    this.navigator.setPresentation(this.lqPresentation);
                    this.navigator.renderSlide();

                    createGUI();

                    borderLayout.setPrefSize(1280, 720);
                    root.getChildren().add(borderLayout);

                    scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

                    scene.setOnKeyPressed((keyEvent) -> {
                        switch (keyEvent.getCode()) {
                            case ESCAPE:
                                stop();
                                break;
                            case RIGHT:
                                navigator.moveNextSlide();
//                    setLevelProgress();
                                updatePresentation();
                                break;
                            case DOWN:
                                navigator.moveNextSlide();
//                    setLevelProgress();
                                updatePresentation();
                                break;
                            case LEFT:
                                navigator.moveBackSlide();
//                    setLevelProgress();
                                updatePresentation();
                                break;
                            case UP:
                                navigator.moveBackSlide();
//                    setLevelProgress();
                                updatePresentation();
                                break;
                            case F11:
                                primaryStage.setFullScreen(!primaryStage.isFullScreen());
                                break;
                        }
                    });

                    root.setEffect(this.colorAdjust);

                    primaryStage.setScene(scene);
                    primaryStage.show();
                    updatePresentation();

                    break;
                }
                case "pws": {
                    pwsPresentation = xmlParser.getParsedPwsPresentation();
//                    Branch to PWS Navigator
//                    TODO: PWS Navigator

                        currentSlide = pwsPresentation.getPwsSlideByID("slide0");

                        root.getChildren().add(currentSlide.getSlidePane());

                        scene.getStylesheets().add(getClass().getResource("presentationStyle.css").toExternalForm());

                        scene.setOnKeyPressed((keyEvent) -> {
                            switch(keyEvent.getCode()) {
                                case ESCAPE:
                                    primaryStage.close();
                                    break;
                                case RIGHT:
                                    if(currentSlideID < (pwsPresentation.getPwsSlideArrayList().size() - 1)) {
                                        root.getChildren().remove(currentSlide.getSlidePane());
                                        currentSlide = pwsPresentation.getPwsSlideByID("slide" + ++currentSlideID);
                                        if(currentSlide != null) {
                                            root.getChildren().add(currentSlide.getSlidePane());
                                        }
                                    }
                                    break;
                                case LEFT:
                                    if(currentSlideID > 0) {
                                        root.getChildren().remove(currentSlide.getSlidePane());
                                        currentSlide = pwsPresentation.getPwsSlideByID("slide" + --currentSlideID);
                                        if (currentSlide != null) {
                                            root.getChildren().add(currentSlide.getSlidePane());
                                        }
                                    }
                                    break;
                                case DOWN:
                                    root.getChildren().remove(currentSlide.getSlidePane());
                                    break;
                                case UP:
                                    root.getChildren().add(currentSlide.getSlidePane());
                                    break;
                            }
                        });

                    primaryStage.setScene(scene);
                    primaryStage.show();

                    break;
                }
                default:
//                    Failed to parse(?)
//                    TODO: Something
                    break;
            }


        }
    }

    private File openFile (Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("PWS (*.pws)", "*.pws"),
            new FileChooser.ExtensionFilter("Quest (*.4l)", "*.4l"),
            new FileChooser.ExtensionFilter("All Types (*.*)", "*.*")
        );
        return fileChooser.showOpenDialog(stage);
    }

    private ImageView resizedImageView(String imageLocation, int sizeX, int sizeY) {
        ImageView resizedImageView = new ImageView(new Image(this.getClass().getResource(imageLocation).toExternalForm()));
        //ImageView resizedImageView = new ImageView(new Image("file:../resources/"+imageLocation));
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
        this.LQprogress.setLevelProgress(this.navigator.getLevelNum());
    }

    private void checkButtonStatus() {
        switch(this.navigator.getPresentation().getSlideByID(this.navigator.getCurrentID()).getLQSlideType()){
            //switch(this.presentation.getSlideByID(slideID){
            case "M":
                setButtonStatus(true, true, true);
                prevBtn.getLQButton().setDisable(true);
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
        this.QuestionBtn.getLQButton().setDisable(Q);
        this.ExampleBtn.getLQButton().setDisable(X);
        this.SolutionBtn.getLQButton().setDisable(S);
    }

    private void toggleAudio() {
        //TODO add slide mute method
//        this.navigator.currentSlide.muteAudio(!soundEnabled);
        this.navigator.getPresentation().getSlideByID(navigator.getCurrentID()).muteAudio(!soundEnabled);
        System.out.println("Audio is now" + soundEnabled);
    }

    private void updatePresentation(){
        checkButtonStatus();
        setLevelProgress();
        toggleAudio();
        this.navigator.getPresentation().resetFeedbackButtons();
    }

    private HBox getMenuHbox() {
        //TODO Refactor into a new method
        //create Bottom Pane
//        this.prevBtn = new FLButton("Previous", new Position(220, 0), 150, 50, "file:../resources/previous_button.png");
//        this.QuestionBtn = new FLButton("Question", new Position(393, 0), 150, 50, "file:../resources/question_button.png");
//        this.ExampleBtn = new FLButton("Example", new Position(566, 0), 150, 50, "file:../resources/example_button.png");
//        this.SolutionBtn = new FLButton("Solution", new Position(739, 0), 150, 50, "file:../resources/solution_button.png");
//        this.nextBtn = new FLButton("Next", new Position(902, 0), 150, 50, "file:../resources/next_button.png");
        this.prevBtn = new LQButton("Previous", new PWSPosition(220, 0, 370, 50), new PWSTransitions("trigger", 0), this.getClass().getResource("previous_button.png").toExternalForm());
        this.QuestionBtn = new LQButton("Question", new PWSPosition(393, 0, 543, 50), new PWSTransitions("trigger", 0), this.getClass().getResource("question_button.png").toExternalForm());
        this.ExampleBtn = new LQButton("Example", new PWSPosition(566, 0, 716, 50), new PWSTransitions("trigger", 0), this.getClass().getResource("example_button.png").toExternalForm());
        this.SolutionBtn = new LQButton("Solution", new PWSPosition(739, 0, 889, 50), new PWSTransitions("trigger", 0), this.getClass().getResource("solution_button.png").toExternalForm());
        this.nextBtn = new LQButton("Next", new PWSPosition(902, 0, 1052, 50), new PWSTransitions("trigger", 0), this.getClass().getResource("next_button.png").toExternalForm());

//        prevBtn.getButton().setDisable(true);

        nextBtn.getLQButton().setOnAction(event -> {
            navigator.moveNextSlide();
            prevBtn.getLQButton().setDisable(false);
//                checkButtonStatus();
//                setLevelProgress();
            updatePresentation();

        });
        QuestionBtn.getLQButton().setOnAction(event -> {
            navigator.moveSlide(navigator.GetQuestionID());//TODO
            prevBtn.getLQButton().setDisable(false);
//                checkButtonStatus();
//                setLevelProgress();
            updatePresentation();

        });
        ExampleBtn.getLQButton().setOnAction(event -> {
            navigator.moveSlide(navigator.GetExampleID());//TODO
            prevBtn.getLQButton().setDisable(false);
//                checkButtonStatus();
//                setLevelProgress();
            updatePresentation();

        });
        SolutionBtn.getLQButton().setOnAction(event -> {
            navigator.moveSlide(navigator.GetSolutionID());  //TODO
            prevBtn.getLQButton().setDisable(false);
//                checkButtonStatus();
//                setLevelProgress();
            updatePresentation();

        });

        prevBtn.getLQButton().setOnAction(event -> {
            navigator.moveBackSlide();
            if(0 == 1) {//TODO if MENU
                prevBtn.getLQButton().setDisable(true);
            }
//                checkButtonStatus();
//                setLevelProgress();
            updatePresentation();
        });

        HBox menu = new HBox();
        //menu.getChildren().add(new ImageView(new Image("file:../resources/4learning_logo.png")));
        //menu.getChildren().add(new ImageView(new Image(this.getClass().getResource("4learning_logo.png").toExternalForm())));
        menu.getChildren().add(prevBtn.getLQMedia());
        menu.getChildren().add(QuestionBtn.getLQMedia());
        menu.getChildren().add(ExampleBtn.getLQMedia());
        menu.getChildren().add(SolutionBtn.getLQMedia());
        menu.getChildren().add(nextBtn.getLQMedia());
        //menu.getChildren().add(new ImageView(new Image("file:../resources/LQ_shield.png")));
        //menu.getChildren().add(new ImageView(new Image(this.getClass().getResource("LQ_shield.png").toExternalForm())));

        menu.setSpacing(23);
        menu.setMargin(prevBtn.getLQButton(), new Insets(0, 125, 8, 72));
        menu.setMargin(QuestionBtn.getLQButton(), new Insets(0, 0, 8, 0));
        menu.setMargin(ExampleBtn.getLQButton(), new Insets(0, 0, 8, 0));
        menu.setMargin(SolutionBtn.getLQButton(), new Insets(0, 0, 8, 0));
        menu.setMargin(nextBtn.getLQButton(), new Insets(0, 72, 8, 125));
        return menu;
    }

    private MenuBar getSettingsBar() {
        MenuBar settingsBar = new MenuBar();
        settingsBar.setMinWidth(125);
        Menu settings = new Menu("");
        settings.setGraphic(resizedImageView("settings.png", 15, 15));

        MenuItem muteItem = new MenuItem("Mute Audio");
        if (this.soundEnabled == true) {
            muteItem.setGraphic(resizedImageView("mute_icon.png", 15, 15));
        }
        else if (this.soundEnabled == false){
            muteItem.setGraphic(resizedImageView("sound_icon.png", 15, 15));
        }

        //TODO change button name
        muteItem.setOnAction(event -> {
            if (soundEnabled == true){
                //mute = true
                //TODO set sound to mute
                soundEnabled = false;
                muteItem.setGraphic(resizedImageView("sound_icon.png", 15, 15));
                muteItem.setText("Enable Audio");
            }
            else {
                //mute = false
                //TODO set to unmute
                soundEnabled = true;
                muteItem.setGraphic(resizedImageView("mute_icon.png", 15, 15));
                muteItem.setText("Mute Audio");
            }

            //navigator.moveNextSlide();
            //prevBtn.setDisable(false);
            //checkButtonStatus();
            //setLevelProgress();
            // FLProgress.setLevelProgress();
            toggleAudio();

        });


        Menu contrastMenu = new Menu("Adjust Contrast");

        // MenuItem highContrast = new MenuItem("High Contrast");
        // MenuItem mediumContrast = new MenuItem("Medium Contrast");
        // MenuItem lowContrast = new MenuItem("Low Contrast");
        Slider contrastSlider = new Slider(-1, 1, 0.0);
        contrastSlider.setMinorTickCount(0);
        contrastSlider.setMajorTickUnit(1);
        contrastSlider.setShowTickMarks(true);
        contrastSlider.setShowTickLabels(true);
        contrastSlider.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double n) {
                if (n < -0.5) return "Low";
                if (n < 0.5) return "Default";
                return "High";
            }

            @Override
            public Double fromString(String s) {
                switch (s) {
                    case "Low":
                        return -1d;
                    case "Default":
                        return 0d;
                    case "High":
                        return 1d;
                    default:
                        return 1d;
                }
            }
        });
        CustomMenuItem contrastItem = new CustomMenuItem(contrastSlider);
        contrastItem.setHideOnClick(false);

        //Change contrast based on slider value
        contrastSlider.valueProperty().addListener((ov, old_val, new_val) -> {
            contrast = contrastSlider.getValue();
            colorAdjust.setContrast(contrastSlider.getValue());
            System.out.println("Contrast: " + Double.toString(contrast));
        });

        contrastMenu.getItems().addAll(contrastItem);

        settings.getItems().addAll(muteItem, contrastMenu);

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


        for (i = 0; i < lqPresentation.getLqLevelArray().size(); i++) {
            levelItems.add(new Menu("Level " + (i + 1)));

            if (/*levelComplete*/false == true) {
                levelItems.get(i).setGraphic(resizedImageView("correct.png", 15, 15));
            }

            ArrayList<MenuItem> questions = new ArrayList<MenuItem>();  //Array of questions in current level.

            for (j = 0; j < lqPresentation.getLqLevelArray().get(i).getLqQuestionArray().size(); j++) {
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
                questions.get(j).setOnAction(event -> {
                    Object o = event.getSource();
                    for (int k = 0; k < questions.size(); k++) {
                        if (o == questions.get(k)) {

                            String menuID = questions.get(k).getId();
                            String menuIDArray[] = menuID.split("/");
                            int levelNum = Integer.parseInt(menuIDArray[0]);
                            int questionNum = Integer.parseInt(menuIDArray[1]);
                            setSlide(levelNum, questionNum);
//                                setLevelProgress();
//                                checkButtonStatus();
                            updatePresentation();
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

        this.LQprogress = new LQProgress(960, this.levelNum, this.lqPresentation.getLqLevelArray().size());

        HBox menuBarBox = new HBox();
        //menuBarBox.setAlignment(Pos.CENTER);
        menuBarBox.getChildren().add(menuBar);
        menuBarBox.getChildren().add(this.LQprogress.getStackPane());
        //menuBarBox.getChildren().add(questionsProgress);
        //menuBarBox.getChildren().add(fLprogress.getStackPane());
        menuBarBox.getChildren().add(settingsBar);
        menuBarBox.setMargin(settingsBar, new Insets(25, 0, 0, 110));


        //BorderPane borderLayout = new BorderPane();
        this.borderLayout.setTop(menuBarBox);
        borderLayout.setCenter(this.lqPresentation.pane);

        HBox menu = getMenuHbox();

        //menu.setAlignment(Pos.CENTER);
        //menu.setMaxHeight(95);

        this.borderLayout.setBottom(menu);
        //menu.setBackground(new Background(new BackgroundFill(Color.web("#FF0000"), CornerRadii.EMPTY, Insets.EMPTY)));

    }

    private String CombineMenuID(int newLevel, int newQuestion){
        return (newLevel + "/" + newQuestion + "/" + 1);
    }



    @Override
    public void stop() {
        Platform.exit();
    }
}
