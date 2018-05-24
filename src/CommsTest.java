import java.io.IOException;
import java.net.Socket;

public class CommsTest {

    public static void main(String args[]) {
        try {
            Socket socket = new Socket("144.32.123.31", 11427);
            System.out.println("Connection");
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
