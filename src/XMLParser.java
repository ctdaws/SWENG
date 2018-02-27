import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.Pane;


// //unneeded
// import javafx.application.Application;


// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.layout.Pane;
// import javafx.stage.Stage;
// import javafx.application.Platform;

// import javafx.scene.input.KeyEvent;
// import javafx.scene.input.KeyCode;
// import javafx.event.EventHandler;
// import javafx.event.ActionEvent;

// import javafx.scene.image.*;

// import javafx.scene.text.*;

// import javafx.scene.media.Media;
// import javafx.scene.media.MediaPlayer;
// import java.io.File;

// import javafx.collections.ObservableList;
// import javafx.collections.FXCollections;
// import javafx.scene.control.ComboBox;
// //end

public class XMLParser extends DefaultHandler{
	String inputFile = "example.pws";
	String font, color, fill, start, path;
	boolean italic, bold, underline;
	double x, y, x2, y2;
	int duration, textsize;

	public ArrayList<Slide> slides;
	public Pane pane;

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
		slides = new ArrayList<Slide>();
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
		// font = color = fill = start = path = null;
		// italic = bold = underline = false;
		// x = y = x2 = y2 = 0.0;
		// duration = textsize = 0;
		
		
		switch (elementName) {
			case "Presentation":
				//currentPresentation = new PresentationEngine();
				System.out.println("A Presentation.");
				pane = new Pane();
				System.out.println("pane created.");

				break;
			case "Slide":
				System.out.println("Slide");
				slides.add(new Slide("1")); //Cannot always be id 1, needs to be altered depending on XML 
				System.out.println("Slide created");
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
				System.out.println("Metadata");
			default:
				//currentElement = "none";
				break;
		}
		
		// font = attrs.getValue("font");
		// color = attrs.getValue("color");
		// path = attrs.getValue("path");
		// //bold = attrs.getValue("bold");
		// //italic = attrs.getValue("italic");
		// //underline = attrs.getValue("underline");
		// //x = attrs.getValue("x");
		// //y = attrs.getValue("y");
		
		// try{

		// 	textsize = Integer.parseInt(attrs.getValue("textsize"));
		// } catch (NumberFormatException e){

		// System.out.println("Caught NumberFormatException");
		// }
		

		// System.out.println("Attributes: "+font+" "+textsize+" "+color+" "+bold+" "+italic+" "+underline+" "+x+" "+y+" "+path);

		int length = attrs.getLength();

		for(int i = 0; i < length; i++){
			String name = attrs.getQName(i);
			String value = attrs.getValue(i);
			System.out.print(" " + name + ": " + value);
		}
		System.out.println("");

	}



}