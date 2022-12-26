package com.example.smartfridge.manager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class manager extends AppCompatActivity {
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    ImageButton Recipes;        //ImageButton that represent the Button "recipes"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        firestore = FirebaseFirestore.getInstance();//Initialization of the object firestore
        CollectionReference costumers_DB = firestore.collection("costumer_accounts");
        auth = FirebaseAuth.getInstance();//Initialization of the object

        FirebaseUser user = auth.getCurrentUser();
        user.delete();
        user.getEmail();
        user.sendEmailVerification();
        user.updatePassword("password");

        Recipes = findViewById(R.id.recipes_manager);

        Recipes.setOnClickListener(v -> uploadRecipes());
    }

    public void delete_user() {
    }

    public void uploadRecipes() {
        Intent intent = new Intent(this, Upload_Recipes.class);
        startActivity(intent);
    }
}