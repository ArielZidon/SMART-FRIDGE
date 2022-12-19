package com.example.smartfridge.costumer;

import static android.content.ContentValues.TAG;
import static com.example.smartfridge.SortProducts.giveMeKeys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.R;
import com.example.smartfridge.createAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class recipes extends AppCompatActivity {
    MaterialButton read;
    FirebaseFirestore db;
    static ArrayList<String> keys = new ArrayList<>();  /* will hold the data and send the keys to FireBase */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        db = FirebaseFirestore.getInstance();
        read = findViewById(R.id.read);
        read.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /**
                * clear the array from previous data
                * get the data from the algorithm
                * have enough products or not
                */
                keys.clear();
                giveMeKeys(keys);
                Log.d(TAG, "SIZE DATA: " + keys.size());//for test only
                for (int i = 0; i < keys.size(); i++) {
//                    Log.d(TAG, "onClick: " + keys.get(i).toString());
                    if(keys.get(i).contains("eggs,tomatoes,garlic,onion\n")){
                        Log.d(TAG, "onClick: --------------------yes!! capara /n-------------------");
                    }
                    if(keys.get(i).contains("eggs,tomatoes,garlic,onion")){
                        Log.d(TAG, "onClick: --------------------yes!! capara-------------------");
                    }
                }
                if (keys.size() == 0) //if we dont have enough products, dont make the search
                {
                    Toast.makeText(recipes.this, "There is not enough products to \ncreate a recipe!\nAdd some products and try again!!", Toast.LENGTH_LONG).show();
                }else {
                    for (int i = 0; i < keys.size(); i++) {
                        DocumentReference docRef = db.collection("recipes").document(keys.get(i));
                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
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
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, com.example.smartfridge.costumer.costumers.class);
        startActivity(intent);
    }
}
