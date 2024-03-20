public class Adjective extends Word {

    String partOfSpeech;
    String translation;
    String latin;


    public Adjective(String translation, String latin){
        this.partOfSpeech = "Adjective";
        this.translation = translation;
        this.latin = cleanLatin(latin);

    }

    public Adjective(){

    }

    public String cleanLatin(String latin){

        String[] parts = latin.split(",");

        return parts[0];
    }

    public String toString(){

        return this.partOfSpeech + ": " +
                this.latin + "; " + this.translation + "\n";

    }
}
