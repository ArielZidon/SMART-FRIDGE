package com.example.smartfridge;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import android.annotation.SuppressLint;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class add_Ingredients extends AppCompatActivity {
    Button b1, b2;
    EditText inName, amount;
    FirebaseFirestore firestore;
    static int counter = 0;
    Ingredient ingredient = new Ingredient();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        inName = (EditText) findViewById(R.id.editText);
        amount = (EditText) findViewById(R.id.editText2);

        ArrayList<Ingredient> ingArray = ingredient.newInArray();

        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Ingredient ingredient = new Ingredient(inName.getText().toString(), amount.getText().toString());
                ingArray.add(ingredient);
                openAddIngredients();
            }
        });


        firestore = FirebaseFirestore.getInstance();
        CollectionReference Recipes_Db = firestore.collection("recipes");
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String collection = "recipes";
                ArrayList<String> recipeKey = ingredient.newNamesArray();
                for (Ingredient i : ingArray) {
                    recipeKey.add(i.InName);
                }
                DocumentReference docRef = firestore.collection(collection).document(ingArray.toString());
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            Map<String, Object> info_c = new HashMap<>();
                            for(Ingredient i:ingArray) {
                                info_c.put("Ingredients", i.toString());
                            }
//                                    authentication.createUserWithEmailAndPassword(String.valueOf(email), String.valueOf(password));
//                                    firestore.collection("costumer_accounts").document(String.valueOf(email)).set(info_c);
                            Recipes_Db.document(ingArray.toString()).set(info_c);
                            Log.d(TAG, "success!!");

                            openWhatToCook();

                        } else {
                            Log.d(TAG, "get failed with ", task.getException());

                        }
                    }
                });
            }
        });

    }
    public void openAddIngredients(){
        Intent intent = new Intent(this, add_Ingredients.class);
        startActivity(intent);
    }
    public void openWhatToCook() {
        Intent intent = new Intent(this, whatToCook.class);
        startActivity(intent);
    }


    }

