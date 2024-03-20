import org.json.JSONObject;

public class Word {

    String translation;
    String partOfSpeech;
    String latin;
    JSONObject json;

    String searchedWord;

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

        if (word.partOfSpeech.equals("Verb"))
        {
           Main.words.add(new Verb(word.translation, word.latin));

        }
        else if (word.partOfSpeech.equals("Noun")){
            Main.words.add(new Noun(word.translation, word.latin));

        }
        else if (word.partOfSpeech.equals("Adjective")){
           Main.words.add(new Adjective(word.translation, word.latin));

        }
        else if (word.partOfSpeech.equals("Preposition")){
            Main.words.add(new Preposition(word.translation, word.latin));

        }
        else if (word.partOfSpeech.equals("Conjunction"))
        {
            Main.words.add(new Conjunction(word.translation, word.latin));

        }
        else if (word.partOfSpeech.equals("Adverb"))
        {
            Main.words.add(new Adverb(word.translation, word.latin));

        }
    }

}
