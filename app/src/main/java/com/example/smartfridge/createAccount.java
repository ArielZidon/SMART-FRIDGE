package com.example.smartfridge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class createAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //tital
        TextView createaccount = (TextView) findViewById(R.id.createaccount);

        //Need to:
        //Make sure they work.
        //Write a func that checks if the name,email,pass is legal.
        //Write a func that put the user in a DB of users.
        TextView name = (TextView) findViewById(R.id.name);
        TextView email = (TextView) findViewById(R.id.email);
        TextView password = (TextView) findViewById(R.id.password);
        TextView user= (TextView) findViewById(R.id.user);

        MaterialButton createbtn = (MaterialButton) findViewById(R.id.createbtn);

        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if user == customer | go to customer app wind
                // else (user == manager | go to manager app wind)
            }
        });

    }
}