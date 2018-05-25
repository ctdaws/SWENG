import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class LQSlide{

    private ArrayList<PWSMedia> pwsMediaArrayList;
    private ArrayList<LQMedia> lqMediaArrayList;
    private String id;

    private Pane slidePane;

    private PWSFonts pwsFonts;
    private PWSColors pwsColors;
    private PWSTransitions pwsTransitions;

    public LQSlide(String id, PWSFonts pwsFonts, PWSColors pwsColors, PWSTransitions pwsTransitions) {
        this.id = id;
        this.pwsMediaArrayList = new ArrayList<>();
        this.lqMediaArrayList = new ArrayList<>();
        this.pwsFonts = pwsFonts;
        this.pwsColors = pwsColors;
        this.pwsTransitions = pwsTransitions;
        this.slidePane = new Pane();
    }

    public Pane getSlidePane() {
        return this.slidePane;
    }

    public String getLQSlideId() { return id; }

    public PWSFonts getPwsFonts() { return pwsFonts; }

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

//    public void add(PWSVideo pwsVideo) { this.pwsMediaArrayList.add(pwsVideo); }

    public void muteAudio(boolean mute) {
        for(PWSMedia pwsMedia : pwsMediaArrayList) {
            if(pwsMedia instanceof PWSAudio) {
                ((PWSAudio) pwsMedia).mute(mute);
            }
        }
    }

    public String toString() {
        return "PWSSlide:\nid = " + this.id;
    }
}
