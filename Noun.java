public class Noun extends Word{

    String translation;
    String latin;
    String partOfSpeech;

    public Noun(String translation, String latin){

        this.partOfSpeech = "Noun";
        this.translation = translation;
        this.latin = cleanLatin(latin);

        super.partOfSpeech = "Noun";
        super.translation = translation;
        super.latin = cleanLatin(latin);

    }

    public String toString(){

        return this.partOfSpeech + ": " +
                this.latin + "; " + this.translation + "\n";

    }

    public String cleanLatin(String latin){

        StringBuilder builder = new StringBuilder();
        builder.append(latin);
        builder.delete(latin.length() - 2, latin.length());

        String gender = builder.substring(builder.length() - 5, builder.length());
        this.partOfSpeech += (" -" + gender);
        builder.delete(builder.length() - 5, builder.length());

        return builder.toString();

    }



}
