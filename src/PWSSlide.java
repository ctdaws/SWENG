import java.util.ArrayList;

public class PWSSlide {

    private ArrayList<PWSMedia> pwsMediaArrayList;
    private String id;

    private PWSFonts pwsFonts;
    private PWSColors pwsColors;
    private PWSTransitions pwsTransitions;

    public PWSSlide(String id, PWSFonts pwsFonts, PWSColors pwsColors, PWSTransitions pwsTransitions) {
        this.id = id;
        this.pwsMediaArrayList = new ArrayList<PWSMedia>();
        this.pwsFonts = pwsFonts;
        this.pwsColors = pwsColors;
        this.pwsTransitions = pwsTransitions;
    }

    public String getPWSSlideId() { return this.id; }

    public PWSFonts getPwsFonts() { return this.pwsFonts; }

    public PWSColors getPwsColors() { return pwsColors; }

    public PWSTransitions getPwsTransitions() { return pwsTransitions; }

    public void add(PWSText pwsText) { this.pwsMediaArrayList.add(pwsText); }

    public void add(PWSShape pwsShape) { this.pwsMediaArrayList.add(pwsShape); }

    public void add(PWSImage pwsImage) { this.pwsMediaArrayList.add(pwsImage); }

    public void add(PWSAudio pwsAudio) { this.pwsMediaArrayList.add(pwsAudio); }

//    public void add(PWSVideo pwsVideo) { this.pwsMediaArrayList.add(pwsVideo); }
}
