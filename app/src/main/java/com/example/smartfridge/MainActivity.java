package com.example.smartfridge;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.smartfridge.ui.main.SectionsPagerAdapter;
import com.example.smartfridge.databinding.ActivityMainBinding;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore firestore; //item that represent the DB firestore
    private Button whatToCook; //Button that represent the Button "whatToCook"
    private Button myShoppingList;//Button that represent the Button "myShoppingList"
    private Button recipes;//Button that represent the Button "recipes"
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firestore = FirebaseFirestore.getInstance();//Initialization of the object firestore

//gonna change, not gonna by here!!!!
        //************************************************
        Map<String, Object> users = new HashMap<>();
//        users.put("ariel","backend");
//        users.put("eran","database");
//        users.put("ofir","administration + database");
//        users.put("afik","design");
        users.put(new KeyToTheRecipe("flour","Tomato sauce","Yellow cheese").createKey(), "המצרכים (4 מנות): " + "\n" +
                "לבצק:" + "\n" + "\n" +
                "500 גרם קמח" + "\n" +
                "2 כפיות של שמרים יבשים" + "\n" +
                "כפית סוכר" + "\n" +
                "כפית מלח" + "\n" +
                "3-4 כפות של שמן זית" + "\n" +
                "1/3 + 1 כוסות של מים פושרים" + "\n" +
                "לרוטב העגבניות:" + "\n" + "\n" +
                "מעט שמן זית" + "\n" +
                "3 עגבניות קלופות וקצוצות" + "\n" +
                "5 שיני שום כתושות" + "\n" +
                "צרור בזיליקום טרי" + "\n" +
                "1/2 כפית סוכר" + "\n" +
                "1 כפית מלח" + "\n" +
                "2 כפות רסק עגבניות" + "\n" +
                "1 כפית אורגנו יבש" + "\n" +
                "להרכבה:" + "\n" + "\n" +
                "300 גרם גבינה קשה מגוררת מכל סוג שאוהבים לפיזור על הפיצה" + "\n" +
                "תוספות שאוהבים (פטריות, זיתים, גבינה מלוחה וכו')");



        firestore.collection("users").add(users).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(),"Failue",Toast.LENGTH_LONG).show();
            }
        });
//************************************************ gonna change, not gonna by here!!!!

        /*
        Jump buttons by mouse click
         */
        whatToCook = (Button) findViewById(R.id.WhatToCook);
        myShoppingList = (Button) findViewById(R.id.MyShoppingList);
        recipes = (Button) findViewById(R.id.Recipes);

        /*
        Application of the transition buttons
         */
        whatToCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatToCook();
            }
        });
        myShoppingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyShoppingList();
            }
        });
        recipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecipes();
            }
        });


    }
/*
Functions that send the click on the transition
buttons to the activity intended for them
 */
    public void openWhatToCook() {
        Intent intent = new Intent(this, whatToCook.class);
        startActivity(intent);
    }

    public void openMyShoppingList() {
        Intent intent = new Intent(this, myShoppingList.class);
        startActivity(intent);
    }

    public void openRecipes() {
        Intent intent = new Intent(this, recipes.class);
        startActivity(intent);
    }
}