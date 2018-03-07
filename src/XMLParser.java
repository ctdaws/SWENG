import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.soap.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.Pane;

public class XMLParser extends DefaultHandler{
	public Presentation presentation;
	public String slideID;
	public Defaults defaults;
	public Slide currentSlide;
	public Attributes textAttrs;
	public FLText currentText;

	public boolean inText = false;
	public boolean inFormat = false;
	public TextStyle currentStyle;
	public Colors currentColor;

	public XMLParser(String inputFile, Defaults programDefault){
        defaults = programDefault;

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
		System.out.println(elementName + " started.");

		switch (elementName) {
			case "Presentation":
				presentation = new Presentation(defaults);
                break;
                // TODO implement slide attrs
			case "Slide":
				currentSlide = new Slide(getAttributeValue(attrs, "id"));
				currentSlide.setSlideDefaults(defaults);
				break;
			case "Text": {
                this.inText = true;
			    currentText = new FLText(new Position(Double.parseDouble(getAttributeValue(attrs, "x")), Double.parseDouble(getAttributeValue(attrs,"y"))),
                                         (Double.parseDouble(getAttributeValue(attrs, "x2")) - Double.parseDouble(getAttributeValue(attrs, "x"))),
                                         currentSlide.getSlideDefaults(),
                                         new Transitions("trigger", 0, 0));

                String color = getAttributeValue(attrs, "color");
                String font = getAttributeValue(attrs, "font");
                String textSize = getAttributeValue(attrs, "textsize");
                String italic = getAttributeValue(attrs, "italic");
                String bold = getAttributeValue(attrs, "bold");
                String underline = getAttributeValue(attrs, "underline");

                if(color != null) { currentText.setColor(new Colors(color)); }
                if(font != null) { currentText.getStyle().setFontFamily(font); }
                if(textSize != null) { currentText.getStyle().setSize(Integer.parseInt(textSize)); }
                if(italic != null) { currentText.getStyle().setItalic(Boolean.parseBoolean(italic)); }
                if(bold != null) { currentText.getStyle().setBold(Boolean.parseBoolean(bold)); }
                if(underline != null) { currentText.getStyle().setUnderlined(Boolean.parseBoolean(underline)); }
            }
                break;
            case "Format": {
                this.inText = true;
                this.inFormat = true;
                String color = getAttributeValue(attrs, "color");
                String font = getAttributeValue(attrs, "font");
                String textSize = getAttributeValue(attrs, "textsize");
                String italic = getAttributeValue(attrs, "italic");
                String bold = getAttributeValue(attrs, "bold");
                String underline = getAttributeValue(attrs, "underline");

                this.currentColor = new Colors(currentText.getColor());
                if(color != null) { this.currentColor = new Colors(color); }

                this.currentStyle = new TextStyle(currentText.getStyle());

                if(font != null) { this.currentStyle.setFontFamily(font); }
                if(textSize != null) { this.currentStyle.setSize(Integer.parseInt(textSize)); }
                if(italic != null) { this.currentStyle.setItalic(Boolean.parseBoolean(italic)); }
                if(bold != null) { this.currentStyle.setBold(Boolean.parseBoolean(bold)); }
                if(underline != null) { this.currentStyle.setUnderlined(Boolean.parseBoolean(underline)); }

            }
                break;
			case "Image":
				currentSlide.add(new FLImage(getAttributeValue(attrs, "path"),
                                             new Position(Double.parseDouble(getAttributeValue(attrs, "x")), Double.parseDouble(getAttributeValue(attrs, "y"))),
                                             (Double.parseDouble(getAttributeValue(attrs, "x2"))-Double.parseDouble(getAttributeValue(attrs, "x"))),
                                             (Double.parseDouble(getAttributeValue(attrs, "y2"))-Double.parseDouble(getAttributeValue(attrs, "y")))));
				break;
			case "Audio":
				currentSlide.add(new FLAudio(getAttributeValue(attrs, "path"),
                                             new Position(Double.parseDouble(getAttributeValue(attrs, "x")), Double.parseDouble(getAttributeValue(attrs, "y")))));
				break;
			case "Video":	//NOTE leave until we get module
				break;
			case "Shape":	//NOTE leave until we get module
				break;
                // TODO implement breaks
			case "Br":
				 System.out.print("BREAK");
				break;
			case "Meta":
				presentation.addMeta(new Meta(getAttributeValue(attrs, "key"), getAttributeValue(attrs, "value")));
				break;
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

        System.out.print(textString + " ");
	}

    @Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		String elementName = localName;
		if("".equals(elementName)) {
			elementName = qName;
		}

		if(elementName.equals("Text")) {
            this.currentSlide.add(this.currentText);
            this.inText = false;
            System.out.println("Text added.");
        }
        else if(elementName.equals("Slide")) {
            presentation.addSlide(currentSlide); //XML updated to contain slide id- not in PWS but needed.
        }
        else if(elementName.equals("Format")) {
		    this.inFormat = false;
        }
		System.out.println(elementName + " Ended.");
	}

    @Override
	public void endDocument() throws SAXException { System.out.println("Finished parsing."); }

	private String getAttributeValue(Attributes attrs, String qName) { return attrs.getValue(qName); }

	public Presentation getPresentation() { return presentation; }

	public String trimLeading(String string) { return string.replaceAll("^\\s+", ""); }
}
