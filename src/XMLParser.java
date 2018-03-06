import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.Pane;

public class XMLParser extends DefaultHandler{
	// String inputFile = "example.pws";
	// String font, color, fill, start, path;
	// boolean italic, bold, underline;
	// double x, y, x2, y2;
	// int duration, textsize;

	public Presentation pres;
	public String slideID;
	public Video video;
	public Defaults defaults;


	public Slide currentSlide;

	Attributes textAttrs;

	//PresentationEngine currentPresentation;
	//Text currentText;
	//Audio currentAudio;
	//Image currentImage;
	String currentElement = "none";
	String currentSubElement = "none";
	//Video currentVideo;

	// public List<PresentationEngine> getList() {
	// 	return slideList;
	// }

	public XMLParser(String inputFile, Defaults programDefault){
		try {
			// Use the default parser
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			// Tell the parser to start reading the XML file
			saxParser.parse(inputFile, this);
		}
		// With every try there must be catch to catch the exceptions
		catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}
		catch (SAXException saxe) {
			saxe.printStackTrace();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}

		defaults = programDefault;
	}

	public void startDocument() throws SAXException {
		//System.out.println("Started parsing: " + inputFile);
		//slideList = new ArrayList<PresentationEngine>();
	}

	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {

		String elementName = localName;
		if ("".equals(elementName)) {
			elementName = qName;
		}

		int length = attrs.getLength();

		for(int i = 0; i < length; i++){
			String name = attrs.getQName(i);
			String value = attrs.getValue(i);
			// System.out.print(name + ": " + value + " ");
		}



		switch (elementName) {
			case "Presentation":
				//currentPresentation = new PresentationEngine();
				// System.out.print("A Presentation.");
				pres = new Presentation(defaults);
				// System.out.print("pane created.");
				break;
			case "Slide":
				System.out.print("Slide");
				slideID = attrs.getValue(0);
				currentSlide = new Slide(slideID);
				pres.addSlide(currentSlide); //XML updated to contain slide id- not in PWS but needed.
				System.out.print("Slide created");
				break;
			case "Text":	//TODO Leave for now! - figure formatting first
				//currentText = new Text();
				System.out.print("Text.");
				currentElement = "Text";
				currentSubElement = "Text";
				//currentSlide.add(new FLText());
				break;
			case "Image":
				//System.out.print("Image.");
				currentSlide.add(new FLImage(getAttrs(attrs, "path"), new Position(Double.parseDouble(getAttrs(attrs, "x")),
																						Double.parseDouble(getAttrs(attrs, "y"))),
																						(Double.parseDouble(getAttrs(attrs, "x2"))-Double.parseDouble(getAttrs(attrs, "x"))),
																						(Double.parseDouble(getAttrs(attrs, "y2"))-Double.parseDouble(getAttrs(attrs, "y")))));
				break;
			case "Audio":
				//System.out.print("Audio.");
				currentSlide.add(new FLAudio(getAttrs(attrs, "path"), new Position(Double.parseDouble(getAttrs(attrs, "x")),
																						Double.parseDouble(getAttrs(attrs, "y")))));
				break;
			case "Video":	//TODO leave until we get module
				//currentVideo = new Video();
				// System.out.print("Video.");
				//currentSlide.add(new Video());
				break;
			case "Shape":	//TODO leave until we get module
				//System.out.print("Shape");
				break;
			// case "Format":	//TODO Leave for now! - figure formatting first
			// System.out.print("Formatted.");
			// 	currentElement = "Text";
			// 	currentSubElement = "Format";
			// 	break;
			case "Br":	//TODO Leave for now!
				 System.out.print("BREAK");
				break;
			case "Meta":
				pres.addMeta(new Meta(getAttrs(attrs, "key"), getAttrs(attrs, "value")));
				 System.out.print("Metadata");
			default:
				currentElement = "none";
				break;
		}

		if(currentElement.equals("Text")) {	//stores text attributes for whole of Text element.
			textAttrs = attrs;
		}
	}
	//Read Text within text and format elements
	public void characters(char ch[], int start, int length) throws SAXException {
		String textString = new String(ch, start, length).trim();

		switch (currentSubElement) {	//TODO Leave for now! - figure formatting first
			case "Format":
				//currentSlide.add(new FLText(textString, ));
				System.out.print(textString + " ");
				break;
			default:
				System.out.print(textString + " ");
				break;
		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		currentElement = "none";
		currentSubElement = "none";
		String elementName = localName;
		if ("".equals(elementName)) {
			elementName = qName;
		}
		System.out.println(elementName + " Ended.");
	}

	public void endDocument() throws SAXException {
		System.out.println("Finished parsing.");
	}

	private String getAttrs(Attributes attrs, String qName) {

		return attrs.getValue(attrs.getIndex(qName));
	}

	public Presentation getPresentation() {

		return pres;
	}
}
