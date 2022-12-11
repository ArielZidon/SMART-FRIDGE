package com.example.smartfridge.manager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.R;

public class manager extends AppCompatActivity {
    ImageButton Recipes;        //ImageButton that represent the Button "recipes"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        Recipes = findViewById(R.id.recipes_manager);

        Recipes.setOnClickListener(v -> uploadRecipes());
    }

    public void uploadRecipes() {
        Intent intent = new Intent(this, Upload_Recipes.class);
        startActivity(intent);
    }
}