import java.util.ArrayList;

public class LQPresentation {

    private ArrayList<PWSMeta> pwsMetaArrayList;

    private ArrayList<LQLevel> lqLevelListArrayList;
    private ArrayList<Integer> lqLevelProgressArrayList;

    private PWSFonts pwsFonts;
    private PWSColors pwsColors;

    public LQPresentation(PWSFonts pwsFonts, PWSColors pwsColors) {
        this.pwsFonts = pwsFonts;
        this.pwsColors = pwsColors;
        this.pwsMetaArrayList = new ArrayList<>();
        this.lqLevelListArrayList = new ArrayList<>();
        this.lqLevelProgressArrayList = new ArrayList<>();
    }

    public PWSFonts getPwsFonts() { return pwsFonts; }

    public PWSColors getPwsColors() { return pwsColors; }

    public void add(PWSMeta pwsMeta) { this.pwsMetaArrayList.add(pwsMeta); }

    public void add(LQLevel lqLevel) {
        this.lqLevelListArrayList.add(lqLevel);
        this.lqLevelProgressArrayList.add(0);
    }
}
