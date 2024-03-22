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

    // This ensures that the tables created using Apache POI won't be shifted left or right because
    // of a substring being too long - this most commonly happened when a translation had many slashes
    // For example, praesto: excel, surpass, be outstanding/superior/best/greater/preferable (to), prevail
    // The outstanding/superior/best/greater/preferable is all one substring - this method splits that into two
    public String cleanTranslation(String translation){

        String[] parts = translation.split(" ");
        StringBuilder translationBuilder = new StringBuilder();

        for (String part : parts){

            if(part.length() > 30){

                StringBuilder builder = new StringBuilder();
                builder.append(part);
                builder.insert(builder.length() / 2, " ");

                translationBuilder.append(builder).append(" ");
                continue;

            }

            translationBuilder.append(part).append(" ");

        }

        return translationBuilder.toString();

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
        else if (word.partOfSpeech.equals("Numeral"))
        {
            Main.words.add(new Numeral(word.translation, word.latin));
        }
    }

}
