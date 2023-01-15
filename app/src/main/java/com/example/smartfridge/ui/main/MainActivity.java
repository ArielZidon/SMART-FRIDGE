package com.example.smartfridge.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.R;
import com.example.smartfridge.accountDB.createAccountActivity;
import com.example.smartfridge.accountDB.customer_user;
import com.example.smartfridge.admin.adminView;
//import com.example.smartfridge.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore firestore;
    //rivate ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firestore = FirebaseFirestore.getInstance();//Initialization of the object firestore

        TextView title = (TextView) findViewById(R.id.title);

        MaterialButton signUpBtn = (MaterialButton) findViewById(R.id.signupbtn);
        MaterialButton signInBtn = (MaterialButton) findViewById(R.id.signinbtn);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to - create_account wind..
                openCreateAccount();
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to - customer_user login wind.
                openLogin();
            }
        });
    }

    public void  openCreateAccount(){
        Intent intent = new Intent(this, createAccountActivity.class);
        startActivity(intent);
    }

    public void openLogin() {
        Intent intent = new Intent(this, customer_user.class);
        startActivity(intent);
    }

    public void openManagement() {
        Intent intent = new Intent(this, adminView.class);
        startActivity(intent);
    }
}