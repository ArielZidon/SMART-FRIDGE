package com.example.smartfridge.recipesDB;

import static android.content.ContentValues.TAG;


import static com.example.smartfridge.business_logic.SortProducts.giveMeKeys;
import static com.example.smartfridge.business_logic.SortProducts.mixCombination;

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

import com.example.smartfridge.business_entities.ModelClass;
import com.example.smartfridge.R;
import com.example.smartfridge.business_logic.SortProducts;
import com.example.smartfridge.ui.main.MainMenu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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
                DocumentReference docRef = db.collection("get_recipes").document(keys.get(i));
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
        if (!SortProducts.getKeys().isEmpty())
            SortProducts.getKeys().clear();

        boolean notEmpty = false;
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
        Gson gson = new Gson();

        String json = sharedPreferences.getString("Item_Data_meat", null);
        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();
        ArrayList<ModelClass> arrayList_all = gson.fromJson(json, type);

        if (gson.fromJson(json, type) != null)
            notEmpty = true;

        ArrayList<ModelClass> arrayList_temp = new ArrayList<>();
        json = sharedPreferences.getString("Item_Data_milky", null);
        if (json != null){
            arrayList_temp = gson.fromJson(json, type);
            arrayList_all.addAll(arrayList_temp);
            notEmpty = true;
        }

        json = sharedPreferences.getString("Item_Data_vege", null);
        if (json != null) {
            arrayList_temp = gson.fromJson(json, type);
            arrayList_all.addAll(arrayList_temp);
            notEmpty = true;
        }

        json = sharedPreferences.getString("Item_Data_Dry", null);
        if (json != null) {
            arrayList_temp = gson.fromJson(json, type);
            arrayList_all.addAll(arrayList_temp);
            notEmpty = true;
        }

        if (notEmpty)
        {
            String[] products = new String[arrayList_all.size()];
            if (products.length > 3) {
                for (int i = 0; i < products.length; i++) {
                    products[i] = arrayList_all.get(i).getItemName();
//                Log.d(TAG, "createRecipes: " + products[i]);
                }
            }

            boolean algo_has_been_activated = false;
            for (int i = 3; i < 5; i++) { //try to find out if we got enough products to get a recipe.
                if (products.length >= i) {
                    mixCombination(products, products.length, i);
                    algo_has_been_activated = true;
                }
            }
            if (!algo_has_been_activated) //if w dont have the algorithm not gonna be active.
                Toast.makeText(get_recipes.this, "There is not enough products to create a recipe!", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(get_recipes.this, "There is not products at all to create a recipe!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
