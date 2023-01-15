package com.example.smartfridge.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.R;

public class admin_user_activity extends AppCompatActivity {

    /**here we can see how the recipe will represent in admin view*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user);
        TextView mName = (TextView) findViewById(R.id.name);
        TextView mEmail = (TextView) findViewById(R.id.email);
        TextView mPassword = (TextView) findViewById(R.id.password);

        Intent intent = getIntent();

        String name = intent.getExtras().getString("Name");
        String mail = intent.getExtras().getString("Email");
        String pas = intent.getExtras().getString("password");

        mName.setText(name);
        mEmail.setText(mail);
        mPassword.setText(pas);
    }
}