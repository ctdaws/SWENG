import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LandingPage extends Application{

    private PWSPresentation pwsPresentation;

    private int currentSlideID = 0;
    private PWSSlide currentSlide;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage landingStage) {

        landingStage.setTitle("Lecture Quest Alpha Launcher");
        landingStage.getIcons().add(new Image(this.getClass().getResource("LQ_logo_2_32.png").toExternalForm()));

        Group root = new Group();
        Scene scene = new Scene(root, 1280, 720);

        scene.setOnKeyPressed((keyEvent) -> {
            switch (keyEvent.getCode()) {
                case ESCAPE:
                    stop();
                    break;
            }
        });

        Pane landingPage = new Pane();
        PWSImage lqLogo = new PWSImage("logo", new PWSPosition(470, 50, 810, 450), new PWSTransitions("trigger", 0), "LQ Shield.png");
        landingPage.getChildren().add(lqLogo.getPwsMedia());
        LQButton lqButton = new LQButton("start", new PWSPosition(500, 500, 780, 550), new PWSTransitions("trigger", 0));
        lqButton.getLQButton().setText("Choose your Quest");

        lqButton.getLQButton().setOnMouseClicked((clickEvent) -> {
            LectureQuest quest = new LectureQuest();
            quest.start(new Stage());
        });
        root.getChildren().addAll(landingPage, lqButton.getLQMedia());

        landingStage.setScene(scene);
        landingStage.show();
    }

    @Override
    public void stop() { Platform.exit(); }
}
