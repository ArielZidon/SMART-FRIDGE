package com.example.smartfridge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class recipe_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        TextView mRecipeName = (TextView) findViewById(R.id.text_recipe);
        TextView mRecipeIngredients = (TextView) findViewById(R.id.ingredients);
        TextView mTime = (TextView) findViewById(R.id.time);
        TextView mRecipeMethodTitle = (TextView) findViewById(R.id.method);
        TextView mRecipe = (TextView) findViewById(R.id.recipe);

        Intent intent = getIntent();
        String title = intent.getExtras().getString("RecipeName");
        String ingredients = intent.getExtras().getString("RecipeIngredients");
        String time = intent.getExtras().getString("Time");
        String method = intent.getExtras().getString("RecipeMethodTitle");
        String recipe = intent.getExtras().getString("Recipe");
        int img = intent.getExtras().getInt("img");

        mRecipeName.setText(title);
        mRecipeIngredients.setText(ingredients);
        mTime.setText(time);
        mRecipeMethodTitle.setText(method);
        mRecipe.setText(recipe);



    }
}