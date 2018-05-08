import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.File;
import java.io.IOException;

public class XMLParserNew {

    Presentation parsedPresentation;

    public void PWSParser(File inputFile) {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        try {
            spf.setNamespaceAware(true);
            SAXParser saxParser = spf.newSAXParser();
            if(getFileExtension(inputFile).equalsIgnoreCase("4l")) {
                System.out.println("Setting handler to LQHandler (.4l)");
                LQHandler handler = new LQHandler();
                saxParser.parse(inputFile, handler);
                parsedPresentation = handler.getPresentation();
            }
            else if(getFileExtension(inputFile).equalsIgnoreCase("pws")) {
                System.out.println("Setting handler to PWSHandler (.pws)");
                PWSHandler handler = new PWSHandler();
                saxParser.parse(inputFile, handler);
                parsedPresentation = handler.getPresentation();
            }
            else {
                System.out.println("Setting handler to PWSHandler (default)");
                PWSHandler handler = new PWSHandler();
                saxParser.parse(inputFile, handler);
                parsedPresentation = handler.getPresentation();
            }
        }
        catch (ParserConfigurationException | SAXException | IOException e) { e.printStackTrace(); }
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i >= 0) {
            extension = fileName.substring(i+1);
        }
        return extension;
    }

    public Presentation getParsedPresentation() {
        return parsedPresentation;
    }
}

class PWSHandler extends DefaultHandler {

    private Presentation presentation;
    private Slide currentSlide;
    private TextStyle currentTextStyle;
    private Colors currentColor;

    private FLText currentText;

    private int slideNumber = 0;
    private int elementId = 0;

    private boolean bPresentation = false;
    private boolean bSlide = false;
    private boolean bText = false;
    private boolean bFormat = false;
    private boolean bImage = false;
    private boolean bAudio = false;
    private boolean bVideo = false;
    private boolean bShape = false;
    private boolean bBr = false;
    private boolean bMeta = false;

    private Colors programDefaultColor = new Colors("#000000", "#000000");
    private TextStyle programDefaultStyle = new TextStyle("Arial", 20, false, false, false);
    private Defaults programDefault = new Defaults(programDefaultStyle, programDefaultColor);

    public Presentation getPresentation() {
        return this.presentation;
    }

    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attrs) throws SAXException {
        if(qName.equalsIgnoreCase("Presentation")) {
            bPresentation = true;

            this.presentation = new Presentation(this.programDefault, new Position(1280, 720));

            String color = attrs.getValue("color");
            String fill = attrs.getValue("fill");
            String font = attrs.getValue("font");
            String textSize = attrs.getValue("textsize");
            String italic = attrs.getValue("italic");
            String bold = attrs.getValue("bold");
            String underline = attrs.getValue("underline");

            if(color != null) { this.presentation.getPresentationDefaults().getDefaultColors().setColor(color); }
            if(fill != null) { this.presentation.getPresentationDefaults().getDefaultColors().setFill(fill); }
            if(font != null) { this.presentation.getPresentationDefaults().getDefaultStyle().setFontFamily(font); }
            if(textSize != null) { this.presentation.getPresentationDefaults().getDefaultStyle().setSize(Integer.parseInt(textSize)); }
            if(italic != null) { this.presentation.getPresentationDefaults().getDefaultStyle().setItalic(Boolean.parseBoolean(italic)); }
            if(bold != null) { this.presentation.getPresentationDefaults().getDefaultStyle().setBold(Boolean.parseBoolean(bold)); }
            if(underline != null) { this.presentation.getPresentationDefaults().getDefaultStyle().setUnderlined(Boolean.parseBoolean(underline)); }
        }
        else if(qName.equalsIgnoreCase("Slide")) {
            bSlide = true;
            elementId = 0;
            this.currentSlide = new Slide(Integer.toString(slideNumber++));
            this.currentSlide.setSlideDefaults(this.presentation.getPresentationDefaults());

            String color = attrs.getValue("color");
            String fill = attrs.getValue("fill");
            String font = attrs.getValue("font");
            String textSize = attrs.getValue("textsize");
            String italic = attrs.getValue("italic");
            String bold = attrs.getValue("bold");
            String underline = attrs.getValue("underline");

            if(color != null) { this.currentSlide.getSlideDefaults().getDefaultColors().setColor(color); }
            if(fill != null) { this.currentSlide.getSlideDefaults().getDefaultColors().setFill(fill); }
            if(font != null) { this.currentSlide.getSlideDefaults().getDefaultStyle().setFontFamily(font); }
            if(textSize != null) { this.currentSlide.getSlideDefaults().getDefaultStyle().setSize(Integer.parseInt(textSize)); }
            if(italic != null) { this.currentSlide.getSlideDefaults().getDefaultStyle().setItalic(Boolean.parseBoolean(italic)); }
            if(bold != null) { this.currentSlide.getSlideDefaults().getDefaultStyle().setBold(Boolean.parseBoolean(bold)); }
            if(underline != null) { this.currentSlide.getSlideDefaults().getDefaultStyle().setUnderlined(Boolean.parseBoolean(underline)); }
        }
        else if(qName.equalsIgnoreCase("Text")) {
            bText = true;
            this.currentText = new FLText("text" + Integer.toString(elementId++), new Position(Double.parseDouble(attrs.getValue("x")), Double.parseDouble(attrs.getValue("y"))), Double.parseDouble(attrs.getValue("x2")) - Double.parseDouble(attrs.getValue("x")), this.currentSlide.getSlideDefaults(), new Transitions("trigger", 0, 0));

            String color = attrs.getValue("color");
            String fill = attrs.getValue("fill");
            String font = attrs.getValue("font");
            String textSize = attrs.getValue("textsize");
            String italic = attrs.getValue("italic");
            String bold = attrs.getValue("bold");
            String underline = attrs.getValue("underline");

            if(color != null) { this.currentText.setColor(new Colors(color)); }
            if(font != null) { this.currentText.getStyle().setFontFamily(font); }
            if(textSize != null) { this.currentText.getStyle().setSize(Integer.parseInt(textSize)); }
            if(italic != null) { this.currentText.getStyle().setItalic(Boolean.parseBoolean(italic)); }
            if(bold != null) { this.currentText.getStyle().setBold(Boolean.parseBoolean(bold)); }
            if(underline != null) { this.currentText.getStyle().setUnderlined(Boolean.parseBoolean(underline)); }
        }
        else if(qName.equalsIgnoreCase("Format")) {
            bFormat = true;
            String color = attrs.getValue("color");
            String fill = attrs.getValue("fill");
            String font = attrs.getValue("font");
            String textSize = attrs.getValue("textsize");
            String italic = attrs.getValue("italic");
            String bold = attrs.getValue("bold");
            String underline = attrs.getValue("underline");

            this.currentColor = new Colors(this.currentText.getColor());
            if(color != null) { this.currentColor = new Colors(color); }

            this.currentTextStyle = new TextStyle(this.currentText.getStyle());
            if(font != null) { this.currentTextStyle.setFontFamily(font); }
            if(textSize != null) { this.currentTextStyle.setSize(Integer.parseInt(textSize)); }
            if(italic != null) { this.currentTextStyle.setItalic(Boolean.parseBoolean(italic)); }
            if(bold != null) { this.currentTextStyle.setBold(Boolean.parseBoolean(bold)); }
            if(underline != null) { this.currentTextStyle.setUnderlined(Boolean.parseBoolean(underline)); }
        }
        else if(qName.equalsIgnoreCase("Image")) {
            bImage = true;
            // TODO: Image parsing (with newer image handler)
        }
        else if(qName.equalsIgnoreCase("Audio")) {
            bAudio = true;
            currentSlide.add(new FLAudio("audio" + Integer.toString(elementId++), attrs.getValue("path"), new Position(Double.parseDouble(attrs.getValue("x")), Double.parseDouble(attrs.getValue("y")))));
        }
        else if(qName.equalsIgnoreCase("Video")) {
            bVideo = true;
            // TODO: Video parsing
        }
        else if(qName.equalsIgnoreCase("Shape")) {
            bShape = true;
            // TODO: Shape parsing
        }
        else if(qName.equalsIgnoreCase("Br")) {
            bBr = true;
            if(bText) { this.currentText.add("\n"); }
        }
        else if(qName.equalsIgnoreCase("Meta")) {
            bMeta = true;
            this.presentation.addMeta(new Meta(attrs.getValue("key"), attrs.getValue("value")));
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException{
        if(qName.equalsIgnoreCase("Presentation")) { bPresentation = false; }
        else if(qName.equalsIgnoreCase("Slide")) {
            bSlide = false;
            this.presentation.addSlide(this.currentSlide);
        }
        else if(qName.equalsIgnoreCase("Text")) {
            bText = false;
            this.currentSlide.add(this.currentText);
        }
        else if(qName.equalsIgnoreCase("Format")) { bFormat = false; }
        else if(qName.equalsIgnoreCase("Image")) { bImage = false; }
        else if(qName.equalsIgnoreCase("Audio")) { bAudio = false; }
        else if(qName.equalsIgnoreCase("Video")) { bVideo = false; }
        else if(qName.equalsIgnoreCase("Shape")) { bShape = false; }
        else if(qName.equalsIgnoreCase("Br")) { bBr = false; }
    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String string = new String(ch, start, length);

        if(bText) {
            if(bFormat) { this.currentText.add(string, this.currentColor, this.currentTextStyle); }
            else { this.currentText.add(string); }
        }
    }
}

class LQHandler extends DefaultHandler {

    Presentation presentation;

    public Presentation getPresentation() {
        return this.presentation;
    }

    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attrs) throws SAXException {

    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException{

    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String string = new String(ch, start, length);
    }
}