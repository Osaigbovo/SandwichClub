package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private final static String TAG = JsonUtils.class.getSimpleName();

    private final static String SANDWICH_NAME = "name";
    private final static String SANDWICH_MAIN_NAME = "mainName";
    private final static String SANDWICH_ALSO_KNOWN_AS = "alsoKnownAs";
    private final static String SANDWICH_PLACE_OF_ORIGIN = "placeOfOrigin";
    private final static String SANDWICH_DESCRIPTION = "description";
    private final static String SANDWICH_IMAGE = "image";
    private final static String SANDWICH_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject sandwichJsonObject = new JSONObject(json);

            JSONObject name = sandwichJsonObject.getJSONObject(SANDWICH_NAME);

            String mainName = name.getString(SANDWICH_MAIN_NAME);

            JSONArray alsoKnownAsJSONArray = name.getJSONArray(SANDWICH_ALSO_KNOWN_AS);
            List<String> alsoKnownAs = jsonArrayToList(alsoKnownAsJSONArray);

            String placeOfOrigin = sandwichJsonObject.optString(SANDWICH_PLACE_OF_ORIGIN);

            String description = sandwichJsonObject.getString(SANDWICH_DESCRIPTION);

            String image = sandwichJsonObject.getString(SANDWICH_IMAGE);

            JSONArray ingredientsJSONArray = sandwichJsonObject.getJSONArray(SANDWICH_INGREDIENTS);
            List<String> ingredients = jsonArrayToList(ingredientsJSONArray);

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    /*
     * Convert JSONArrey to List
     * */
    private static List<String> jsonArrayToList(JSONArray jsonArray) throws JSONException {

        List<String> list = new ArrayList<>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }

        return list;
    }
}
