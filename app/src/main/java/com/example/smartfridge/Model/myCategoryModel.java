package com.example.smartfridge.Model;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.view.Menu;
import android.widget.Toast;

import com.example.smartfridge.R;
import com.example.smartfridge.business_entities.ModelClass;
import com.example.smartfridge.local_customer_memory.category;
import com.example.smartfridge.local_customer_memory.myCategoryActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class myCategoryModel implements category {
    myCategoryActivity activiy;
    ArrayList<ModelClass> arrayList;
    private String text;
public myCategoryModel(myCategoryActivity activiy){
    this.activiy = activiy;
}
    /**
     * Upload items form sharedPreferences
     * if list == null => create new empty list
     * else => show on the screen all items from the sharedPreferences "Item_Data_meat"
     */
    public void loadData() {
        SharedPreferences sharedPreferences = activiy.getApplicationContext().getSharedPreferences("DATA", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Item_Data_my_category", null);
        Type type = new TypeToken<ArrayList<ModelClass>>() {
        }.getType();
        arrayList = gson.fromJson(json, type);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
//            tvSize.setText(""+0);
        } else {
            for (int i = 0; i < arrayList.size(); i++) {
                activiy.addCard(arrayList.get(i).itemName, arrayList.get(i).itemNumber);
            }
        }
    }

    /**
     * @param name  => item name
     * @param count => count of items (it String because we want to be able to show different options)
     *              save on sharedPreferences item (name, count)
     *              and upload the view with the new item
     */
    public void saveData(String name, String count) {
        SharedPreferences sharedPreferences = activiy.getApplicationContext().getSharedPreferences("DATA", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        arrayList.add((new ModelClass(name, count)));
        String json = gson.toJson(arrayList);
        editor.putString("Item_Data_my_category", json);
        editor.apply();
        activiy.addCard(name, count);
    }
    /**
     * @param str -> the name we what to view
     *  save str in sharedPreferences
     */
    public void saveName(String str){
        SharedPreferences sharedPreferences = activiy.getSharedPreferences("Name", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("text", str);
        editor.apply();

        Toast.makeText(activiy, "Data saved", Toast.LENGTH_SHORT).show();
    }

    /**
     * load the name from sharedPreferences
     */
    public void loadName() {
        SharedPreferences sharedPreferences = activiy.getSharedPreferences("Name", MODE_PRIVATE);
        text = sharedPreferences.getString("text", "");
        activiy.nameView.setText(text);

    }

    public boolean onCreteOptionsMenu(Menu menu) {
        activiy.getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    /**
     * @param name  => item name that customer select to delete
     * @param count => count item that customer select to delete
     *              1. remove item from the screen
     *              2. remove item from sharedPreferences
     */
    public void removeArray(String name, String count) {
        SharedPreferences sharedPreferences = activiy.getApplicationContext().getSharedPreferences("DATA", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getItemName().equals(name) &&
                    arrayList.get(i).itemNumber.equals(count)) {
                arrayList.remove(i);
            }
        }
        String json = gson.toJson(arrayList);
        editor.putString("Item_Data_my_category", json);
        editor.apply();
    }



}
