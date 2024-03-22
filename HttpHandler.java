import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;

public class HttpHandler {

    String word;
    String URL;
    HttpRequest request;
    HttpResponse<String> response;


    public HttpHandler(){

        this.word = null;
        this.URL = null;
        this.request = null;
        this.response = null;

    }

    public void changeWord(String word){
        this.setURL(word);
        this.setRequest();

    }

    public void setRequest(){
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(this.URL))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

    }

    public void setURL(String word){

        this.URL = "https://www.latin-is-simple.com/api/vocabulary/search/?query=" + word;
    }

    public void sendRequest(){

        try {
            this.response = HttpClient.newHttpClient().send(this.request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            System.out.println("No internet, reconnect and try again.");
            System.exit(2);
        } catch (InterruptedException e) {
            System.out.println("No internet, reconnect and try again.");
            System.exit(2);
        }

        if (this.response.body().length() == 2){
            this.response = null;
        }

    }


}
