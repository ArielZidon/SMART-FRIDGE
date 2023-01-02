package com.example.smartfridge.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.smartfridge.R;
import com.example.smartfridge.accountDB.createAccount;
import com.example.smartfridge.admin.admin_user;
import com.example.smartfridge.databinding.ActivityMainBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import com.example.smartfridge.accountDB.customer_user;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore firestore;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firestore = FirebaseFirestore.getInstance();//Initialization of the object firestore


        TextView tital = (TextView) findViewById(R.id.title);

        MaterialButton signupbtn = (MaterialButton) findViewById(R.id.signupbtn);
        MaterialButton signinbtn = (MaterialButton) findViewById(R.id.signinbtn);
        MaterialButton mamagebtn = (MaterialButton) findViewById(R.id.mamagebtn);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to - create_account wind..
                openCreateAccount();
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
                //Go to - admin_user login wind.
                openManagement();
            }
        });

    }

    public void  openCreateAccount(){
        Intent intent = new Intent(this, createAccount.class);
        startActivity(intent);
    }

    public void openLogin() {
        Intent intent = new Intent(this, customer_user.class);
        startActivity(intent);
    }

    public void openManagement() {
        Intent intent = new Intent(this, admin_user.class);
        startActivity(intent);
    }
/*
    public void openRecipes() {
        Intent intent = new Intent(this, get_recipes.class);
        startActivity(intent);
    }
 */
}