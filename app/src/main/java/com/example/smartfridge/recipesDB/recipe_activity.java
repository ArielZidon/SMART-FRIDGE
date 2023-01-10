package com.example.smartfridge.recipesDB;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.R;

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
        TextView mUploadBy = (TextView) findViewById(R.id.UploadBy);
        TextView mUser = (TextView) findViewById(R.id.user);

        Intent intent = getIntent();

        String title = intent.getExtras().getString("RecipeName");
        String time = intent.getExtras().getString("Time");
        String ingredients_title = intent.getExtras().getString("RecipeIngredientsTitle");
        String ingredients = intent.getExtras().getString("RecipeIngredients");
        String method = intent.getExtras().getString("RecipeMethodTitle");
        String recipe = intent.getExtras().getString("Recipe");
        int img = intent.getExtras().getInt("img");
        String user = intent.getExtras().getString("user");

        mRecipeName.setText(title);
        mRecipeIngredientsTitle.setText(ingredients_title);
        mTime.setText(time);
        mRecipeIngredients.setText(ingredients);
        mRecipeMethodTitle.setText(method);
        mRecipe.setText(recipe);
        mUser.setText(user);
    }
}