import javafx.scene.Node;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.lang.String;
import java.util.ArrayList;

class Navigator {
	private LQPresentation lqPresentation;
	public String id;
	public int currentSlideNum;
	public int currentQuestionNum;
	public int currentLevelNum;
	public int n = 0;
	public int aVal = 0;
	public LQSlide currentSlide;
	private String currentID, nextID;
	private ArrayList<String> prevID;
	private WebComms webComms = new WebComms();
	private Boolean isInteractionEnabled = true;

	public Navigator() {
		this.prevID = new ArrayList<String>();
		this.currentID = "menu";
	}

	public void setPresentation(LQPresentation presentation){
		this.lqPresentation = presentation;
	}

	public String getCurrentID() { return this.currentID; } //TODO move from presentor

	public void setCurrentID(String newID) { this.currentID = newID;} //TODO move from presentor

	public String GetNextID() {
		this.nextID = this.currentID;
		switch(this.currentID){
			case "menu":
				currentLevelNum = 1;
				currentQuestionNum = 1;
				currentSlideNum = 1;
				SetQuestionNum();
				if(isInteractionEnabled) {
					try {
						webComms.sendPost(false, true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						webComms.sendPost(true, false);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
			case "feedback":
				if(isInteractionEnabled) {
					this.nextID = "analytics";

					try {
						webComms.sendGet();

						this.lqPresentation.feedbackChart.getData().clear();

						XYChart.Series series = new XYChart.Series();

						XYChart.Data<String, Number> sadData = new XYChart.Data("Sad", this.webComms.sadCount);
						XYChart.Data<String, Number> confusedData = new XYChart.Data("Confused", this.webComms.confusedCount);
						XYChart.Data<String, Number> happyData = new XYChart.Data("Happy", this.webComms.happyCount);

						series.getData().add(sadData);
						series.getData().add(confusedData);
						series.getData().add(happyData);

						sadData.nodeProperty().addListener((ov, oldNode, newNode) -> {
							if (null != newNode) {
								newNode.setStyle("-fx-bar-fill: red;");
							}
						});
						confusedData.nodeProperty().addListener((ov, oldNode, newNode) -> {
							if (null != newNode) {
								newNode.setStyle("-fx-bar-fill: orange;");
							}
						});
						happyData.nodeProperty().addListener((ov, oldNode, newNode) -> {
							if (null != newNode) {
								newNode.setStyle("-fx-bar-fill: green;");
							}
						});

						this.lqPresentation.feedbackChart.getData().addAll(series);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else{
					try {
						webComms.sendPost(true, false);
					} catch (Exception e) {
						e.printStackTrace();
					}
					SplitID(this.prevID.get(this.prevID.size()-1));
					n = this.aVal + lqPresentation.fVal;
					currentLevelNum += n;
					currentSlideNum = 1;
					if (currentLevelNum < 1) {
						currentLevelNum = 1;
						//currentQuestionNum = SetQuestionNum();
						SetQuestionNum();
						//if (currentQuestionNum > this.p.tArray.get(currentTopicNum-1).lArray.get(currentLevelNum-1).qArray.size() - 1) {
						if (currentQuestionNum > this.lqPresentation.getLqLevelArray().get(currentLevelNum-1).getLqQuestionArray().size() - 1) {
							currentLevelNum ++;
						}
						this.nextID = CombineID();
					}
					//else if (currentLevelNum > this.p.tArray.get(currentTopicNum-1).lArray.size()) {
					else if (currentLevelNum > this.lqPresentation.getLqLevelArray().size()) {
						this.nextID = "end";
					}
					else {
						SetQuestionNum();
					}
				}

				break;
			case "analytics":
				SplitID(this.prevID.get(this.prevID.size()-2));
				n = this.aVal + lqPresentation.fVal;
				currentLevelNum += n;
				currentSlideNum = 1;
				if (currentLevelNum < 1) {
					currentLevelNum = 1;
					//currentQuestionNum = SetQuestionNum();
					SetQuestionNum();
					//if (currentQuestionNum > this.p.tArray.get(currentTopicNum-1).lArray.get(currentLevelNum-1).qArray.size() - 1) {
					if (currentQuestionNum > this.lqPresentation.getLqLevelArray().get(currentLevelNum-1).getLqQuestionArray().size() - 1) {
						currentLevelNum ++;
					}
					this.nextID = CombineID();
				}
				//else if (currentLevelNum > this.p.tArray.get(currentTopicNum-1).lArray.size()) {
				else if (currentLevelNum > this.lqPresentation.getLqLevelArray().size()) {
					this.nextID = "end";
				}
				else {
					SetQuestionNum();
				}
				break;
			case "end":
				this.nextID = "menu";
				break;
			default:
				SplitID(this.currentID);
				//if question number > 0, it is a question
				if (currentQuestionNum > 0) {
					//TODO: Send question to server
					if(isInteractionEnabled) {
						try {
							webComms.sendPost(false, true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						try {
							webComms.sendPost(true, false);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (currentSlideNum == 1) {
						currentSlideNum++;
						this.nextID = CombineID();
						this.resetAnswer(this.nextID);
					}
					else if(currentSlideNum == 2) {
						if(this.lqPresentation.getSlideByID(this.currentID).getGotAnswerCorrect()){
							this.aVal = 1;
						}
						else {
							this.aVal = 0;
						}
						this.nextID = "feedback";
						if(isInteractionEnabled) {
							try {
								webComms.sendGet();

								this.lqPresentation.answersChart.getData().clear();

								XYChart.Series series = new XYChart.Series();

								XYChart.Data<String, Number> answer1Data = new XYChart.Data("A", this.webComms.aCount);
								XYChart.Data<String, Number> answer2Data = new XYChart.Data("B", this.webComms.bCount);
								XYChart.Data<String, Number> answer3Data = new XYChart.Data("C", this.webComms.cCount);
								XYChart.Data<String, Number> answer4Data = new XYChart.Data("D", this.webComms.dCount);

								series.getData().add(answer1Data);
								series.getData().add(answer2Data);
								series.getData().add(answer3Data);
								series.getData().add(answer4Data);

//							answer1Data.getNode().setStyle("-fx-bar-fill: #00A324;");
//							answer2Data.getNode().setStyle("-fx-bar-fill: #E5C800;");
//							answer3Data.getNode().setStyle("-fx-bar-fill: #0076A3;");
//							answer4Data.getNode().setStyle("-fx-bar-fill: #A30060;");

								answer1Data.nodeProperty().addListener((ov, oldNode, newNode) -> {
									if (null != newNode) {
										newNode.setStyle("-fx-bar-fill: #00A324;");
									}
								});
								answer2Data.nodeProperty().addListener((ov, oldNode, newNode) -> {
									if (null != newNode) {
										newNode.setStyle("-fx-bar-fill: #E5C800;");
									}
								});
								answer3Data.nodeProperty().addListener((ov, oldNode, newNode) -> {
									if (null != newNode) {
										newNode.setStyle("-fx-bar-fill: #0076A3;");
									}
								});
								answer4Data.nodeProperty().addListener((ov, oldNode, newNode) -> {
									if (null != newNode) {
										newNode.setStyle("-fx-bar-fill: #A30060;");
									}
								});

								int correctAnswerNum = this.lqPresentation.getSlideByID(currentID).getCorrectAnswerNum();
								switch (correctAnswerNum) {
									case 1:
										this.lqPresentation.correctAnswerImage.setImage(this.getClass().getResource("answer_1.png").toExternalForm());
										break;
									case 2:
										this.lqPresentation.correctAnswerImage.setImage(this.getClass().getResource("answers_2.png").toExternalForm());
										break;
									case 3:
										this.lqPresentation.correctAnswerImage.setImage(this.getClass().getResource("answers_3.png").toExternalForm());
										break;
									case 4:
										this.lqPresentation.correctAnswerImage.setImage(this.getClass().getResource("answers_4.png").toExternalForm());
										break;
								}

								String correctAnswerText = this.lqPresentation.getSlideByID(currentID).getButtonArray()[correctAnswerNum-1].getLQButton().getText();
								this.lqPresentation.correctAnswerText.clear();
                                this.lqPresentation.correctAnswerText.add(correctAnswerText, new PWSColors("#FFFFFF", "transparent"), new PWSFonts("Bebas Neue Regular", false, false, false, 30, "center"));


								this.lqPresentation.answersChart.getData().addAll(series);
								this.lqPresentation.answersChart.getXAxis().setAutoRanging(true);
								//this.lqPresentation.correctAnswer.clear();
								//this.lqPresentation.correctAnswer.add("Correct answer was: " + String.valueOf((char)(64 + correctAnswerNum)));


								webComms.sendPost(false, false);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}  else {
							try {
								webComms.sendPost(true, false);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						if(currentQuestionNum > lqPresentation.getLqProgressArray().get(currentLevelNum-1)) {
							this.lqPresentation.getLqProgressArray().set(currentLevelNum - 1, currentQuestionNum);
						}
					}
					else if (currentSlideNum == 3) {
						this.nextID = "feedback";
						if(isInteractionEnabled) {
							try {
								webComms.sendGet();

								this.lqPresentation.answersChart.getData().clear();

								XYChart.Series series = new XYChart.Series();

								XYChart.Data<String, Number> answer1Data = new XYChart.Data("A", this.webComms.aCount);
								XYChart.Data<String, Number> answer2Data = new XYChart.Data("B", this.webComms.bCount);
								XYChart.Data<String, Number> answer3Data = new XYChart.Data("C", this.webComms.cCount);
								XYChart.Data<String, Number> answer4Data = new XYChart.Data("D", this.webComms.dCount);

								series.getData().add(answer1Data);
								series.getData().add(answer2Data);
								series.getData().add(answer3Data);
								series.getData().add(answer4Data);


								answer1Data.nodeProperty().addListener((ov, oldNode, newNode) -> {
									if (null != newNode) {
										newNode.setStyle("-fx-bar-fill: #00A324;");
									}
								});
								answer2Data.nodeProperty().addListener((ov, oldNode, newNode) -> {
									if (null != newNode) {
										newNode.setStyle("-fx-bar-fill: #E5C800;");
									}
								});
								answer3Data.nodeProperty().addListener((ov, oldNode, newNode) -> {
									if (null != newNode) {
										newNode.setStyle("-fx-bar-fill: #0076A3;");
									}
								});
								answer4Data.nodeProperty().addListener((ov, oldNode, newNode) -> {
									if (null != newNode) {
										newNode.setStyle("-fx-bar-fill: #A30060;");
									}
								});

								int correctAnswerNum = this.lqPresentation.getSlideByID(currentLevelNum + "/" + currentQuestionNum + "/" + 2).getCorrectAnswerNum();
								switch (correctAnswerNum) {
									case 1:
										this.lqPresentation.correctAnswerImage.setImage(this.getClass().getResource("answer_1.png").toExternalForm());
										break;
									case 2:
										this.lqPresentation.correctAnswerImage.setImage(this.getClass().getResource("answers_2.png").toExternalForm());
										break;
									case 3:
										this.lqPresentation.correctAnswerImage.setImage(this.getClass().getResource("answers_3.png").toExternalForm());
										break;
									case 4:
										this.lqPresentation.correctAnswerImage.setImage(this.getClass().getResource("answers_4.png").toExternalForm());
										break;
								}

                                String correctAnswerText = this.lqPresentation.getSlideByID(currentID).getButtonArray()[correctAnswerNum-1].getLQButton().getText();
                                this.lqPresentation.correctAnswerText.clear();
                                this.lqPresentation.correctAnswerText.add(correctAnswerText, new PWSColors("#FFFFFF", "transparent"), new PWSFonts("Bebas Neue Regular", false, false, false, 30, "center"));

								this.lqPresentation.answersChart.getData().addAll(series);
								this.lqPresentation.answersChart.getXAxis().setAutoRanging(true);
								//this.lqPresentation.correctAnswer.clear();
								//this.lqPresentation.correctAnswer.add("Correct answer was: " + String.valueOf((char)(64 + correctAnswerNum)));

								webComms.sendPost(false, false);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							try {
								webComms.sendPost(true, false);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						//if (currentQuestionNum > this.p.tArray.get(currentTopicNum-1).lProgress.get(currentLevelNum-1) ) {
						//  this.p.tArray.get(currentTopicNum-1).lProgress.set(currentLevelNum-1, currentQuestionNum);
						//}
						//if (currentQuestionNum > this.p.lProgress.get(currentLevelNum-1) ) {
						if(currentQuestionNum > lqPresentation.getLqProgressArray().get(currentLevelNum-1)) {
						this.lqPresentation.getLqProgressArray().set(currentLevelNum-1, currentQuestionNum);
						}
					}
				}
				//if question number = 0, it is an example
				else if (currentQuestionNum == 0) {
					currentSlideNum++;
					if (currentSlideNum > this.lqPresentation.getLqLevelArray().get(currentLevelNum-1).getLqQuestionArray().get(currentQuestionNum).getLqSlideArray().size() ) {
						//currentLevelNum ++;
						currentSlideNum = 1;
						SetQuestionNum();
					}
					if (nextID != "end") {
						nextID = CombineID();
					}
				}
				break;
		}
		this.prevID.add(this.currentID);
		return this.nextID;

	} //TODO move from presentor

	public String GetExampleID(){
		this.nextID = this.currentID;
		SplitID(this.currentID);
		currentQuestionNum = 0;
		currentSlideNum = 1;
		this.nextID = CombineID();
		return this.nextID;
	} //TODO move from presentor

	public String GetQuestionID(){
		this.nextID = this.currentID;
		SplitID(this.currentID);
		SetQuestionNum();
		return this.nextID;
	} //TODO move from presentor

	public String GetSolutionID(){
		this.nextID = this.currentID;
		SplitID(this.currentID);
		currentSlideNum = 3;
		this.nextID = CombineID();
		return this.nextID;
	} //TODO move from presentor

	public String GetPrevID(){
		String lastID;
		if (this.prevID.size() > 0){
			lastID = this.prevID.get(this.prevID.size()-1);
			this.prevID.remove(this.prevID.size()-1);
		}
		else { lastID = this.currentID; }
		return lastID;
	} //TODO move from presentor

	public void SplitID(String id){
		//splits ID into individual values
		String idArray[] = id.split("/");
		//currentTopicNum = Integer.parseInt(idArray[0]);
		currentLevelNum = Integer.parseInt(idArray[0]);
		currentQuestionNum = Integer.parseInt(idArray[1]);
		currentSlideNum = Integer.parseInt(idArray[2]);
	} //TODO move from presentor

	public void SetQuestionNum(){

		do {
			currentQuestionNum = this.lqPresentation.getLqProgressArray().get(currentLevelNum-1) + 1;
			if (currentQuestionNum > this.lqPresentation.getLqLevelArray().get(currentLevelNum-1).getLqQuestionArray().size() - 1) {
				currentLevelNum ++;
				if (currentLevelNum > this.lqPresentation.getLqLevelArray().size()) {
					//currentLevelNum -= 1;
					this.nextID = "end";
					break;
				}
				else {
					currentQuestionNum = this.lqPresentation.getLqProgressArray().get(currentLevelNum-1) + 1;
				}
			}
		}  while (currentQuestionNum > this.lqPresentation.getLqLevelArray().get(currentLevelNum-1).getLqQuestionArray().size() - 1);
		if (this.nextID != "end") {
			this.nextID = CombineID();
		}
	}

	public String CombineID(){
		//combines values into string ID
		//(...currentTopicNum + "/" + ...)
		String newID = (currentLevelNum + "/"
				+ currentQuestionNum + "/"
				+ currentSlideNum);
		return newID;
	}   //TODO move from presentor

	public void moveSlide(String slideID) {
		this.unloadSlide();
		this.setCurrentID(slideID);
		this.renderSlide();
	} //TODO move from presentor

	public void moveNextSlide() {
		this.unloadSlide();
		this.setCurrentID(this.GetNextID());
		this.renderSlide();
	} //TODO move from presentor

	public void moveBackSlide() {
		this.unloadSlide();
		this.setCurrentID(this.GetPrevID());
		this.renderSlide();
	} //TODO move from presentor

	public void renderSlide() {
		this.lqPresentation.pane.getChildren().add(this.lqPresentation.getSlideByID(currentID).getSlidePane());
		this.lqPresentation.getSlideByID(currentID).startTransitions();
	}

	public void unloadSlide() {
		this.lqPresentation.getSlideByID(currentID).endTransitions();
		this.lqPresentation.pane.getChildren().remove(this.lqPresentation.getSlideByID(currentID).getSlidePane());
	}

	public void resetAnswer(String id){
		this.aVal = 0;
		this.lqPresentation.getSlideByID(id).resetAnswer();
	}

	public int getLevelNum() {
		return this.currentLevelNum;
	}

	public int GetCurrentLevelNum() {
		return this.currentLevelNum;
	}   //TODO Needed?

	public int GetCurrentQuestionNum() {
		return this.currentQuestionNum;
	}   //TODO Needed?

	public int GetCurrentSlideNum() {
		return this.currentSlideNum;
	}   //TODO Needed?

	public LQPresentation getPresentation() {
		return lqPresentation;
	}

	public void setInteractionEnabled(Boolean isInteractionEnabled){
		this.isInteractionEnabled = isInteractionEnabled;
		if(!isInteractionEnabled) {
			try {
				webComms.sendPost(true, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
