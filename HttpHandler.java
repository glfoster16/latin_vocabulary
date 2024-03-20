import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;
import java.lang.StringBuilder;
import org.json.JSONObject;
import org.json.JSONException;


public class HttpHandler {

    String word;
    String URL;
    HttpRequest request;
    HttpResponse<String> response;
    JSONObject[] jsonObjects;
    JSONObject chosenJSON;


    public HttpHandler(){

        this.word = null;
        this.URL = null;
        this.request = null;
        this.response = null;
        this.jsonObjects = null;
        this.chosenJSON = null;

    }

    public void changeWord(String word){
        this.setWord(word);
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

    public void setWord(String word){

        this.word = word;
    }

    public void sendRequest(){

        try {
            this.response = HttpClient.newHttpClient().send(this.request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void stringToJson(){

        // Since response.body() gives a list full of json-formatted strings, I had to convert those into individual JSON Objects
        StringBuilder responseStringBuilder = new StringBuilder();
        responseStringBuilder.append(this.response.body());
        responseStringBuilder.delete(0,2);
        responseStringBuilder.deleteCharAt(responseStringBuilder.length() - 1);

        String[] jsonStrings = responseStringBuilder.toString().split("},\\{");
        this.jsonObjects = new JSONObject[jsonStrings.length];

        for (int i = 0; i < jsonStrings.length; i++){

            jsonStrings[i] = "{" + jsonStrings[i] + "}";

        }

        for (int i = 0; i < jsonStrings.length; i++) {

            try {
                JSONObject jsonObject = new JSONObject(jsonStrings[i]);
                jsonObjects[i] = jsonObject;
            } catch (JSONException e) {
                System.out.println("Error " + e.toString());
            }
        }


    }

}
