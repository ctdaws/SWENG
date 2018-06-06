import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * LQHandler
 * The LQHandler class implements a SAX Parser handler for parsing .4l xml files.
 *
 * @author Samuel Broughton, Ben Grainger, Matt Holt, Chris Dawson, Oscar Thorpe
 * @version 2.5
 */

public class LQHandler extends DefaultHandler {
//    Presentation being created
    private LQPresentation lqPresentation;
//    Current level
    private LQLevel currentLqLevel;
    private int levelCounter = 0;
//    Current example
    private LQExample currentLqExample;
    private int exampleCounter = 0;
//    Current question
    private LQQuestion currentLqQuestion;
    private int questionCounter = 0;
//    Current slide
    private LQSlide currentLqSlide;
//    Current media objects
    private PWSText currentPwsText;
    private LQButton currentLqButton;
    private PWSImage currentPwsImage;
    private PWSAudio currentPwsAudio;
//    Current colours and text formatting
    private PWSColors formatColors;
    private PWSFonts formatFonts;
//    Counters for generating default ids
    private int slideNumber = 0;
    private int elementId = 0;
//    Boolean values for tracking nested elements
    private boolean bText = false;
    private boolean bFormat = false;
    private boolean bButton = false;

    /**
     * Returns the generated presentation
     * @return LQPresentation described by the XML
     */
    public LQPresentation getPresentation() { return this.lqPresentation; }

    /**
     * Runs when the parser starts a new document
     */
    @Override
    public void startDocument() {
        System.out.println("Starting to parse XML document");
    }

    /**
     * Runs when the parsers starts a new element
     * @param qName Name of the element
     * @param attrs List of attributes associated with the element
     */
    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attrs) {
//        Attribute objects
        PWSColors pwsColors;
        PWSFonts pwsFonts;
        PWSPosition pwsPosition;
        PWSTransitions pwsTransitions;
        PWSMeta pwsMeta;
        /*
        Queries attrs for all possible known attributes. These values are returned and stored as strings. Will be null
        if attribute is nonexistent
         */
//        PWS Attributes
//        Position Attributes
        String x_attr = attrs.getValue("x");
        String y_attr = attrs.getValue("y");
        String x2_attr = attrs.getValue("x2");
        String y2_attr = attrs.getValue("y2");
//        Fonts Attributes
        String font_attr = attrs.getValue("font");
        String italic_attr = attrs.getValue("italic");
        String bold_attr = attrs.getValue("bold");
        String textsize_attr = attrs.getValue("textsize");
        String underline_attr = attrs.getValue("underline");
        String align_attr = attrs.getValue("align");
//        Colors Attributes
        String color_attr = attrs.getValue("color");
        String fill_attr = attrs.getValue("fill");
//        Transitions Attributes
        String start_attr = attrs.getValue("start");
        String duration_attr = attrs.getValue("duration");
//        Meta Attributes
        String key_attr = attrs.getValue("key");
        String value_attr = attrs.getValue("value");
//        Shape Attributes
        String type_attr = attrs.getValue("type");
        String stroke_attr = attrs.getValue("stroke");
//        Media Attributes
        String path_attr = attrs.getValue("path");
//        ID Attributes
        String id_attr = attrs.getValue("id");
//        LQ Attributes
//        Answer Attributes
        String answerNum_attr = attrs.getValue("answernum");
        String answerCorrect_attr = attrs.getValue("correct");
//        Position
        double x;
        double y;
        double x2;
        double y2;
//        Sets position attributes to correctly formatted values, else assigns default values
        if(x_attr != null) { x = Integer.parseInt(x_attr); }
        else { x = 0; }
        if(y_attr != null) { y = Integer.parseInt(y_attr); }
        else { y = 0; }
        if(x2_attr != null) { x2 = Integer.parseInt(x2_attr); }
        else { x2 = 1280; }
        if(y2_attr != null) { y2 = Integer.parseInt(y2_attr); }
        else { y2 = 720; }
//        Creates PWSPosition from position attributes
        pwsPosition = new PWSPosition(x, y, x2, y2);
//        Font
        String font;
        boolean italic;
        boolean bold;
        int textsize;
        boolean underline;
        String align;
//        Color
        String color;
        String fill;
//        Transitions
        String start;
        int duration;
//        Sets transition attributes to correctly formatted values, else assigns default values
        if(start_attr != null) { start = start_attr; }
//        Set to 0 as to appear when slide loaded
        else { start = "0"; }
        if(duration_attr != null) { duration = Integer.parseInt(duration_attr); }
//        -1 Used to signify indefinite duration
        else { duration = -1; }
//        Creates PWSTransitions from transitions attributes
        pwsTransitions = new PWSTransitions(start, duration);
//        Meta
        String key;
        String value;
//        Shape
        String type;
        double stroke;
//        Sets shape attribute to correctly formatted value, else assigns default value
        if(stroke_attr != null) { stroke = Double.parseDouble(stroke_attr); }
        else { stroke = 1; }
//        Path
        String path;
//        Sets attribute to correctly formatted value, else assigns default value
        if(path_attr != null) { path = path_attr; }
        else { path = ""; }
//        ID
        String id;
//        Answer
        int answerNum;
        boolean answerCorrect;
//        Sets attribute to correctly formatted value, else assigns default value
        if(answerNum_attr != null) { answerNum = Integer.parseInt(answerNum_attr); }
        else { answerNum = 0; }
        if(answerCorrect_attr != null) { answerCorrect = Boolean.parseBoolean(answerCorrect_attr); }
        else { answerCorrect = false; }
//        Create object for relevant element
//        Presentation
        if(qName.equalsIgnoreCase("Presentation")) {
//            Font
//            Sets attribute to correctly formatted value, else assigns default value
            if(font_attr != null) { font = font_attr; }
            else { font = "Arial"; }
            if(italic_attr != null) { italic = Boolean.parseBoolean(italic_attr); }
            else { italic = false; }
            if(bold_attr != null) { bold = Boolean.parseBoolean(bold_attr); }
            else { bold = false; }
            if(textsize_attr != null) { textsize = Integer.parseInt(textsize_attr); }
            else { textsize = 20; }
            if(underline_attr != null) { underline = Boolean.parseBoolean(underline_attr); }
            else { underline = false; }
            if(align_attr != null) { align = align_attr; }
            else { align = "left"; }
//            Color
//            Sets attribute to correctly formatted value, else assigns default value
            if(color_attr != null) { color = color_attr; }
            else { color = "#000000"; }
            if(fill_attr != null) { fill = fill_attr; }
            else { fill = "#000000"; }
//            Creates Font and Color objects from attributes
            pwsFonts = new PWSFonts(font, italic, bold, underline, textsize, align);
            pwsColors = new PWSColors(color, fill);
//            Creates new LQPresentation, setting default fonts and colours
            this.lqPresentation = new LQPresentation(pwsFonts, pwsColors);
        }
//        Level
        else if(qName.equalsIgnoreCase("Level")) {
//            ID
//            Sets ID attribute, else assigns default value
            if(id_attr != null ) { id = id_attr; }
            else { id = "l" + Integer.toString(levelCounter++); }
//            Resets example and question default id counters
            exampleCounter = 0;
            questionCounter = 0;
//            Sets current level
            this.currentLqLevel = new LQLevel(id);
        }
//        Example
        else if(qName.equalsIgnoreCase("Example")) {
//            ID
//            Sets ID attribute, else assigns default value
            if(id_attr != null ) { id = id_attr; }
            else { id = "x" + Integer.toString(levelCounter++); }
//            Sets current level
            this.currentLqExample = new LQExample(id);
        }
//        Question
        else if(qName.equalsIgnoreCase("Question")) {
//            ID
//            Sets ID attribute, else assigns default value
            if(id_attr != null) { id= id_attr; }
            else { id = "q" + Integer.toString(questionCounter++); }
//            Sets current question
            this.currentLqQuestion = new LQQuestion(id);
        }
//        Slide
        else if(qName.equalsIgnoreCase("Slide")) {
//            Font
//            Sets font attributes to correctly formatted values, else assigns default values
            if(font_attr != null) { font = font_attr; }
            else { font = lqPresentation.getPwsFonts().getPwsFont(); }
            if(italic_attr != null) { italic = Boolean.parseBoolean(italic_attr); }
            else { italic = lqPresentation.getPwsFonts().getPwsItalic(); }
            if(bold_attr != null) { bold = Boolean.parseBoolean(bold_attr); }
            else { bold = lqPresentation.getPwsFonts().getPwsBold(); }
            if(textsize_attr != null) { textsize = Integer.parseInt(textsize_attr); }
            else { textsize = lqPresentation.getPwsFonts().getPwsTextsize(); }
            if(underline_attr != null) { underline = Boolean.parseBoolean(underline_attr); }
            else { underline = lqPresentation.getPwsFonts().getPwsUnderline(); }
            if(align_attr != null) { align = align_attr; }
            else { align = lqPresentation.getPwsFonts().getLQAlign(); }
//            Color
//            Sets color attributes to correctly formatted values, else assigns default values
            if(color_attr != null) { color = color_attr; }
            else { color = lqPresentation.getPwsColors().getPwsColor(); }
            if(fill_attr != null) { fill = fill_attr; }
            else { fill = lqPresentation.getPwsColors().getPwsFill(); }
//            Transitions
//            Sets transition attributes to correctly formatted values, else assigns default values
            if(start_attr != null) { start = start_attr; }
            else { start = ""; }
            if(duration_attr != null) { duration = Integer.parseInt(duration_attr); }
            else { duration = -1; }
//            ID
//            Sets ID attribute, else assigns default
            if(id_attr != null) { id = id_attr; }
            else { id = "s" + Integer.toString(slideNumber++); }
//            Type
//            Sets type attribute, else assigns default type to 'example'
            if(type_attr != null) { type = type_attr; }
            else { type = "X"; }
//            Create font, color and transition objects
            pwsFonts = new PWSFonts(font, italic, bold, underline, textsize, align);
            pwsColors = new PWSColors(color, fill);
            pwsTransitions = new PWSTransitions(start, duration);
//            Reset slide element default id count
            elementId = 0;
//            Set current slide
            this.currentLqSlide = new LQSlide(id, type, pwsFonts, pwsColors, pwsTransitions);
        }
//        Text
        else if(qName.equalsIgnoreCase("Text")) {
//            Inside a text tag, used for adding strings found by characters() method
            bText = true;
//            Font
//            Sets font attributes to correctly formatted values, else assigns default values
            if(font_attr != null) { font = font_attr; }
            else { font = currentLqSlide.getPwsFonts().getPwsFont(); }
            if(italic_attr != null) { italic = Boolean.parseBoolean(italic_attr); }
            else { italic = currentLqSlide.getPwsFonts().getPwsItalic(); }
            if(bold_attr != null) { bold = Boolean.parseBoolean(bold_attr); }
            else { bold = currentLqSlide.getPwsFonts().getPwsBold(); }
            if(textsize_attr != null) { textsize = Integer.parseInt(textsize_attr); }
            else { textsize = currentLqSlide.getPwsFonts().getPwsTextsize(); }
            if(underline_attr != null) { underline = Boolean.parseBoolean(underline_attr); }
            else { underline = currentLqSlide.getPwsFonts().getPwsUnderline(); }
            if(align_attr != null) { align = align_attr; }
            else { align = currentLqSlide.getPwsFonts().getLQAlign(); }
//            Color
//            Sets color attributes to correctly formatted values, else assigns default values
            if(color_attr != null) { color = color_attr; }
            else { color = currentLqSlide.getPwsColors().getPwsColor(); }
            if(fill_attr != null) { fill = fill_attr; }
            else { fill = currentLqSlide.getPwsColors().getPwsFill(); }
//            Create font and color objects
            pwsFonts = new PWSFonts(font, italic, bold, underline, textsize, align);
            pwsColors = new PWSColors(color, fill);
//            Set text id, else assign default id
            if(id_attr != null) { id = id_attr; }
            else { id = "text" + Integer.toString(elementId++); }
//            Create ne PWSText objects with textbox position and default formatting
            this.currentPwsText = new PWSText(id, pwsPosition, pwsTransitions, pwsFonts, pwsColors);
        }
//        Format
        else if(qName.equalsIgnoreCase("Format")) {
//            Inside a format tag, set so that characters() adds text with appropriate formatting applied
            bFormat = true;
//            Font
//            Sets font attributes to correctly formatted values, else assigns default values
            if(font_attr != null) { font = font_attr; }
            else { font = currentPwsText.getPwsFonts().getPwsFont(); }
            if(italic_attr != null) { italic = Boolean.parseBoolean(italic_attr); }
            else { italic = currentPwsText.getPwsFonts().getPwsItalic(); }
            if(bold_attr != null) { bold = Boolean.parseBoolean(bold_attr); }
            else { bold = currentPwsText.getPwsFonts().getPwsBold(); }
            if(textsize_attr != null) { textsize = Integer.parseInt(textsize_attr); }
            else { textsize = currentPwsText.getPwsFonts().getPwsTextsize(); }
            if(underline_attr != null) { underline = Boolean.parseBoolean(underline_attr); }
            else { underline = currentPwsText.getPwsFonts().getPwsUnderline(); }
//            Color
//            Sets color attributes to correctly formatted values, else assigns default values
            if(color_attr != null) { color = color_attr; }
            else { color = currentPwsText.getPwsColors().getPwsColor(); }
            if(fill_attr != null) { fill = fill_attr; }
            else { fill = currentPwsText.getPwsColors().getPwsFill(); }
//            Create font and color objects
            pwsFonts = new PWSFonts(font, italic, bold, underline, textsize);
            pwsColors = new PWSColors(color, fill);
//            Set current formatting styles
            formatColors = pwsColors;
            formatFonts = pwsFonts;
        }
//        Image
        else if(qName.equalsIgnoreCase("Image")) {
//            ID
//            Sets ID attribute, else assigns default
            if(id_attr != null) { id = id_attr; }
            else { id = "image" + Integer.toString(elementId++); }
//            Create new image, then add it to the current slide
            currentPwsImage = new PWSImage(id, pwsPosition, pwsTransitions, path);
            currentLqSlide.add(currentPwsImage);
        }
//        Audio
        else if(qName.equalsIgnoreCase("Audio")) {
//            ID
//            Sets ID attribute, else assigns default
            if(id_attr != null) { id = id_attr; }
            else { id = "audio" + Integer.toString(elementId++); }
//            Create new audio, then add to current slide
            currentPwsAudio = new PWSAudio(id, pwsPosition, pwsTransitions, path);
            currentLqSlide.add(currentPwsAudio);
        }
//        Video
        else if(qName.equalsIgnoreCase("Video")) {
//            ID
//            Sets ID attribute, else assigns default
            if(id_attr != null) { id = id_attr; }
            else { id = "video" + Integer.toString(elementId++); }
//            Create new video
            ContractVideo contractVideo = new ContractVideo(id, pwsPosition, pwsTransitions, path);
//            Enable video controls
            contractVideo.enableVideoControls();
//            Add video to current slide
            currentLqSlide.add(contractVideo);
        }
//        Shape
        else if(qName.equalsIgnoreCase("Shape")) {
//            Color
//            Sets color attributes to correctly formatted values, else assigns default values
            if(color_attr != null) { color = color_attr; }
            else { color = currentLqSlide.getPwsColors().getPwsColor(); }
            if(fill_attr != null) { fill = fill_attr; }
            else { fill = currentLqSlide.getPwsColors().getPwsFill(); }
//            Shape
//            Set shape type, else set to default of rectangle
            if(type_attr != null) { type = type_attr; }
            else { type = "rectangle"; }
//            Create color object
            pwsColors = new PWSColors(color, fill);
//            ID
//            Sets ID attribute, else assigns default
            if(id_attr != null) { id = id_attr; }
            else { id = "shape" + Integer.toString(elementId++); }
//            Create new shape object, then add it to the current slide
            PWSShape pwsShape = new PWSShape(id, pwsPosition, pwsTransitions, pwsColors, type, stroke);
            currentLqSlide.add(pwsShape);
        }
//        Break
        else if(qName.equalsIgnoreCase("Br")) {
//            If the break element is inside a text element, add a new line to the text
            if(bText) { this.currentPwsText.add("\n"); }
        }
//        Meta
        else if(qName.equalsIgnoreCase("Meta")) {
//            Get key/value pair from attributes, else set to empty strings
            if(key_attr != null) { key = key_attr; }
            else { key = ""; }
            if(value_attr != null) { value = value_attr; }
            else { value = ""; }
//            Create new meta object, then add to the presentation
            pwsMeta = new PWSMeta(key, value);
            this.lqPresentation.add(pwsMeta);
        }
//        Answer
        else if(qName.equalsIgnoreCase("Answer")) {
//            Answers are treated as lqButtons, tracked for adding text/audio/image
            bButton = true;
//            ID
//            Sets ID attribute, else assigns default
            if(id_attr != null) { id = id_attr; }
            else { id = "a" + Integer.toString(elementId++); }
//            Re-assign answerNum for use elsewhere
            int answerNumInt = answerNum - 1;
//            Define button positions
            PWSPosition position = new PWSPosition(0, 0, 0, 0);
            PWSPosition position1 = new PWSPosition(125, 399, 625, 474);
            PWSPosition position2 = new PWSPosition(649, 399, 1149, 474);
            PWSPosition position3 = new PWSPosition(125, 485, 625, 560);
            PWSPosition position4 = new PWSPosition(649, 485, 1149, 560);
//            Define button background images
            String answerBanner1 = "answer_1.png";
            String answerBanner2 = "answers_2.png";
            String answerBanner3 = "answers_3.png";
            String answerBanner4 = "answers_4.png";
//            Set definitions used based on answer number
            if (answerNumInt == 0){ position = position1;}
            if (answerNumInt == 1){ position = position2; answerBanner1 = answerBanner2; }
            if (answerNumInt == 2){ position = position3; answerBanner1 = answerBanner3; }
            if (answerNumInt == 3){ position = position4; answerBanner1 = answerBanner4; }
            this.currentLqSlide.setAnswerNum("id");
//            Give slide answer properties
            this.currentLqSlide.setCorrectArray(answerCorrect, answerNumInt);
//            Create new button with background, then add it to the slide
            currentLqButton = new LQButton(id, position, new PWSTransitions("0", -1), answerBanner1);
            this.currentLqSlide.add(this.currentLqButton);
        }
    }

    /**
     * Runs when the parser gets to the end of an element
     * @param qName qualified name of the element
     */
    @Override
    public void endElement(String namespaceURI, String localName, String qName) {
//        Level
        if(qName.equalsIgnoreCase("Level")) {
//            Add the level to the presentation
            this.lqPresentation.add(this.currentLqLevel);
        }
//        Question
        else if(qName.equalsIgnoreCase("Question")) {
//            Add the question to the level
            this.currentLqLevel.add(this.currentLqQuestion);
        }
//        Example
        else if(qName.equalsIgnoreCase("Example")) {
//            Add the example to the level
            this.currentLqLevel.add(this.currentLqExample);
        }
//        Slide
        else if (qName.equalsIgnoreCase("Slide")) {
//            If slide is of type example, add the slide to the example
            if(this.currentLqSlide.getLQSlideType().equalsIgnoreCase("x")) { this.currentLqExample.add(this.currentLqSlide); }
//            If the slide is of type answer, set the actionListeners to the answer buttons, then add the slide to the question
            else if(this.currentLqSlide.getLQSlideType().equalsIgnoreCase("a")) {
                this.currentLqSlide.setActionListeners();
                this.currentLqQuestion.add(this.currentLqSlide);
            }
//            If the slide is of type solution or question, add it to the question
            else if(this.currentLqSlide.getLQSlideType().equalsIgnoreCase("s") || this.currentLqSlide.getLQSlideType().equalsIgnoreCase("q")) {
                this.currentLqQuestion.add(this.currentLqSlide);
            }
        }
//        Text
        else if (qName.equalsIgnoreCase("Text")) {
//            Set tracking flag to signify the parser is no longer in a text element
            bText = false;
//            Add the text onject to the slide
            this.currentLqSlide.add(this.currentPwsText);
        }
//        Format
        else if(qName.equalsIgnoreCase("Format")) {
//            Set flag to signify that the parser is no longer in a format element
            bFormat = false;
        }
//        Image
        else if(qName.equalsIgnoreCase("Image")) {
//            If the image was added inside of a button, add a reference to it in the button
            if(bButton) {
                this.currentLqButton.add(currentPwsImage);
            }
        }
//        Audio
        else if(qName.equalsIgnoreCase("Audio")) {
//            If the image was added inside of a button, add a reference to it in the button
            if(bButton) {
                this.currentLqButton.add(currentPwsAudio);
            }
        }
//        Answer
        else if(qName.equalsIgnoreCase("Answer")) {
//            Set flag to signify that the parser is no longer in a button
            bButton = false;
        }

    }

    /**
     * Runs when the parser reaches the end of the file
     */
    public void endDocument() {
        System.out.println("\nFinished parsing file.");
    }

    /**
     * Runs when parser finds characters outside of element tags
     * @param ch Array of characters making up the document
     * @param start The index of the first character
     * @param length The number of characters that make up the string
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
//        Create a new string from given characters
        String string = new String(ch, start, length);
//        If currently in a text element
        if(bText) {
//            If in a format element, add string with formatting to text object
            if(bFormat) { this.currentPwsText.add(string, this.formatColors, this.formatFonts); }
//            Otherwise trim whitespace from either side of the string (removes spaces & tabs used in xml layout
// formatting), and add it to the text onbect
            else { this.currentPwsText.add(string.trim()); }
        }
//        If in a button
        if(bButton) {
//            Add the text to the button
            this.currentLqButton.add(string.trim());
        }
    }
}