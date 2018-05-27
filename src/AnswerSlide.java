/*import java.util.ArrayList;

public class AnswerSlide extends Slide {

//    private Integer answernum;
//    private Boolean correct;
//    private Boolean answered = false;//, correct = false;
    private FLButton button1;
    private FLButton button2;
    private FLButton button3;
    private FLButton button4;
//    private Boolean[] correctArray;

//    public AnswerSlide(String id, String type) {
//        super(id, type,);
//
//        //displayAnswers();
//    }

    public AnswerSlide(String id, String type){//}, Integer answernum, Boolean correct){
        super(id, type);
        this.answernum = answernum;
        this.correct = correct;

	}

    public void displayAnswers(){

//        switch(getAttributeString(attrs, "action")) {
//            case "correctAnswer": {
//                String currentSlideID = super.getId();
//                this.currentButton.getButton().setOnMouseClicked((clickEvent) -> {
//                    this.presentation.playAudio(currentSlideID, "correctAnswer");
//                    this.presentation.showImage(currentSlideID, "correct");
//                });
//                this.currentButton.getButton().getStyleClass().add("answer");
//            }
//            break;
//            case "wrongAnswer1": {
//                String currentSlideID = super.getId();
//                this.currentButton.getButton().setOnMouseClicked((clickEvent) -> {
//                    this.presentation.playAudio(currentSlideID, "wrongAnswer");
//                    this.presentation.showImage(currentSlideID, "incorrect1");
//                });
//                this.currentButton.getButton().getStyleClass().add("answer");
//            }
//            break;
//            case "wrongAnswer2": {
//                String currentSlideID = super.getId();
//                this.currentButton.getButton().setOnMouseClicked((clickEvent) -> {
//                    this.presentation.playAudio(currentSlideID, "wrongAnswer");
//                    this.presentation.showImage(currentSlideID, "incorrect2");
//                });
//                this.currentButton.getButton().getStyleClass().add("answer");
//            }
//            break;
//            case "wrongAnswer3": {
//                String currentSlideID = this.currentSlide.getId();
//                this.currentButton.getButton().setOnMouseClicked((clickEvent) -> {
//                    this.presentation.playAudio(currentSlideID, "wrongAnswer");
//                    this.presentation.showImage(currentSlideID, "incorrect3");
//                });
//                this.currentButton.getButton().getStyleClass().add("answer");
//            }
//            break;
//        }


        //this.currentButton.addText(textString.trim());
        String answernumArray[] = answernum.split("/");
        switch(Integer.parseInt(answernumArray[1])) {
            case 1:
                 button1 = new FLButton(id,
                        new Position(100, 350), 490, 185, "file:../resources/answer_flag_1.png");
                break;
            case 2:
                 button2 = new FLButton(id,
                        new Position(690, 350), 490, 185, "file:../resources/answer_flag_2.png");
                break;
            case 3:
                 button3 = new FLButton(id,
                        new Position(100, 535), 490, 185, "file:../resources/answer_flag_3.png");
                break;
            case 4:
                 button4 = new FLButton(id,
                        new Position(690, 535), 490, 185, "file:../resources/answer_flag_4.png");
                break;
            default:
                break;
        }

//        this.button1.getButton().setOnMouseClicked((clickEvent) -> {
//            super.setCorrect(correct);
//            super.setAnswered(true);
//            //if(correct = true){
//                this.presentation.playAudio(currentSlideID, "correctAnswer");
//                this.presentation.showImage(currentSlideID, "correct");
//            //}
//        });
//        this.button2.getButton().setOnMouseClicked((clickEvent) -> {
//            super.setCorrect(correct);
//            this.presentation.playAudio(currentSlideID, "wrongAnswer");
//            this.presentation.showImage(currentSlideID, "incorrect1");
//            super.setAnswered(true);
//        });
//        this.button3.getButton().setOnMouseClicked((clickEvent) -> {
//            super.setCorrect(correct);
//            this.presentation.playAudio(currentSlideID, "wrongAnswer");
//            this.presentation.showImage(currentSlideID, "incorrect1");
//            super.setAnswered(true);
//        });
//        this.button4.getButton().setOnMouseClicked((clickEvent) -> {
//            super.setCorrect(correct);
//            this.presentation.playAudio(currentSlideID, "wrongAnswer");
//            this.presentation.showImage(currentSlideID, "incorrect1");
//            super.setAnswered(true);
//        });
        //this.inButton = true;
    }

    public void setCorrect(Boolean correct){
        this.correct = correct;
    }

    public void setAnswered(Boolean answered){
        this.answered = answered;
    }

    public void setAnswerNum(String answernum){
        this.answernum = answernum;
    }

    public String getAnswerNum(String answernum){
        return this.answernum;
    }

    public Boolean getCorrect(){
        return this.correct;
    }

    public Boolean getAnswered(){
        return this.answered;
    }

    public Boolean setCorrectArray(Boolean correct, Integer answernum){
        this.correctArray[answernum] = correct;
    }
}
*/
