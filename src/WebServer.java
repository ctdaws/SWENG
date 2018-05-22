import com.oracle.tools.packager.IOUtils;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class WebServer {

    int port = 0;

    public WebServer() {
        try {
            port = 9000;
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            System.out.println("server started at " + port);
            server.createContext("/", new RootHandler());
            server.createContext("/echoHeader", new EchoHeaderHandler());
            server.createContext("/echoGet", new EchoGetHandler());
            server.createContext("/echoPost", new EchoPostHandler());
            server.setExecutor(null);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class RootHandler implements HttpHandler {

        @Override

        public void handle(HttpExchange he) throws IOException {
            //String response = ;
            //String response = (String)this.getClass().getResource("html_test.html").toURI().get;

            StringBuilder contentBuilder = new StringBuilder();
            try (Stream<String> stream = Files.lines( Paths.get("/Users/chris/IdeaProjects/SWENG/resources/html_test.html"), StandardCharsets.UTF_8))
            {
                stream.forEach(s -> contentBuilder.append(s).append("\n"));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            String response = new String();
            response = contentBuilder.toString();


            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

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
            os.write(response.toString().getBytes());
            os.close();
        }
    }

    public class EchoGetHandler implements HttpHandler {

        @Override

        public void handle(HttpExchange he) throws IOException {
            // parse request
            Map<String, Object> parameters = new HashMap<String, Object>();
            URI requestedUri = he.getRequestURI();
            String query = requestedUri.getRawQuery();
            parseQuery(query, parameters);

            String formData = "{\"form\":[{\"type\":\"button\", \"display\":\"A\", \"return\":\"a\"}, " +
                    "{\"type\":\"button\", \"display\":\"B\", \"return\":\"b\"}, " +
                    "{\"type\":\"button\", \"display\":\"C\", \"return\":\"c\"}, " +
                    "{\"type\":\"button\", \"display\":\"D\", \"return\":\"d\"}]}";

            // send response
            String response = formData;
//            for (String key : parameters.keySet())
//                response += key + " = " + parameters.get(key) + "\n";
            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.toString().getBytes());

            os.close();
        }
    }

    public class EchoPostHandler implements HttpHandler {

        @Override

        public void handle(HttpExchange he) throws IOException {
            // parse request
            Map<String, Object> parameters = new HashMap<String, Object>();
            InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String query = br.readLine();
            System.out.println(query);
            parseQuery(query, parameters);

            // send response
            String response = "";
            for (String key : parameters.keySet())
                response += key + " = " + parameters.get(key) + "\n";
            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();
        }
    }

    public static void parseQuery(String query, Map<String, Object> parameters) throws UnsupportedEncodingException {

        if (query != null) {
            String pairs[] = query.split("[&]");
            for (String pair : pairs) {
                String param[] = pair.split("[=]");
                String key = null;
                String value = null;
                if (param.length > 0) {
                    key = URLDecoder.decode(param[0],
                            System.getProperty("file.encoding"));
                }

                if (param.length > 1) {
                    value = URLDecoder.decode(param[1],
                            System.getProperty("file.encoding"));
                }

                if (parameters.containsKey(key)) {
                    Object obj = parameters.get(key);
                    if (obj instanceof List<?>) {
                        List<String> values = (List<String>) obj;
                        values.add(value);

                    } else if (obj instanceof String) {
                        List<String> values = new ArrayList<String>();
                        values.add((String) obj);
                        values.add(value);
                        parameters.put(key, values);
                    }
                } else {
                    parameters.put(key, value);
                }
            }
        }
    }
}
