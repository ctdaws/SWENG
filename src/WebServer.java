import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.file.*;
import java.util.*;


/**
 * Class which sets up the web server.
 *
 * @author Chris Dawson, Samuel Broughton
 */


public class WebServer {

    // Counters which store how many times each of the
    // 4 answers have been pressed
    private int aCount = 0;
    private int bCount = 0;
    private int cCount = 0;
    private int dCount = 0;

    // Counters which store how many times each of the
    // feedback buttons have been pressed
    private int happyCount = 0;
    private int confusedCount = 0;
    private int sadCount = 0;

    // Set true if the incoming messages from LectureQuest is a question
    private boolean isQuestion = false;

    // The default data to send to the client if neither a question or answer
    // have been received from LectureQuest
    private String formData = "{\"type\":\"none\"}";

    public WebServer() {
        try {
            // Create server on port 9000
            int port = 9000;
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

            // Set up server http end points
            server.createContext("/", new RootHandler());
            server.createContext("/echoHeader", new EchoHeaderHandler());
            server.createContext("/get", new GetHandler());
            server.createContext("/post", new PostHandler());
            server.createContext("/responses", new ResponsesHandler());
            server.createContext("/questions", new QuestionsHandler());
            server.createContext("/answer1Image", new Answer1ImageHandler());
            server.createContext("/answer2Image", new Answer2ImageHandler());
            server.createContext("/answer3Image", new Answer3ImageHandler());
            server.createContext("/answer4Image", new Answer4ImageHandler());
            server.createContext("/happyImage", new HappyImageHandler());
            server.createContext("/confusedImage", new ConfusedImageHandler());
            server.createContext("/sadImage", new SadImageHandler());
            server.createContext("/favicon", new FaviconImageHandler());
            server.createContext("/loading", new LoadingImageHandler());
            server.createContext("/font", new FontHandler());

            server.setExecutor(null);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Class that handles the "/" http endpoint (e.g. lecturequest.york.ac.uk/)
    public class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange he) throws IOException {
            byte[] response = null;
            try {
                final URI uri = getClass().getResource("client.html").toURI();
                Map<String, String> env = new HashMap<>();
                env.put("create", "true");
                FileSystem fs = FileSystems.newFileSystem(uri, env);
                Path myFolderPath = Paths.get(uri);

                response = Files.readAllBytes(myFolderPath);
                fs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


            he.sendResponseHeaders(200, response.length);
            OutputStream os = he.getResponseBody();
            os.write(response);
            os.close();
        }
    }

    // Class that handles the "/echoHeader" http endpoint (e.g. lecturequest.york.ac.uk/echoHeader)
    public class EchoHeaderHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange he) throws IOException {
            Headers headers = he.getRequestHeaders();
            Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
            String response = "";
            for (Map.Entry<String, List<String>> entry : entries)
                response += entry.toString() + "\n";
            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    // Class that handles the "/get" http endpoint (e.g. lecturequest.york.ac.uk/get)
    // Will send the html that should be displayed on the client end
    public class GetHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange he) throws IOException {
            // parse request
            Map<String, Object> parameters = new HashMap<String, Object>();
            URI requestedUri = he.getRequestURI();
            String query = requestedUri.getRawQuery();

            // send response
            String response = formData;
            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.toString().getBytes());

            os.close();
        }
    }

    // Class that handles the "/post" http endpoint (e.g. lecturequest.york.ac.uk/post)
    // Will receive data back from the client end and send an acknowledgement
    public class PostHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange he) throws IOException {
            // parse request
            Map<String, Object> parameters = new HashMap<String, Object>();
            InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String query = br.readLine();

            // Receives which button was pressed on the client and updates the relevant counter
            switch(query) {
                case "a":
                    aCount++;
                    break;
                case "b":
                    bCount++;
                    break;
                case "c":
                    cCount++;
                    break;
                case "d":
                    dCount++;
                    break;
                case "happy":
                    happyCount++;
                    break;
                case "confused":
                    confusedCount++;
                    break;
                case "sad":
                    sadCount++;
                    break;
            }

            // Send response
            String response = "";
            for (String key : parameters.keySet())
                response += key + " = " + parameters.get(key) + "\n";

            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();
        }
    }

    // Class that handles the "/responses" http endpoint (e.g. lecturequest.york.ac.uk/responses)
    // Sends the responses for a question or feedback to LecutreQuest
    public class ResponsesHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange he) throws IOException {

            String response;
            if(isQuestion) {
                response = "a=" + Integer.toString(aCount) +
                        ", b=" +  Integer.toString(bCount) +
                        ", c=" + Integer.toString(cCount) +
                        ", d=" + Integer.toString(dCount);
            } else {
                response = "happy=" + Integer.toString(happyCount) +
                        ", confused=" + Integer.toString(confusedCount) +
                        ", sad=" + Integer.toString(sadCount);
            }

            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    // Class that handles the "/questions" http endpoint (e.g. lecturequest.york.ac.uk/questions)
    // This end point is used both for questions and for feedback
    // Depending on which JSON is send form LectureQuest either the questions or feedback buttons
    // are displayed
    public class QuestionsHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange he) throws IOException {
            InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String query = br.readLine();
            formData = query;

            // Reset the feedback counters when the answer buttons are active and
            // reset the answer buttons when the feedback buttons are active
            if(formData.contains("question")) {
                isQuestion = true;
                happyCount = 0;
                confusedCount = 0;
                sadCount= 0;
            } else {
                isQuestion = false;
                aCount = 0;
                bCount = 0;
                cCount = 0;
                dCount = 0;
            }

            // send response
            String response = "ack";

            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();
        }
    }

    // Class that handles the "/answer1Image" http endpoint (e.g. lecturequest.york.ac.uk/answer1Image)
    // Provides the image for the 1st answer box
    public class Answer1ImageHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange he) throws IOException {
            byte[] response = null;

            try {
                final URI uri = getClass().getResource("answer_1.png").toURI();
                Map<String, String> env = new HashMap<>();
                env.put("create", "true");
                FileSystem fs = FileSystems.newFileSystem(uri, env);
                Path myFolderPath = Paths.get(uri);

                response = Files.readAllBytes(myFolderPath);
                fs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            he.sendResponseHeaders(200, response.length);
            OutputStream os = he.getResponseBody();
            os.write(response);
            os.close();

        }
    }

    // Class that handles the "/answer2Image" http endpoint (e.g. lecturequest.york.ac.uk/answer2Image)
    // Provides the image for the 2nd answer box
    public class Answer2ImageHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange he) throws IOException {
            byte[] response = null;

            try {
                final URI uri = getClass().getResource("answers_2.png").toURI();
                Map<String, String> env = new HashMap<>();
                env.put("create", "true");
                FileSystem fs = FileSystems.newFileSystem(uri, env);
                Path myFolderPath = Paths.get(uri);

                response = Files.readAllBytes(myFolderPath);
                fs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            he.sendResponseHeaders(200, response.length);
            OutputStream os = he.getResponseBody();
            os.write(response);
            os.close();
        }
    }

    // Class that handles the "/answer3Image" http endpoint (e.g. lecturequest.york.ac.uk/answer3Image)
    // Provides the image for the 3rd answer box
    public class Answer3ImageHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange he) throws IOException {
            byte[] response = null;

            try {
                final URI uri = getClass().getResource("answers_3.png").toURI();
                Map<String, String> env = new HashMap<>();
                env.put("create", "true");
                FileSystem fs = FileSystems.newFileSystem(uri, env);
                Path myFolderPath = Paths.get(uri);

                response = Files.readAllBytes(myFolderPath);
                fs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            he.sendResponseHeaders(200, response.length);
            OutputStream os = he.getResponseBody();
            os.write(response);
            os.close();
        }
    }

    // Class that handles the "/answer4Image" http endpoint (e.g. lecturequest.york.ac.uk/answer4Image)
    // Provides the image for the 4th answer box
    public class Answer4ImageHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange he) throws IOException {
            byte[] response = null;

            try {
                final URI uri = getClass().getResource("answers_4.png").toURI();
                Map<String, String> env = new HashMap<>();
                env.put("create", "true");
                FileSystem fs = FileSystems.newFileSystem(uri, env);
                Path myFolderPath = Paths.get(uri);

                response = Files.readAllBytes(myFolderPath);
                fs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            he.sendResponseHeaders(200, response.length);
            OutputStream os = he.getResponseBody();
            os.write(response);
            os.close();
        }
    }

    // Class that handles the "/happyImage" http endpoint (e.g. lecturequest.york.ac.uk/happyImage)
    // Provides the happy image for the feedback
    public class HappyImageHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange he) throws IOException {
            byte[] response = null;

            try {
                final URI uri = getClass().getResource("smiling.png").toURI();
                Map<String, String> env = new HashMap<>();
                env.put("create", "true");
                FileSystem fs = FileSystems.newFileSystem(uri, env);
                Path myFolderPath = Paths.get(uri);

                response = Files.readAllBytes(myFolderPath);
                fs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            he.sendResponseHeaders(200, response.length);
            OutputStream os = he.getResponseBody();
            os.write(response);
            os.close();
        }
    }

    // Class that handles the "/confusedImage" http endpoint (e.g. lecturequest.york.ac.uk/confusedImage)
    // Provides the confused image for the feedback
    public class ConfusedImageHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange he) throws IOException {
            byte[] response = null;

            try {
                final URI uri = getClass().getResource("confused.png").toURI();
                Map<String, String> env = new HashMap<>();
                env.put("create", "true");
                FileSystem fs = FileSystems.newFileSystem(uri, env);
                Path myFolderPath = Paths.get(uri);

                response = Files.readAllBytes(myFolderPath);
                fs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            he.sendResponseHeaders(200, response.length);
            OutputStream os = he.getResponseBody();
            os.write(response);
            os.close();
        }
    }

    // Class that handles the "/sadImage" http endpoint (e.g. lecturequest.york.ac.uk/sadImage)
    // Provides the sad image for the feedback
    public class SadImageHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange he) throws IOException {
            byte[] response = null;

            try {
                final URI uri = getClass().getResource("sad.png").toURI();
                Map<String, String> env = new HashMap<>();
                env.put("create", "true");
                FileSystem fs = FileSystems.newFileSystem(uri, env);
                Path myFolderPath = Paths.get(uri);

                response = Files.readAllBytes(myFolderPath);
                fs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            he.sendResponseHeaders(200, response.length);
            OutputStream os = he.getResponseBody();
            os.write(response);
            os.close();
        }
    }

    // Class that handles the "/favicon" http endpoint (e.g. lecturequest.york.ac.uk/favicon)
    // Provides the favicon
    public class FaviconImageHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange he) throws IOException {
            byte[] response = null;

            try {
                final URI uri = getClass().getResource("favicon.png").toURI();
                Map<String, String> env = new HashMap<>();
                env.put("create", "true");
                FileSystem fs = FileSystems.newFileSystem(uri, env);
                Path myFolderPath = Paths.get(uri);

                response = Files.readAllBytes(myFolderPath);
                fs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            he.sendResponseHeaders(200, response.length);
            OutputStream os = he.getResponseBody();
            os.write(response);
            os.close();
        }
    }

    // Class that handles the "/loading" http endpoint (e.g. lecturequest.york.ac.uk/loading)
    // Provides the loading gif
    public class LoadingImageHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange he) throws IOException {
            byte[] response = null;

            try {
                final URI uri = getClass().getResource("queston_loading.gif").toURI();
                Map<String, String> env = new HashMap<>();
                env.put("create", "true");
                FileSystem fs = FileSystems.newFileSystem(uri, env);
                Path myFolderPath = Paths.get(uri);

                response = Files.readAllBytes(myFolderPath);
                fs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            he.sendResponseHeaders(200, response.length);
            OutputStream os = he.getResponseBody();
            os.write(response);
            os.close();
        }
    }

    // Class that handles the "/font" http endpoint (e.g. lecturequest.york.ac.uk/font)
    // Provides the font for the answers text
    public class FontHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange he) throws IOException {
            byte[] response = null;

            try {
                final URI uri = getClass().getResource("fonts/BebasNeue-Regular.ttf").toURI();
                Map<String, String> env = new HashMap<>();
                env.put("create", "true");
                FileSystem fs = FileSystems.newFileSystem(uri, env);
                Path myFolderPath = Paths.get(uri);

                response = Files.readAllBytes(myFolderPath);
                fs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            he.sendResponseHeaders(200, response.length);
            OutputStream os = he.getResponseBody();
            os.write(response);
            os.close();
        }
    }
}
