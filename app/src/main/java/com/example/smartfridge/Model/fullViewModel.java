package com.example.smartfridge.Model;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;

import com.example.smartfridge.business_entities.ModelClass;
import com.example.smartfridge.local_customer_memory.full_view;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class fullViewModel {
    full_view activity;
    ArrayList<ModelClass> arrayList = new ArrayList<>();

    public fullViewModel(full_view activity){
        this.activity = activity;
    }
    /**
     * reload data to full view with all card and type of card
     */
    public void loadData(){
        SharedPreferences sharedPreferences = activity.getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();

        String json = sharedPreferences.getString("Item_Data_Meat", null);
        if (json != null) {
            arrayList = gson.fromJson(json, type);
            for (int i = 0; i < arrayList.size(); i++) {
                activity.addCard_meat(arrayList.get(i).itemName, arrayList.get(i).itemNumber);
            }
        }

        json = sharedPreferences.getString("Item_Data_Milk", null);
        if (json != null) {
            arrayList = gson.fromJson(json, type);
            for (int i = 0; i < arrayList.size(); i++) {
                activity.addCard_milky(arrayList.get(i).itemName, arrayList.get(i).itemNumber);
            }
        }

        json = sharedPreferences.getString("Item_Data_Veg", null);
        if (json != null) {
            arrayList = gson.fromJson(json, type);
            for (int i = 0; i < arrayList.size(); i++) {
                activity.addCard_vege(arrayList.get(i).itemName, arrayList.get(i).itemNumber);
            }
        }

        json = sharedPreferences.getString("Item_Data_Cleaning", null);
        if (json != null) {
            arrayList = gson.fromJson(json, type);
            for (int i = 0; i < arrayList.size(); i++) {
                activity.addCard_clean(arrayList.get(i).itemName, arrayList.get(i).itemNumber);
            }
        }

        json = sharedPreferences.getString("Item_Data_Dry", null);
        if (json != null) {
            arrayList = gson.fromJson(json, type);
            for (int i = 0; i < arrayList.size(); i++) {
                activity.addCard_dry(arrayList.get(i).itemName, arrayList.get(i).itemNumber);
            }
        }

        json = sharedPreferences.getString("Item_Data_my_category", null);
        if (json != null) {
            arrayList = gson.fromJson(json, type);
            for (int i = 0; i < arrayList.size(); i++) {
                activity.addCard_my_category(arrayList.get(i).itemName, arrayList.get(i).itemNumber);
            }
        }
    }
    /**
     * @param name => item name that customer select to delete
     * @param count => count item that customer select to delete
     *  1. remove item from the screen
     *  2. remove item from sharedPreferences
     */
    public void removeArray_vege(String name, String count) {
        SharedPreferences sharedPreferences = activity.getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();
        String json = sharedPreferences.getString("Item_Data_Veg", null);
        arrayList = gson.fromJson(json, type);
        for (int i = 0; i < arrayList.size();i++) {
            if (arrayList.get(i).getItemName().equals(name) &&
                    arrayList.get(i).itemNumber.equals(count)) {
                arrayList.remove(i);
            }
        }
        json = gson.toJson(arrayList);
        editor.putString("Item_Data_Veg", json);
        editor.apply();
    }


    /**
     * @param name => item name that customer select to delete
     * @param count => count item that customer select to delete
     *  1. remove item from the screen
     *  2. remove item from sharedPreferences
     */
    public void removeArray_meat(String name, String count) {
        SharedPreferences sharedPreferences = activity.getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();
        String json = sharedPreferences.getString("Item_Data_Meat", null);
        arrayList = gson.fromJson(json, type);
        for (int i = 0; i < arrayList.size();i++) {
            if (arrayList.get(i).getItemName().equals(name) &&
                    arrayList.get(i).itemNumber.equals(count)) {
                arrayList.remove(i);
            }
        }
        json = gson.toJson(arrayList);
        editor.putString("Item_Data_Meat", json);
        editor.apply();
    }

    /**
     * @param name => item name that customer select to delete
     * @param count => count item that customer select to delete
     *  1. remove item from the screen
     *  2. remove item from sharedPreferences
     */
    public void removeArray_milky(String name, String count) {
        SharedPreferences sharedPreferences = activity.getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();
        String json = sharedPreferences.getString("Item_Data_Milk", null);
        arrayList = gson.fromJson(json, type);
        for (int i = 0; i < arrayList.size();i++) {
            if (arrayList.get(i).getItemName().equals(name) &&
                    arrayList.get(i).itemNumber.equals(count)) {
                arrayList.remove(i);
            }
        }
        json = gson.toJson(arrayList);
        editor.putString("Item_Data_Milk", json);
        editor.apply();
    }

    /**
     * @param name => item name that customer select to delete
     * @param count => count item that customer select to delete
     *  1. remove item from the screen
     *  2. remove item from sharedPreferences
     */
    public void removeArray_clean(String name, String count) {
        SharedPreferences sharedPreferences = activity.getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();
        String json = sharedPreferences.getString("Item_Data_Cleaning", null);
        arrayList = gson.fromJson(json, type);
        for (int i = 0; i < arrayList.size();i++) {
            if (arrayList.get(i).getItemName().equals(name) &&
                    arrayList.get(i).itemNumber.equals(count)) {
                arrayList.remove(i);
            }
        }
        json = gson.toJson(arrayList);
        editor.putString("Item_Data_Cleaning", json);
        editor.apply();
    }

    /**
     * @param name => item name that customer select to delete
     * @param count => count item that customer select to delete
     *  1. remove item from the screen
     *  2. remove item from sharedPreferences
     */
    public void removeArray_dry(String name, String count) {
        SharedPreferences sharedPreferences = activity.getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();
        String json = sharedPreferences.getString("Item_Data_Dry", null);
        arrayList = gson.fromJson(json, type);
        for (int i = 0; i < arrayList.size();i++) {
            if (arrayList.get(i).getItemName().equals(name) &&
                    arrayList.get(i).itemNumber.equals(count)) {
                arrayList.remove(i);
            }
        }
        json = gson.toJson(arrayList);
        editor.putString("Item_Data_Dry", json);
        editor.apply();
    }

    public void removeArray_my_category(String name, String count) {
        SharedPreferences sharedPreferences = activity.getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();
        String json = sharedPreferences.getString("Item_Data_my_category", null);
        arrayList = gson.fromJson(json, type);
        for (int i = 0; i < arrayList.size();i++) {
            if (arrayList.get(i).getItemName().equals(name) &&
                    arrayList.get(i).itemNumber.equals(count)) {
                arrayList.remove(i);
            }
        }
        json = gson.toJson(arrayList);
        editor.putString("Item_Data_my_category", json);
        editor.apply();
    }

}