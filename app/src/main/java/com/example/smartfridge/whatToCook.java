package com.example.smartfridge;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class whatToCook extends AppCompatActivity {
    Button b1, b2;
    EditText recipeName, pTime, Ingredients, pOrder;
    FirebaseFirestore firestore;
    Ingredient ingredient = new Ingredient();
    ArrayList<String> namesArrayList;
    ArrayList<Ingredient> ingArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_to_cook);

        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        recipeName = (EditText) findViewById(R.id.editText);
        pTime = (EditText) findViewById(R.id.editText2);
        pOrder = (EditText) findViewById(R.id.editText4);

        firestore = FirebaseFirestore.getInstance();
        CollectionReference Recipes_Db = firestore.collection("recipes");
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddIngredients();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String collection = "recipes";
                namesArrayList=ingredient.getNamesArrayList();
                ingArrayList=ingredient.getIngArray();
                Log.d(TAG,namesArrayList.toString());
                DocumentReference docRef = firestore.collection(collection).document(namesArrayList.toString());
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            Map<String, Object> info_c = new HashMap<>();
                            info_c.put("name", recipeName.getText().toString());
                            info_c.put("Preparing Time", pTime.getText().toString());
                            info_c.put("Preparing Order", pOrder.getText().toString());

                                info_c.put("Ingredients", ingArrayList.toString());

//                                    authentication.createUserWithEmailAndPassword(String.valueOf(email), String.valueOf(password));
//                                    firestore.collection("costumer_accounts").document(String.valueOf(email)).set(info_c);
                            Recipes_Db.document(namesArrayList.toString()).set(info_c);
                            Toast.makeText(whatToCook.this, "recipe in", Toast.LENGTH_LONG).show();
                            Log.d(TAG, "success");
                            ingredient.clearIngredients();
                            ingredient.clearNames();
                            openWhatToCook();
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }

                });
            }
        });
    }

    public void openWhatToCook() {
        Intent intent = new Intent(this, whatToCook.class);
        startActivity(intent);
    }

    public void openAddIngredients() {
        Intent intent = new Intent(this, add_Ingredients.class);
        startActivity(intent);
    }
}


