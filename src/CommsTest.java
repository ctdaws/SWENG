import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;


public class CommsTest {

    int aCount = 0;
    int bCount = 0;
    int cCount = 0;
    int dCount = 0;

    int happyCount = 0;
    int confusedCount = 0;
    int sadCount = 0;

    public static void main(String args[]) {

        CommsTest commsTest = new CommsTest();

        String getUrl = "http://localhost:9000/responses";
        String postUrl = "http://localhost:9000/questions";
        try {
            commsTest.sendGet(getUrl);
            commsTest.sendPost(postUrl);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    // HTTP GET request
    private void sendGet(String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer responseBuff = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            responseBuff.append(inputLine);
        }
        in.close();

        String response = responseBuff.toString();
        String[] responseParts = response.split(",");

        // Response was feedback
        if(response.contains("happy")) {
            happyCount = Integer.parseInt(responseParts[0].split("=")[1]);
            confusedCount = Integer.parseInt(responseParts[1].split("=")[1]);
            sadCount = Integer.parseInt(responseParts[2].split("=")[1]);
        } else { // Response was a question
            aCount = Integer.parseInt(responseParts[0].split("=")[1]);
            bCount = Integer.parseInt(responseParts[1].split("=")[1]);
            cCount = Integer.parseInt(responseParts[2].split("=")[1]);
            dCount = Integer.parseInt(responseParts[3].split("=")[1]);
        }
        //print result
        System.out.println(response);
    }

    // HTTP POST request
    private void sendPost(String postUrl) throws Exception {

        String url = postUrl;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");

        String question = "2+2=?";
        String aText = "0";
        String bText = "1";
        String cText = "4";
        String dText = "8";

        String questionData = "{\"type\":\"question\"," +
                                "\"questionText\":\"" + question + "\"," +
                                "\"form\":[{\"type\":\"button\", \"display\":\"" + aText + "\", \"return\":\"a\"}, " +
                                          "{\"type\":\"button\", \"display\":\"" + bText + "\", \"return\":\"b\"}, " +
                                          "{\"type\":\"button\", \"display\":\"" + cText + "\", \"return\":\"c\"}, " +
                                          "{\"type\":\"button\", \"display\":\"" + dText + "\", \"return\":\"d\"}]}";

        String feedbackData = "{\"type\":\"feedback\"," +
                                "\"form\":[{\"type\":\"button\", \"backgroundImg\":\"url('http://drive.google.com/uc?export=view&id=1lDcJD0CoiIsGYubwAt3LLSMW0PxmS156')\", \"return\":\"happy\"}," +
                                          "{\"type\":\"button\", \"backgroundImg\":\"url('http://drive.google.com/uc?export=view&id=1rOg70vB_xFdT1PJ-U36B5On-M0-_04Z4')\", \"return\":\"confused\"}," +
                                          "{\"type\":\"button\", \"backgroundImg\":\"url('http://drive.google.com/uc?export=view&id=1SLEbwWcfPyjg7LvDi4OWRwTFjE5Rg-yn')\", \"return\":\"sad\"}]}";

        String urlParameters = feedbackData;

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
}
