package com.example.smartfridge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartfridge.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class customer_USER extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_user);


        TextView username =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("USERS");
        Query q = usersRef.orderByKey().equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
//        activity_customer_user.xml
        //admin and admin

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    //correct
                    Toast.makeText(customer_USER.this,"LOGIN SUCCESSFUL.\nHELLO COSTUMER!",Toast.LENGTH_SHORT).show();
                    openUploadRecipes();
                }else
                    //incorrect
                    Toast.makeText(customer_USER.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void openUploadRecipes() {
        Intent intent = new Intent(this, costumers.class);
        startActivity(intent);
        //test
    }
}