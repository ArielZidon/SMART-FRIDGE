package com.example.smartfridge.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.R;
import com.example.smartfridge.business_logic.getOut;
import com.example.smartfridge.local_customer_memory.ShoppingTables;
import com.example.smartfridge.recipesDB.get_recipes;
import com.example.smartfridge.recipesDB.insert_recipe;
import com.example.smartfridge.recipesDB.recipes_wind;

public class MainMenu extends AppCompatActivity {
    private Button button;

    ImageButton Cooking;        //ImageButton that represent the Button "insertRecipe"
    ImageButton My_shoping;     //ImageButton that represent the Button "myShoppingList"
    ImageButton Recipes;        //ImageButton that represent the Button "get_recipes"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.costumers);


        /**
        Jump buttons by mouse click
         */
        Cooking = (ImageButton) findViewById(R.id.cooking);
        My_shoping = (ImageButton) findViewById(R.id.my_shoping);
        Recipes = findViewById(R.id.recipes);

        /**
        Application of the transition buttons
         */
        Cooking.setOnClickListener(v -> openWhatToCook());
        My_shoping.setOnClickListener(v -> openMyShoppingList());
        Recipes.setOnClickListener(v -> openRecipes());

    }
    /**
    Functions that send the click on the transition
    buttons to the activity intended for them
     */
    public void openWhatToCook() {
        Intent intent = new Intent(this, insert_recipe.class);
        startActivity(intent);
    }

    public void openMyShoppingList() {
        Intent intent = new Intent(this, ShoppingTables.class);
        startActivity(intent);
    }
    public void openRecipeWind() {
        Intent intent = new Intent(this, recipes_wind.class);
        startActivity(intent);
    }

    public void openRecipes() {
        Intent intent = new Intent(this, get_recipes.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        getOut sure_you_want = new getOut();
        sure_you_want.show(getSupportFragmentManager(), "example dialog");
        if(sure_you_want.getTemp() == 1)
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}