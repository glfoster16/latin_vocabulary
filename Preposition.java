public class Preposition extends Word{

    String translation;
    String latin;
    String partOfSpeech;

    // Latin does not need cleaning
    public Preposition(String translation, String latin){

        this.partOfSpeech = "Preposition";
        this.translation = cleanTranslation(translation);
        this.latin = latin;

        super.partOfSpeech = "Preposition";
        super.translation = cleanTranslation(translation);
        super.latin = latin;

    }

    public String toString(){

        return this.partOfSpeech + ": " +
                this.latin + "; " + this.translation + "\n";

    }

}
