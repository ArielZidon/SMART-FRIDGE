package com.example.smartfridge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class first_LOGIN extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_login);

        TextView tital = (TextView) findViewById(R.id.title);

        MaterialButton signupbtn = (MaterialButton) findViewById(R.id.signupbtn);
        MaterialButton signinbtn = (MaterialButton) findViewById(R.id.signinbtn);
//        MaterialButton Managementbtn = (MaterialButton) findViewById(R.id.mamagebtn);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }
}