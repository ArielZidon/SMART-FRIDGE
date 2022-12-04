package com.example.smartfridge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import costumer.customer_USER;
import manager.management_USER;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tital = (TextView) findViewById(R.id.title);

        //MaterialButton signupbtn = (MaterialButton) findViewById(R.id.signupbtn);
        MaterialButton signinbtn = (MaterialButton) findViewById(R.id.signinbtn);
        MaterialButton mamagebtn = (MaterialButton) findViewById(R.id.mamagebtn);
/*
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to - create_account wind.
            }
        });
*/
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

//    public void openRecipes() {
//        Intent intent = new Intent(this, recipes.class);
//        startActivity(intent);
//    }
}