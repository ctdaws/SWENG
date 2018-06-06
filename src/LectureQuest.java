import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.File;
import java.util.ArrayList;

public class LectureQuest extends Application {

//    Holders for presentations returned from parser
    private PWSPresentation pwsPresentation;
    private LQPresentation lqPresentation;

//    PWS Presentations

//    Used for navigating PWS presentations
    private int currentSlideID = 0;
    private PWSSlide currentSlide;

//    LQ Presentations

    private BorderPane borderLayout = new BorderPane();
    private LQProgress LQprogress;

    private int levelNum = 0, qNum, i, j = 0;

    private LQButton nextBtn, QuestionBtn, ExampleBtn, SolutionBtn, prevBtn;
    private Boolean soundEnabled = true;
    private Boolean isInteractionEnabled = true;
    private Navigator navigator;
    private Pane sizePane;

    private double contrast, brightness, saturation;
    private ColorAdjust colorAdjust = new ColorAdjust();
    private MenuBar menuBar;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {

//        Prepare LectureQuest window
        primaryStage.setTitle("Lecture Quest");
        primaryStage.getIcons().add(new Image(this.getClass().getResource("LQ_shield_32.png").toExternalForm()));

//        Load external font
        Font.loadFont(this.getClass().getResource("fonts/BebasNeue-Regular.ttf").toExternalForm(), 20);

//        Create root group, add to scene
        Group root = new Group();
        Scene scene = new Scene(root, 1280, 720);

//        Get xml file from FileChooser
        File questXml = openFile(primaryStage);

//        Check if a file was returned
        if(questXml == null) {
//            File does not exist
            System.out.println("null or invalid file chosen. Closing.");
//            Close LectureQuest window
            primaryStage.close();
        }
        else {
//            File exists
//            Create new XML parser
            XMLParser xmlParser = new XMLParser();
//            Hand file to parser & parse
            xmlParser.parse(questXml);
//            Handle type of presentation appropriately
//            Get filetype from parser
            switch(xmlParser.getXmlType()) {
//                LectureQuest presentation
                case "4l": {
                    lqPresentation = xmlParser.getParsedLQPresentation();

                    this.navigator = new Navigator();

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
                                updatePresentation();
                                break;
                            case DOWN:
                                navigator.moveNextSlide();
                                updatePresentation();
                                break;
                            case LEFT:
                                navigator.moveBackSlide();
                                updatePresentation();
                                break;
                            case UP:
                                navigator.moveBackSlide();
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
//                    PWS presentation
                case "pws": {
//                    Retrieve presentation from parser
                    pwsPresentation = xmlParser.getParsedPwsPresentation();

//                    Set 1st slide
                    currentSlide = pwsPresentation.getPwsSlideByID("slide0");

//                    Add 1st slide to window
                    root.getChildren().add(currentSlide.getSlidePane());

//                    Apply CSS to presentation
                    scene.getStylesheets().add(getClass().getResource("presentationStyle.css").toExternalForm());

//                    Set ActionListeners for navigation
                    scene.setOnKeyPressed((keyEvent) -> {
                        switch(keyEvent.getCode()) {
//                                Close window when 'ESC' pressed
                            case ESCAPE:
                                primaryStage.close();
                                break;
//                                Move to next slide when '->' pressed
                            case RIGHT:
//                                Check that current slide is not last
                                if(currentSlideID < (pwsPresentation.getPwsSlideArrayList().size() - 1)) {
//                                    Stop transitions on current slide
                                    currentSlide.endTransitions();
//                                    Remove current slide from window
                                    root.getChildren().remove(currentSlide.getSlidePane());
//                                    Get next slide
                                    currentSlide = pwsPresentation.getPwsSlideByID("slide" + ++currentSlideID);
//                                    Check that a slide has been set
                                    if(currentSlide != null) {
//                                        Add new slide to window
                                        root.getChildren().add(currentSlide.getSlidePane());
//                                        Start transitions
                                        currentSlide.startTransitions();
                                    }
                                }
                                break;
//                                Move to previous slide when '<-' pressed
                            case LEFT:
//                                Check that current slide is not first
                                if(currentSlideID > 0) {
//                                    Stop transitions on current slide
                                    currentSlide.endTransitions();
//                                    Remove current slide from window
                                    root.getChildren().remove(currentSlide.getSlidePane());
//                                    Get previous slide
                                    currentSlide = pwsPresentation.getPwsSlideByID("slide" + --currentSlideID);
//                                    Check that a slide has been set
                                    if (currentSlide != null) {
//                                        Add new slide to window
                                        root.getChildren().add(currentSlide.getSlidePane());
                                    }
                                }
                                break;
//                                Set window to fullscreen when 'F11' pressed
                            case F11:
                                primaryStage.setFullScreen(!primaryStage.isFullScreen());
                                break;
                        }
                    });

//                    Show presentation window
                    primaryStage.setScene(scene);
                    primaryStage.show();

                    break;
                }
                default:
                    System.out.println("Parser returned unknown presentation type.");
                    break;
            }


        }
    }

//    Method for creating a FileChooser for getting xml files
    private File openFile (Stage stage) {
//        Create FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
//        Add filetype filters to FileChooser
        fileChooser.getExtensionFilters().addAll(
//                .4l extension for LectureQuest presentations
                new FileChooser.ExtensionFilter("Quest (*.4l)", "*.4l"),
//                .pws extension for PWS presentations
                new FileChooser.ExtensionFilter("PWS (*.pws)", "*.pws"),
//                Shows all filetypes, useful if xml has not had extension set
                new FileChooser.ExtensionFilter("All Types (*.*)", "*.*")
        );

//        Show FileChooser, return selected file
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

        this.navigator.moveSlide(CombineMenuID(levelNum, qNum));
    }

    private void setLevelProgress(){
        this.LQprogress.setLevelProgress(this.navigator.getLevelNum());
    }

    private void checkButtonStatus() {
        switch(this.navigator.getPresentation().getSlideByID(this.navigator.getCurrentID()).getLQSlideType()){
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
                setButtonStatus(true, false, false);
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
        this.navigator.getPresentation().getSlideByID(navigator.getCurrentID()).muteAudio(!soundEnabled);
    }

    private void updateQuestionProgress(int levelNum, int questionNum, Boolean answerResponse){
        MenuItem levelMenuItem = this.menuBar.getMenus().get(0).getItems().get(levelNum - 1);
        Menu levelMenu = (Menu)levelMenuItem;
        if(answerResponse){
            levelMenu.getItems().get(questionNum).setGraphic(resizedImageView("correct.png", 15, 15));
        }
        else {
            levelMenu.getItems().get(questionNum).setGraphic(resizedImageView("incorrect.png", 15, 15));
        }
    }

    private void clearQuestionProgress(){
        for (i = 0; i < lqPresentation.getLqLevelArray().size(); i++) {
            MenuItem levelMenuItem = this.menuBar.getMenus().get(0).getItems().get(i);
            Menu levelMenu = (Menu)levelMenuItem;
            for (j = 0; j < lqPresentation.getLqLevelArray().get(i).getLqQuestionArray().size(); j++) {
                levelMenu.getItems().get(j).setGraphic(null);
            }
        }
    }

    private void updatePresentation(){
        checkButtonStatus();
        setLevelProgress();
        toggleAudio();
        if(this.navigator.getCurrentID().equals("feedback")){
            Boolean answerResponse = this.lqPresentation.getSlideByID(this.navigator.GetCurrentLevelNum()+"/"+this.navigator.GetCurrentQuestionNum()+"/"+"2").getGotAnswerCorrect();
            updateQuestionProgress(this.navigator.GetCurrentLevelNum(), this.navigator.GetCurrentQuestionNum(), answerResponse);
        }
        if(!this.navigator.getCurrentID().equals("analytics")) {
            this.navigator.getPresentation().resetFeedbackButtons();
        }
    }

    private HBox getMenuHbox() {
        this.prevBtn = new LQButton("Previous", new PWSPosition(220, 0, 370, 50), new PWSTransitions("0", -1), this.getClass().getResource("previous_arrow_new.png").toExternalForm());
        this.prevBtn.add("Back");
        this.QuestionBtn = new LQButton("Question", new PWSPosition(393, 0, 543, 50), new PWSTransitions("0", -1), this.getClass().getResource("button.png").toExternalForm());
        this.QuestionBtn.add("Question");
        this.ExampleBtn = new LQButton("Example", new PWSPosition(566, 0, 716, 50), new PWSTransitions("0", -1), this.getClass().getResource("button.png").toExternalForm());
        this.ExampleBtn.add("Example");
        this.SolutionBtn = new LQButton("Solution", new PWSPosition(739, 0, 889, 50), new PWSTransitions("0", -1), this.getClass().getResource("button.png").toExternalForm());
        this.SolutionBtn.add("Solution");
        this.nextBtn = new LQButton("Next", new PWSPosition(902, 0, 1052, 50), new PWSTransitions("0", -1), this.getClass().getResource("next_arrow_new.png").toExternalForm());
        this.nextBtn.add("Next");

        nextBtn.getLQButton().setOnAction(event -> {
            navigator.moveNextSlide();
            prevBtn.getLQButton().setDisable(false);
            updatePresentation();

        });
        QuestionBtn.getLQButton().setOnAction(event -> {
            navigator.moveSlide(navigator.GetQuestionID());
            prevBtn.getLQButton().setDisable(false);
            updatePresentation();

        });
        ExampleBtn.getLQButton().setOnAction(event -> {
            navigator.moveSlide(navigator.GetExampleID());
            prevBtn.getLQButton().setDisable(false);
            updatePresentation();

        });
        SolutionBtn.getLQButton().setOnAction(event -> {
            navigator.moveSlide(navigator.GetSolutionID());
            prevBtn.getLQButton().setDisable(false);
            updatePresentation();

        });

        prevBtn.getLQButton().setOnAction(event -> {
            navigator.moveBackSlide();
            if(0 == 1) {
                prevBtn.getLQButton().setDisable(true);
            }
            updatePresentation();
        });

        HBox menu = new HBox();
        menu.getChildren().add(prevBtn.getLQMedia());
        menu.getChildren().add(QuestionBtn.getLQMedia());
        menu.getChildren().add(ExampleBtn.getLQMedia());
        menu.getChildren().add(SolutionBtn.getLQMedia());
        menu.getChildren().add(nextBtn.getLQMedia());

        menu.setSpacing(23);
        menu.setMargin(prevBtn.getLQButton(), new Insets(0, 125, 15, 72));
        menu.setMargin(QuestionBtn.getLQButton(), new Insets(0, 0, 15, 0));
        menu.setMargin(ExampleBtn.getLQButton(), new Insets(0, 0, 15, 0));
        menu.setMargin(SolutionBtn.getLQButton(), new Insets(0, 0, 15, 0));
        menu.setMargin(nextBtn.getLQButton(), new Insets(0, 72, 15, 125));

        return menu;
    }

    private MenuBar getSettingsBar() {
        MenuBar settingsBar = new MenuBar();
        settingsBar.setPrefWidth(125);
        settingsBar.getStylesheets().add("settings.css");
        Menu settings = new Menu("");
        settings.setGraphic(resizedImageView("settings.png", 40, 40));

        MenuItem muteItem = new MenuItem("Mute Audio");
        if (this.soundEnabled) {
            muteItem.setGraphic(resizedImageView("mute_icon.png", 20, 20));
        }
        else {
            muteItem.setGraphic(resizedImageView("sound_icon.png", 20, 20));
        }

        muteItem.setOnAction(event -> {
            if (soundEnabled){
                soundEnabled = false;
                muteItem.setGraphic(resizedImageView("sound_icon.png", 20, 20));
                muteItem.setText("Enable Audio");
            }
            else {
                soundEnabled = true;
                muteItem.setGraphic(resizedImageView("mute_icon.png", 20, 20));
                muteItem.setText("Mute Audio");
            }
            toggleAudio();

        });

        Menu displayMenu = new Menu("Display Settings");
        Menu contrastMenu = new Menu("Contrast");
        Menu brightnessMenu = new Menu("Brightness");
        Menu saturationMenu = new Menu("Saturation");

        Slider[] displaySliders = new Slider[3];
        for(int i = 0; i<3; i++) {
            displaySliders[i] = new Slider(-1, 1, 0.0);
            displaySliders[i].setMinorTickCount(0);
            displaySliders[i].setMajorTickUnit(1);
            displaySliders[i].setShowTickMarks(true);
            displaySliders[i].setShowTickLabels(true);
            displaySliders[i].setLabelFormatter(new StringConverter<Double>() {
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
        }

        Slider contrastSlider = displaySliders[0];
        Slider brightnessSlider = displaySliders[1];
        Slider saturationSlider = displaySliders[2];

        //Change contrast based on slider value
        contrastSlider.valueProperty().addListener((ov, old_val, new_val) -> {
            contrast = contrastSlider.getValue();
            colorAdjust.setContrast(contrastSlider.getValue());
        });

        //Change brightness based on slider value
        brightnessSlider.valueProperty().addListener((ov, old_val, new_val) -> {
            brightness = brightnessSlider.getValue();
            colorAdjust.setBrightness(brightnessSlider.getValue());
        });

        //Change saturation based on slider value
        saturationSlider.valueProperty().addListener((ov, old_val, new_val) -> {
            saturation = saturationSlider.getValue();
            colorAdjust.setSaturation(saturationSlider.getValue());
        });

        CustomMenuItem contrastItem = new CustomMenuItem(contrastSlider);
        contrastItem.setHideOnClick(false);
        contrastMenu.getItems().addAll(contrastItem);

        CustomMenuItem brightnessItem = new CustomMenuItem(brightnessSlider);
        brightnessItem.setHideOnClick(false);
        brightnessMenu.getItems().addAll(brightnessItem);

        CustomMenuItem saturationItem = new CustomMenuItem(saturationSlider);
        saturationItem.setHideOnClick(false);
        saturationMenu.getItems().addAll(saturationItem);

        CheckMenuItem wirelessInteraction = new CheckMenuItem("Classroom Interaction");
        wirelessInteraction.setOnAction((e) -> {
            this.isInteractionEnabled = !this.isInteractionEnabled;
            this.navigator.setInteractionEnabled(isInteractionEnabled);
        });
        wirelessInteraction.setSelected(true);

        MenuItem resetProgress = new MenuItem("Reset Progress");
        resetProgress.setOnAction((e) -> {
            for(int i=0; i < this.navigator.getPresentation().getLqProgressArray().size(); i++) {
                this.navigator.getPresentation().getLqProgressArray().set(i, 0);
                clearQuestionProgress();
            }
        });

        displayMenu.getItems().addAll(contrastMenu, brightnessMenu, saturationMenu);
        settings.getItems().addAll(muteItem, displayMenu, wirelessInteraction, resetProgress);

        settingsBar.getMenus().add(settings);
        return settingsBar;
    }

    private MenuBar getMenuBar() {
        MenuBar menuBar = new MenuBar();

        menuBar.setMinWidth(125);
        menuBar.setPrefHeight(45);

        Menu levels = new Menu("Level Select");
        levels.setStyle("");

        ArrayList<Menu> levelItems = new ArrayList<Menu>(); //Levels array
        ArrayList<ArrayList<MenuItem>> levelQuestions = new ArrayList<ArrayList<MenuItem>>(); //Array of the questions array for each level


        for (i = 0; i < lqPresentation.getLqLevelArray().size(); i++) {
            levelItems.add(new Menu("Level " + (i + 1)));

            ArrayList<MenuItem> questions = new ArrayList<MenuItem>();  //Array of questions in current level.

            for (j = 0; j < lqPresentation.getLqLevelArray().get(i).getLqQuestionArray().size(); j++) {
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
                            updatePresentation();
                        }
                    }
                });
            }

            levels.getItems().add(levelItems.get(i));
            levelQuestions.add(questions);
        }

        menuBar.getMenus().add(levels);
        return menuBar;
    }

    private void createGUI() {
        MenuBar settingsBar = getSettingsBar();
        this.menuBar = getMenuBar();

        this.LQprogress = new LQProgress(960+70, this.levelNum, this.lqPresentation.getLqLevelArray().size());

        HBox menuBarBox = new HBox();
        menuBarBox.getChildren().add(menuBar);
        menuBarBox.getChildren().add(this.LQprogress.getStackPane());
        menuBarBox.getChildren().add(settingsBar);
        menuBarBox.setMargin(menuBar, new Insets(10, 0, 0, 10));
        menuBarBox.setMargin(this.LQprogress.getStackPane(), new Insets(8, 0, 0, 20));
        menuBarBox.setMargin(settingsBar, new Insets(8, 20, 0, 0));

        this.borderLayout.setTop(menuBarBox);
        borderLayout.setCenter(this.lqPresentation.pane);

        HBox menu = getMenuHbox();

        this.borderLayout.setBottom(menu);
    }

    private String CombineMenuID(int newLevel, int newQuestion){ return (newLevel + "/" + newQuestion + "/" + 1); }

    @Override
    public void stop() { Platform.exit(); }
}
