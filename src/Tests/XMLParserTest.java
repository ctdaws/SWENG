import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class XMLParserTest {
    private XMLParser xmlParser = new XMLParser();
    private File LQ, PWS, XML;

    @Before
    public void setUp() throws Exception {
        System.out.println("testing");
        LQ = new File(this.getClass().getResource("NewXML.4l").toExternalForm());
        XMLParser xmlParser = new XMLParser();

    }
//    @Test
//    public void parse() {
//    }

    @Test
    public void getXmlType() {
        xmlParser.parse(LQ);
        assertEquals(this.xmlParser.getXmlType(), "4l");
    }

    @Test
    public void getParsedLQPresentation() {
        xmlParser.parse(PWS);
        assertNotNull(this.xmlParser.getParsedLQPresentation());

    }

    @Test
    public void getParsedPwsPresentation() {
        PWS = new File(this.getClass().getResource("example.pws").toExternalForm());
        XMLParser xmlParser = new XMLParser();
        xmlParser.parse(PWS);
        assertEquals(this.xmlParser.getXmlType(), "pws");
        assertNotNull(this.xmlParser.getParsedPwsPresentation());
    }

    @Test
    public void getParsedXmlPresentation() {
        XML = new File(this.getClass().getResource("example.xml").toExternalForm());
        XMLParser xmlParser = new XMLParser();
        xmlParser.parse(XML);
        assertEquals(this.xmlParser.getXmlType(), "pws");
        assertNotNull(this.xmlParser.getParsedPwsPresentation());
    }
}