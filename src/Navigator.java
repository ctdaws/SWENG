import javafx.scene.Node;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.lang.String;
import java.util.ArrayList;

/**
 * This class is used to create a Navigator which will control the chosen presentation.
 * It contains the code for calculating the correct slide ID that should be displayed next,
 * depending on the current position in the presentation and the user inputs.
 *
 * @author Oscar Thorpe, Matt Holt, Ben Grainger, Chris Dawson, Joe Williams, Jack O'Neill, Kris Sivills
 */
class Navigator {
//	Presentation data used for navigation
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

//	Used to control web server interaction
	private WebComms webComms = new WebComms();
	private Boolean isInteractionEnabled = true;

	public Navigator() {
		this.prevID = new ArrayList<String>();
		this.currentID = "menu";
	}

//	Loads the presentation object into navigator
	public void setPresentation(LQPresentation presentation){
		this.lqPresentation = presentation;
	}

//	Get the current ID
	public String getCurrentID() { return this.currentID; }

//	Set the current ID
	public void setCurrentID(String newID) { this.currentID = newID;}

//	Used to calculate the next ID when the 'Next' button is pressed
	public String GetNextID() {
		this.nextID = this.currentID;
		switch(this.currentID){
//			From start screen, navigate to first unanswered question
			case "menu":
				currentLevelNum = 1;
				currentQuestionNum = 1;
				currentSlideNum = 1;
				SetQuestionNum();
				break;
//			From feedback slide
			case "feedback":
//				If classroom interaction is enabled, go to analytics slide and display data from web server
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
//				If classroom interaction is not enabled, calculate next question slide ID
				else{
					try {
						webComms.sendPost(true, false);
					} catch (Exception e) {
						e.printStackTrace();
					}
					SplitID(this.prevID.get(this.prevID.size()-1));
//					Use answer and feedback values to decide which level to move to
					n = this.aVal + lqPresentation.fVal;
					currentLevelNum += n;
					currentSlideNum = 1;
//					Check that new level value is within appropriate range
					if (currentLevelNum < 1) {
						currentLevelNum = 1;
						SetQuestionNum();
						if (currentQuestionNum > this.lqPresentation.getLqLevelArray().get(currentLevelNum-1).getLqQuestionArray().size() - 1) {
							currentLevelNum ++;
						}
						this.nextID = CombineID();
					}
					else if (currentLevelNum > this.lqPresentation.getLqLevelArray().size()) {
						this.nextID = "end";
					}
					else {
						SetQuestionNum();
					}
				}

				break;
//			From analytics slide, calculate next question slide ID
			case "analytics":
				SplitID(this.prevID.get(this.prevID.size()-2));
//				Use answer and feedback values to decide which level to move to
				n = this.aVal + lqPresentation.fVal;
				currentLevelNum += n;
				currentSlideNum = 1;
//				Check that new level value is within appropriate range
				if (currentLevelNum < 1) {
					currentLevelNum = 1;
					SetQuestionNum();
					if (currentQuestionNum > this.lqPresentation.getLqLevelArray().get(currentLevelNum-1).getLqQuestionArray().size() - 1) {
						currentLevelNum ++;
					}
					this.nextID = CombineID();
				}
				else if (currentLevelNum > this.lqPresentation.getLqLevelArray().size()) {
					this.nextID = "end";
				}
				else {
					SetQuestionNum();
				}
				break;
//			From end of quest slide, navigate back to start screen
			case "end":
				this.nextID = "menu";
				break;
//			If the current ID is not one of default slide IDs it must be a regular slide ID
			default:
				SplitID(this.currentID);
//				If question number > 0, it is a question
				if (currentQuestionNum > 0) {
//					If classroom interaction is enabled, send the answer strings to server
					if(isInteractionEnabled) {
						try {
							String[] answers = new String[4];
							for(int i = 0; i < answers.length; i++) {
								answers[i] = this.lqPresentation.getSlideByID(currentLevelNum+"/"+currentQuestionNum+"/"+"2").getButtonArray()[i].getLQButton().getText();
							}
							webComms.sendPost(false, true, answers);
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
//					If slide number is 1, it is a question title slide
					if (currentSlideNum == 1) {
						currentSlideNum++;
						this.nextID = CombineID();
						this.resetAnswer(this.nextID);
					}
//					If slide number is 2, it is an answer slide
					else if(currentSlideNum == 2) {
						if(this.lqPresentation.getSlideByID(this.currentID).getGotAnswerCorrect()){
							this.aVal = 1;
						}
						else {
							this.aVal = 0;
						}
//						Navigate to feedback slide and add answer data to analytics
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
//					If slide number is 3, it is a worked solution slide
					else if (currentSlideNum == 3) {
//						Navigate to feedback slide and add answer data to analytics
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
						if(currentQuestionNum > lqPresentation.getLqProgressArray().get(currentLevelNum-1)) {
							this.lqPresentation.getLqProgressArray().set(currentLevelNum-1, currentQuestionNum);
						}
					}
				}
//				If question number is 0, it is an example slide
				else if (currentQuestionNum == 0) {
					currentSlideNum++;
					if (currentSlideNum > this.lqPresentation.getLqLevelArray().get(currentLevelNum-1).getLqQuestionArray().get(currentQuestionNum).getLqSlideArray().size() ) {
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
	}

//	Calculates the ID of the example slide for the current level
	public String GetExampleID(){
		this.nextID = this.currentID;
		SplitID(this.currentID);
		currentQuestionNum = 0;
		currentSlideNum = 1;
		this.nextID = CombineID();
		return this.nextID;
	}

//	Calculates the ID of the question slide for the current question number
	public String GetQuestionID(){
		this.nextID = this.currentID;
		SplitID(this.currentID);
		SetQuestionNum();
		return this.nextID;
	}

//	Calculates the ID of the solution slide for the current question number
	public String GetSolutionID(){
		this.nextID = this.currentID;
		SplitID(this.currentID);
		currentSlideNum = 3;
		this.nextID = CombineID();
		return this.nextID;
	}

//	Gets the ID of the previous slide when 'Back' button is pressed
	public String GetPrevID(){
		String lastID;
		if (this.prevID.size() > 0){
			lastID = this.prevID.get(this.prevID.size()-1);
//			Removes ID from array of previous IDs once selected
			this.prevID.remove(this.prevID.size()-1);
		}
		else { lastID = this.currentID; }
		return lastID;
	}

//	Splits ID into individual values for current level, question and slide number
	public void SplitID(String id){
		String idArray[] = id.split("/");
		currentLevelNum = Integer.parseInt(idArray[0]);
		currentQuestionNum = Integer.parseInt(idArray[1]);
		currentSlideNum = Integer.parseInt(idArray[2]);
	}

//	Calculates what the next question slide ID should be,
	public void SetQuestionNum(){
//		Checks if next question has already been completed or is outside level range
		do {
			currentQuestionNum = this.lqPresentation.getLqProgressArray().get(currentLevelNum-1) + 1;
			if (currentQuestionNum > this.lqPresentation.getLqLevelArray().get(currentLevelNum-1).getLqQuestionArray().size() - 1) {
				currentLevelNum ++;
				if (currentLevelNum > this.lqPresentation.getLqLevelArray().size()) {
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

//	Combine parameters for current slide into a single ID string
	public String CombineID() {
		String newID = (currentLevelNum + "/"
				+ currentQuestionNum + "/"
				+ currentSlideNum);
		return newID;
	}

//	Displays slide of any given ID
	public void moveSlide(String slideID) {
		this.unloadSlide();
		this.setCurrentID(slideID);
		this.renderSlide();
	}

//	Displays next slide when 'Next' button is pressed
	public void moveNextSlide() {
		this.unloadSlide();
		this.setCurrentID(this.GetNextID());
		this.renderSlide();
	}

//	Displays previous slide when 'Back' button is pressed
	public void moveBackSlide() {
		this.unloadSlide();
		this.setCurrentID(this.GetPrevID());
		this.renderSlide();
	}

//	Adds the current slide to the display pane
	public void renderSlide() {
		this.lqPresentation.pane.getChildren().add(this.lqPresentation.getSlideByID(currentID).getSlidePane());
		this.lqPresentation.getSlideByID(currentID).startTransitions();
	}

//	Removes the current slide from the display pane
	public void unloadSlide() {
		this.lqPresentation.getSlideByID(currentID).endTransitions();
		this.lqPresentation.pane.getChildren().remove(this.lqPresentation.getSlideByID(currentID).getSlidePane());
	}

//	Resets the Booleans in an answer slide to defaults
	public void resetAnswer(String id){
		this.aVal = 0;
		this.lqPresentation.getSlideByID(id).resetAnswer();
	}

//	Returns the current level number
	public int getLevelNum() {
		return this.currentLevelNum;
	}

//	Returns the current level number
	public int GetCurrentLevelNum() {
		return this.currentLevelNum;
	}

//	Returns the current question number
	public int GetCurrentQuestionNum() {
		return this.currentQuestionNum;
	}

//	Returns the current slide number
	public int GetCurrentSlideNum() {
		return this.currentSlideNum;
	}

//	Returns the currently loaded presentation
	public LQPresentation getPresentation() {
		return lqPresentation;
	}

//	Enables the web server interaction
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
