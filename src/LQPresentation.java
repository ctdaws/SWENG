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
    protected LQSlide feedback, end, menu;

    protected int fVal = 0;

    private PWSText feedbackText;
    private LQButton sadBtn, confusedBtn, happyBtn;


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
    }

    private LQSlide CreateFeedbackSlide(){
        LQSlide feedback = new LQSlide("feedback", "F", this.pwsFonts, this.pwsColors, new PWSTransitions("trigger", 0));
        PWSText feedbackText = new PWSText("textF", new PWSPosition(0, 100, 1280, 720), new PWSTransitions("trigger", 0), new PWSFonts("Arial", false, false, false, 50, "center"), this.pwsColors);
        feedbackText.add("How confident did you feel with that question?");
        this.sadBtn = new LQButton("sadBtn", new PWSPosition(200, 300, 400, 500), new PWSTransitions("trigger", 0), this.getClass().getResource("sad.png").toExternalForm());
        this.confusedBtn = new LQButton("confusedBtn", new PWSPosition(540, 300, 740, 500), new PWSTransitions("trigger", 0), this.getClass().getResource("confused.png").toExternalForm());
        this.happyBtn = new LQButton("happyBtn", new PWSPosition(880, 300, 1080, 500), new PWSTransitions("trigger", 0), this.getClass().getResource("smiling.png").toExternalForm());
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
        LQSlide end = new LQSlide("end", "E", this.pwsFonts, this.pwsColors, new PWSTransitions("trigger", 0));
        PWSText endText = new PWSText("textE", new PWSPosition(0, 100, 1280, 720), new PWSTransitions("trigger", 0), new PWSFonts("Arial", false, false, false, 50, "center"), this.pwsColors);
        endText.add("You completed the Quest!");
//    FLButton endBtn = new FLButton("endBtn", new Position(565, 300), 150, 50, this.getClass().getResource("button.png").toExternalForm());
//    endBtn.addText("RETURN TO START");

        end.add(endText);
        //end.add(endBtn);
        return end;
    }

    private LQSlide createMenuSlide(){
        LQSlide menu = new LQSlide("menu", "M", this.pwsFonts, this.pwsColors, new PWSTransitions("trigger", 0));
        PWSText menuText = new PWSText("textE", new PWSPosition(0, 100, 1280, 720), new PWSTransitions("trigger", 0), new PWSFonts("Arial", false, false, false, 50, "center"), this.pwsColors);
        menuText.add("Lecture Quest Demo Content Pack");
//    FLButton menuBtn = new FLButton("menuBtn", new Position(565, 300), 150, 50, this.getClass().getResource("button.png").toExternalForm());
//    menuBtn.addText("START QUEST");

        menu.add(menuText);
        //menu.add(menuBtn);
        return menu;
    }
}
