class Navigator {
  public int currentSlideNum;
  public int currentQuestionNum;
  public int currentLevelNum;
  public int currentTopicNum;
  Topic t1;
  Level l1;
  Level l2;
  Question q1;
  Slide s1;
  Slide s2;
  Slide s3;
  Question q2;
  Slide s4;
  Slide s5;
  Slide s6;
  Example l1X;
  Slide l1Xs1;

  Question l2q1;
  Slide l2q1Q;
  Slide l2q1A;
  Slide l2q1S;
  Question l2q2;
  Slide l2q2Q;
  Slide l2q2A;
  Slide l2q2S;
  Example l2X;
  Slide l2Xs1;

  Slide currentSlide;
  /*int numOfLevels = 2;
  int numOfQuestions = 2;*/
  public Navigator() {
    /*

    for(int i=0;i<numOfLevels+1;i++) {
      this.t1.add(new Level());
      for(int j=0;j<numOfQuestions+2;j++){
        if(j==0) {
          this.t1.lArray.get(i).add(new Example());
          this.t1.lArray.get(i).get(j).add(new Slide())
        } else {
          this.t1.lArray.get(i).add(new Question());
        }
      }
    }*/

    this.t1 = new Topic();

    this.l1 = new Level();
    this.l1X = new Example();
    this.l1Xs1 = new Slide(7, "L1 X s1");
    this.q1 = new Question();
    this.s1 = new Slide(1, "L1 Q1");
    this.s2 = new Slide(2, "L1 A1");
    this.s3 = new Slide(3, "L1 S1");
    this.q2 = new Question();
    this.s4 = new Slide(4, "L1 Q2");
    this.s5 = new Slide(5, "L1 A2");
    this.s6 = new Slide(6, "L1 S2");


    this.l2 = new Level();
    this.l2X = new Example();
    this.l2Xs1 = new Slide(8, "L2 X s1");
    this.l2q1 = new Question();
    this.l2q1Q = new Slide(9, "L2 Q1");
    this.l2q1A = new Slide(10, "L2 A1");
    this.l2q1S = new Slide(11, "L2 S1");
    this.l2q2 = new Question();
    this.l2q2Q = new Slide(12, "L2 Q2");
    this.l2q2A = new Slide(13, "L2 A2");
    this.l2q2S = new Slide(14, "L2 S2");



    this.currentSlide = new Slide(1, "Current Slide");

    this.l1X.add(l1Xs1);

    this.q1.add(s1);
    this.q1.add(s2);
    this.q1.add(s3);

    this.q2.add(s4);
    this.q2.add(s5);
    this.q2.add(s6);

    this.l2X.add(l2Xs1);

    this.l2q1.add(l2q1Q);
    this.l2q1.add(l2q1A);
    this.l2q1.add(l2q1S);

    this.l2q2.add(l2q2Q);
    this.l2q2.add(l2q2A);
    this.l2q2.add(l2q2S);

    this.l1.add(l1X);
    this.l1.add(q1);
    this.l1.add(q2);

    this.l2.add(l2X);
    this.l2.add(l2q1);
    this.l2.add(l2q2);

    this.t1.add(l1);
    this.t1.add(l2);

    currentTopicNum = 1;
    currentLevelNum = 2;
    currentQuestionNum = 1;
    currentSlideNum = 3;
    SetCurrentSlide(currentTopicNum, currentLevelNum, currentQuestionNum, currentSlideNum);
  }

public static void main(String[] args) {
  Navigator navigator = new Navigator();
}

public void SetCurrentSlide(int topic, int level, int question, int slide){

  System.out.println("Topic: " + topic + " Lvl: " + level +" Question: "+question+" Slide: "+slide);

  this.currentSlide = this.t1.lArray.get(level-1).qArray.get(question).slideArray.get(slide-1);
  System.out.println(this.currentSlide.text);
}

}
