import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.soap.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.Pane;

import static java.lang.Boolean.parseBoolean;


public class XMLParserNew extends DefaultHandler {

    private Presentation presentation;
    private Defaults defaults;
    private Level currentLevel;
    private Example currentExample;
    private Question currentQuestion;
    private Slide currentSlide;
    private FLText currentText;
    private FLButton currentButton;
    private FLAudio currentAudio;

    private boolean inText = false;
    private boolean inFormat = false;
    private boolean inButton = false;
    private TextStyle currentStyle;
    private Colors currentColor;


    public XMLParserNew(File inputFile, Defaults programDefault){
        this.defaults = programDefault;
        System.out.println("Starting to parse " + inputFile);

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputFile, this);
        }
        catch (ParserConfigurationException pce) { pce.printStackTrace(); }
        catch (SAXException saxe) { saxe.printStackTrace(); }
        catch (IOException ioe) { ioe.printStackTrace(); }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
        String elementName = localName;
        if (elementName.equals("")) {
            elementName = qName;
        }

        switch (elementName) {
            case "Presentation": {
                this.presentation = new Presentation(this.defaults, new Position(getAttributeDouble(attrs, "width"),
                        getAttributeDouble(attrs, "height")));

                String color = getAttributeString(attrs, "color");
                String fill = getAttributeString(attrs, "fill");
                String font = getAttributeString(attrs, "font");
                String textSize = getAttributeString(attrs, "textsize");
                String italic = getAttributeString(attrs, "italic");
                String bold = getAttributeString(attrs, "bold");
                String underline = getAttributeString(attrs, "underline");

                if (color != null) {
                    this.presentation.getPresentationDefaults().getDefaultColors().setColor(color);
                }
                if (fill != null) {
                    this.presentation.getPresentationDefaults().getDefaultColors().setFill(fill);
                }
                if (font != null) {
                    this.presentation.getPresentationDefaults().getDefaultStyle().setFontFamily(font);
                }
                if (textSize != null) {
                    this.presentation.getPresentationDefaults().getDefaultStyle().setSize(Integer.parseInt(textSize));
                }
                if (italic != null) {
                    this.presentation.getPresentationDefaults().getDefaultStyle().setItalic(parseBoolean(italic));
                }
                if (bold != null) {
                    this.presentation.getPresentationDefaults().getDefaultStyle().setBold(parseBoolean(bold));
                }
                if (underline != null) {
                    this.presentation.getPresentationDefaults().getDefaultStyle().setUnderlined(parseBoolean(underline));
                }
            }
            break;
            // case "Topic": {
            //     this.currentTopic = new Topic(getAttributeString(attrs, "id"));
            //     this.presentation.addTopic(this.currentTopic);
            //     break;
            // }
            case "Level": {
                this.currentLevel = new Level(getAttributeString(attrs, "id"));
                //this.presentation.add(this.currentLevel);
                break;
            }
            case "Example": {
                this.currentExample = new Example(getAttributeString(attrs, "id"));
                //this.currentLevel.add(this.currentExample);
                break;
            }
            case "Question": {
                this.currentQuestion = new Question(getAttributeString(attrs, "id"));
                //this.currentLevel.add(this.currentQuestion);
            }
            case "Slide": {
                //System.out.println(getAttributeString(attrs, "type"));
                if (getAttributeString(attrs, "type") != null && getAttributeString(attrs, "type").equals("A")) {
                    this.currentSlide = new AnswerSlide(getAttributeString(attrs, "id"), getAttributeString(attrs, "type"));
                    System.out.println("Answer Slide Created");
                }
                else {
                    this.currentSlide = new Slide(getAttributeString(attrs, "id"), getAttributeString(attrs, "type"));
                }

                this.currentSlide.setSlideDefaults(this.defaults);
                // if(getAttributeString(attrs, "type") == "X") {
                //     this.currentExample.add(this.currentSlide);
                // } else if(getAttributeString(attrs, "type") == "Q" || getAttributeString(attrs, "type") == "A" || getAttributeString(attrs, "type") == "S") {
                //     this.currentQuestion.add(this.currentSlide);
                // }

                String color = getAttributeString(attrs, "color");
                String fill = getAttributeString(attrs, "fill");
                String font = getAttributeString(attrs, "font");
                String textSize = getAttributeString(attrs, "textsize");
                String italic = getAttributeString(attrs, "italic");
                String bold = getAttributeString(attrs, "bold");
                String underline = getAttributeString(attrs, "underline");

                if(color != null) { this.currentSlide.getSlideDefaults().getDefaultColors().setColor(color); }
                if(fill != null) { this.currentSlide.getSlideDefaults().getDefaultColors().setFill(fill); }

                if(font != null) { this.currentSlide.getSlideDefaults().getDefaultStyle().setFontFamily(font); }
                if(textSize != null) { this.currentSlide.getSlideDefaults().getDefaultStyle().setSize(Integer.parseInt(textSize)); }
                if(italic != null) { this.currentSlide.getSlideDefaults().getDefaultStyle().setItalic(parseBoolean(italic)); }
                if(bold != null) { this.currentSlide.getSlideDefaults().getDefaultStyle().setBold(parseBoolean(bold)); }
                if(underline != null) { this.currentSlide.getSlideDefaults().getDefaultStyle().setUnderlined(parseBoolean(underline)); }


            }
            break;
            // TODO: when a new text is created without a textsize being specified, it takes on the size of the last created piece of text
            case "Text": {
                this.inText = true;
                this.currentText = new FLText(getAttributeString(attrs, "id"),
                        new Position(getAttributeDouble(attrs, "x"),
                                getAttributeDouble(attrs,"y")),
                        (getAttributeDouble(attrs, "x2") - getAttributeDouble(attrs, "x")),
                        this.currentSlide.getSlideDefaults(),
                        new Transitions("trigger", 0, 0));

                String color = getAttributeString(attrs, "color");
                String font = getAttributeString(attrs, "font");
                String textSize = getAttributeString(attrs, "textsize");
                String italic = getAttributeString(attrs, "italic");
                String bold = getAttributeString(attrs, "bold");
                String underline = getAttributeString(attrs, "underline");
                String alignment = getAttributeString(attrs, "align");

                if(color != null) { this.currentText.setColor(new Colors(color)); }
                if(font != null) { this.currentText.getStyle().setFontFamily(font); }
                if(textSize != null) { this.currentText.getStyle().setSize(Integer.parseInt(textSize)); }
                if(italic != null) { this.currentText.getStyle().setItalic(parseBoolean(italic)); }
                if(bold != null) { this.currentText.getStyle().setBold(parseBoolean(bold)); }
                if(underline != null) { this.currentText.getStyle().setUnderlined(parseBoolean(underline)); }
                if(alignment != null) { this.currentText.setAlignment(alignment); }
            }
            break;
            case "Format": {
                this.inText = true;
                this.inFormat = true;
                String color = getAttributeString(attrs, "color");
                String font = getAttributeString(attrs, "font");
                String textSize = getAttributeString(attrs, "textsize");
                String italic = getAttributeString(attrs, "italic");
                String bold = getAttributeString(attrs, "bold");
                String underline = getAttributeString(attrs, "underline");

                this.currentColor = new Colors(currentText.getColor());
                if(color != null) { this.currentColor = new Colors(color); }

                this.currentStyle = new TextStyle(currentText.getStyle());

                if(font != null) { this.currentStyle.setFontFamily(font); }
                if(textSize != null) { this.currentStyle.setSize(Integer.parseInt(textSize)); }
                if(italic != null) { this.currentStyle.setItalic(parseBoolean(italic)); }
                if(bold != null) { this.currentStyle.setBold(parseBoolean(bold)); }
                if(underline != null) { this.currentStyle.setUnderlined(parseBoolean(underline)); }

            }
            break;
            case "Image":
                currentSlide.add(new FLImage(getAttributeString(attrs, "id"),
                        getAttributeString(attrs, "path"),
                        new Position(getAttributeDouble(attrs, "x"), getAttributeDouble(attrs, "y")),
                        (getAttributeDouble(attrs, "x2") - getAttributeDouble(attrs, "x")),
                        (getAttributeDouble(attrs, "y2")) - getAttributeDouble(attrs, "y"),
                        getAttributeBoolean(attrs, "visibleOnLoad")));
                break;
            case "Audio":
                currentSlide.add(new FLAudio(getAttributeString(attrs, "id"),
                        getAttributeString(attrs, "path"),
                        new Position(getAttributeDouble(attrs, "x"), getAttributeDouble(attrs, "y"))));
                break;
            case "Video":	//TODO leave until we get module
                break;
            case "Shape":	//TODO leave until we get module
                break;
            case "Br":
                this.currentText.add("\n");
                break;
            case "Meta":
                presentation.addMeta(new Meta(getAttributeString(attrs, "key"), getAttributeString(attrs, "value")));
                break;
            case "Answer": {
                //TODO move this to a new AnswerSlide class (extends Slide)
//                switch(getAttributeInteger(attrs, "answernum")) {
//                  case 1:
//                    this.currentButton = new FLButton(getAttributeString(attrs, "id"),
//                          new Position(100, 350), 490, 185, "../resources/answer_flag_1.png");
//                    break;
//                  case 2:
//                    this.currentButton = new FLButton(getAttributeString(attrs, "id"),
//                          new Position(690, 350), 490, 185, "file:../resources/answer_flag_2.png");
//                    break;
//                  case 3:
//                    this.currentButton = new FLButton(getAttributeString(attrs, "id"),
//                          new Position(100, 535), 490, 185, "file:../resources/answer_flag_3.png");
//                    break;
//                  case 4:
//                    this.currentButton = new FLButton(getAttributeString(attrs, "id"),
//                          new Position(690, 535), 490, 185, "file:../resources/answer_flag_4.png");
//                    break;
//                }
//
//                this.currentButton.getButton().setOnMouseClicked((clickEvent) -> {
//                            currentSlide.setCorrect(getAttributeBoolean(attrs, "correct"));
//                            currentSlide.setAnswered(true);
//                        });
//                this.inButton = true;
//                break;

                String correct = getAttributeString(attrs, "correct");

                String answernumID = getAttributeString(attrs, "id");
                String answernum = getAttributeString(attrs, "answernum");
                Integer answernumInt = (Integer.parseInt(answernum)-1);

//                String[] correctArray = new String[];
//                correctArray[answernumInt] = getAttributeString(attrs, "correct");


                //this.currentSlide.setCorrect( Boolean.parseBoolean(getAttributeString(attrs, "correct")));

                //if(getAttributeString(attrs, "correct" ) != null) { this.currentSlide.setCorrect(false); }
                //if(answernum != null) { answernum = "1" }

                if(answernumID != null) { this.currentSlide.setAnswerNum("id"); }
                if(correct != null) { this.currentSlide.setCorrectArray(parseBoolean(correct), answernumInt); }
              }
//            case "Button":
//                if(getAttributeString(attrs,"background") != null) {
//                    this.currentButton = new FLButton(getAttributeString(attrs, "id"),
//                            new Position(getAttributeDouble(attrs, "x"),
//                                    getAttributeDouble(attrs, "y")),
//                            getAttributeDouble(attrs, "width"),
//                            getAttributeDouble(attrs, "height"),
//                            getAttributeString(attrs, "background"));
//
//                } else if ((getAttributeString(attrs, "width") != null) && (getAttributeString(attrs, "height") != null)) {
//                    this.currentButton = new FLButton(getAttributeString(attrs, "id"),
//                            new Position(getAttributeDouble(attrs, "x"),
//                                    getAttributeDouble(attrs, "y")),
//                            getAttributeDouble(attrs, "width"),
//                            getAttributeDouble(attrs, "height"));
//                } else {
//                    this.currentButton = new FLButton(getAttributeString(attrs, "id"),
//                            new Position(getAttributeDouble(attrs, "x"),
//                                    getAttributeDouble(attrs, "y")));
//                }
//                switch(getAttributeString(attrs, "action")) {
//                    case "nextSlide":
//                        this.currentButton.getButton().setOnMouseClicked((clickEvent) -> {
//                            getNavigator().moveNextSlide();
//                        });
//                        this.currentButton.getButton().getStyleClass().add("navNext");
//                        break;
//                    case "moveQ":
//                        this.currentButton.getButton().setOnMouseClicked((clickEvent) -> {
//                            this.presentation.navigator.moveSlide("Q");
//                        });
//                        this.currentButton.getButton().getStyleClass().add("navQ");
//                        break;
//                    case "moveX":
//                        this.currentButton.getButton().setOnMouseClicked((clickEvent) -> {
//                            this.presentation.navigator.moveSlide("X");
//                        });
//                        this.currentButton.getButton().getStyleClass().add("navX");
//                        break;
//                    case "moveS":
//                        this.currentButton.getButton().setOnMouseClicked((clickEvent) -> {
//                            this.presentation.navigator.moveSlide("S");
//                        });
//                        this.currentButton.getButton().getStyleClass().add("navS");
//                        break;
//                    case "correctAnswer": {
//                        String currentSlideID = this.currentSlide.getId();
//                        this.currentButton.getButton().setOnMouseClicked((clickEvent) -> {
//                            this.presentation.playAudio(currentSlideID, "correctAnswer");
//                            this.presentation.showImage(currentSlideID, "correct");
//                        });
//                        this.currentButton.getButton().getStyleClass().add("answer");
//                    }
//                    break;
//                    case "wrongAnswer1": {
//                        String currentSlideID = this.currentSlide.getId();
//                        this.currentButton.getButton().setOnMouseClicked((clickEvent) -> {
//                            this.presentation.playAudio(currentSlideID, "wrongAnswer");
//                            this.presentation.showImage(currentSlideID, "incorrect1");
//                        });
//                        this.currentButton.getButton().getStyleClass().add("answer");
//                    }
//                    break;
//                    case "wrongAnswer2": {
//                        String currentSlideID = this.currentSlide.getId();
//                        this.currentButton.getButton().setOnMouseClicked((clickEvent) -> {
//                            this.presentation.playAudio(currentSlideID, "wrongAnswer");
//                            this.presentation.showImage(currentSlideID, "incorrect2");
//                        });
//                        this.currentButton.getButton().getStyleClass().add("answer");
//                    }
//                    break;
//                    case "wrongAnswer3": {
//                        String currentSlideID = this.currentSlide.getId();
//                        this.currentButton.getButton().setOnMouseClicked((clickEvent) -> {
//                            this.presentation.playAudio(currentSlideID, "wrongAnswer");
//                            this.presentation.showImage(currentSlideID, "incorrect3");
//                        });
//                        this.currentButton.getButton().getStyleClass().add("answer");
//                    }
//                    break;
//                }
//                this.inButton = true;
            default:
                break;
        }

    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        String textString = new String(ch, start, length);

        if(this.inText) {
            if(this.inFormat) {
                this.currentText.add(textString, this.currentColor, this.currentStyle);
            } else {
                this.currentText.add(textString.trim());
            }
        }
        if(this.inButton) {
            this.currentButton.addText(textString.trim());
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String elementName = localName;
        if("".equals(elementName)) {
            elementName = qName;
        }

        switch (elementName) {
            case "Presentation":
                //this.presentation.renderSlide();
                break;
            case "Level" :
                this.presentation.add(this.currentLevel);
                break;
            case "Question" :
                this.currentLevel.add(this.currentQuestion);
                break;
            case "Example" :
                this.currentLevel.add(this.currentExample);
                break;
            case "Slide" :
                if(this.currentSlide.getType().equals("X")) {
                    this.currentExample.add(this.currentSlide);
                } else if(this.currentSlide.getType().equals("Q") || this.currentSlide.getType().equals("A") || this.currentSlide.getType().equals("S")) {
                    this.currentQuestion.add(this.currentSlide);
                }
                break;
            case "Text":
                this.currentSlide.add(this.currentText);
                this.inText = false;
                break;
            case "Format":
                this.inFormat = false;
                break;
            case "Answer":
                //this.currentSlide.add(this.currentButton);
                //this.inButton = false;
                break;
            default:
                break;
        }
    }

    @Override
    public void endDocument() throws SAXException { System.out.println("\nFinished parsing."); }

    private String getAttributeString(Attributes attrs, String qName) { return attrs.getValue(qName); }
    private double getAttributeDouble(Attributes attrs, String qName) { return Double.parseDouble(attrs.getValue(qName)); }
    private int getAttributeInteger(Attributes attrs, String qName) { return Integer.parseInt(attrs.getValue(qName)); }
    private boolean getAttributeBoolean(Attributes attrs, String qName) { return parseBoolean(attrs.getValue(qName)); }

    public Presentation getPresentation() { return presentation; }

    public String trimLeading(String string) { return string.replaceAll("^\\s+", ""); }

}
