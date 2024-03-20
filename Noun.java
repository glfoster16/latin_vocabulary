public class Noun extends Word{

    String translation;
    String latin;
    String partOfSpeech;

    public Noun(String translation, String latin){

        this.partOfSpeech = "Noun";
        this.translation = translation;
        this.latin = cleanLatin(latin);

    }

    public String toString(){

        return this.partOfSpeech + ": " +
                this.latin + "; " + this.translation + "\n";

    }

    public String cleanLatin(String latin){

        StringBuilder builder = new StringBuilder();
        builder.append(latin);
        builder.delete(latin.length() - 2, latin.length());

        return builder.toString();

    }



}
