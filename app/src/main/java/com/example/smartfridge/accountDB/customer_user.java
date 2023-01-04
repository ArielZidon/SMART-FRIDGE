package com.example.smartfridge.accountDB;
import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.smartfridge.admin.adminView;
import com.example.smartfridge.ui.main.MainActivity;
import com.example.smartfridge.ui.main.MainMenu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.R;
import com.google.android.material.button.MaterialButton;

import java.util.Map;

public class customer_user extends AppCompatActivity {
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_user);

        firestore = FirebaseFirestore.getInstance();//Initialization of the object firestore


        TextView email =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        MaterialButton forgotpass = (MaterialButton) findViewById(R.id.forgotpass);

        //open MaterialButton loginbtn - 0n click domain
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //basic terms for email to not be empty.
//                if(email.getText().toString().equals("")){
//                    Toast.makeText(customer_USER.this,"The email: "+email.getText().toString()+" is NOT registered in the system",Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    if (password.getText().toString().contains("SFmanager")) {
//                        DocumentReference docRef = firestore.collection("manager_accounts").document(email.getText().toString());
//                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                                if (task.isSuccessful()) {
//                                    DocumentSnapshot document = task.getResult();
//                                    if (document.exists()) {
//                                        Map<String, Object> info = document.getData();
//                                        if(info.containsValue(password.getText().toString())){
//                                            Toast.makeText(customer_USER.this,"SINGIN SUCCESSFUL.\nHELLO MANAGER!",Toast.LENGTH_SHORT).show();
//                                            openManager();
//                                        }
//                                        else {
//                                            Toast.makeText(customer_USER.this,"PASSWORD IS UNCORRECTED! \nTRY AGAIN PLEASE.",Toast.LENGTH_LONG).show();
//                                        }
//                                    }
//                                    else {
//                                        Toast.makeText(customer_USER.this,"The email: "+email.getText().toString()+" is NOT registered in the system",Toast.LENGTH_SHORT).show();
//                                        Log.d(TAG, "No such document");
//                                    }
//                                }
//                                else {
//                                    Log.d(TAG, "get failed with ", task.getException());
//                                }
//                            }
//                        });
//                    }
//                    else {
//                        DocumentReference docRef = firestore.collection("costumer_accounts").document(email.getText().toString());
//                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                                if (task.isSuccessful()) {
//                                    DocumentSnapshot document = task.getResult();
//                                    if (document.exists()) {
//                                        Map<String, Object> info = document.getData();
//                                        if(info.containsValue(password.getText().toString())){
//                                            Toast.makeText(customer_USER.this,"SINGIN SUCCESSFUL.\nHELLO COSTUMER!",Toast.LENGTH_SHORT).show();
//                                            openCostumers();
//                                        }
//                                        else {
//                                            Toast.makeText(customer_USER.this,"PASSWORD IS UNCORRECTED! \nTRY AGAIN PLEASE.",Toast.LENGTH_LONG).show();
//                                        }
//                                    }
//                                    else {
//                                        Toast.makeText(customer_USER.this,"The email: "+email.getText().toString()+" is NOT registered in the system",Toast.LENGTH_SHORT).show();
//                                        Log.d(TAG, "No such document");
//                                    }
//                                }
//                                else {
//                                    Log.d(TAG, "get failed with ", task.getException());
//                                }
//                            }
//                        });
//                    }
//
//                }

                if(email.getText().toString().equals("") && password.getText().toString().equals("")){
                    //correct
                    Toast.makeText(customer_user.this,"LOGIN SUCCESSFUL.\nHELLO COSTUMER!",Toast.LENGTH_SHORT).show();
                    openCostumers();
                }else
                    //incorrect
                    Toast.makeText(customer_user.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();
            }
        });



        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to - create_account wind..
                openForgotpass();
            }
        });;
    }



    public void  openCostumers(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    public void  openManager(){
        Intent intent = new Intent(this, adminView.class);
        startActivity(intent);
    }

    public void  openForgotpass(){
        Intent intent = new Intent(this, forgot_password.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
}
}