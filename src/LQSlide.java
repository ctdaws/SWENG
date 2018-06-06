import javafx.scene.chart.BarChart;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * This class is used to create slide objects in the presentation.
 * It contains information about the media objects on the slide and the style.
 * @author Oscar Thorpe, Matt Holt, Ben Grainger
 */
public class LQSlide{

//  Arrays of media objects
    private ArrayList<PWSMedia> pwsMediaArrayList;
    private ArrayList<LQMedia> lqMediaArrayList;

//  Slide parameters
    private String id;
    private String type;

//  Slide style
    private PWSFonts pwsFonts;
    private PWSColors pwsColors;
    private PWSTransitions pwsTransitions;

//  Display pane which media objects are added to
    private Pane slidePane;

//  Answer slides
    private Boolean answered = false;
    private String answerNum;
    private Boolean gotAnswerCorrect = false;
    private Boolean[] correctArray;
    private LQButton[] buttonArray;
    private PWSAudio incorrectAudio, correctAudio;
    protected PWSImage correctImage, incorrectImage0, incorrectImage1, incorrectImage2, incorrectImage3;

    /**
     * Constructor for slide objects
     * @param id string, slide ID
     * @param type String, the slide type (e.g. Q for question, X for example)
     * @param pwsFonts fonts object
     * @param pwsColors colors object
     * @param pwsTransitions transitions object
     */
    public LQSlide(String id, String type, PWSFonts pwsFonts, PWSColors pwsColors, PWSTransitions pwsTransitions) {
        this.id = id;
        this.type = type;
        this.pwsMediaArrayList = new ArrayList<>();
        this.lqMediaArrayList = new ArrayList<>();
        this.pwsFonts = pwsFonts;
        this.pwsColors = pwsColors;
        this.pwsTransitions = pwsTransitions;
        this.slidePane = new Pane();

        //answer slide
        this.correctArray = new Boolean[4];
        this.answerNum = answerNum;

//        this.correctAudio = new PWSAudio(" correct jingle", new PWSPosition(0, 0, 0, 0), new PWSTransitions("trigger", 0), "CORRECT.mp3");
//        this.add(this.correctAudio);
//
//        this.incorrectAudio = new PWSAudio(" incorrect jingle", new PWSPosition(0, 0, 0, 0), new PWSTransitions("trigger", 0), "INCORRECT.wav");
//        this.add(this.incorrectAudio);
    }

    /**
     * Gets the slide pane object
     * @return slidePane
     */
    public Pane getSlidePane() {
        return this.slidePane;
    }

    /**
     * Gets the ID of this slide
     * @return id
     */
    public String getLQSlideId() { return id; }

    /**
     * Gets the slide type
     * @return type
     */
    public String getLQSlideType() { return this.type; }

    /**
     * Gets the slide fonts object
     * @return pwsFonts
     */
    public PWSFonts getPwsFonts() { return pwsFonts; }

    /**
     * Gets the slide colors object
     * @return pwsColors
     */
    public PWSColors getPwsColors() { return pwsColors; }

    /**
     * Gets the slide transitions object
     * @return pwsTransitions
     */
    public PWSTransitions getPwsTransitions() { return pwsTransitions; }

    /**
     * Plays slide transitions
     */
    public void startTransitions() {
        for(PWSMedia pwsMedia : pwsMediaArrayList) {
            pwsMedia.getTimeline().playFrom("auto");
        }
        for(LQMedia lqMedia : lqMediaArrayList) {
            lqMedia.getTimeline().playFrom("auto");
        }
    }

    /**
     * Stops slide transitions
     */
    public void endTransitions() {
        for(PWSMedia pwsMedia : pwsMediaArrayList) {
            if(pwsMedia instanceof PWSAudio) {
                ((PWSAudio) pwsMedia).stop();
            }
            if(pwsMedia instanceof PWSVideo) {
                ((PWSVideo) pwsMedia).stop();
            }
            if(pwsMedia instanceof ContractVideo) {
                ((ContractVideo) pwsMedia).getPwsVideo().stop();
            }
            pwsMedia.getTimeline().stop();
        }
        for(LQMedia lqMedia : lqMediaArrayList) {
            lqMedia.getTimeline().stop();
        }
    }

    /**
     * Adds text to the slide
     * @param pwsText PWSText object
     */
    public void add(PWSText pwsText) {
        this.pwsMediaArrayList.add(pwsText);
        this.slidePane.getChildren().add(pwsText.getPwsMedia());
    }

    /**
     * Adds a shape to the slide
     * @param pwsShape PWSShape object
     */
    public void add(PWSShape pwsShape) {
        this.pwsMediaArrayList.add(pwsShape);
        this.slidePane.getChildren().add(pwsShape.getPwsMedia());
    }

    /**
     * Adds an image to the slide
     * @param pwsImage PWSImage object
     */
    public void add(PWSImage pwsImage) {
        this.pwsMediaArrayList.add(pwsImage);
        this.slidePane.getChildren().add(pwsImage.getPwsMedia());
    }

    /**
     * Adds audio to the slide
     * @param pwsAudio PWSAudio object
     */
    public void add(PWSAudio pwsAudio) {
        this.pwsMediaArrayList.add(pwsAudio);
    }

    /**
     * Adds a video to the slide
     * @param pwsVideo PWSVideo Object
     */
    public void add(PWSVideo pwsVideo) {
        this.pwsMediaArrayList.add(pwsVideo);
        this.slidePane.getChildren().add(pwsVideo.getPwsMedia());
    }

    /**
     * Adds a video using the contracted video module
     * @param contractVideo ContractVideo object
     */
    public void add(ContractVideo contractVideo) {
        this.pwsMediaArrayList.add(contractVideo);
        this.slidePane.getChildren().add(contractVideo.getPwsMedia());
    }

    /**
     * Adds a button to the slide
     * @param lqButton LQButton object
     */
    public void add(LQButton lqButton) {
        this.lqMediaArrayList.add(lqButton);
        this.slidePane.getChildren().add(lqButton.getLQMedia());
    }

    /**
     * Adds a bar chart to the slide
     * @param bc BarChart object with String and Number axis
     */
    public void add(BarChart<String, Number> bc) {
        this.slidePane.getChildren().add(bc);
    }

    /**
     * Mutes audio on the slide
     * @param mute Boolean: mute is true if audio should be muted
     */
    public void muteAudio(boolean mute) {
        for(PWSMedia pwsMedia : pwsMediaArrayList) {
            if(pwsMedia instanceof PWSAudio) {
                ((PWSAudio) pwsMedia).mute(mute);
            }
            if(pwsMedia instanceof PWSVideo) {
                ((PWSVideo) pwsMedia).mute(mute);
            }
            if(pwsMedia instanceof ContractVideo) {
                ((ContractVideo) pwsMedia).getPwsVideo().mute(mute);
            }
        }
    }

    /**
     * Gets the ID of the current answer number
     * @return answerNum, String: the ID of the current answer number
     */
    public String getAnswerNum() { return this.answerNum; }

    public void setCorrectArray(Boolean correct, Integer answerNum){
        this.correctArray[answerNum] = correct;
    }

    /**
     * Gets array of Booleans defining which answer is correct
     * @return correctArray
     */
    public Boolean[] getCorrectArray() { return correctArray; }

    /**
     * Gets the correct answer number
     * @return int, the value of the correct answer (1, 2, 3, 4)
     */
    public int getCorrectAnswerNum() {
        for(int i = 0; i < correctArray.length; i++) {
            if(correctArray[i]) {
                return i + 1;
            }
        }
        return 0;
    }

    /**
     * Sets the action listeners for the answer buttons
     */
    protected void setActionListeners() {
        LQButton button1;
        LQButton button2;
        LQButton button3;
        LQButton button4;

        this.buttonArray = new LQButton[4];
        int numberOfButtons = 0;

        for (LQMedia lqMediaArrayList : this.lqMediaArrayList) {
            if (lqMediaArrayList instanceof LQButton) {
                buttonArray[numberOfButtons++] = (LQButton) lqMediaArrayList;
            }
        }

        button1 = buttonArray[0];
        button1.getLQButton().setOnMouseClicked((clickEvent) -> {
            if(!this.getAnswered()) {
                this.setAnswered(true);
                checkCorrect(this.getCorrectArray(), 0);
                button1.getButtonTriggerAudio().trigger();
                button1.getButtonTriggerImage().trigger();
            }
        });

        button2 = buttonArray[1];
        button2.getLQButton().setOnMouseClicked((clickEvent) -> {
            if(!this.getAnswered()) {
                this.setAnswered(true);
                checkCorrect(this.getCorrectArray(), 1);
                button2.getButtonTriggerAudio().trigger();
                button2.getButtonTriggerImage().trigger();
            }
        });

        button3 = buttonArray[2];
        button3.getLQButton().setOnMouseClicked((clickEvent) -> {
            if(!this.getAnswered()) {
                this.setAnswered(true);
                checkCorrect(this.getCorrectArray(), 2);
                button3.getButtonTriggerAudio().trigger();
                button3.getButtonTriggerImage().trigger();
            }
        });

        button4 = buttonArray[3];
        button4.getLQButton().setOnMouseClicked((clickEvent) -> {
            if(!this.getAnswered()) {
                this.setAnswered(true);
                checkCorrect(this.getCorrectArray(), 3);
                button4.getButtonTriggerAudio().trigger();
                button4.getButtonTriggerImage().trigger();
            }
        });
    }

    /**
     * Sets the gotAnswerCorrect Boolean to define if this question was answered correctly
     * @param correct Boolean: correct is true if the answer given was correct
     */
    private void setGotAnswerCorrect(Boolean correct) { this.gotAnswerCorrect = correct; }

    /**
     * Sets the answered Boolean to define if this question has been answered
     * @param answered Boolean: answered is true if the question has been answered
     */
    private void setAnswered(Boolean answered) { this.answered = answered; }

    /**
     * Sets the current answer ID
     * @param answerNum
     */
    public void setAnswerNum(String answerNum) { this.answerNum = answerNum; }

    /**
     * Gets gotAnswerCorrect Boolean
     * @return gotAnswerCorrect Boolean: gotAnswerCorrect is true if the user answered correctly
     */
    public Boolean getGotAnswerCorrect() { return this.gotAnswerCorrect; }

    /**
     * Gets answered Boolean
     * @return answered Boolean: answered is true if the question has been answered
     */
    public Boolean getAnswered() { return this.answered; }

    /**
     * Resets answer Booleans to default values
     */
    public void resetAnswer(){
        setAnswered(false);
        setGotAnswerCorrect(false);
    }

    /**
     * Checks if the question was answered correctly
     * @param correctArray array of Booleans defining which answer is correct
     * @param answerNumInt int value for the answer that was selected
     */
    private void checkCorrect(Boolean[] correctArray, Integer answerNumInt) {
        if (correctArray[answerNumInt]) {
            this.setGotAnswerCorrect(true);
        }
        else{
            this.setGotAnswerCorrect(false);
        }
    }

    /**
     * Gets array of answer buttons
     * @return buttonArray
     */
    public LQButton[] getButtonArray(){
        return this.buttonArray;
    }

    /**
     * Gets the ID and type
     * @return String including slide ID and type
     */
    @Override
    public String toString() {
        return "PWSSlide:\nid = " + this.id + "\ntype = " + this.type;
    }
}
