import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;


public class CommsTest {

    public static void main(String args[]) {

        CommsTest commsTest = new CommsTest();

        String url = "http://lecturequest.york.ac.uk/echoResponses";
        try {
            commsTest.sendGet(url);
            commsTest.sendPost();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    // HTTP GET request
    private void sendGet(String url) throws Exception {

        //String USER_AGENT = "Mozilla/5.0";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        // con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

    // HTTP POST request
    private void sendPost() throws Exception {

        String url = "http://lecturequest.york.ac.uk/echoQuestions";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        //con.setRequestProperty("User-Agent", USER_AGENT);
        //con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String aText = "TestA";
        String bText = "TestB";
        String cText = "TestC";
        String dText = "TestD";

        String formData = "{\"form\":[{\"type\":\"button\", \"display\":\"" + aText + "\", \"return\":\"a\"}, " +
                                     "{\"type\":\"button\", \"display\":\"" + bText + "\", \"return\":\"b\"}, " +
                                     "{\"type\":\"button\", \"display\":\"" + cText + "\", \"return\":\"c\"}, " +
                                     "{\"type\":\"button\", \"display\":\"" + dText + "\", \"return\":\"d\"}]}";
        String urlParameters = formData;

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }
}
