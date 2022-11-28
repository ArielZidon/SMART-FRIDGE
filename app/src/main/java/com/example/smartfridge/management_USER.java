package com.example.smartfridge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartfridge.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.ArchTaskExecutor;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.Query;

public class management_USER extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_user);

        TextView username =(TextView) findViewById(R.id.m_username);
        TextView password =(TextView) findViewById(R.id.m_password);

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("USERS");

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.m_loginbtn);

        //admin and admin
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("") && password.getText().toString().equals("")){
                    //correct
                    Toast.makeText(management_USER.this,"LOGIN SUCCESSFUL.\nHELLO MANAGER!",Toast.LENGTH_SHORT).show();
                    openUploadRecipes();
                }else
                    //incorrect
                    Toast.makeText(management_USER.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void openUploadRecipes() {
        Intent intent = new Intent(this, manager.class);
        startActivity(intent);
    }
//    ic_baseline_person_outline_24
//    ic_baseline_info_24
}