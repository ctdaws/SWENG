import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

/**
 * This class is used to create the Presentation object, including the internal structure and style.
 *
 * @author Oscar Thorpe, Chris Dawson, Matt Holt, Ben Grainger
 */
public class LQPresentation {

    private ArrayList<PWSMeta> pwsMetaArrayList;

//  Arrays containing level objects and progress value for each level
    private ArrayList<LQLevel> lqLevelListArrayList;
    private ArrayList<Integer> lqLevelProgressArrayList;

//  Presentation style objects
    private PWSFonts pwsFonts;
    private PWSColors pwsColors;

    public Pane pane;

    public PWSAudio currentAudio;

//  Slide data
    protected String nextSlideID;
    protected LQSlide feedback, end, menu, analytics;
    protected int fVal = 0;
    private PWSText feedbackText;
    private LQButton sadBtn, confusedBtn, happyBtn;
    public BarChart<String, Number> answersChart;
    public BarChart<String, Number> feedbackChart;
    public PWSImage correctAnswerImage;
    public PWSText correctAnswerText;

    /**
     * Constructor for Presentation object
     * @param pwsFonts object defining presentation fonts
     * @param pwsColors object defining presentation colors
     */
    public LQPresentation(PWSFonts pwsFonts, PWSColors pwsColors) {
        this.pwsFonts = pwsFonts;
        this.pwsColors = pwsColors;
        this.pwsMetaArrayList = new ArrayList<>();
        this.lqLevelListArrayList = new ArrayList<>();
        this.lqLevelProgressArrayList = new ArrayList<>();

        createDefaultSlides();

        this.pane = new Pane();
        this.pane.setMinWidth(1280);
        this.pane.setMinHeight(720 - 165);
    }

    /**
     * Gets presentation fonts
     * @return fonts object
     */
    public PWSFonts getPwsFonts() { return pwsFonts; }

    /**
     * Gets presentation colors
     * @return colors object
     */
    public PWSColors getPwsColors() { return pwsColors; }

    /**
     * TODO ----
     * @param pwsMeta
     */
    public void add(PWSMeta pwsMeta) { this.pwsMetaArrayList.add(pwsMeta); }

    /**
     * TODO ----
     * @param key
     * @return pwsMeta
     */
    public PWSMeta getPwsMetaByKey(String key) {
        for(PWSMeta pwsMeta : pwsMetaArrayList) {
            if(pwsMeta.getKey().equals(key)) {
                return pwsMeta;
            }
        }
        return null;
    }

    /**
     * Adds a level to the presentation
     * @param lqLevel level object containing question and example slide sets
     */
    public void add(LQLevel lqLevel) {
        this.lqLevelListArrayList.add(lqLevel);
        this.lqLevelProgressArrayList.add(0);
    }

    /**
     * Gets array of level objects
     * @return lqLevelListArrayList Array of level objects
     */
    public ArrayList<LQLevel> getLqLevelArray() { return this.lqLevelListArrayList; }

    /**
     * Gets array of level progress values
     * @return lqLevelProgressArrayList Array of level progress values
     */
    public ArrayList<Integer> getLqProgressArray() { return this.lqLevelProgressArrayList; }

    /**
     * Gets the slide object specified by the given ID
     * @param id String, slide ID
     * @return currentSlide, the current slide object
     */
    public LQSlide getSlideByID(String id) {
        LQSlide currentSlide;
        switch(id){
            case "menu":
                currentSlide = menu;
                break;
            case "analytics":
                currentSlide = analytics;
                break;
            case "feedback":
                currentSlide = feedback;
                break;
            case "end":
                currentSlide = end;
                break;
            default:
                String idArray[] = id.split("/");
                currentSlide = this.getLqLevelArray().get(Integer.parseInt(idArray[0])-1).getLqQuestionArray().get(Integer.parseInt(idArray[1])).getLqSlideArray().get(Integer.parseInt(idArray[2])-1);
                break;
        }
        return currentSlide;
    }

    /**
     * Creates the slides that are repeated in all presentations
     */
    public void createDefaultSlides(){
        this.feedback = CreateFeedbackSlide();
        this.end = createEndSlide();
        this.menu = createMenuSlide();
        this.analytics = createAnalyticsSlide();
    }

    /**
     * Creates the feedback slide
     * @return feedback, slide object
     */
    private LQSlide CreateFeedbackSlide(){
        LQSlide feedback = new LQSlide("feedback", "F", this.pwsFonts, this.pwsColors, new PWSTransitions("0", -1));
        PWSText feedbackText = new PWSText("textF", new PWSPosition(0, 100, 1280, 720), new PWSTransitions("0", -1), new PWSFonts("Bebas Neue Regular", false, false, false, 70, "center"), this.pwsColors);
        feedbackText.add("How confident did you feel with that question?");
        this.sadBtn = new LQButton("sadBtn", new PWSPosition(200, 300, 400, 500), new PWSTransitions("0", -1), this.getClass().getResource("sad.png").toExternalForm());
        this.confusedBtn = new LQButton("confusedBtn", new PWSPosition(540, 300, 740, 500), new PWSTransitions("0", -1), this.getClass().getResource("confused.png").toExternalForm());
        this.happyBtn = new LQButton("happyBtn", new PWSPosition(880, 300, 1080, 500), new PWSTransitions("0", -1), this.getClass().getResource("smiling.png").toExternalForm());
        setFeedbackButtonOpacity(0.8, 0.8, 0.8);

        sadBtn.getLQButton().setOnAction(event -> {
            fVal = -1;
            setFeedbackButtonOpacity(1, 0.4, 0.4);
        });

        confusedBtn.getLQButton().setOnAction(event -> {
            fVal = 0;
            setFeedbackButtonOpacity(0.4, 1, 0.4);
        });

        happyBtn.getLQButton().setOnAction(event -> {
            fVal = 1;
            setFeedbackButtonOpacity(0.4, 0.4, 1);
        });

        feedback.add(sadBtn);
        feedback.add(confusedBtn);
        feedback.add(happyBtn);
        feedback.add(feedbackText);
        return feedback;
    }

    /**
     * Sets the opacity of the buttons on the feedback slide
     * @param sad sad button
     * @param confused confused button
     * @param happy happy button
     */
    public void setFeedbackButtonOpacity(double sad, double confused, double happy){
        sadBtn.getLQButton().setOpacity(sad);
        confusedBtn.getLQButton().setOpacity(confused);
        happyBtn.getLQButton().setOpacity(happy);
    }

    /**
     * Resets the feedback slide to default state
     */
    public void resetFeedbackButtons(){
        setFeedbackButtonOpacity(0.8, 0.8, 0.8);
        this.fVal = 0;
    }

    /**
     * Creates the end of quest slide
     * @return end, slide object
     */
    private LQSlide createEndSlide(){
        LQSlide end = new LQSlide("end", "E", this.pwsFonts, this.pwsColors, new PWSTransitions("0", -1));
        PWSText endText = new PWSText("textE", new PWSPosition(0, 100, 1280, 720), new PWSTransitions("0", -1), new PWSFonts("Bebas Neue Regular", false, false, false, 70, "center"), this.pwsColors);
        endText.add("You completed the Quest!");

        end.add(endText);
        return end;
    }

    /**
     * Creates the start screen slide
     * @return menu, slide object
     */
    private LQSlide createMenuSlide(){
        LQSlide menu = new LQSlide("menu", "M", this.pwsFonts, this.pwsColors, new PWSTransitions("0", -1));
        PWSText menuText = new PWSText("textE", new PWSPosition(0, 100, 1280, 720), new PWSTransitions("0", -1), new PWSFonts("Bebas Neue Regular", false, false, false, 70, "center"), this.pwsColors);
        menuText.add("Lecture Quest Demo Content Pack");

        PWSImage qrCode = new PWSImage("qrCode", new PWSPosition(490, 200, 790, 500), new PWSTransitions("0", -1), "QR_black_shield.png");
        menu.add(qrCode);

        PWSText websiteText = new PWSText("textE", new PWSPosition(0, 510, 1280, 560), new PWSTransitions("0", -1), new PWSFonts("Calibri", false, false, false, 30, "center"), this.pwsColors);
        websiteText.add("lecturequest.york.ac.uk");

        menu.add(websiteText);
        menu.add(menuText);
        return menu;
    }

    /**
     * Creates the analytics slide to display data from web server
     * @return analytics, slide object
     */
    private LQSlide createAnalyticsSlide() {
        LQSlide analytics = new LQSlide("analytics", "An", this.pwsFonts, this.pwsColors, new PWSTransitions("0", -1));
        CategoryAxis ansXAxis = new CategoryAxis();
        CategoryAxis feedXAxis = new CategoryAxis();
        NumberAxis ansYAxis = new NumberAxis();
        ansYAxis.setTickUnit(1);
        ansYAxis.setMinorTickVisible(false);
        NumberAxis feedYAxis = new NumberAxis();
        feedYAxis.setTickUnit(1);
        feedYAxis.setMinorTickVisible(false);
        this.answersChart = new BarChart<String, Number>(ansXAxis, ansYAxis);
        this.feedbackChart = new BarChart<String, Number>(feedXAxis, feedYAxis);
        this.correctAnswerImage = new PWSImage("correctAnswerImage", new PWSPosition(390, 15, 890, 88), new PWSTransitions("0", -1), "answer_1.png");
        PWSImage correctAnswerTick = new PWSImage("correctAnswerTick", new PWSPosition(380, 0, 480, 100), new PWSTransitions("0", -1), "correct.png");
        this.correctAnswerText = new PWSText("correctAnswerText", new PWSPosition(390, 40, 890, 113), new PWSTransitions("0", -1), new PWSFonts("Arial", false, false, false, 50, "center"), this.pwsColors);

        this.answersChart.setTitle("Answers");
        this.answersChart.setLayoutY(100.0);
        this.answersChart.setMinHeight(250.0);
        this.answersChart.setMaxHeight(250.0);
        this.answersChart.setMinWidth(1280.0);
        this.answersChart.setMaxWidth(1280.0);
        this.answersChart.getXAxis().setTickLabelsVisible(false);
        this.answersChart.getXAxis().setTickMarkVisible(false);
        this.answersChart.setVerticalGridLinesVisible(false);
        this.answersChart.setLegendVisible(false);

        this.feedbackChart.setTitle("Feedback");
        this.feedbackChart.setLayoutY(350.0);
        this.feedbackChart.setMinHeight(250.0);
        this.feedbackChart.setMaxHeight(250.0);
        this.feedbackChart.setMinWidth(1280.0);
        this.feedbackChart.setMaxWidth(1280.0);
        this.feedbackChart.getXAxis().setTickLabelsVisible(false);
        this.feedbackChart.getXAxis().setTickMarkVisible(false);
        this.feedbackChart.setVerticalGridLinesVisible(false);
        this.feedbackChart.setLegendVisible(false);
        analytics.add(answersChart);
        analytics.add(feedbackChart);
        analytics.add(correctAnswerImage);
        analytics.add(correctAnswerTick);
        analytics.add(this.correctAnswerText);

        return  analytics;
    }
}
