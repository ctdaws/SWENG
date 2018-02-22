import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLParser extends DefaultHandler{
	String inputFile = "example.pws";
	PresentationEngine currentPresentation;
	Text currentText;
	Audio currentAudio;
	Image currentImage;
	String currentElement;
	List<PresentationEngine> slideList;

	public List<PresentationEngine> getList() {
		return slideList;
	}

	public XMLParser(String inputFile){
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
	}

	public void startDocument() throws SAXException {
		System.out.println("Started parsing: " + inputFile);
		slideList = new ArrayList<PresentationEngine>();
	}

	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
		
		String elementName = localName;
		if ("".equals(elementName)) {
			elementName = qName;
		}
		
		switch (elementName) {
			case "presentation":
				currentPresentation = new PresentationEngine();
				break;
			case "slide":
				;
				break;
			case "text":
				currentText = new Text();
				break
			case "image":
				currentImage = new Image();
				break;			
			case "audio":
				currentAudio = new Audio();
				break;
			case "video":
				currentVideo = new Video();
				break;
			default:
				currentElement = "none";
				break;
		}
	}


}