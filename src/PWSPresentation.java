import java.util.ArrayList;

public class PWSPresentation {

    private ArrayList<PWSSlide> pwsSlideArrayList;
    private ArrayList<PWSMeta> pwsMetaArrayList;

    private PWSFonts pwsFonts;
    private PWSColors pwsColors;

    public PWSPresentation(PWSFonts pwsFonts, PWSColors pwsColors) {
        this.pwsFonts = pwsFonts;
        this.pwsColors = pwsColors;
        this.pwsSlideArrayList = new ArrayList<>();
        this.pwsMetaArrayList = new ArrayList<>();
    }

    public ArrayList<PWSSlide> getPwsSlideArrayList() {
        return pwsSlideArrayList;
    }

    public PWSFonts getPwsFonts() { return this.pwsFonts; }

    public PWSColors getPwsColors() { return this.pwsColors; }

    public void add(PWSMeta pwsMeta) { this.pwsMetaArrayList.add(pwsMeta); }

    public PWSMeta getPwsMetaByKey(String key) {
        for(PWSMeta pwsMeta : this.pwsMetaArrayList) {
            if(pwsMeta.getKey().equalsIgnoreCase(key)) { return pwsMeta; }
        }
        return null;
    }

    public void add(PWSSlide pwsSlide) { this.pwsSlideArrayList.add(pwsSlide); }

    public PWSSlide getPwsSlideByID(String id) {
        for(PWSSlide pwsSlide : this.pwsSlideArrayList) {
            if(pwsSlide.getPWSSlideId().equalsIgnoreCase(id)) { return pwsSlide; }
        }
        return null;
    }

    public boolean slideExists(String id) {
        for(PWSSlide pwsSlide : this.pwsSlideArrayList) {
            if(pwsSlide.getPWSSlideId().equalsIgnoreCase(id)) { return true; }
        }
        return false;
    }
}
