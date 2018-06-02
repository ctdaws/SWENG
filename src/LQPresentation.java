import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

public class LQPresentation {

    private ArrayList<PWSMeta> pwsMetaArrayList;

    private ArrayList<LQLevel> lqLevelListArrayList;
    private ArrayList<Integer> lqLevelProgressArrayList;

    private PWSFonts pwsFonts;
    private PWSColors pwsColors;

    public Pane pane;

    public PWSAudio currentAudio;

    protected String nextSlideID;
    protected LQSlide feedback, end, menu, analytics;

    protected int fVal = 0;

    private PWSText feedbackText;
    private LQButton sadBtn, confusedBtn, happyBtn;

    public BarChart<String, Number> answersChart;
    public BarChart<String, Number> feedbackChart;
    public PWSImage correctAnswerImage;


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

    public PWSFonts getPwsFonts() { return pwsFonts; }

    public PWSColors getPwsColors() { return pwsColors; }

    public void add(PWSMeta pwsMeta) { this.pwsMetaArrayList.add(pwsMeta); }

    public PWSMeta getPwsMetaByKey(String key) {
        for(PWSMeta pwsMeta : pwsMetaArrayList) {
            if(pwsMeta.getKey().equals(key)) {
                return pwsMeta;
            }
        }
        return null;
    }

    public void add(LQLevel lqLevel) {
        this.lqLevelListArrayList.add(lqLevel);
        this.lqLevelProgressArrayList.add(0);
    }

    public ArrayList<LQLevel> getLqLevelArray() { return this.lqLevelListArrayList; }

    public ArrayList<Integer> getLqProgressArray() { return this.lqLevelProgressArrayList; }

    public LQSlide getSlideByID(String id) {
        LQSlide currentSlide;
        //AnswerSlide currentAnswerSlide;
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
//       case "A":
//         String idArray[] = id.split("/");
//         currentAnswerSlide = this.lArray.get(Integer.parseInt(idArray[0])-1).qArray.get(Integer.parseInt(idArray[1])).slideArray.get(Integer.parseInt(idArray[2])-1);
//         return currentAnswerSlide;
//          break;
            default:
                String idArray[] = id.split("/");
                currentSlide = this.getLqLevelArray().get(Integer.parseInt(idArray[0])-1).getLqQuestionArray().get(Integer.parseInt(idArray[1])).getLqSlideArray().get(Integer.parseInt(idArray[2])-1);
                break;
        }
        return currentSlide;
    }

    public void createDefaultSlides(){
        this.feedback = CreateFeedbackSlide();
        this.end = createEndSlide();
        this.menu = createMenuSlide();
        this.analytics = createAnalyticsSlide();
    }

    private LQSlide CreateFeedbackSlide(){
        LQSlide feedback = new LQSlide("feedback", "F", this.pwsFonts, this.pwsColors, new PWSTransitions("0", -1));
        PWSText feedbackText = new PWSText("textF", new PWSPosition(0, 100, 1280, 720), new PWSTransitions("0", -1), new PWSFonts("Arial", false, false, false, 50, "center"), this.pwsColors);
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

    public void setFeedbackButtonOpacity(double sad, double confused, double happy){
        sadBtn.getLQButton().setOpacity(sad);
        confusedBtn.getLQButton().setOpacity(confused);
        happyBtn.getLQButton().setOpacity(happy);
    }

    public void resetFeedbackButtons(){
        setFeedbackButtonOpacity(0.8, 0.8, 0.8);
        this.fVal = 0;
    }

    private LQSlide createEndSlide(){
        LQSlide end = new LQSlide("end", "E", this.pwsFonts, this.pwsColors, new PWSTransitions("0", -1));
        PWSText endText = new PWSText("textE", new PWSPosition(0, 100, 1280, 720), new PWSTransitions("0", -1), new PWSFonts("Arial", false, false, false, 50, "center"), this.pwsColors);
        endText.add("You completed the Quest!");
//    FLButton endBtn = new FLButton("endBtn", new Position(565, 300), 150, 50, this.getClass().getResource("button.png").toExternalForm());
//    endBtn.addText("RETURN TO START");

        end.add(endText);
        //end.add(endBtn);
        return end;
    }

    private LQSlide createMenuSlide(){
        LQSlide menu = new LQSlide("menu", "M", this.pwsFonts, this.pwsColors, new PWSTransitions("0", -1));
        PWSText menuText = new PWSText("textE", new PWSPosition(0, 100, 1280, 720), new PWSTransitions("0", -1), new PWSFonts("Arial", false, false, false, 50, "center"), this.pwsColors);
        menuText.add("Lecture Quest Demo Content Pack");
//    FLButton menuBtn = new FLButton("menuBtn", new Position(565, 300), 150, 50, this.getClass().getResource("button.png").toExternalForm());
//    menuBtn.addText("START QUEST");

        menu.add(menuText);
        //menu.add(menuBtn);
        return menu;
    }

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

        this.answersChart.setTitle("Answers");
        this.answersChart.setLayoutY(100.0);
        this.answersChart.setMinHeight(250.0);
        this.answersChart.setMaxHeight(250.0);
        this.answersChart.setMinWidth(1280.0);
        this.answersChart.setMaxWidth(1280.0);
        this.answersChart.getXAxis().setTickLabelsVisible(false);
        this.answersChart.getXAxis().setTickMarkVisible(false);
        this.answersChart.setVerticalGridLinesVisible(false);
        //this.answersChart.setCategoryGap(50.0);
        //this.answersChart.setBarGap(0.0);
        this.answersChart.setLegendVisible(false);

        this.feedbackChart.setTitle("Feedback");
        this.feedbackChart.setLayoutY(350.0);
        this.feedbackChart.setMinHeight(250.0);
        this.feedbackChart.setMaxHeight(250.0);
        this.feedbackChart.setMinWidth(1280.0);
        this.feedbackChart.setMaxWidth(1280.0);
        //this.feedbackChart.getYAxis().setTickLabelGap(1.0);
        this.feedbackChart.setVerticalGridLinesVisible(false);
        //this.feedbackChart.setCategoryGap(50.0);
        //this.feedbackChart.setBarGap(0.0);
        this.feedbackChart.setLegendVisible(false);
        analytics.add(answersChart);
        analytics.add(feedbackChart);
        analytics.add(correctAnswerImage);
        analytics.add(correctAnswerTick);

        return  analytics;
    }
}
