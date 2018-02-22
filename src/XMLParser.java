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
	String currentElement;
	List<PresentationEngine> presentationList;

	public List<PresentationEngine> getList() {
		return presentationList;
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
		presentationList = new ArrayList<PresentationEngine>();
	}

	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
		
		// Sort out element name if (no) namespace in use
		String elementName = localName;
		if ("".equals(elementName)) {
			elementName = qName;
		}
		
		// Work out what to do with this element, either create new VideoFile or store
		// information in this VideoFile
		switch (elementName) {
			case "presentation":
				currentPresentation = new PresentationEngine();
				break;
			case "slide":
				currentElement = "slide";
				break;
			case "text":
				currentElement = "text";
				break;
			default:
				currentElement = "none";
				break;
		}
	}


}