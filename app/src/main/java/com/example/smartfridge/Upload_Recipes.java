package com.example.smartfridge;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Upload_Recipes extends AppCompatActivity {

    FirebaseFirestore firestore; //item that represent the DB firestore
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_recipes);

        Map<String, Object> users = new HashMap<>();
        users.put("ariel","backend");
        users.put("eran","database");
        users.put("ofir","administration + database");
        users.put("afik","design");

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

        firestore.collection("users").add(users).addOnSuccessListener(documentReference -> Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show()).addOnSuccessListener(documentReference -> Toast.makeText(getApplicationContext(),"Failue",Toast.LENGTH_LONG).show());
    }
}