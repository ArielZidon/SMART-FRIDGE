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

        TextView m_username = (TextView) findViewById(R.id.m_username);
        TextView m_password = (TextView) findViewById(R.id.m_password);

        MaterialButton m_loginbtn = (MaterialButton) findViewById(R.id.m_loginbtn);

        m_loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}