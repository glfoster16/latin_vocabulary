public class Error extends Word{

    String searchedWord;
    String latin;
    String translation;
    String partOfSpeech;

    public Error(String searchedWord){
        this.searchedWord = searchedWord;
        this.latin = "Error";
        this.translation = "Error";
        this.partOfSpeech = "Error";

        super.latin = "Error";
        super.translation = "Error";
        super.partOfSpeech = "Error";
    }

    public String toString(){

        return "Error whilst searching for: " + this.searchedWord + ". Check spelling.";
    }

}
