package com.example.smartfridge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import android.os.Bundle;

public class whatToCook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_to_cook);


        TextView username =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        //admin and admin

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    //correct
                    Toast.makeText(whatToCook.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
                    openUploadRecipes();
                }else
                    //incorrect
                    Toast.makeText(whatToCook.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void openUploadRecipes() {
        Intent intent = new Intent(this, Upload_Recipes.class);
        startActivity(intent);
    }
}