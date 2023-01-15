package com.example.smartfridge.recipesDB;

import static android.content.ContentValues.TAG;
import static com.example.smartfridge.business_logic.SortProducts.mixCombination;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.smartfridge.business_entities.ModelClass;
import com.example.smartfridge.business_logic.SortProducts;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class createKeys {
    public static boolean Create_keys_for_Recipes(SharedPreferences sharedPreferences) {
        boolean notEmpty = false;
        boolean algo_has_been_activated = false;
        if (!SortProducts.getKeys().isEmpty())
            SortProducts.getKeys().clear();
        Gson gson = new Gson();

        String json = sharedPreferences.getString("Item_Data_Meat", null);
        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();
        ArrayList<ModelClass> arrayList_all = gson.fromJson(json, type);

        if (gson.fromJson(json, type) != null)
            notEmpty = true;

        ArrayList<ModelClass> arrayList_temp = new ArrayList<>();
        json = sharedPreferences.getString("Item_Data_Milk", null);
        if (json != null){
            arrayList_temp = gson.fromJson(json, type);
            arrayList_all.addAll(arrayList_temp);
            notEmpty = true;
        }

        json = sharedPreferences.getString("Item_Data_Veg", null);
        if (json != null) {
            arrayList_temp = gson.fromJson(json, type);
            arrayList_all.addAll(arrayList_temp);
            notEmpty = true;
        }

        json = sharedPreferences.getString("Item_Data_Dry", null);
        if (json != null) {
            arrayList_temp = gson.fromJson(json, type);
            arrayList_all.addAll(arrayList_temp);
            notEmpty = true;
        }
        json = sharedPreferences.getString("Item_Data_my_category", null);
        if (json != null) {
            arrayList_temp = gson.fromJson(json, type);
            arrayList_all.addAll(arrayList_temp);
            notEmpty = true;
        }

        if (notEmpty)
        {
            String[] products = new String[arrayList_all.size()];
            if (products.length > 3) {
                for (int i = 0; i < products.length; i++) {
                    products[i] = arrayList_all.get(i).getItemName();
                    Log.d(TAG, "createRecipes: " + products[i]);
                }
            }

            for (int i = 3; i < 5; i++) { //try to find out if we got enough products to get a recipe.
                if (products.length >= i) {
                    mixCombination(products, products.length, i);
                    Log.d(TAG, "createRecipes: " + products.length);
                    algo_has_been_activated = true;
                }
            }
        }
        return algo_has_been_activated && notEmpty;
    }
}
