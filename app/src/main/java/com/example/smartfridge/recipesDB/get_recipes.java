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
        keys.clear();
        giveMeKeys(keys);

        for (int i = 0; i < keys.size(); i++) {
                    Log.d(TAG, "onClick: " + keys.get(i).toString());
        }
        if (keys.size() == 0) //if we dont have enough products, dont make the search
        {
            Toast.makeText(get_recipes.this, "There is not enough products to \ncreate a recipeObject!\nAdd some products and try again!!", Toast.LENGTH_LONG).show();
        }else {
            for (int i = 0; i < keys.size(); i++) {
                DocumentReference docRef = db.collection("recipe_DB").document(keys.get(i));
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                                addCard(document);
                            } else {
                                Log.d(TAG, "No such document");  //for test only!
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException()); //try to see if the search is failed
                        }
                    }
                });
            }
        }
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
