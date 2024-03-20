import org.json.JSONObject;

public class Verb extends Word{

    String translation;
    String latin;
    String partOfSpeech;

    public Verb(String translation, String latin){
        this.partOfSpeech = "Verb";
        this.translation = translation;
        this.latin = latin;

    }

}
