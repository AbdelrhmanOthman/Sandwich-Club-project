package com.udacity.sandwichclub.utils;

import android.support.v7.app.AppCompatActivity;

import com.udacity.sandwichclub.MainActivity;
import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public  class JsonUtils extends AppCompatActivity  {


    public static Sandwich parseSandwichJson(String json) {

        List<String> AnotherName = new ArrayList<String>();
        List<String> Ingredients = new ArrayList<String>();



            JSONObject reader = null;

            try {
                reader = new JSONObject(json);
                JSONObject name = reader.getJSONObject("name");
                String mainNmae = name.getString("mainName");






                JSONArray AlsoKnownAs = name.getJSONArray("alsoKnownAs");
                for (int i = 0 ; i <AlsoKnownAs.length();i++){
                    String Arr = AlsoKnownAs.get(i).toString();
                   if (Arr==null){
                       String extra = "No Additional names";
                       AnotherName.add(extra);
                   }else {
                       AnotherName.add(Arr);
                   }
                }


                String placeOfOrigin = reader.getString("placeOfOrigin");

               String description = reader.getString("description");

                String image = reader.getString("image");


                JSONArray ingredients = reader.getJSONArray("ingredients");
                for (int y = 0 ; y<ingredients.length();y++){
                    String Ingredints = ingredients.get(y).toString();
                    Ingredients.add(Ingredints);

                }

                return new Sandwich(mainNmae, AnotherName, placeOfOrigin, description, image, Ingredients);



            } catch (JSONException e) {
                e.printStackTrace();
            }




               return null;
    }

}
