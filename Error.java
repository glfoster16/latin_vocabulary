public class Error extends Word{

    String searchedWord;

    public Error(String searchedWord){
        this.searchedWord = searchedWord;
    }

    public String toString(){

        return "Error whilst searching for: " + this.searchedWord + ". Check spelling.";
    }

}
