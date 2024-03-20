import org.json.JSONObject;
import org.json.JSONException;
import java.lang.StringBuilder;
import java.util.ArrayList;

public class JsonParser {


    public JSONObject[] stringToJSON(String string){

        String[] formattedStrings = prepareString(string);

        JSONObject[] jsonObjects = new JSONObject[formattedStrings.length];

        for (int i = 0; i < formattedStrings.length; i++) {

            try {
                JSONObject jsonObject = new JSONObject(formattedStrings[i]);
                jsonObjects[i] = jsonObject;
            } catch (JSONException e) {
                System.out.println("Error " + e.toString());
            }
        }

        return cleanJSONObjects(jsonObjects);
    }

    public String[] prepareString(String string){

        StringBuilder builder = new StringBuilder();
        builder.append(string);
        builder.delete(0,2);
        builder.deleteCharAt(builder.length() - 1);

        String[] formattedStrings = builder.toString().split("},\\{");

        for (int i = 0; i < formattedStrings.length; i++){

            formattedStrings[i] = "{" + formattedStrings[i] + "}";

        }

        return formattedStrings;

    }

    public JSONObject[] cleanJSONObjects(JSONObject[] jsonObjects){

        ArrayList<JSONObject> nonPhraseObjects = new ArrayList<>();
        for (JSONObject object : jsonObjects){

            if (!object.getJSONObject("type").get("label").equals("Phrase")){

                nonPhraseObjects.add(object);
            }

        }

        JSONObject[] returnObjects = new JSONObject[nonPhraseObjects.size()];
        for (int i = 0; i < nonPhraseObjects.size(); i++){

            returnObjects[i] = nonPhraseObjects.get(i);
        }

        return returnObjects;

    }


}


