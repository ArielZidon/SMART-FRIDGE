package com.example.smartfridge;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartfridge.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.Query;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import costumer.costumers;
import manager.manager;

public class createAccount extends AppCompatActivity {

    FirebaseFirestore firestore;
    FirebaseAuth authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        firestore = FirebaseFirestore.getInstance();//Initialization of the object firestore

        //tital
        TextView createaccount = (TextView) findViewById(R.id.createaccount);


        TextView name = (TextView) findViewById(R.id.name);
        TextView email = (TextView) findViewById(R.id.email);
        TextView password = (TextView) findViewById(R.id.password);
        TextView user = (TextView) findViewById(R.id.user);

        Map<Object, Map<String, Object>> costumer_accounts = new HashMap<>();

        firestore.collection("costumer_accounts").add(costumer_accounts).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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


        Map<Object, Map<String, Object>> manager_accounts = new HashMap<>();

        firestore.collection("manager_accounts").add(manager_accounts).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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

        CollectionReference costumer_DB = firestore.collection("costumer_accounts");
        CollectionReference manager_DB = firestore.collection("manager_accounts");

        //tast - insert eran as a costumer and as a manager
        //DONT FORGET TO DELETE THIS!!!!!!
        Map<String, Object> info = new HashMap<>();
        info.put("name","erantzarum");
        info.put("user_type","user");
        info.put("password","password");
        costumer_DB.document("tzarum77@gmail.com").set(info);

        Map<String, Object> info_m = new HashMap<>();
        info_m.put("name","erantzarum");
        info_m.put("user_type","user");
        info_m.put("password","password");
        manager_DB.document("eran.davidtz@gmail.com").set(info);
        //DONT FORGET TO DELETE THIS!!!!!!



        MaterialButton createbtn = (MaterialButton) findViewById(R.id.createbtn);

        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(String.valueOf(user) == "costumer"){
                    if(!(costumer_accounts.containsKey(email))){
                        Map<String, Object> info = new HashMap<>();
                        info.put("name",name);
                        info.put("user_type",user);
                        info.put("password",password);
                        costumer_DB.document(String.valueOf(email)).set(info);
                        authentication.createUserWithEmailAndPassword(String.valueOf(email),String.valueOf(password));
                        Toast.makeText(createAccount.this,"SINGUP SUCCESSFUL.\nHELLO COSTUMER!",Toast.LENGTH_SHORT).show();
                        openCostumers();
                    }
                    else{
                        Toast.makeText(createAccount.this,"The email: "+email+" is already registered in the system",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    if(!(manager_accounts.containsKey(email))){
                        Map<String, Object> info = new HashMap<>();
                        info.put("name",name);
                        info.put("user_type",user);
                        info.put("password",password);
                        manager_DB.document(String.valueOf(email)).set(info);
                        authentication.createUserWithEmailAndPassword(String.valueOf(email),String.valueOf(password));
                        Toast.makeText(createAccount.this,"SINGUP SUCCESSFUL.\nHELLO MANAGER!",Toast.LENGTH_SHORT).show();
                        openManagers();
                    }
                    else{
                        Toast.makeText(createAccount.this,"The email: "+email+" is already registered in the system",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void  openCostumers(){
        Intent intent = new Intent(this, costumers.class);
        startActivity(intent);
    }

    public void  openManagers(){
        Intent intent = new Intent(this, manager.class);
        startActivity(intent);
    }
}