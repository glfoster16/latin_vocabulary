import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import userInput.getVariable;
import org.json.JSONObject;


 class Main {

     public static ArrayList<Word> words = new ArrayList<>();
     public static void main(String[] args){


         HttpHandler handler = new HttpHandler();
         JsonParser jsonParser = new JsonParser();

         System.out.println("What words do you want to search for? Separate words with one space");
         String words = getVariable.getString();
         String[] wordsArray = words.split(" ");

         for (String word : wordsArray) {

             handler.changeWord(word);
             handler.sendRequest();
             JSONObject[] jsonObjects;

             if (handler.response != null) {
                 jsonObjects = jsonParser.stringToJSON(handler.response.body());
             } else {
                 Main.words.add(new Error(word));
                 continue;
             }

             if (jsonObjects.length > 1) {

                 System.out.println("\n\nThere are multiple definitions for " + word + ". Choose one.");
                 for (int i = 0; i < jsonObjects.length; i++) {

                     System.out.println((i + 1) + ")\t" + jsonObjects[i].getJSONObject("type").get("label") +
                             ": " + jsonObjects[i].get("full_name"));
                     System.out.println(jsonObjects[i].getJSONObject("translations_unstructured").get("en"));
                     System.out.println();
                 }

                 int choice = getVariable.getSafeInt(1, jsonObjects.length) - 1;

                 Word.assignPartOfSpeech(new Word(jsonObjects[choice]));


             } else if (jsonObjects.length == 1) {

                 Word.assignPartOfSpeech(new Word(jsonObjects[0]));
             }

//             for (Word word1 : Main.words){
//                 if (word1 == null){
//                     break;
//                 }
//                 System.out.println("printing: " + word1.latin);
//                 System.out.println(word1);
//             }

         }
         //System.out.println(Main.words);

         DocxCreator docxCreator = new DocxCreator(Main.words);
         docxCreator.createDocument();


     }


}
