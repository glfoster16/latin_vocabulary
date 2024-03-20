public class Adverb extends Word{


    String translation;
    String latin;
    String partOfSpeech;

    // Latin does not need cleaning
    public Adverb(String translation, String latin){

        this.partOfSpeech = "Adverb";
        this.translation = translation;
        this.latin = latin;

    }

    public String toString(){

        return this.partOfSpeech + ": " +
                this.latin + "; " + this.translation + "\n";

    }


}
