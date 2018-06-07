import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * PWSHandler
 * The PWSHandler class implements a SAX Parser handler for parsing .pws xml files.
 *
 * @author Samuel Broughton, Ben Grainger, Matt Holt
 * @version 2.4
 */

public class PWSHandler extends DefaultHandler {
//    Presentation being created
    private PWSPresentation pwsPresentation;
//    Current slide
    private PWSSlide currentPwsSlide;
//    Current text object
    private PWSText currentPwsText;
//    Current colour and text formatting
    private PWSColors formatColors;
    private PWSFonts formatFonts;
//    Counters for generating default ids
    private int slideNumber = 0;
    private int elementId = 0;
//    Boolean values for tracking nested elements
    private boolean bText = false;
    private boolean bFormat = false;

    /**
     * Returns generated presentation
     * @return PWSPresentation described by the XML
     */
    public PWSPresentation getPresentation() { return this.pwsPresentation; }

    /**
     * Runs when the parser starts a new document
     */
    @Override
    public void startDocument() {
        System.out.println("Starting to parse XML document");
    }

    /**
     * Runs when the parser starts a new element
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
        if attribute is nonexistent.
         */
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
        String position_attr = attrs.getValue("position");
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
        String position;
//        Colour
        String color;
        String fill;
//        Transitions
        String start;
        int duration;
//        Sets transition attributes to correctly formatted values, else assigns default values
        if(start_attr != null) { start = start_attr; }
//        Set to 0 to appear when slide loads
        else { start = "0"; }
        if(duration_attr != null) { duration = Integer.parseInt(duration_attr); }
//        -1 Used to signify indefinite duration
        else { duration = -1; }
//        Creates PWSTransitions from transition attributes
        pwsTransitions = new PWSTransitions(start, duration);
//        Meta
        String key;
        String value;
//        Shape
        String type;
        double stroke;
//        Sets shape type, else sets to default of rectangle
        if(type_attr != null) { type = type_attr; }
        else { type = "rectangle"; }
//        Sets stroke to correctly formatted value, else assigns default value of 1
        if(stroke_attr != null) { stroke = Double.parseDouble(stroke_attr); }
        else { stroke = 1; }
//        Path
        String path;
//        Sets path, else assigns empty path
        if(path_attr != null) { path = path_attr; }
        else { path = ""; }
//        Handle xml elements
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
            if(position_attr != null) { position = position_attr; }
            else { position = "normal"; }
//            Color
//            Sets attribute to correctly formatted value, else assigns default value
            if(color_attr != null) { color = color_attr; }
            else { color = "#000000"; }
            if(fill_attr != null) { fill = fill_attr; }
            else { fill = "#000000"; }
//            Creates Font and Color objects from attributes
            pwsFonts = new PWSFonts(font, italic, bold, underline, textsize, align, position);
            pwsColors = new PWSColors(color, fill);
//            Creates new PWSPresentation, setting default fonts and colours
            this.pwsPresentation = new PWSPresentation(pwsFonts, pwsColors);
        }
//        Slide
        else if(qName.equalsIgnoreCase("Slide")) {
//            Font
//            Sets font attributes to correctly formatted values, else assigns default values
            if(font_attr != null) { font = font_attr; }
            else { font = pwsPresentation.getPwsFonts().getPwsFont(); }
            if(italic_attr != null) { italic = Boolean.parseBoolean(italic_attr); }
            else { italic = pwsPresentation.getPwsFonts().getPwsItalic(); }
            if(bold_attr != null) { bold = Boolean.parseBoolean(bold_attr); }
            else { bold = pwsPresentation.getPwsFonts().getPwsBold(); }
            if(textsize_attr != null) { textsize = Integer.parseInt(textsize_attr); }
            else { textsize = pwsPresentation.getPwsFonts().getPwsTextsize(); }
            if(underline_attr != null) { underline = Boolean.parseBoolean(underline_attr); }
            else { underline = pwsPresentation.getPwsFonts().getPwsUnderline(); }
            if(align_attr != null) { align = align_attr; }
            else { align = "left"; }
            if(position_attr != null) { position = position_attr; }
            else { position = "normal"; }
//            Color
//            Sets color attributes to correctly formatted values, else assigns default values
            if(color_attr != null) { color = color_attr; }
            else { color = pwsPresentation.getPwsColors().getPwsColor(); }
            if(fill_attr != null) { fill = fill_attr; }
            else { fill = pwsPresentation.getPwsColors().getPwsFill(); }
//            Transitions
//            Sets transition attributes to correctly formatted values, else assigns default values
            if(start_attr != null) { start = start_attr; }
            else { start = ""; }
            if(duration_attr != null) { duration = Integer.parseInt(duration_attr); }
            else { duration = -1; }
//            Create font, color and transition objects
            pwsFonts = new PWSFonts(font, italic, bold, underline, textsize, align, position);
            pwsColors = new PWSColors(color, fill);
            pwsTransitions = new PWSTransitions(start, duration);
//            Reset element id counter
            elementId = 0;
//            Set current slide
            this.currentPwsSlide = new PWSSlide("slide" + Integer.toString(slideNumber++), pwsFonts, pwsColors, pwsTransitions);
        }
//        Text
        else if(qName.equalsIgnoreCase("Text")) {
//            Inside a text tag, used for adding strings found by characters() method
            bText = true;
//            Font
//            Sets font attributes to correctly formatted values, else assigns default values
            if(font_attr != null) { font = font_attr; }
            else { font = currentPwsSlide.getPwsFonts().getPwsFont(); }
            if(italic_attr != null) { italic = Boolean.parseBoolean(italic_attr); }
            else { italic = currentPwsSlide.getPwsFonts().getPwsItalic(); }
            if(bold_attr != null) { bold = Boolean.parseBoolean(bold_attr); }
            else { bold = currentPwsSlide.getPwsFonts().getPwsBold(); }
            if(textsize_attr != null) { textsize = Integer.parseInt(textsize_attr); }
            else { textsize = currentPwsSlide.getPwsFonts().getPwsTextsize(); }
            if(underline_attr != null) { underline = Boolean.parseBoolean(underline_attr); }
            else { underline = currentPwsSlide.getPwsFonts().getPwsUnderline(); }
            if(align_attr != null) { align = align_attr; }
            else { align = "left"; }
            if(position_attr != null) { position = position_attr; }
            else { position = "normal"; }
//            Color
//            Sets color attributes to correctly formatted values, else assigns default values
            if(color_attr != null) { color = color_attr; }
            else { color = currentPwsSlide.getPwsColors().getPwsColor(); }
            if(fill_attr != null) { fill = fill_attr; }
            else { fill = currentPwsSlide.getPwsColors().getPwsFill(); }
//            Create font and color objects
            pwsFonts = new PWSFonts(font, italic, bold, underline, textsize, align, position);
            pwsColors = new PWSColors(color, fill);
//            Set text id
            String id = "text" + Integer.toString(elementId++);
//            Create new PWSText objects with textbox position and default formatting
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
            if(align_attr != null) { align = align_attr; }
            else { align = "left"; }
            if(position_attr != null) { position = position_attr; }
            else { position = "normal"; }
//            Color
//            Sets color attributes to correctly formatted values, else assigns default values
            if(color_attr != null) { color = color_attr; }
            else { color = currentPwsText.getPwsColors().getPwsColor(); }
            if(fill_attr != null) { fill = fill_attr; }
            else { fill = currentPwsText.getPwsColors().getPwsFill(); }
//            Create font and color objects
            pwsFonts = new PWSFonts(font, italic, bold, underline, textsize, align, position);
            pwsColors = new PWSColors(color, fill);
//            Set current formatting styles
            formatColors = pwsColors;
            formatFonts = pwsFonts;
        }
//        Image
        else if(qName.equalsIgnoreCase("Image")) {
//            Create new image, then add it to the current slide
            PWSImage pwsImage = new PWSImage("image" + Integer.toString(elementId++), pwsPosition, pwsTransitions, path);
            currentPwsSlide.add(pwsImage);
        }
//        Audio
        else if(qName.equalsIgnoreCase("Audio")) {
//            Create new audio, then add to current slide
            PWSAudio pwsAudio = new PWSAudio("audio" + Integer.toString(elementId++), pwsPosition, pwsTransitions, path);
            currentPwsSlide.add(pwsAudio);
        }
//        Video
        else if(qName.equalsIgnoreCase("Video")) {
//            Create new video
            ContractVideo contractVideo = new ContractVideo("video" + Integer.toString(elementId++), pwsPosition, pwsTransitions, path);
//            Enable video controls
            contractVideo.enableVideoControls();
//            Add video to current slide
            currentPwsSlide.add(contractVideo);
        }
//        Shape
        else if(qName.equalsIgnoreCase("Shape")) {
//            Color
//            Sets color attributes to correctly formatted values, else assigns default values
            if(color_attr != null) { color = color_attr; }
            else { color = currentPwsSlide.getPwsColors().getPwsColor(); }
            if(fill_attr != null) { fill = fill_attr; }
            else { fill = currentPwsSlide.getPwsColors().getPwsFill(); }
//            Create color object
            pwsColors = new PWSColors(color, fill);
//            Create new shape object, then add it to the current slide
            PWSShape pwsShape = new PWSShape("shape" + Integer.toString(elementId++), pwsPosition, pwsTransitions, pwsColors, type, stroke);
            currentPwsSlide.add(pwsShape);
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
            this.pwsPresentation.add(pwsMeta);
        }
    }

    /**
     * Runs when the parser gets to the end of an element
     * @param qName qualified name of the element
     */
    @Override
    public void endElement(String namespaceURI, String localName, String qName) {
//        Slide
        if (qName.equalsIgnoreCase("Slide")) {
//            Add slide to the presentation
            this.pwsPresentation.add(this.currentPwsSlide);
//            Text
        } else if (qName.equalsIgnoreCase("Text")) {
//            Set tracking flag to signify the parser is no longer in a text element
            bText = false;
//            Add the text object to the slide
            this.currentPwsSlide.add(this.currentPwsText);
//            Format
        } else if(qName.equalsIgnoreCase("Format")) {
//            Set flag to signify that the parser is no longer in a format element
            bFormat = false;
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
     */
    @Override
    public void characters(char[] ch, int start, int length) {
//        Create a new string from given characters
        String string = new String(ch, start, length);
//        If currently in a text element
        if(bText) {
//            If in a format element, add string with formatting to text object
            if(bFormat) { this.currentPwsText.add(string, this.formatColors, this.formatFonts); }
//            Otherwise trim whitespace from either side of the string (removes spaces & tabs used in xml layout
// formatting), and add it to the text object
            else { this.currentPwsText.add(string.trim()); }
        }
    }
}