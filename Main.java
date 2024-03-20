import java.util.Arrays;
import java.util.Scanner;
import userInput.getVariable;
import org.json.JSONObject;


 class Main {

     public static Word[] words = new Word[10000];
     public static int count = 0;
     public static void main(String[] args) {

         Scanner scanner = new Scanner(System.in);
         HttpHandler handler = new HttpHandler();
         JsonParser jsonParser = new JsonParser();

         System.out.println("What words do you want to search for? Separate words with one space");
         String words = scanner.nextLine();
         String[] wordsArray = words.split(" ");

//         for (String word : wordsArray){
//
//             handler.changeWord(word);
//             handler.sendRequest();
//             System.out.println(handler.response.body());
//         }

         for (String word : wordsArray){

             handler.changeWord(word);
             handler.sendRequest();
             JSONObject[] jsonObjects = jsonParser.stringToJSON(handler.response.body());

             if (jsonObjects.length > 1){

                 System.out.println("There are multiple definitions for " + word + ". Choose one.");
                 for (int i = 0; i < jsonObjects.length; i++){

                     System.out.println((i + 1) + ")\t" + jsonObjects[i].getJSONObject("type").get("label") +
                             ": " + jsonObjects[i].get("full_name"));
                     System.out.println(jsonObjects[i].getJSONObject("translations_unstructured").get("en"));
                     System.out.println();
                 }

                 int choice = getVariable.getSafeInt(1, jsonObjects.length) - 1;

                 Word.assignPartOfSpeech(new Word(jsonObjects[choice]));


             } else if (jsonObjects.length == 1){

                 Word.assignPartOfSpeech(new Word(jsonObjects[0]));


             }

             System.out.println(Arrays.toString(Main.words));

         }


     }


}
