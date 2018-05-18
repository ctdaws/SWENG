import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class LectureQuest extends Application {

//    private Colors programDefaultColor = new Colors("#000000", "#000000");
//    private TextStyle programDefaultStyle = new TextStyle("Arial", 20, false, false, false);
//    private Defaults programDefault = new Defaults(programDefaultStyle, programDefaultColor);
//    private Presentation presentation;

    private PWSPresentation pwsPresentation;

    private int currentSlideID = 0;
    private PWSSlide currentSlide;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Lecture Quest Alpha");
        primaryStage.getIcons().add(new Image(this.getClass().getResource("LQ_logo_2_32.png").toExternalForm()));

        File questXml = openFile(primaryStage);

        if(questXml == null) {
            System.out.println("null or invalid file chosen. Exiting.");
            stop();
        }
        else {
//            XMLParser xmlReader = new XMLParser(questXml, programDefault);
//            presentation = xmlReader.getPresentation();
            XMLParserNew xmlParser = new XMLParserNew();
            xmlParser.PWSParser(questXml);
            pwsPresentation = xmlParser.getParsedPwsPresentation();

            Font.loadFont(this.getClass().getResource("fonts/BebasNeue-Regular.ttf").toExternalForm(), 20);

//            Scene scene = new Scene(this.presentation.pane, presentation.getWidth(), presentation.getHeight());
            Group root = new Group();
            Scene scene = new Scene(root, 1280, 720);

            scene.getStylesheets().add(getClass().getResource("presentationStyle.css").toExternalForm());
//            scene.getStylesheets().add("https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css");

            currentSlide = pwsPresentation.getPwsSlideByID("slide0");

            root.getChildren().add(currentSlide.getSlidePane());

            scene.setOnKeyPressed((keyEvent) -> {
                switch(keyEvent.getCode()) {
                    case ESCAPE:
                        stop();
                        break;
                    case RIGHT:
//                        this.presentation.moveNextSlide();
//                        root.getChildren().remove(0,1);
                        if(currentSlideID < (pwsPresentation.getPwsSlideArrayList().size() - 1)) {
                            root.getChildren().remove(currentSlide.getSlidePane());
                            currentSlide = pwsPresentation.getPwsSlideByID("slide" + ++currentSlideID);
                            if(currentSlide != null) {
                                root.getChildren().add(currentSlide.getSlidePane());
                            }
                        }
                        break;
                    case LEFT:
//                        TODO: go to previous slide
//                        root.getChildren().remove(0, 1);
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

    @Override
    public void stop() { Platform.exit(); }
}
