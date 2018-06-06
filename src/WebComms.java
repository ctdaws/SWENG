import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

/**
 * Class which communicates between the web server and LectureQuest
 *
 * @author Chris Dawson, Samuel Broughton
 */

// Sends get and post requests to the web server from LectureQuest
public class WebComms {

    // Counters which are set to the amount of times each answer button has been pressed
    public int aCount = 0;
    public int bCount = 0;
    public int cCount = 0;
    public int dCount = 0;

    // Counters which are set to the amount of times each feedback button has been pressed
    public int happyCount = 0;
    public int confusedCount = 0;
    public int sadCount = 0;

    // The addresses of the http end points where responses are gotten from and JSON containing the questions/feedback
    // data are sent to
    private String getUrl = "http://localhost:9000/responses";
    private String postUrl = "http://localhost:9000/questions";

    public WebComms() {}

    // HTTP GET request
    public void sendGet() throws Exception {
        String url = this.getUrl;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        //add request header
        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer responseBuff = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            responseBuff.append(inputLine);
        }
        in.close();

        String response = responseBuff.toString();
        // Split the response into an array of the form x=y
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
    }

    // HTTP POST request
    // isWaiting is set true when web interaction in LectureQuest is disabled
    // isQuestion is set true when the data to be sent is a question and is set false when the data is feedback
    // answers is an optional argument which contains the text to be put in the answer boxes on the web client end
    public void sendPost(boolean isWaiting, boolean isQuestion, String... answers) throws Exception {

        String url = postUrl;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");

        String answer1Text = "";
        String answer2Text = "";
        String answer3Text = "";
        String answer4Text = "";

        if(isQuestion) {
            answer1Text = answers[0];
            answer2Text = answers[1];
            answer3Text = answers[2];
            answer4Text = answers[3];
        }

        // Set up some JSON which will be sent to the web server
        String waitingData = "{\"type\":\"waiting\"}";

        String questionData = "{\"type\":\"question\"," +
                "\"form\":[{\"type\":\"button\", \"answerText\":\"" + answer1Text + "\", \"backgroundImg\":\"url('http://localhost:9000/answer1Image')\", \"return\":\"a\"}," +
                "{\"type\":\"button\", \"answerText\":\"" + answer2Text + "\", \"backgroundImg\":\"url('http://localhost:9000/answer2Image')\", \"return\":\"b\"}," +
                "{\"type\":\"button\", \"answerText\":\"" + answer3Text + "\", \"backgroundImg\":\"url('http://localhost:9000/answer3Image')\", \"return\":\"c\"}," +
                "{\"type\":\"button\", \"answerText\":\"" + answer4Text + "\", \"backgroundImg\":\"url('http://localhost:9000/answer4Image')\", \"return\":\"d\"}]}";

        String feedbackData = "{\"type\":\"feedback\"," +
                "\"form\":[{\"type\":\"button\", \"backgroundImg\":\"url('http://localhost:9000/sadImage')\", \"return\":\"sad\"}," +
                "{\"type\":\"button\", \"backgroundImg\":\"url('http://localhost:9000/confusedImage')\", \"return\":\"confused\"}," +
                "{\"type\":\"button\", \"backgroundImg\":\"url('http://localhost:9000/happyImage')\", \"return\":\"happy\"}]}";

        String data;
        if(isWaiting) {
            data = waitingData;
        } else {
            if (isQuestion) {
                data = questionData;
            } else {
                data = feedbackData;
            }
        }

        // Send post request with accompanying JSON data
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(data);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        // Receive acknowledgement
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
    }
}
