package com.example.smartfridge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.FirebaseDatabase;

public class recipe_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        TextView mRecipeName = (TextView) findViewById(R.id.text_recipe);
        TextView mTime = (TextView) findViewById(R.id.time);
        TextView mRecipeIngredientsTitle = (TextView) findViewById(R.id.ingredients_title);
        TextView mRecipeIngredients = (TextView) findViewById(R.id.ingredients);
        TextView mRecipeMethodTitle = (TextView) findViewById(R.id.method);
        TextView mRecipe = (TextView) findViewById(R.id.recipe);

        Intent intent = getIntent();

        String title = intent.getExtras().getString("RecipeName");
        String time = intent.getExtras().getString("Time");
        String ingredients_title = intent.getExtras().getString("RecipeIngredientsTitle");
        String ingredients = intent.getExtras().getString("RecipeIngredients");
        String method = intent.getExtras().getString("RecipeMethodTitle");
        String recipe = intent.getExtras().getString("Recipe");
        int img = intent.getExtras().getInt("img");

        mRecipeName.setText(title);
        mRecipeIngredientsTitle.setText(ingredients_title);
        mTime.setText(time);
        mRecipeIngredients.setText(ingredients);
        mRecipeMethodTitle.setText(method);
        mRecipe.setText(recipe);



    }
}