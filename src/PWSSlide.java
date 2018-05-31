import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class PWSSlide {

    private ArrayList<PWSMedia> pwsMediaArrayList;
    private String id;

    private Pane slidePane;

    private PWSFonts pwsFonts;
    private PWSColors pwsColors;
    private PWSTransitions pwsTransitions;

    public PWSSlide(String id, PWSFonts pwsFonts, PWSColors pwsColors, PWSTransitions pwsTransitions) {
        this.id = id;
        this.pwsMediaArrayList = new ArrayList<>();
        this.pwsFonts = pwsFonts;
        this.pwsColors = pwsColors;
        this.pwsTransitions = pwsTransitions;
        this.slidePane = new Pane();
    }

    public Pane getSlidePane() {
//        this.slidePane = new Pane();
//        for(PWSMedia pwsMedia : this.pwsMediaArrayList) {
//            this.slidePane.getChildren().add((Node)pwsMedia.getPwsMedia());
//        }
        return this.slidePane;
    }

    public String getPWSSlideId() { return this.id; }

    public PWSFonts getPwsFonts() { return this.pwsFonts; }

    public PWSColors getPwsColors() { return pwsColors; }

    public PWSTransitions getPwsTransitions() { return pwsTransitions; }

    public void add(PWSText pwsText) {
        this.pwsMediaArrayList.add(pwsText);
        this.slidePane.getChildren().add(pwsText.getPwsMedia());
    }

    public void add(PWSShape pwsShape) {
        this.pwsMediaArrayList.add(pwsShape);
        this.slidePane.getChildren().add(pwsShape.getPwsMedia());
    }

    public void add(PWSImage pwsImage) {
        this.pwsMediaArrayList.add(pwsImage);
        this.slidePane.getChildren().add(pwsImage.getPwsMedia());
    }

    public void add(PWSAudio pwsAudio) {
        this.pwsMediaArrayList.add(pwsAudio);
//        this.slidePane.getChildren().add(pwsAudio.getPwsMedia());
    }

    public void add(PWSVideo pwsVideo) {
        this.pwsMediaArrayList.add(pwsVideo);
        this.slidePane.getChildren().add(pwsVideo.getPwsMedia());
    }

    public void add(ContractVideo contractVideo) {
        this.pwsMediaArrayList.add(contractVideo);
        this.slidePane.getChildren().add(contractVideo.getPwsMedia());
    }

    public String toString() {
        return "PWSSlide:\nid = " + this.id;
    }
}
