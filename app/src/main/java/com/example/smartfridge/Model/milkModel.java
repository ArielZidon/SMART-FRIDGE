package com.example.smartfridge.Model;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;

import com.example.smartfridge.business_entities.ModelClass;
import com.example.smartfridge.local_customer_memory.milkCategoryActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class milkModel {
    milkCategoryActivity activity;
    ArrayList<ModelClass> arrayList;

    public milkModel(milkCategoryActivity activity){
        this.activity = activity;
    }
    /**
     * Upload items form sharedPreferences
     * if list == null => create new empty list
     * else => show on the screen all items from the sharedPreferences "Item_Data_Dry"
     */
    public void loadData() {
        SharedPreferences sharedPreferences = activity.getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Item_Data_Milk", null);
        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();
        arrayList = gson.fromJson(json, type);
        if(arrayList == null){
            arrayList = new ArrayList<>();
//            tvSize.setText(""+0);
        }else {
            for (int i = 0; i < arrayList.size(); i++){
                activity.addCard(arrayList.get(i).itemName, arrayList.get(i).itemNumber);
            }
        }
    }

    /**
     * @param name => item name
     * @param count => count of items (it String because we want to be able to show different options)
     *  save on sharedPreferences item (name, count)
     *  and upload the view with the new item
     */
    public void saveData(String name, String count) {
        SharedPreferences sharedPreferences = activity.getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        arrayList.add((new ModelClass(name, count)));
        String json = gson.toJson(arrayList);
        editor.putString("Item_Data_Milk", json);
        editor.apply();
        activity.addCard(name, count);
    }
    /**
     * @param name => item name that customer select to delete
     * @param count => count item that customer select to delete
     *  1. remove item from the screen
     *  2. remove item from sharedPreferences
     */
    public void removeArray(String name, String count) {
        SharedPreferences sharedPreferences = activity.getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        for (int i = 0; i < arrayList.size();i++) {
            if (arrayList.get(i).getItemName().equals(name) &&
                    arrayList.get(i).itemNumber.equals(count)) {
                arrayList.remove(i);
            }
        }
        String json = gson.toJson(arrayList);
        editor.putString("Item_Data_Milk", json);
        editor.apply();
    }
}
