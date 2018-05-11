import java.util.ArrayList;

public class PWSPresentation {

    private ArrayList<PWSSlide> pwsSlideArrayList;
    private ArrayList<PWSMeta> pwsMetaArrayList;

    private PWSFonts pwsFonts;
    private PWSColors pwsColors;

    public PWSPresentation(PWSFonts pwsFonts, PWSColors pwsColors) {
        this.pwsFonts = pwsFonts;
        this.pwsColors = pwsColors;
        this.pwsSlideArrayList = new ArrayList<PWSSlide>();
        this.pwsMetaArrayList = new ArrayList<PWSMeta>();
    }

    public PWSFonts getPwsFonts() { return this.pwsFonts; }

    public PWSColors getPwsColors() { return this.pwsColors; }

    public void add(PWSMeta pwsMeta) { this.pwsMetaArrayList.add(pwsMeta); }

    public void add(PWSSlide pwsSlide) { this.pwsSlideArrayList.add(pwsSlide); }
}
