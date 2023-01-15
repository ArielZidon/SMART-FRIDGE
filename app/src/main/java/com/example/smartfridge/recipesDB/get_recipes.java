package com.example.smartfridge.recipesDB;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.R;
import com.example.smartfridge.ui.main.MainMenu;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.FirebaseFirestore;

public class get_recipes extends AppCompatActivity {

    MaterialButton createRecipes;
    LinearLayout layout;

    MaterialButton read;
    FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        db = FirebaseFirestore.getInstance();
        read = findViewById(R.id.read);

        layout = findViewById(R.id.container_recipes);
        createRecipes = findViewById(R.id.crete_recipes);

        createRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateRecipes();
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFromDb();
            }
        });
    }

    private void readFromDb() {
        Intent intent = new Intent(this, recipes_wind.class);
        startActivity(intent);
    }

    private void CreateRecipes() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
        boolean algo_has_been_activated = createKeys.Create_keys_for_Recipes(sharedPreferences);
        if (!algo_has_been_activated) //if w dont have the algorithm not gonna be active.
            Toast.makeText(get_recipes.this, "There is not enough products to create a recipe!", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(get_recipes.this, "Just a moment, recipes are being prepared for you!\uD83D\uDE01", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
