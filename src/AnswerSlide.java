import java.util.ArrayList;

public class AnswerSlide extends Slide {

    private FLAudio incorrectAudio, correctAudio;
    private FLImage correctImage, incorrectImage1, incorrectImage2, incorrectImage3;


    public AnswerSlide(String id, String type) {//}, Integer answernum, Boolean correct){
        super(id, type);
        this.answerNum = answerNum;
        this.correct = correct;
        this.answerNumInt = answerNumInt;
        System.out.println("printed Answers Buttons");
        //displayAnswers();
    }

    protected void displayAnswers() {

        FLButton button1;
        FLButton button2;
        FLButton button3;
        FLButton button4;

        Position position1 = new Position(125, 399);
        Position position2 = new Position(649, 399);
        Position position3 = new Position(125, 485);
        Position position4 = new Position(649, 485);

        button1 = new FLButton(id, position1, 500, 75, this.getClass().getResource("answer_1.png").toExternalForm());
        //button1.addText("Answer 1");
        //button1.getMedia().getStyleClass().add("answer 1");
        this.add(button1);

        button2 = new FLButton(id, position2, 500, 75, this.getClass().getResource("answers_2.png").toExternalForm());
        //button2.addText("Answer 2");
        this.add(button2);

        button3 = new FLButton(id, position3, 500, 75, this.getClass().getResource("answers_3.png").toExternalForm());
        //button3.addText("Answer 3");
        this.add(button3);

        button4 = new FLButton(id, position4, 500, 75, this.getClass().getResource("answers_4.png").toExternalForm());
        //button4.addText("Answer 4");
        this.add(button4);

        button1.getButton().setOnMouseClicked((clickEvent) -> {
            this.setAnswered(true);
            checkCorrect(this.getCorrectArray(), 0);
        });
        button2.getButton().setOnMouseClicked((clickEvent) -> {
            this.setAnswered(true);
            checkCorrect(this.getCorrectArray(), 1);
        });
        button3.getButton().setOnMouseClicked((clickEvent) -> {
            this.setAnswered(true);
            checkCorrect(this.getCorrectArray(), 2);
        });
        button4.getButton().setOnMouseClicked((clickEvent) -> {
            this.setAnswered(true);
            checkCorrect(this.getCorrectArray(), 3);
        });

        System.out.println("printed Answers Buttons");

        correctAudio = new FLAudio(" correct jingle", "CORRECT.mp3", new Position(100, 100));
        this.add(correctAudio);

        incorrectAudio = new FLAudio(" incorrect jingle", "INCORRECT.wav", new Position(100, 100));
        this.add(incorrectAudio);


        System.out.println(correctArray[0] + ", " + correctArray[1] + ", " + correctArray[2] + ", " + correctArray[3]);

        if(this.getCorrectArray()[0]){
             correctImage  = new FLImage("correct image",   "correct.png", position1, 100, 100, false);
            this.add(correctImage);

            incorrectImage1 = new FLImage("correct image", "incorrect.png", position2, 100, 100, false);
            this.add(incorrectImage1);

            incorrectImage2 = new FLImage("correct image", "incorrect.png", position3, 100, 100, false);
            this.add(incorrectImage2);

            incorrectImage3 = new FLImage("correct image", "incorrect.png", position4, 100, 100, false);
            this.add(incorrectImage3);

        }else if (this.getCorrectArray()[1]){
            correctImage  = new FLImage("correct image",   "correct.png", position2, 100, 100, false);
            this.add(correctImage);

            incorrectImage1 = new FLImage("correct image", "incorrect.png", position1, 100, 100, false);
            this.add(incorrectImage1);

            incorrectImage2 = new FLImage("correct image", "incorrect.png", position3, 100, 100, false);
            this.add(incorrectImage2);

            incorrectImage3 = new FLImage("correct image", "incorrect.png", position4, 100, 100, false);
            this.add(incorrectImage3);
        }else if (this.getCorrectArray()[2]){
            correctImage  = new FLImage("correct image",   "correct.png", position3, 100, 100, false);
            this.add(correctImage);

            incorrectImage1 = new FLImage("correct image", "incorrect.png", position2, 100, 100, false);
            this.add(incorrectImage1);

            incorrectImage2 = new FLImage("correct image", "incorrect.png", position1, 100, 100, false);
            this.add(incorrectImage2);

            incorrectImage3 = new FLImage("correct image", "incorrect.png", position4, 100, 100, false);
            this.add(incorrectImage3);
        }else if (this.getCorrectArray()[3]){
            correctImage  = new FLImage("correct image",   "correct.png", position4, 100, 100, false);
            this.add(correctImage);

            incorrectImage1 = new FLImage("correct image", "incorrect.png", position2, 100, 100, false);
            this.add(incorrectImage1);

            incorrectImage2 = new FLImage("correct image", "incorrect.png", position3, 100, 100, false);
            this.add(incorrectImage2);

            incorrectImage3 = new FLImage("correct image", "incorrect.png", position1, 100, 100, false);
            this.add(incorrectImage3);
        }

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

    public void setAnswerNumInt(Integer answernumInt){
        this.answerNumInt = answernumInt;
    }

    public Integer getAnswerNumInt(){
        return this.answerNumInt;
    }

//    public void setCorrectArray(Boolean correct, Integer answerNum){
//        this.correctArray[answerNum] = correct;
//        System.out.println(correctArray[0] + ", " + correctArray[1] + ", " + correctArray[2] + ", " + correctArray[3]);
//    }
//
//    public Boolean[] getCorrectArray() {
//        return correctArray;
//    }

//  public Boolean setCorrectArray(Boolean correct, Integer answernum){
//      this.correctArray[answernum] = correct;
//  }

    public void checkCorrect(Boolean[] correctArray, Integer answernumInt) {
        if (correctArray[answernumInt]) {
            this.correctAudio.play();
            this.correctImage.setVisible();
            this.setCorrect(true);
        }
        else{
            this.incorrectAudio.play();
            this.setCorrect(false);
            switch (answernumInt) {
            case 1:
                this.incorrectAudio.play();
                this.incorrectImage1.setVisible();
                break;
            case 2:
                this.incorrectAudio.play();
                this.incorrectImage2.setVisible();
                break;
            case 3:
                this.incorrectAudio.play();
                this.incorrectImage3.setVisible();
                break;
            default:
                break;
            }
        }
        System.out.println(this.getCorrect());
    }


}

