public class Numeral extends Word{

    String translation;
    String latin;
    String partOfSpeech;

    // Latin does not need cleaning
    public Numeral(String translation, String latin){

        this.partOfSpeech = "Numeral";
        this.translation = cleanTranslation(translation);
        this.latin = latin;

        super.partOfSpeech = "Numeral";
        super.translation = cleanTranslation(translation);
        super.latin = latin;

    }

    public String toString(){

        return this.partOfSpeech + ": " +
                this.latin + "; " + this.translation + "\n";

    }


}
