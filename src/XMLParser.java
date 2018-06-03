import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class XMLParser {

    private LQPresentation parsedLQPresentation;
    private PWSPresentation parsedPwsPresentation;

    private String xmlType;

    public void parse(File inputFile) {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        try {
            spf.setNamespaceAware(true);
            SAXParser saxParser = spf.newSAXParser();
            if(getFileExtension(inputFile).equalsIgnoreCase("4l")) {
//                System.out.println("Setting handler to LQHandler (.4l)");
                LQHandler handler = new LQHandler();
                saxParser.parse(inputFile, handler);
                parsedLQPresentation = handler.getPresentation();
                xmlType = "4l";
//                return (T) parsedLQPresentation;
            }
            else if(getFileExtension(inputFile).equalsIgnoreCase("pws")) {
//                System.out.println("Setting handler to PWSHandler (.pws)");
                PWSHandler handler = new PWSHandler();
                saxParser.parse(inputFile, handler);
                parsedPwsPresentation = handler.getPresentation();
                xmlType = "pws";
//                return (T) parsedPwsPresentation;
            }
            else {
//                System.out.println("Setting handler to PWSHandler (default)");
                PWSHandler handler = new PWSHandler();
                saxParser.parse(inputFile, handler);
                parsedPwsPresentation = handler.getPresentation();
                xmlType = "pws";
//                return (T) parsedPwsPresentation;
            }
        }
        catch (ParserConfigurationException | SAXException | IOException e) { e.printStackTrace(); }
//        return null;
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

    public String getXmlType() { return this.xmlType; }

    public LQPresentation getParsedLQPresentation() { return parsedLQPresentation; }

    public PWSPresentation getParsedPwsPresentation() { return parsedPwsPresentation; }
}
