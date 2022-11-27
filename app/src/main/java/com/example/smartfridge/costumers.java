package com.example.smartfridge;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class costumers extends AppCompatActivity {

    ImageButton Cooking;        //ImageButton that represent the Button "whatToCook"
    ImageButton My_shoping;     //ImageButton that represent the Button "myShoppingList"
    ImageButton Recipes;        //ImageButton that represent the Button "recipes"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.costumers);


        /*
        Jump buttons by mouse click
         */
        Cooking = (ImageButton) findViewById(R.id.cooking);
        My_shoping = (ImageButton) findViewById(R.id.my_shoping);
        Recipes = findViewById(R.id.recipes);

        /*
        Application of the transition buttons
         */
        Cooking.setOnClickListener(v -> openWhatToCook());
        My_shoping.setOnClickListener(v -> openMyShoppingList());
        Recipes.setOnClickListener(v -> openRecipes());


    }
    /*
    Functions that send the click on the transition
    buttons to the activity intended for them
     */
    public void openWhatToCook() {
        Intent intent = new Intent(this, whatToCook.class);
        startActivity(intent);
    }

    public void openMyShoppingList() {
        Intent intent = new Intent(this, shopingListNew.class);
        startActivity(intent);
    }

    public void openRecipes() {
        Intent intent = new Intent(this, recipes.class);
        startActivity(intent);
    }
}