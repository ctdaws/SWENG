import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class which runs the web server.
 *
 * @author Chris Dawson
 */

// The web server can be accessed by running this class and navigating in a web
// browser to "localhost:9000"
// This will take you to the home/waiting page of the server which will remain in
// that state until LectureQuest navigates to an answer or feedback slide
public class WebServerTest {
    public static void main(String args[]) { WebServer server = new WebServer(); }
}
