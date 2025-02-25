import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

public class RestApiClient {

    private static final String API_URL = "https://jsonplaceholder.typicode.com/posts"; 

    public static void main(String[] args) {
        try {
            
            String getResponse = sendGetRequest(API_URL);
            System.out.println("GET Response:\n" + getResponse);

            
            String postData = "{ \"title\": \"Java REST API\", \"body\": \"This is a test post.\", \"userId\": 1 }";
            String postResponse = sendPostRequest(API_URL, postData);
            System.out.println("POST Response:\n" + postResponse);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    
    public static String sendGetRequest(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    
    public static String sendPostRequest(String url, String jsonData) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonData))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
