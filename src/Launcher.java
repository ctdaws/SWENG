import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Launcher extends Application{

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage landingStage) {

        landingStage.setTitle("Lecture Quest Alpha Launcher");
        landingStage.getIcons().add(new Image(this.getClass().getResource("LQ_shield_32.png").toExternalForm()));

        Group root = new Group();
        Scene scene = new Scene(root, 1280, 720);
//        scene.getStylesheets().add("style.css");

        scene.setOnKeyPressed((keyEvent) -> {
            switch (keyEvent.getCode()) {
                case ESCAPE:
                    stop();
                    break;
            }
        });

        Pane landingPage = new Pane();
        PWSImage lqLogo = new PWSImage("logo", new PWSPosition(440, 50, 840, 450), new PWSTransitions("0", -1), "LQ_shield_400.png");
        landingPage.getChildren().add(lqLogo.getPwsMedia());
        LQButton lqButton = new LQButton("start", new PWSPosition(500, 500, 780, 550), new PWSTransitions("0", -1));
        lqButton.getLQButton().setText("SELECT QUEST");

//        PWSAudio audioTest = new PWSAudio("audioTest", new PWSPosition(0, 0, 0, 0), new PWSTransitions("2000", -1), "CORRECT.mp3");

        lqLogo.getTimeline().playFrom("auto");
        lqButton.getTimeline().playFrom("auto");
//        audioTest.getTimeline().playFrom("auto");

        lqButton.getLQButton().setOnMouseClicked((MouseEvent clickEvent) -> {
//            audioTest.getTimeline().playFrom("trigger");
//            audioTest.trigger();
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
