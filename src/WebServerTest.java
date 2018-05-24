import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServerTest {

    public static void main(String args[]) {
        WebServer server = new WebServer();

        // Setup socket comms
        ServerSocket serverSock;
        Socket socket;
        try {
            serverSock = new ServerSocket(9000);

            while(true) {
                socket = serverSock.accept();
                System.out.println("Connection");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
