import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;


public class WebComms {

    int aCount = 0;
    int bCount = 0;
    int cCount = 0;
    int dCount = 0;

    int happyCount = 0;
    int confusedCount = 0;
    int sadCount = 0;

    String getUrl = "http://lecturequest.york.ac.uk/responses";
    String postUrl = "http://lecturequest.york.ac.uk/questions";

    public WebComms() {

    }

    // HTTP GET request
    public void sendGet() throws Exception {
        String url = this.getUrl;
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
    public void sendPost(boolean isWaiting, boolean isQuestion) throws Exception {

        String url = postUrl;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");

//        String question = "2+2=?";
////        String aText = "0";
////        String bText = "1";
////        String cText = "4";
////        String dText = "8";

//        String questionData = "{\"type\":\"question\"," +
//                                "\"questionText\":\"" + question + "\"," +
//                                "\"form\":[{\"type\":\"button\", \"display\":\"" + aText + "\", \"return\":\"a\"}, " +
//                                          "{\"type\":\"button\", \"display\":\"" + bText + "\", \"return\":\"b\"}, " +
//                                          "{\"type\":\"button\", \"display\":\"" + cText + "\", \"return\":\"c\"}, " +
//                                          "{\"type\":\"button\", \"display\":\"" + dText + "\", \"return\":\"d\"}]}";

        String waitingData = "{\"type\":\"waiting\"}";

        String questionData = "{\"type\":\"question\"," +
                               "\"form\":[{\"type\":\"button\", \"backgroundImg\":\"url('http://lecturequest.york.ac.uk/answer1Image')\", \"return\":\"a\"}," +
                                         "{\"type\":\"button\", \"backgroundImg\":\"url('http://lecturequest.york.ac.uk/answer2Image')\", \"return\":\"b\"}," +
                                         "{\"type\":\"button\", \"backgroundImg\":\"url('http://lecturequest.york.ac.uk/answer3Image')\", \"return\":\"c\"}," +
                                         "{\"type\":\"button\", \"backgroundImg\":\"url('http://lecturequest.york.ac.uk/answer4Image')\", \"return\":\"d\"}]}";

        String feedbackData = "{\"type\":\"feedback\"," +
                                "\"form\":[{\"type\":\"button\", \"backgroundImg\":\"url('http://lecturequest.york.ac.uk/sadImage')\", \"return\":\"sad\"}," +
                                          "{\"type\":\"button\", \"backgroundImg\":\"url('http://lecturequest.york.ac.uk/confusedImage')\", \"return\":\"confused\"}," +
                                          "{\"type\":\"button\", \"backgroundImg\":\"url('http://lecturequest.york.ac.uk/happyImage')\", \"return\":\"happy\"}]}";
        String urlParameters;
        if(isWaiting) {
            urlParameters = waitingData;
        } else {
            if (isQuestion) {
                urlParameters = questionData;
            } else {
                urlParameters = feedbackData;
            }
        }


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
