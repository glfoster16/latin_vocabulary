import java.util.Arrays;
import java.util.Scanner;
import userInput.getVariable;


 class Main {

     public static Word[] words = new Word[10000];
     public static int count = 0;
     public static void main(String[] args) {

         Scanner scanner = new Scanner(System.in);
         HttpHandler handler = new HttpHandler();

         System.out.println("What words do you want to search for? Separate words with one space");
         String words = scanner.nextLine();
         String[] wordsArray = words.split(" ");


         for (String word : wordsArray){

             handler.changeWord(word);
             handler.sendRequest();
             handler.stringToJson();

             if (handler.jsonObjects.length > 1){

                 System.out.println("There are multiple definitions for " + word + ". Choose one.");
                 for (int i = 0; i < handler.jsonObjects.length; i++){

                     System.out.println((i + 1) + ")\t" + handler.jsonObjects[i].getJSONObject("type").get("label") +
                             ": " + handler.jsonObjects[i].get("full_name"));
                     System.out.println(handler.jsonObjects[i].getJSONObject("translations_unstructured").get("en"));
                     System.out.println();
                 }
                 int choice = getVariable.getSafeInt(1, handler.jsonObjects.length) - 1;
                 handler.chosenJSON = handler.jsonObjects[choice];

             }

             System.out.println("Chosen JSON: " + handler.chosenJSON);

         }


     }


}
