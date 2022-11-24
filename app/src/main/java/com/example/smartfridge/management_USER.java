package com.example.smartfridge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class management_USER extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_user);

        //Need to:
        //Write a func that checks if the name,email,pass is legal - in the user DB
        TextView m_username = (TextView) findViewById(R.id.m_username);
        TextView m_password = (TextView) findViewById(R.id.m_password);

        MaterialButton m_loginbtn = (MaterialButton) findViewById(R.id.m_loginbtn);

        m_loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //IF user in the user DB | Go to - main_manage wind.
            }
        });

    }
}