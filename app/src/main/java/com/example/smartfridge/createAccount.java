package com.example.smartfridge;


import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartfridge.manager.management_USER;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import com.example.smartfridge.costumer.costumers;
import com.example.smartfridge.manager.manager;

public class createAccount extends AppCompatActivity {

    FirebaseFirestore firestore;
    FirebaseAuth authentication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        firestore = FirebaseFirestore.getInstance();//Initialization of the object firestore
        CollectionReference costumers_DB = firestore.collection("costumer_accounts");
        CollectionReference managers_DB = firestore.collection("manager_accounts");


        //tital
        TextView createaccount = (TextView) findViewById(R.id.createaccount);


        TextView name = (TextView) findViewById(R.id.name);
        TextView email = (TextView) findViewById(R.id.email);
        TextView password = (TextView) findViewById(R.id.password);
        TextView user = (TextView) findViewById(R.id.user);


        MaterialButton createbtn = (MaterialButton) findViewById(R.id.createbtn);

        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user.getText().toString().equals("costumer")){
                    String collection = "costumer_accounts";
                    DocumentReference docRef = firestore.collection(collection).document(email.getText().toString());
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    Toast.makeText(createAccount.this, "The email: " + email.getText().toString() + " is registered in the system", Toast.LENGTH_SHORT).show();
                                } else {
                                    Map<String, Object> info_c = new HashMap<>();
                                    info_c.put("name", name.getText().toString());
                                    info_c.put("user_type", user.getText().toString());
                                    info_c.put("password", password.getText().toString());
//                                    authentication.createUserWithEmailAndPassword(String.valueOf(email), String.valueOf(password));
//                                    firestore.collection("costumer_accounts").document(String.valueOf(email)).set(info_c);
                                    costumers_DB.document(email.getText().toString()).set(info_c);
                                    Toast.makeText(createAccount.this,"SINGUP SUCCESSFUL.\nHELLO COSTUMER!",Toast.LENGTH_LONG).show();
                                    openCostumers();
                                }
                            } else {
                                Log.d(TAG, "get failed with ", task.getException());
                            }
                        }
                    });
                }
                if(user.getText().toString().equals("manager")){
                    String collection = "manager_accounts";
                    DocumentReference docRef = firestore.collection(collection).document(email.getText().toString());
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    Toast.makeText(createAccount.this, "The email: " + email.getText().toString() + " is registered in the system", Toast.LENGTH_SHORT).show();
                                } else {
                                    Map<String, Object> info_c = new HashMap<>();
                                    info_c.put("name", name.getText().toString());
                                    info_c.put("user_type", user.getText().toString());
                                    info_c.put("password", password.getText().toString());
//                                    authentication.createUserWithEmailAndPassword(String.valueOf(email), String.valueOf(password));
//                                    firestore.collection("costumer_accounts").document(String.valueOf(email)).set(info_c);
                                    managers_DB.document(email.getText().toString()).set(info_c);
                                    Toast.makeText(createAccount.this,"SINGUP SUCCESSFUL.\nHELLO MANAGER!",Toast.LENGTH_LONG).show();
                                    openManagers();
                                }
                            } else {
                                Log.d(TAG, "get failed with ", task.getException());
                            }
                        }
                    });
                }
//                else Toast.makeText(createAccount.this,"ERROR SINGING UP",Toast.LENGTH_LONG).show();
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
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, com.example.smartfridge.MainActivity.class);
        startActivity(intent);
    }
}


//                Map<Object, Map<String, Object>> costumer_accounts = new HashMap<>();
//
//        firestore.collection("costumer_accounts").add(costumer_accounts).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//            @Override
//            public void onSuccess(DocumentReference documentReference) {
//                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
//
//            }
//        }).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//            @Override
//            public void onSuccess(DocumentReference documentReference) {
//                Toast.makeText(getApplicationContext(),"Failue",Toast.LENGTH_LONG).show();
//            }
//        });
//
//
//        Map<Object, Map<String, Object>> manager_accounts = new HashMap<>();
//
//        firestore.collection("manager_accounts").add(manager_accounts).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//            @Override
//            public void onSuccess(DocumentReference documentReference) {
//                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
//
//            }
//        }).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//            @Override
//            public void onSuccess(DocumentReference documentReference) {
//                Toast.makeText(getApplicationContext(),"Failue",Toast.LENGTH_LONG).show();
//            }
//        });

//
//                if(String.valueOf(user).equals("costumer")){
//                    if(!(costumer_accounts.containsKey(email))){
//                        Map<String, Object> info = new HashMap<>();
//                        info.put("name",name);
//                        info.put("user_type",user);
//                        info.put("password",password);
//                        costumer_DB.document(String.valueOf(email)).set(info);
//                        authentication.createUserWithEmailAndPassword(String.valueOf(email),String.valueOf(password));
//                        Toast.makeText(createAccount.this,"SINGUP SUCCESSFUL.\nHELLO COSTUMER!",Toast.LENGTH_SHORT).show();
//                        openCostumers();
//                    }
//                    else{
//                        Toast.makeText(createAccount.this,"The email: "+email+" is already registered in the system",Toast.LENGTH_SHORT).show();
//                    }
//                }
//                else {
//                    if(!(manager_accounts.containsKey(email))){
//                        Map<String, Object> info = new HashMap<>();
//                        info.put("name",name);
//                        info.put("user_type",user);
//                        info.put("password",password);
//                        manager_DB.document(String.valueOf(email)).set(info);
//                        authentication.createUserWithEmailAndPassword(String.valueOf(email),String.valueOf(password));
//                        Toast.makeText(createAccount.this,"SINGUP SUCCESSFUL.\nHELLO MANAGER!",Toast.LENGTH_SHORT).show();
//                        openManagers();
//                    }
//                    else{
//                        Toast.makeText(createAccount.this,"The email: "+email+" is already registered in the system",Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
