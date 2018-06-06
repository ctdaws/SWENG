import java.util.ArrayList;

/**
 * Object holding metadata / slides as described in the PWS
 */
public class PWSPresentation {

//    ArrayList of presentation slides
    private ArrayList<PWSSlide> pwsSlideArrayList;
//    ArrayList of presentation metadata
    private ArrayList<PWSMeta> pwsMetaArrayList;

//    Default font and colours
    private PWSFonts pwsFonts;
    private PWSColors pwsColors;

    /**
     * Constructor for new presentatio object
     * @param pwsFonts default font formatting
     * @param pwsColors default colour/fill
     */
    PWSPresentation(PWSFonts pwsFonts, PWSColors pwsColors) {
        this.pwsFonts = pwsFonts;
        this.pwsColors = pwsColors;
//        Create new ArrayLists
        this.pwsSlideArrayList = new ArrayList<>();
        this.pwsMetaArrayList = new ArrayList<>();
    }

    /**
     * Getter for slide arrayList
     * @return slide ArrayList
     */
    public ArrayList<PWSSlide> getPwsSlideArrayList() {
        return pwsSlideArrayList;
    }

    /**
     * Getter for default font formatting
     * @return default font formatting in PWSFonts object
     */
    public PWSFonts getPwsFonts() { return this.pwsFonts; }

    /**
     * Getter for default colours
     * @return default colour/fill in PWSColors object
     */
    public PWSColors getPwsColors() { return this.pwsColors; }

    /**
     * Method for adding metadata to presentation
     * @param pwsMeta metadata to add in a PWSMeta object
     */
    public void add(PWSMeta pwsMeta) { this.pwsMetaArrayList.add(pwsMeta); }

    /**
     * Searches metadata arrayList for matching key
     * @param key String key to find matching metadata
     * @return PWSMeta with matching key, else returns null
     */
    public PWSMeta getPwsMetaByKey(String key) {
//        Itrate through metadata arrayList
        for(PWSMeta pwsMeta : this.pwsMetaArrayList) {
//            If key of metadata matches input key, return the current metadata
            if(pwsMeta.getKey().equalsIgnoreCase(key)) { return pwsMeta; }
        }
//        Matching meetadata not found
        return null;
    }

    /**
     * Method for adding a new slide to the presentation
     * @param pwsSlide New slide object to add
     */
    public void add(PWSSlide pwsSlide) { this.pwsSlideArrayList.add(pwsSlide); }

    /**
     * Method for getting a slide with a given ID
     * @param id requested slide ID
     * @return PWSSlide with matching ID
     */
    public PWSSlide getPwsSlideByID(String id) {
//        Iterate through slide arrayList
        for(PWSSlide pwsSlide : this.pwsSlideArrayList) {
//            If slide ID matches, return slide
            if(pwsSlide.getPWSSlideId().equalsIgnoreCase(id)) { return pwsSlide; }
        }
//        No slide found with given ID
        return null;
    }

    /**
     * Method for checking that a slide with a given ID exists within the presentation
     * @param id Slide ID to look for
     * @return boolean if a slide matching the given ID exists
     */
    public boolean slideExists(String id) {
//        Iterate through the slide ArrayList
        for(PWSSlide pwsSlide : this.pwsSlideArrayList) {
//            If the given ID matches the slides ID, return true
            if(pwsSlide.getPWSSlideId().equalsIgnoreCase(id)) { return true; }
        }
//        No matching slide was found
        return false;
    }
}
