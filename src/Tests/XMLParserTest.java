//import javafx.scene.Group;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.File;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//import javafx.scene.control.Button;
//
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.MouseButton;
//import javafx.stage.Stage;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
////import org.testfx.api.FxToolkit;
////import org.testfx.framework.junit.ApplicationTest;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//
//import static org.junit.Assert.*;
//
//public class XMLParserTest {
//    private XMLParser xmlParser = new XMLParser();
//    private File LQ, PWS, XML;
////public class MainTest extends ApplicationTest {
//
//   // @Override
//    public void start (Stage stage) throws Exception {
//
//        //Parent mainNode = FXMLLoader.load(this.getClass().getResource("sample.fxml"));
////        stage.setScene(new Scene(mainNode));
////        stage.show();
////        stage.toFront();
//        Group root = new Group();
//        Scene scene = new Scene(root, 1280, 720);
//
//
//        try {
//            LQ = new File(this.getClass().getResource("NewXML.4l").toURI());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        stage.setScene(scene);
//        stage.show();
//        stage.toFront();
//
//        XMLParser xmlParser = new XMLParser();
//    }
//
//    @Before
//    public void setUp () throws Exception {
//    }
//
////    @After
////    public void tearDown () throws Exception {
////        FxToolkit.hideStage();
////        release(new KeyCode[]{});
////        release(new MouseButton[]{});
////    }
//
//
//
//
//
//
////    @Before
////    public void setUp(){//String[] args) throws Exception { launch(args); }
////
////    //@Override
////    //public void start(Stage primaryStage) {
////        System.out.println("testing");
////
////        try {
////            LQ = new File(this.getClass().getResource("NewXML.4l").toURI());
////
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////        XMLParser xmlParser = new XMLParser();
////        LQ = new File(this.getClass().getResource("NewXML.4l").toExternalForm());
////        XMLParser xmlParser = new XMLParser();
//
////        Group root = new Group();
////        Scene scene = new Scene(root, 1280, 720);
//
////        LQ = openFile(primaryStage);
////
////        primaryStage.setScene(scene);
////        primaryStage.show();
//
//
//
//
////    private File openFile (Stage stage) {
////        FileChooser fileChooser = new FileChooser();
////        fileChooser.setTitle("Open Image");
////        fileChooser.getExtensionFilters().addAll(
////                new FileChooser.ExtensionFilter("PWS (*.pws)", "*.pws"),
////                new FileChooser.ExtensionFilter("Quest (*.4l)", "*.4l"),
////                new FileChooser.ExtensionFilter("All Types (*.*)", "*.*")
////        );
////        return fileChooser.showOpenDialog(stage);
////    }
////
////    //@Override
////    public void stop() {
////        Platform.exit();
////    }
////    @Test
////    public void parse() {
////    }
//
//    @Test
//    public void getXmlType() {
//        xmlParser.parse(LQ);
//        assertNotNull(this.xmlParser.getXmlType());
//        //assertEquals(this.xmlParser.getXmlType(), "4l");
//    }
//
//    @Test
//    public void getParsedLQPresentation() {
//        try {
//            PWS = new File(this.getClass().getResource("example.pws").toURI());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        XMLParser xmlParser = new XMLParser();
//        xmlParser.parse(PWS);
//        assertNotNull(this.xmlParser.getParsedLQPresentation());
//
//    }
//
//    @Test
//    public void getParsedPwsPresentation() {
//        try {
//            PWS = new File(this.getClass().getResource("example.pws").toURI());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        XMLParser xmlParser = new XMLParser();
//        xmlParser.parse(PWS);
//        assertEquals(this.xmlParser.getXmlType(), "pws");
//        //assertNotNull(this.xmlParser.getParsedPwsPresentation());
//    }
//
//    @Test
//    public void getParsedXmlPresentation() {
//        try {
//            PWS = new File(this.getClass().getResource("example.xml").toURI());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        XMLParser xmlParser = new XMLParser();
//        xmlParser.parse(XML);
//        assertEquals(this.xmlParser.getXmlType(), "pws");
//        //assertNotNull(this.xmlParser.getParsedPwsPresentation());
//    }
//}