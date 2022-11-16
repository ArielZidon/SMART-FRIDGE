package com.example.smartfridge;

import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import com.example.smartfridge.ui.main.SectionsPagerAdapter;
import com.example.smartfridge.databinding.ActivityMainBinding;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore firestore;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firestore = FirebaseFirestore.getInstance();

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

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = binding.fab;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}