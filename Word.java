import org.json.JSONObject;

public class Word {

    String translation;
    String partOfSpeech;
    String latin;
    JSONObject json;

    public Word(JSONObject object) {

        this.json = object;
        this.partOfSpeech = (String) object.getJSONObject("type").get("label");
        this.translation = (String) object.getJSONObject("translations_unstructured").get("en");
        this.latin = (String) object.get("full_name");


    }

    public Word(){

    }

    public void setJson(JSONObject object){

        this.json = object;
    }

    public static void assignPartOfSpeech(Word word){

        if (word.partOfSpeech.equals("Verb")){

           Main.words[Main.count] = new Verb(word.translation, word.latin);
           Main.count++;
        } else if (word.partOfSpeech.equals("Noun")){

            Main.words[Main.count] = new Noun(word.translation, word.latin);
            Main.count++;
        } else if (word.partOfSpeech.equals("Adjective")){

           Main.words[Main.count] = new Adjective(word.translation, word.latin);
           Main.count++;
        }
    }

}
