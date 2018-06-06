import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Launcher extends Application{

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage landingStage) {

//        Prepare launcher window
        landingStage.setTitle("Lecture Quest");
        landingStage.getIcons().add(new Image(this.getClass().getResource("LQ_shield_32.png").toExternalForm()));

//        Load external font
        Font.loadFont(this.getClass().getResource("fonts/BebasNeue-Regular.ttf").toExternalForm(), 20);

//        Create group and add it to the scene
        Group root = new Group();
        Scene scene = new Scene(root, 1280, 720);
//        Add CSS to the scene
        scene.getStylesheets().add(this.getClass().getResource("launcher.css").toExternalForm());

//        Create content for launcher
        Pane landingPage = new Pane();
//        Background image
        PWSImage bg = new PWSImage("bg", new PWSPosition(0, 0, 1280, 720), new PWSTransitions("0", -1), "Background hills.png");
//        LectureQuest logo
        PWSImage lqLogo = new PWSImage("logo", new PWSPosition(440, 130, 840, 530), new PWSTransitions("0", -1), "LQ_shield_400.png");
//        Add images to pane
        landingPage.getChildren().addAll(bg.getPwsMedia(), lqLogo.getPwsMedia());

//        Create launch button
        LQButton lqButton = new LQButton("start", new PWSPosition(500, 570, 780, 620), new PWSTransitions("0", -1), "button.png");
        lqButton.getLQButton().setText("CHOOSE QUEST");
//        Create button-press audio, add to button
        PWSAudio pwsAudio = new PWSAudio("launchAudio", new PWSPosition(0, 0, 0, 0), new PWSTransitions("trigger", -1), "Opening.mp3");
        lqButton.add(pwsAudio);

//        Start transitions for launcher media
        bg.getTimeline().playFrom("auto");
        lqLogo.getTimeline().playFrom("auto");
        lqButton.getTimeline().playFrom("auto");
        pwsAudio.getTimeline().playFrom("auto");

//        Create EventListener for button
        lqButton.getLQButton().setOnMouseClicked((MouseEvent clickEvent) -> {
//            Play audio
            pwsAudio.trigger();
//            Create new instance of LectureQuest
            LectureQuest quest = new LectureQuest();
//            Start LectureQuest
            quest.start(new Stage());
        });

//        Add content to the root group
        root.getChildren().addAll(landingPage, lqButton.getLQMedia());

//        Display Launcher
        landingStage.setScene(scene);
        landingStage.show();
    }

    @Override
    public void stop() { Platform.exit(); }
}
