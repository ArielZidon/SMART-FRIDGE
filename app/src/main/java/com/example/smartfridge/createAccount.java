package com.example.smartfridge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartfridge.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.Query;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class createAccount extends AppCompatActivity {

    FirebaseFirestore firestore;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firestore = FirebaseFirestore.getInstance();//Initialization of the object firestore
        Map<TextView, ArrayList<TextView>> USERS = new HashMap<>();

        firestore.collection("USERS").add(USERS).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(),"Failue",Toast.LENGTH_LONG).show();
            }
        });


        //tital
        TextView createaccount = (TextView) findViewById(R.id.createaccount);

        //Need to:
        //Make sure they work.
        //Write a func that checks if the name,email,pass is legal.
        //Write a func that put the user in a DB of users.
        TextView name = (TextView) findViewById(R.id.name);
        TextView email = (TextView) findViewById(R.id.email);
        TextView password = (TextView) findViewById(R.id.password);
        TextView user = (TextView) findViewById(R.id.user);

        if(!(USERS.containsKey(email))){
            ArrayList<TextView> INFO = new ArrayList<>();
            INFO.add(name);
            INFO.add(user);
            INFO.add(password);

            USERS.put(email,INFO);
            Toast.makeText(createAccount.this,"SINGUP SUCCESSFUL.\nHELLO COSTUMER!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(createAccount.this,"The email: "+email+" is already registered in the system",Toast.LENGTH_SHORT).show();
        }


        //make the insert_user work!!!!


        MaterialButton createbtn = (MaterialButton) findViewById(R.id.createbtn);

        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if user == customer | go to customer app wind
                // else (user == manager | go to manager app wind)
            }
        });


    }

//    public static void insert_user(FirebaseFirestore USERS,TextView name,TextView email,TextView password,TextView user){
//        if(!(USERS.containsKey(email))){
//            ArrayList<TextView> INFO = new ArrayList<>();
//            INFO.add(name);
//            INFO.add(user);
//            INFO.add(password);
//
//            USERS.put(email,INFO);
//            Toast.makeText(createAccount.this,"SINGUP SUCCESSFUL.\nHELLO COSTUMER!",Toast.LENGTH_SHORT).show();
//        }
//        else{
//            Toast.makeText(createAccount.this,"The email: "+email+" is already registered in the system",Toast.LENGTH_SHORT).show();
//        }
//    }
}