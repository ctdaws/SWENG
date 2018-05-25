import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LQHandler extends DefaultHandler {

    private LQPresentation lqPresentation;

    private LQLevel currentLqLevel;
    private int levelCounter = 0;

    private LQExample currentLqExample;
    private int exampleCounter = 0;

    private LQSlide currentLqSlide;
    private PWSText currentPwsText;

    private PWSColors formatColors;
    private PWSFonts formatFonts;

    private int slideNumber = 0;
    private int elementId = 0;

    private boolean bText = false;
    private boolean bFormat = false;

    public LQPresentation getPresentation() {
        return this.lqPresentation;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Starting to parse XML document");
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

        if(type_attr != null) { type = type_attr; }
        else { type = ""; }
        if(stroke_attr != null) { stroke = Double.parseDouble(stroke_attr); }
        else { stroke = 1; }

        String path;

        if(path_attr != null) { path = path_attr; }
        else { path = ""; }

        String id;

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

            if(color_attr != null) { color = color_attr; }
            else { color = "#000000"; }
            if(fill_attr != null) { fill = fill_attr; }
            else { fill = "#000000"; }

            pwsFonts = new PWSFonts(font, italic, bold, underline, textsize);
            pwsColors = new PWSColors(color, fill);

            this.lqPresentation = new LQPresentation(pwsFonts, pwsColors);

            System.out.println("New PWSPresentation created:\n" + lqPresentation);
        }
        else if(qName.equalsIgnoreCase("Level")) {

            if(id_attr != null ) { id = id_attr; }
            else {
//                TODO: Set default level id
                id = "level" + Integer.toString(levelCounter++);
            }

            this.currentLqLevel = new LQLevel(id);
        }
        else if(qName.equalsIgnoreCase("Example")) {

            if(id_attr != null ) { id = id_attr; }
            else {
//                TODO: Set default example id
                id = "example" + Integer.toString(levelCounter++);
            }

            this.currentLqExample = new LQExample(id);
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

            if(color_attr != null) { color = color_attr; }
            else { color = lqPresentation.getPwsColors().getPwsColor(); }
            if(fill_attr != null) { fill = fill_attr; }
            else { fill = lqPresentation.getPwsColors().getPwsFill(); }

            if(start_attr != null) { start = start_attr; }
            else { start = ""; }
            if(duration_attr != null) { duration = Integer.parseInt(duration_attr); }
            else { duration = -1; }

            pwsFonts = new PWSFonts(font, italic, bold, underline, textsize);
            pwsColors = new PWSColors(color, fill);
            pwsTransitions = new PWSTransitions(start, duration);

            elementId = 0;
            this.currentLqSlide = new LQSlide(Integer.toString(slideNumber++), pwsFonts, pwsColors, pwsTransitions);

            System.out.println("New PWSSlide created:\n" + currentLqSlide);
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

            if(color_attr != null) { color = color_attr; }
            else { color = currentLqSlide.getPwsColors().getPwsColor(); }
            if(fill_attr != null) { fill = fill_attr; }
            else { fill = currentLqSlide.getPwsColors().getPwsFill(); }

            pwsFonts = new PWSFonts(font, italic, bold, underline, textsize);
            pwsColors = new PWSColors(color, fill);

            if(id_attr != null) { id = id_attr; }
            else { id = "text" + Integer.toString(elementId++); }

            this.currentPwsText = new PWSText(id, pwsPosition, pwsTransitions, pwsFonts, pwsColors);

            System.out.println("New PWSText created:\n" + currentPwsText);
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

            System.out.println("New Format created:\n" + formatColors + "\n" + formatFonts);
        }
        else if(qName.equalsIgnoreCase("Image")) {

            if(id_attr != null) { id = id_attr; }
            else { id = "image" + Integer.toString(elementId++); }

            PWSImage pwsImage = new PWSImage(id, pwsPosition, pwsTransitions, path);
            currentLqSlide.add(pwsImage);

            System.out.println("New PWSImage created:\n" + pwsImage);
        }
        else if(qName.equalsIgnoreCase("Audio")) {

            if(id_attr != null) { id = id_attr; }
            else { id = "audio" + Integer.toString(elementId++); }

            PWSAudio pwsAudio = new PWSAudio(id, pwsPosition, pwsTransitions, path);
            currentLqSlide.add(pwsAudio);

            System.out.println("New PWSAudio created:\n" + pwsAudio);
        }
        else if(qName.equalsIgnoreCase("Video")) {
            // TODO: Video parsing
        }
        else if(qName.equalsIgnoreCase("Shape")) {

            if(color_attr != null) { color = color_attr; }
            else { color = currentLqSlide.getPwsColors().getPwsColor(); }
            if(fill_attr != null) { fill = fill_attr; }
            else { fill = currentLqSlide.getPwsColors().getPwsFill(); }

            pwsColors = new PWSColors(color, fill);

            if(id_attr != null) { id = id_attr; }
            else { id = "shape" + Integer.toString(elementId++); }

            PWSShape pwsShape = new PWSShape(id, pwsPosition, pwsTransitions, pwsColors, type, stroke);
            currentLqSlide.add(pwsShape);

            System.out.println("New PWSShape created:\n" + pwsShape);
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

            System.out.println("New PWSMeta created:\n" + pwsMeta);
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if(qName.equalsIgnoreCase("Level")) {
            this.lqPresentation.add(this.currentLqLevel);
        }
//        else if(qName.equalsIgnoreCase("Example")) {
//            this.lqPresentation.add(this.currentLqExample);
//        }
        else if (qName.equalsIgnoreCase("Slide")) {
            this.lqPresentation.add(this.currentLqSlide);
            System.out.println("New PWSSlide added:\n" + currentLqSlide);
        } else if (qName.equalsIgnoreCase("Text")) {
            bText = false;
            this.currentLqSlide.add(this.currentPwsText);
            System.out.println("New PWSText added:\n" + currentPwsText);
        }
    }

    public void endDocument() throws SAXException {
        System.out.println("\nFinished parsing file.");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String string = new String(ch, start, length);

        if(bText) {
            if(bFormat) { this.currentPwsText.add(string, this.formatColors, this.formatFonts); }
            else { this.currentPwsText.add(string.trim()); }
        }
    }
}