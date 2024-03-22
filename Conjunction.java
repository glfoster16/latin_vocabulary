public class Conjunction extends Word{


    String translation;
    String latin;
    String partOfSpeech;

    // Latin does not need cleaning
    public Conjunction(String translation, String latin){

        this.partOfSpeech = "Conjunction";
        this.translation = cleanTranslation(translation);
        this.latin = latin;

        super.partOfSpeech = "Conjunction";
        super.translation = cleanTranslation(translation);
        super.latin = latin;

    }

    public String toString(){

        return this.partOfSpeech + ": " +
                this.latin + "; " + this.translation + "\n";

    }

}
