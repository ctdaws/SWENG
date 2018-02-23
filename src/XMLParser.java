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
	String font, color, fill, start, path;
	boolean italic, bold, underline;
	double x, y, x2, y2;
	int duration, textsize;

	//PresentationEngine currentPresentation;
	//Text currentText;
	//Audio currentAudio;
	//Image currentImage;
	//String currentElement;
	//Video currentVideo;

	// public List<PresentationEngine> getList() {
	// 	return slideList;
	// }

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

	// public void startDocument() throws SAXException {
	// 	System.out.println("Started parsing: " + inputFile);
	// 	slideList = new ArrayList<PresentationEngine>();
	// }

	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
		
		String elementName = localName;
		if ("".equals(elementName)) {
			elementName = qName;
		}
		font = color = fill = start = path = null;
		italic = bold = underline = false;
		x = y = x2 = y2 = 0.0;
		duration = textsize = 0;
		
		
		switch (elementName) {
			case "Presentation":
				//currentPresentation = new PresentationEngine();
				System.out.println("A Presentation.");
				break;
			case "Slide":
				System.out.println("Slide, Duration: " + attrs.getValue("duration"));
				break;
			case "Text":
				//currentText = new Text();



				System.out.println("Some Text.");
				break;
			case "Image":
				//currentImage = new Image();
				System.out.println("An Image.");
				break;			
			case "Audio":
				//currentAudio = new Audio();
				System.out.println("Some Audio.");
				break;
			case "Video":
				//currentVideo = new Video();
				System.out.println("A Video.");
				break;
			case "Shape":
				System.out.println("A Shape");
				break;
			case "Format":
				System.out.println("Formatted.");
				break;
			case "Br":
				System.out.println("BREAK");
				break;
			case "Meta":
				System.out.println("Metadata read- " + attrs.getValue("key") + ": " + attrs.getValue("value"));
			default:
				//currentElement = "none";
				break;
		}
		
		font = attrs.getValue("font");
		color = attrs.getValue("color");
		path = attrs.getValue("path");
		//bold = attrs.getValue("bold");
		//italic = attrs.getValue("italic");
		//underline = attrs.getValue("underline");
		//x = attrs.getValue("x");
		//y = attrs.getValue("y");
		
		try{

			textsize = Integer.parseInt(attrs.getValue("textsize"));
		} catch (NumberFormatException e){

		System.out.println("Caught NumberFormatException");
		}
		

		System.out.println("Attributes: "+font+" "+textsize+" "+color+" "+bold+" "+italic+" "+underline+" "+x+" "+y+" "+path);
	}



}