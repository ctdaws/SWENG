import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LQHandler extends DefaultHandler {

    private LQPresentation lqPresentation;

    private LQLevel currentLqLevel;
    private int levelCounter = 0;

    private LQExample currentLqExample;
    private int exampleCounter = 0;

    private LQQuestion currentLqQuestion;
    private int questionCounter = 0;

    private LQSlide currentLqSlide;

    private PWSText currentPwsText;
    private LQButton currentLqButton;
    private PWSText currentLqButtonText;
    private PWSImage currentPwsImage;
    private PWSAudio currentPwsAudio;

    private PWSColors formatColors;
    private PWSFonts formatFonts;

    private int slideNumber = 0;
    private int elementId = 0;

    private boolean bText = false;
    private boolean bFormat = false;
    private boolean bButton = false;

    public LQPresentation getPresentation() {
        return this.lqPresentation;
    }

    @Override
    public void startDocument() throws SAXException {
//        System.out.println("Starting to parse XML document");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attrs) throws SAXException {

        PWSColors pwsColors;
        PWSFonts pwsFonts;
        PWSPosition pwsPosition;
        PWSTransitions pwsTransitions;
        PWSMeta pwsMeta;

        // Position Attributes
        String x_attr = attrs.getValue("x");
        String y_attr = attrs.getValue("y");
        String x2_attr = attrs.getValue("x2");
        String y2_attr = attrs.getValue("y2");
        // Fonts Attributes
        String font_attr = attrs.getValue("font");
        String italic_attr = attrs.getValue("italic");
        String bold_attr = attrs.getValue("bold");
        String textsize_attr = attrs.getValue("textsize");
        String underline_attr = attrs.getValue("underline");
        String align_attr = attrs.getValue("align");
        // Colors Attributes
        String color_attr = attrs.getValue("color");
        String fill_attr = attrs.getValue("fill");
        // Transitions Attributes
        String start_attr = attrs.getValue("start");
        String duration_attr = attrs.getValue("duration");
        // Meta Attributes
        String key_attr = attrs.getValue("key");
        String value_attr = attrs.getValue("value");
        // Shape Attributes
        String type_attr = attrs.getValue("type");
        String stroke_attr = attrs.getValue("stroke");
        // Media Attributes
        String path_attr = attrs.getValue("path");
        // ID Attributes
        String id_attr = attrs.getValue("id");

        // Button Attributes
        String answerNum_attr = attrs.getValue("answernum");
        String answerCorrect_attr = attrs.getValue("correct");

        double x;
        double y;
        double x2;
        double y2;

        if(x_attr != null) { x = Integer.parseInt(x_attr); }
        else { x = 0; }
        if(y_attr != null) { y = Integer.parseInt(y_attr); }
        else { y = 0; }
        if(x2_attr != null) { x2 = Integer.parseInt(x2_attr); }
        else { x2 = 0; }
        if(y2_attr != null) { y2 = Integer.parseInt(y2_attr); }
        else { y2 = 0; }

        pwsPosition = new PWSPosition(x, y, x2, y2);

        String font;
        boolean italic;
        boolean bold;
        int textsize;
        boolean underline;
        String align;

        String color;
        String fill;

        String start;
        int duration;

        if(start_attr != null) { start = start_attr; }
        else { start = "0"; }
        if(duration_attr != null) { duration = Integer.parseInt(duration_attr); }
        else { duration = -1; }

        pwsTransitions = new PWSTransitions(start, duration);

        String key;
        String value;

        String type;
        double stroke;

        if(stroke_attr != null) { stroke = Double.parseDouble(stroke_attr); }
        else { stroke = 1; }

        String path;

        if(path_attr != null) { path = path_attr; }
        else { path = ""; }

        String id;

        int answerNum;
        boolean answerCorrect;

        if(answerNum_attr != null) { answerNum = Integer.parseInt(answerNum_attr); }
        else { answerNum = 0; }
        if(answerCorrect_attr != null) { answerCorrect = Boolean.parseBoolean(answerCorrect_attr); }
        else { answerCorrect = false; }

        if(qName.equalsIgnoreCase("Presentation")) {

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

            if(color_attr != null) { color = color_attr; }
            else { color = "#000000"; }
            if(fill_attr != null) { fill = fill_attr; }
            else { fill = "#000000"; }

            pwsFonts = new PWSFonts(font, italic, bold, underline, textsize, align);
            pwsColors = new PWSColors(color, fill);

            this.lqPresentation = new LQPresentation(pwsFonts, pwsColors);

//            System.out.println("New PWSPresentation created:\n" + lqPresentation);
        }
        else if(qName.equalsIgnoreCase("Level")) {

            if(id_attr != null ) { id = id_attr; }
            else {
                id = "l" + Integer.toString(levelCounter++);
            }

            exampleCounter = 0;
            questionCounter = 0;

            this.currentLqLevel = new LQLevel(id);

//            System.out.println("New LQLevel created:\n" + currentLqLevel);
        }
        else if(qName.equalsIgnoreCase("Example")) {

            if(id_attr != null ) { id = id_attr; }
            else {
                id = "x" + Integer.toString(levelCounter++);
            }

            this.currentLqExample = new LQExample(id);

//            System.out.println("New LQExample created:\n" + currentLqExample);
        }
        else if(qName.equalsIgnoreCase("Question")) {

            if(id_attr != null) { id= id_attr; }
            else {
                id = "q" + Integer.toString(questionCounter++);
            }

            this.currentLqQuestion = new LQQuestion(id);

//            System.out.println("New LQQuestion created:\n" + currentLqQuestion);
        }
        else if(qName.equalsIgnoreCase("Slide")) {

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

            if(color_attr != null) { color = color_attr; }
            else { color = lqPresentation.getPwsColors().getPwsColor(); }
            if(fill_attr != null) { fill = fill_attr; }
            else { fill = lqPresentation.getPwsColors().getPwsFill(); }

            if(start_attr != null) { start = start_attr; }
            else { start = ""; }
            if(duration_attr != null) { duration = Integer.parseInt(duration_attr); }
            else { duration = -1; }

            if(id_attr != null) { id = id_attr; }
            else { id = "s" + Integer.toString(slideNumber++); }

            if(type_attr != null) { type = type_attr; }
            else { type = "X"; }

            pwsFonts = new PWSFonts(font, italic, bold, underline, textsize, align);
            pwsColors = new PWSColors(color, fill);
            pwsTransitions = new PWSTransitions(start, duration);

            elementId = 0;
            this.currentLqSlide = new LQSlide(id, type, pwsFonts, pwsColors, pwsTransitions);

//            System.out.println("New PWSSlide created:\n" + currentLqSlide);
        }
        else if(qName.equalsIgnoreCase("Text")) {
            bText = true;

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

            if(color_attr != null) { color = color_attr; }
            else { color = currentLqSlide.getPwsColors().getPwsColor(); }
            if(fill_attr != null) { fill = fill_attr; }
            else { fill = currentLqSlide.getPwsColors().getPwsFill(); }

            pwsFonts = new PWSFonts(font, italic, bold, underline, textsize, align);
            pwsColors = new PWSColors(color, fill);

            if(id_attr != null) { id = id_attr; }
            else { id = "text" + Integer.toString(elementId++); }

            this.currentPwsText = new PWSText(id, pwsPosition, pwsTransitions, pwsFonts, pwsColors);

//            System.out.println("New PWSText created:\n" + currentPwsText);
        }
        else if(qName.equalsIgnoreCase("Format")) {
            bFormat = true;

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

            if(color_attr != null) { color = color_attr; }
            else { color = currentPwsText.getPwsColors().getPwsColor(); }
            if(fill_attr != null) { fill = fill_attr; }
            else { fill = currentPwsText.getPwsColors().getPwsFill(); }

            pwsFonts = new PWSFonts(font, italic, bold, underline, textsize);
            pwsColors = new PWSColors(color, fill);

            formatColors = pwsColors;
            formatFonts = pwsFonts;

//            System.out.println("New Format created:\n" + formatColors + "\n" + formatFonts);
        }
        else if(qName.equalsIgnoreCase("Image")) {

            if(id_attr != null) { id = id_attr; }
            else { id = "image" + Integer.toString(elementId++); }

            currentPwsImage = new PWSImage(id, pwsPosition, pwsTransitions, path);
            currentLqSlide.add(currentPwsImage);

//            System.out.println("New PWSImage created:\n" + pwsImage);
        }
        else if(qName.equalsIgnoreCase("Audio")) {

            if(id_attr != null) { id = id_attr; }
            else { id = "audio" + Integer.toString(elementId++); }
            currentPwsAudio = new PWSAudio(id, pwsPosition, pwsTransitions, path);
            currentLqSlide.add(currentPwsAudio);

//            System.out.println("New PWSAudio created:\n" + pwsAudio);
        }
        else if(qName.equalsIgnoreCase("Video")) {
            ContractVideo contractVideo = new ContractVideo("video" + Integer.toString(elementId++), pwsPosition, pwsTransitions, path);
            contractVideo.enableVideoControls();
            currentLqSlide.add(contractVideo);
        }
        else if(qName.equalsIgnoreCase("Shape")) {

            if(color_attr != null) { color = color_attr; }
            else { color = currentLqSlide.getPwsColors().getPwsColor(); }
            if(fill_attr != null) { fill = fill_attr; }
            else { fill = currentLqSlide.getPwsColors().getPwsFill(); }

            if(type_attr != null) { type = type_attr; }
            else { type = ""; }

            pwsColors = new PWSColors(color, fill);

            if(id_attr != null) { id = id_attr; }
            else { id = "shape" + Integer.toString(elementId++); }

            PWSShape pwsShape = new PWSShape(id, pwsPosition, pwsTransitions, pwsColors, type, stroke);
            currentLqSlide.add(pwsShape);

//            System.out.println("New PWSShape created:\n" + pwsShape);
        }
        else if(qName.equalsIgnoreCase("Br")) {
            if(bText) { this.currentPwsText.add("\n"); }
        }
        else if(qName.equalsIgnoreCase("Meta")) {

            if(key_attr != null) { key = key_attr; }
            else { key = ""; }
            if(value_attr != null) { value = value_attr; }
            else { value = ""; }

            pwsMeta = new PWSMeta(key, value);

            this.lqPresentation.add(pwsMeta);

//            System.out.println("New PWSMeta created:\n" + pwsMeta);
        }
        else if(qName.equalsIgnoreCase("Answer")) {
            bButton = true;

            if(id_attr != null) { id = id_attr; }
            else { id = "a" + Integer.toString(elementId++); }

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

            if(color_attr != null) { color = color_attr; }
            else { color = currentLqSlide.getPwsColors().getPwsColor(); }
            if(fill_attr != null) { fill = fill_attr; }
            else { fill = currentLqSlide.getPwsColors().getPwsFill(); }

            pwsFonts = new PWSFonts(font, italic, bold, underline, textsize, align);
            pwsColors = new PWSColors(color, fill);

            int answerNumInt = answerNum - 1;

//            System.out.println("correct =  " + answerCorrect);
//            System.out.println("answerNumInt =  " + (answerNumInt));

            PWSPosition position = new PWSPosition(0, 0, 0, 0);
            PWSPosition position1 = new PWSPosition(125, 399, 625, 474);
            PWSPosition position2 = new PWSPosition(649, 399, 1149, 474);
            PWSPosition position3 = new PWSPosition(125, 485, 625, 560);
            PWSPosition position4 = new PWSPosition(649, 485, 1149, 560);

            String answerBanner1 = "answer_1.png";
            String answerBanner2 = "answers_2.png";
            String answerBanner3 = "answers_3.png";
            String answerBanner4 = "answers_4.png";

            if (answerNumInt == 0){ position = position1;}
            if (answerNumInt == 1){ position = position2; answerBanner1 = answerBanner2; }
            if (answerNumInt == 2){ position = position3; answerBanner1 = answerBanner3; }
            if (answerNumInt == 3){ position = position4; answerBanner1 = answerBanner4; }

//            System.out.println("Answer Created");

            this.currentLqSlide.setAnswerNum("id");

            this.currentLqSlide.setCorrectArray(answerCorrect, answerNumInt);

//            currentLqButtonText = new PWSText("answerText", new PWSPosition(0, 0, 1280, 0), new PWSTransitions("0", -1), pwsFonts, pwsColors);

//            System.out.println(id);

            currentLqButton = new LQButton(id, position, new PWSTransitions("0", -1), answerBanner1);
            this.currentLqSlide.add(this.currentLqButton);

//            if(this.currentLqSlide.getCorrectArray()[answerNumInt] != null && this.currentLqSlide.getCorrectArray()[answerNumInt]){
//                this.currentLqSlide.correctImage = new PWSImage("correct image", new PWSPosition(position.getX(), position.getY(), position.getX() + 100, position.getY() + 100), new PWSTransitions("0", -1),   "correct.png");
//                this.currentLqSlide.add(this.currentLqSlide.correctImage);
//            }else {
//                this.currentLqSlide.incorrectImage1 = new PWSImage("incorrect image", new PWSPosition(position.getX(), position.getY(), position.getX() + 100, position.getY() + 100), new PWSTransitions("0", -1), "incorrect.png");
//                this.currentLqSlide.add(this.currentLqSlide.incorrectImage1);
//            }

//            System.out.println("New LQButton created:\n" + currentLqButton);
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if(qName.equalsIgnoreCase("Level")) {
            this.lqPresentation.add(this.currentLqLevel);
        }
        else if(qName.equalsIgnoreCase("Question")) {
            this.currentLqLevel.add(this.currentLqQuestion);
        }
        else if(qName.equalsIgnoreCase("Example")) {
            this.currentLqLevel.add(this.currentLqExample);
        }
        else if (qName.equalsIgnoreCase("Slide")) {
//            this.lqPresentation.add(this.currentLqSlide);
//            System.out.println("New PWSSlide added:\n" + currentLqSlide);

            if(this.currentLqSlide.getLQSlideType().equalsIgnoreCase("x")) { this.currentLqExample.add(this.currentLqSlide); }
//            else if(currentLqSlide.getLQSlideType().equalsIgnoreCase("q") || currentLqSlide.getLQSlideType().equalsIgnoreCase("a") || currentLqSlide.getLQSlideType().equalsIgnoreCase("s")) { this.currentLqQuestion.add(this.currentLqSlide); }
            else if(this.currentLqSlide.getLQSlideType().equalsIgnoreCase("a")) {
//                System.out.println("Answer slide created");
                this.currentLqSlide.setActionListeners();
                this.currentLqQuestion.add(this.currentLqSlide);
            }
            else if(this.currentLqSlide.getLQSlideType().equalsIgnoreCase("s") || this.currentLqSlide.getLQSlideType().equalsIgnoreCase("q")) {
                this.currentLqQuestion.add(this.currentLqSlide);
            }
        }
        else if (qName.equalsIgnoreCase("Text")) {
            bText = false;
            if(bButton) {
                this.currentLqButton.add(currentPwsText);
            }
            else {
                this.currentLqSlide.add(this.currentPwsText);
            }
//            System.out.println("New PWSText added:\n" + currentPwsText);
        }
        else if(qName.equalsIgnoreCase("Format")) {
            bFormat = false;
        }
        else if(qName.equalsIgnoreCase("Image")) {
            if(bButton) {
                this.currentLqButton.add(currentPwsImage);
            }
        }
        else if(qName.equalsIgnoreCase("Audio")) {
            if(bButton) {
                this.currentLqButton.add(currentPwsAudio);
            }
        }
        else if(qName.equalsIgnoreCase("Answer")) {
            bButton = false;
            this.currentLqButton.setButton();
//            this.currentLqSlide.add(this.currentLqButton);
        }

    }

    public void endDocument() throws SAXException {
//        System.out.println("\nFinished parsing file.");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String string = new String(ch, start, length);

        if(bText) {
            if(bFormat) { this.currentPwsText.add(string, this.formatColors, this.formatFonts); }
                else { this.currentPwsText.add(string.trim()); }
        }
//        else {
//            if(bButton) {
//                this.currentLqButton.add(string.trim());
//            }
//        }
    }
}