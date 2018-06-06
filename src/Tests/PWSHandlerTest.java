//
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.File;
//
//import static org.junit.Assert.*;
//
//public class PWSHandlerTest {
//
//    @Before
//    public void setUp() throws Exception {
//        System.out.println("testing");
//
//    }
//
//    @Test
//    public void getPresentation() {
//        //assertFalse(false);
//
//        File PWS = null;
//
//        try {
//            PWS = new File(this.getClass().getResource("example.pws").toURI());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        assertNotNull(PWS);
//
//        XMLParser xmlParser = new XMLParser();
//        xmlParser.parse(PWS);
//
//
//    }
//
////    @Test
////    public void testTest(){
////        assertFalse(false);
////    }
////
////    @Test
////    public void getFailureTest(){
////        assertFalse(true);
////    }
//}