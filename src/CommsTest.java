import java.io.IOException;
import java.net.Socket;

public class CommsTest {

    public static void main(String args[]) {
        try {
            Socket socket = new Socket("144.32.123.31", 9000);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
