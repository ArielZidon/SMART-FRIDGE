package com.example.smartfridge.costumer;

import static android.content.ContentValues.TAG;
import static com.example.smartfridge.SortProducts.giveMeKeys;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.R;
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
    static ArrayList<String> recipes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        db = FirebaseFirestore.getInstance();
        read = findViewById(R.id.read);
        read.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                recipes.clear();
                giveMeKeys(recipes);
                Log.d(TAG, "SIZE DATA: " + recipes.size());
                for (int i = 0; i <recipes.size() ; i++) {
                    DocumentReference docRef = db.collection("recipes").document(recipes.get(i));
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                                } else {
                                    Log.d(TAG, "No such document");
                                }
                            } else {
                                Log.d(TAG, "get failed with ", task.getException());
                            }
                        }
                    });
                }
            }
        });
    }
}
