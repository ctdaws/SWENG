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

    private PWSPresentation pwsPresentation;
    private LQPresentation lqPresentation;

    private int currentSlideID = 0;
    private PWSSlide currentSlide;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Lecture Quest Alpha");
        primaryStage.getIcons().add(new Image(this.getClass().getResource("LQ_logo_2_32.png").toExternalForm()));

        Font.loadFont(this.getClass().getResource("fonts/BebasNeue-Regular.ttf").toExternalForm(), 20);

        Group root = new Group();
        Scene scene = new Scene(root, 1280, 720);
        scene.getStylesheets().add(getClass().getResource("presentationStyle.css").toExternalForm());

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
                    break;
                }
                case "pws": {
                    pwsPresentation = xmlParser.getParsedPwsPresentation();
//                    Branch to PWS Navigator
//                    TODO: PWS Navigator

                        currentSlide = pwsPresentation.getPwsSlideByID("slide0");

                        root.getChildren().add(currentSlide.getSlidePane());

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

                    break;
                }
                default:
//                    Failed to parse(?)
//                    TODO: Something
                    break;
            }

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
    public void stop() {
        Platform.exit();
    }
}
