package com.example.smartfridge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import com.example.smartfridge.ui.main.SectionsPagerAdapter;
import com.example.smartfridge.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firestore = FirebaseFirestore.getInstance();//Initialization of the object firestore

//        Map<String, Object> USERS = new HashMap<>();
//
//        firestore.collection("USERS").add(USERS).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//            @Override
//            public void onSuccess(DocumentReference documentReference) {
//                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
//
//            }
//        }).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//            @Override
//            public void onSuccess(DocumentReference documentReference) {
//                Toast.makeText(getApplicationContext(),"Failue",Toast.LENGTH_LONG).show();
//            }
//        });

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("USERS");

        TextView tital = (TextView) findViewById(R.id.title);

        MaterialButton signupbtn = (MaterialButton) findViewById(R.id.signupbtn);
        MaterialButton signinbtn = (MaterialButton) findViewById(R.id.signinbtn);
        MaterialButton mamagebtn = (MaterialButton) findViewById(R.id.mamagebtn);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to - create_account wind.
            }
        });

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to - customer_user login wind.
                openLogin();
            }
        });

        mamagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to - management_user login wind.
                openManagement();
            }
        });

    }

    public void openLogin() {
        Intent intent = new Intent(this, customer_USER.class);
        startActivity(intent);
    }

    public void openManagement() {
        Intent intent = new Intent(this, management_USER.class);
        startActivity(intent);
    }
/*
    public void openRecipes() {
        Intent intent = new Intent(this, recipes.class);
        startActivity(intent);
    }
 */
}