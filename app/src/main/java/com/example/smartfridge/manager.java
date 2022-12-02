package com.example.smartfridge;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class manager extends AppCompatActivity {
    ImageButton Recipes;        //ImageButton that represent the Button "recipes"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        Recipes = findViewById(R.id.recipes);

        Recipes.setOnClickListener(v -> uploadRecipes());
    }

    public void uploadRecipes() {
        Intent intent = new Intent(this, Upload_Recipes.class);
        startActivity(intent);
    }
}