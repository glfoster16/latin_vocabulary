public class Noun extends Word{

    String translation;
    String latin;
    String partOfSpeech;

    public Noun(String translation, String latin){

        this.partOfSpeech = "Noun";
        this.translation = cleanTranslation(translation);
        this.latin = cleanLatin(latin);

        super.partOfSpeech = this.partOfSpeech;
        super.translation = cleanTranslation(translation);
        super.latin = cleanLatin(latin);

    }

    public String toString(){

        return this.partOfSpeech + ": " +
                this.latin + "; " + this.translation + "\n";

    }

    public String cleanLatin(String latin){


        StringBuilder builder = new StringBuilder();
        builder.append(latin);

        // For some reason not all nouns include the gender in the latin
        // The gender is always in brackets and brackets are never in latin words
        // So, this makes sure we don't cut off part of a word if it doesn't have a gender
        if (latin.contains("[")) {
            builder.delete(latin.length() - 2, latin.length());

            String gender = builder.substring(builder.length() - 5, builder.length());
            this.partOfSpeech += (" -" + gender);
            builder.delete(builder.length() - 5, builder.length());
        }

        return builder.toString();

    }



}
