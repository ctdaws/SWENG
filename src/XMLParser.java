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
                LQHandler handler = new LQHandler();
                saxParser.parse(inputFile, handler);
                parsedLQPresentation = handler.getPresentation();
                xmlType = "4l";
            }
            else if(getFileExtension(inputFile).equalsIgnoreCase("pws")) {
                PWSHandler handler = new PWSHandler();
                saxParser.parse(inputFile, handler);
                parsedPwsPresentation = handler.getPresentation();
                xmlType = "pws";
            }
            else {
                PWSHandler handler = new PWSHandler();
                saxParser.parse(inputFile, handler);
                parsedPwsPresentation = handler.getPresentation();
                xmlType = "pws";
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

    public String getXmlType() { return this.xmlType; }

    public LQPresentation getParsedLQPresentation() { return parsedLQPresentation; }

    public PWSPresentation getParsedPwsPresentation() { return parsedPwsPresentation; }
}
