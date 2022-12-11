package com.example.smartfridge.costumer;

import static android.content.ContentValues.TAG;
import static com.example.smartfridge.SortProducts.mixCombination;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.ModelClass;
import com.example.smartfridge.R;
import com.example.smartfridge.SortProducts;
import com.example.smartfridge.createAccount;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ShoppingTables extends AppCompatActivity {

    private ImageButton bt_meat;
    private ImageButton bt_milky;
    private ImageButton bt_clean;
    private ImageButton bt_dry;
    private ImageButton bt_vegetables;
    private ImageButton bt_recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_tables);

        bt_meat = (ImageButton) findViewById(R.id.bt_meat);
        bt_milky = (ImageButton) findViewById(R.id.bt_milky);
        bt_clean = (ImageButton) findViewById(R.id.bt_clean);
        bt_dry = (ImageButton) findViewById(R.id.bt_dty);
        bt_vegetables = (ImageButton) findViewById(R.id.bt_vegetables);
        bt_recipes = (ImageButton) findViewById(R.id.bt_recipes);

        bt_meat.setOnClickListener(v -> openPage_meat());
        bt_milky.setOnClickListener(v -> openPage_milky());
        bt_clean.setOnClickListener(v -> openPage_clean());
        bt_dry.setOnClickListener(v -> openPage_dry());
        bt_vegetables.setOnClickListener(v -> openPage_bt_vegetables());
        bt_recipes.setOnClickListener(v -> createRecipes());

    }

    public void openPage_meat() {
        Intent intent = new Intent(this, page_meat.class);
        startActivity(intent);
    }
    public void openPage_milky() {
        Intent intent = new Intent(this, page_milky.class);
        startActivity(intent);
    }
    public void openPage_clean() {
        Intent intent = new Intent(this, page_cleaning_materials.class);
        startActivity(intent);
    }
    public void openPage_dry() {
        Intent intent = new Intent(this, page_dryFood.class);
        startActivity(intent);
    }
    public void openPage_bt_vegetables() {
        Intent intent = new Intent(this, page_vegetables.class);
        startActivity(intent);
    }
    public void createRecipes(){
        SortProducts.getKeys().clear();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
        Gson gson = new Gson();

        String json = sharedPreferences.getString("Item_Data_meat", null);
        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();
        ArrayList<ModelClass> arrayList_all = gson.fromJson(json, type);

        ArrayList<ModelClass> arrayList_temp = new ArrayList<>();
        json = sharedPreferences.getString("Item_Data_milky", null);
        if (json != null){
            arrayList_temp = gson.fromJson(json, type);
            arrayList_all.addAll(arrayList_temp);
        }

        json = sharedPreferences.getString("Item_Data_vege", null);
        if (json != null) {
            arrayList_temp = gson.fromJson(json, type);
            arrayList_all.addAll(arrayList_temp);
        }

        json = sharedPreferences.getString("Item_Data_Dry", null);
        if (json != null) {
            arrayList_temp = gson.fromJson(json, type);
            arrayList_all.addAll(arrayList_temp);
        }

        String[] products = new String[arrayList_all.size()];
        if (products.length!=0) {
            for (int i = 0; i < products.length; i++) {
                products[i] = arrayList_all.get(i).getItemName();
            }
        }

        boolean algo_has_been_activated = false;
        for (int i = 3; i <5 ; i++) { //try to find out if we got enough products to get a recipe.
            if (products.length >= i)
            {
                mixCombination(products, products.length, i);
                algo_has_been_activated = true;
            }
        }
        if (!algo_has_been_activated) //if w dont have the algorithm not gonna be active.
            Toast.makeText(ShoppingTables.this,"There is not enough products to create a recipe!",Toast.LENGTH_LONG).show();
    }
}