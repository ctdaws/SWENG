import java.util.ArrayList;

public class AnswerSlide extends Slide {

    //    private Integer answernum;
//    private Boolean correct;
//    private Boolean answered = false;//, correct = false;
    private FLButton button1;
    private FLButton button2;
    private FLButton button3;
    private FLButton button4;
    private FLAudio incorrectAudio, correctAudio;
    private FLImage correctImage, incorrectImage1, incorrectImage2, incorrectImage3;
//    private Boolean[] correctArray;

//    public AnswerSlide(String id, String type) {
//        super(id, type,);
//
//        //displayAnswers();
//    }

    public AnswerSlide(String id, String type) {//}, Integer answernum, Boolean correct){
        super(id, type);
        //System.out.println(" Answers Constructed");
        this.answerNum = answerNum;
        this.correct = correct;
        this.answerNumInt = answerNumInt;
        displayAnswers();
    }

    public void displayAnswers() {

        button1 = new FLButton(id,
                new Position(100, 350), 490, 185, this.getClass().getResource("answer_flag_1.png").toExternalForm());
        button1.addText("Answer 1");
        //button1.getMedia().getStyleClass().add("answer 1");
        this.add(button1);


        button2 = new FLButton(id,
                new Position(690, 350), 490, 185, this.getClass().getResource("answer_flag_2.png").toExternalForm());
        button2.addText("Answer 2");
        this.add(button2);

        button3 = new FLButton(id,
                new Position(100, 535), 490, 185, this.getClass().getResource("answer_flag_3.png").toExternalForm());
        button3.addText("Answer 3");
        this.add(button3);

        button4 = new FLButton(id,
                new Position(690, 535), 490, 185, this.getClass().getResource("answer_flag_4.png").toExternalForm());
        button4.addText("Answer 4");
        this.add(button4);

        System.out.println("printed Answers Buttons");

        correctAudio = new FLAudio(" correct jingle", "CORRECT.mp3", new Position(100, 100));
        this.add(correctAudio);

        incorrectAudio = new FLAudio(" incorrect jingle", "INCORRECT.wav", new Position(200, 200));
        this.add(incorrectAudio);

        correctImage = new FLImage("correct image", "correct.png", new Position(100, 350), 490, 185, false);
        this.add(correctImage);

        incorrectImage1 = new FLImage("correct image", "incorrect.png", new Position(690, 350), 490, 185, false);
        this.add(incorrectImage1);

        incorrectImage2 = new FLImage("correct image", "incorrect.png", new Position(100, 535), 490, 185, false);
        this.add(incorrectImage2);

        incorrectImage3 = new FLImage("correct image", "incorrect.png", new Position(690, 535), 490, 185, false);
        this.add(incorrectImage3);


        this.button1.getButton().setOnMouseClicked((clickEvent) -> {
            super.setAnswered(true);
            checkCorrect(super.getCorrectArray(), 0);
        });
        this.button2.getButton().setOnMouseClicked((clickEvent) -> {
            super.setAnswered(true);
            checkCorrect(super.getCorrectArray(), 1);
        });
        this.button3.getButton().setOnMouseClicked((clickEvent) -> {
            super.setAnswered(true);
            checkCorrect(super.getCorrectArray(), 2);
        });
        this.button4.getButton().setOnMouseClicked((clickEvent) -> {
            super.setAnswered(true);
            checkCorrect(super.getCorrectArray(), 3);
        });
        //this.inButton = true;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public void setAnswered(Boolean answered) {
        this.answered = answered;
    }

    public void setAnswerNum(String answerNum) {
        this.answerNum = answerNum;
    }

    public String getAnswerNum(String answernum) {
        return this.answerNum;
    }

    public Boolean getCorrect() {
        return this.correct;
    }

    public Boolean getAnswered() {
        return this.answered;
    }

    public void checkCorrect(Boolean[] correctArray, Integer answernumInt) {
        if (correctArray[answernumInt]) {
            this.correctAudio.play();
            this.correctImage.setVisible();
            setCorrect(true);
        } else
            this.incorrectAudio.play();
            setCorrect(false);
            switch (answernumInt) {
            case 1:
                //this.incorrectAudio.play();
                this.incorrectImage1.setVisible();
                break;
            case 2:
                //this.incorrectAudio.play();
                this.incorrectImage2.setVisible();
                break;
            case 3:
                //this.incorrectAudio.play();
                this.incorrectImage3.setVisible();
                break;
            default:
                break;
        }
    }


//  public Boolean setCorrectArray(Boolean correct, Integer answernum){
//      this.correctArray[answernum] = correct;
//  }
}

