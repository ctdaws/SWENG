import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * Object for holding media as described in the PWS
 *
 * @author Samuel Broughton, Chris Dawson
 * @version 1.4
 */
public class PWSSlide {

//    ArrayList of media objects
    private ArrayList<PWSMedia> pwsMediaArrayList;
//    Slide ID
    private String id;
//    Pane generated containing all visual media objects
    private Pane slidePane;

//    Default font formatting/colours
    private PWSFonts pwsFonts;
    private PWSColors pwsColors;
//    Slide transition timings
    private PWSTransitions pwsTransitions;

    /**
     * Constructor for new Slide onject
     * @param id ID for identifying slide
     * @param pwsFonts Default font formatting
     * @param pwsColors Default colours
     * @param pwsTransitions Slide transition timings
     */
    PWSSlide(String id, PWSFonts pwsFonts, PWSColors pwsColors, PWSTransitions pwsTransitions) {
        this.id = id;
//        Create new ArrayList
        this.pwsMediaArrayList = new ArrayList<>();
        this.pwsFonts = pwsFonts;
        this.pwsColors = pwsColors;
        this.pwsTransitions = pwsTransitions;
//        Create new Pane
        this.slidePane = new Pane();
    }

    /**
     * Getter for pane
     * @return Pane populated with visual media objects
     */
    public Pane getSlidePane() { return this.slidePane; }

    /**
     * Getter for slide ID
     * @return String ID for the slide
     */
    public String getPWSSlideId() { return this.id; }

    /**
     * Getter for slide default font formatting
     * @return font formatting defaults in PWSFonts onbject
     */
    public PWSFonts getPwsFonts() { return this.pwsFonts; }

    /**
     * Getter for slide default colours
     * @return colour defaults in PWSColor object
     */
    public PWSColors getPwsColors() { return pwsColors; }

    /**
     * Getter for slide transition timings
     * @return transition timings
     */
    public PWSTransitions getPwsTransitions() { return pwsTransitions; }

    /**
     * Method for playing all media objects initial keyframes, usually when the slide is loaded
     */
    public void startTransitions() { for(PWSMedia pwsMedia : pwsMediaArrayList) { pwsMedia.getTimeline().playFrom("auto"); } }

    /**
     * Method for stopping all media objects, usually when the slide is unloaded
     */
    public void endTransitions() {
//        Iterate through media ArrayList
        for(PWSMedia pwsMedia : pwsMediaArrayList) {
//            If the media is a type of audio or video, stop the media
            if(pwsMedia instanceof PWSAudio) {
                ((PWSAudio) pwsMedia).stop();
            }
            if(pwsMedia instanceof PWSVideo) {
                ((PWSVideo) pwsMedia).stop();
            }
            if(pwsMedia instanceof ContractVideo) {
                ((ContractVideo) pwsMedia).getPwsVideo().stop();
            }
//            Stop the timeline
            pwsMedia.getTimeline().stop();
        }
    }

    /**
     * Method for adding text to the slide
     * @param pwsText text object to add to the slide
     */
    public void add(PWSText pwsText) {
//        Add the text object to the media arrayList
        this.pwsMediaArrayList.add(pwsText);
//        Add the TextFlow to the Pane
        this.slidePane.getChildren().add(pwsText.getPwsMedia());
    }

    /**
     * Method for adding shapes to the slide
     * @param pwsShape shape object to add to the slide
     */
    public void add(PWSShape pwsShape) {
//        Add the shape object to the media arrayList
        this.pwsMediaArrayList.add(pwsShape);
//        Add the Shape to the Pane
        this.slidePane.getChildren().add(pwsShape.getPwsMedia());
    }

    /**
     * Method for adding images to the slide
     * @param pwsImage image object to be added to the slide
     */
    public void add(PWSImage pwsImage) {
//        Add the image object to the media ArrayList
        this.pwsMediaArrayList.add(pwsImage);
//        Add the ImageView to the Pane
        this.slidePane.getChildren().add(pwsImage.getPwsMedia());
    }

    /**
     * Method for adding audio to the slide
     * @param pwsAudio audio object to be added to the slide
     */
    public void add(PWSAudio pwsAudio) {
//        Add the audio object to the media ArrayList
        this.pwsMediaArrayList.add(pwsAudio);
//        Does not need adding to the Pane as there is no visual
    }

    /**
     * Method for adding video to the slide. This video has no controls and can only be triggered by transitions.
     * @param pwsVideo video object to be added to the slide
     */
    public void add(PWSVideo pwsVideo) {
//        Add video object to the media ArrayList
        this.pwsMediaArrayList.add(pwsVideo);
//        Add MediaPlayerView to the Pane
        this.slidePane.getChildren().add(pwsVideo.getPwsMedia());
    }

    /**
     * Method for adding ContractVideo to the slide. This video has the option of adding controls
     * @param contractVideo video object to e added to the slide
     */
    public void add(ContractVideo contractVideo) {
//        Add video to the media ArrayList
        this.pwsMediaArrayList.add(contractVideo);
//        Add the VBox to the Pane
        this.slidePane.getChildren().add(contractVideo.getPwsMedia());
    }

    /**
     * Override of toString method
     * @return String containing information about the slide
     */
    @Override
    public String toString() {
        return "PWSSlide:\nid = " + this.id;
    }
}
