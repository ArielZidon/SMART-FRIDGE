package com.example.smartfridge.recipesDB;

import static android.content.ContentValues.TAG;
import static com.example.smartfridge.business_logic.SortProducts.giveMeKeys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.R;
import com.example.smartfridge.ui.main.MainMenu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class get_recipes extends AppCompatActivity {

    MaterialButton createRecipes;
    LinearLayout layout;


    MaterialButton read;
    FirebaseFirestore db;
    static ArrayList<String> keys = new ArrayList<>();  /* will hold the data and send the keys to FireBase */

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
        /**
         * clear the array from previous data
         * get the data from the algorithm
         * have enough products or not
         */
        Intent intent = new Intent(this, recipes_wind.class);
        startActivity(intent);
    }

    private void addCard(DocumentSnapshot document) {
    }

    private void CreateRecipes() {
        boolean notEmpty = false;
        boolean algo_has_been_activated = false;
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
        createKeys.Create_keys_for_Recipes(notEmpty,algo_has_been_activated,sharedPreferences);
        if (!notEmpty)
            Toast.makeText(get_recipes.this, "There is not products at all to create a recipe!", Toast.LENGTH_LONG).show();

        else if (notEmpty && !algo_has_been_activated) //if w dont have the algorithm not gonna be active.
            Toast.makeText(get_recipes.this, "There is not enough products to create a recipe!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
