import java.util.ArrayList;

public class LQPresentation {

    private ArrayList<LQSlide> lqSlideArrayList;
    private ArrayList<PWSMeta> pwsMetaArrayList;

    private ArrayList<LQTopic> lqTopicArrayList;
    private ArrayList<LQLevel> lqLevelListArrayList;
    private ArrayList<LQQuestion> lqQuestionArrayList;

    private PWSFonts pwsFonts;
    private PWSColors pwsColors;

    public LQPresentation(PWSFonts pwsFonts, PWSColors pwsColors) {
        this.pwsFonts = pwsFonts;
        this.pwsColors = pwsColors;
        this.lqSlideArrayList = new ArrayList<>();
        this.pwsMetaArrayList = new ArrayList<>();
    }

    public PWSFonts getPwsFonts() { return pwsFonts; }

    public PWSColors getPwsColors() { return pwsColors; }

    public void add(PWSMeta pwsMeta) { this.pwsMetaArrayList.add(pwsMeta); }

    public void add(LQLevel lqLevel) { this.lqLevelListArrayList.add(lqLevel); }

//    public void add(LQExample lqExample) { this.lqLevelListArrayList.add(lqExample); }

    public void add(LQSlide lqSlide) { this.lqSlideArrayList.add(lqSlide); }
}
