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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    private FirebaseFirestore firestore;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_user);

        firestore = FirebaseFirestore.getInstance();//Initialization of the object firestore
        auth = FirebaseAuth.getInstance();//Initialization of the object


        TextView email =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        MaterialButton forgotpass = (MaterialButton) findViewById(R.id.forgotpass);

        //open MaterialButton loginbtn - 0n click domain
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //basic terms for email to not be empty.
                if(email.getText().toString().equals("")){
                    Toast.makeText(customer_user.this,"The email: "+email.getText().toString()+" is NOT registered in the system",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password.getText().toString().contains("SFmanager")) {
                        DocumentReference docRef = firestore.collection("manager_accounts").document(email.getText().toString());
                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        Map<String, Object> info = document.getData();
                                        if(info.containsValue(password.getText().toString())){
                                            Toast.makeText(customer_user.this,"SINGIN SUCCESSFUL.\nHELLO MANAGER!",Toast.LENGTH_SHORT).show();
                                            Auto_login(email,password);
                                            openManager();
                                        }
                                        else {
                                            Toast.makeText(customer_user.this,"PASSWORD IS UNCORRECTED! \nTRY AGAIN PLEASE.",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    else {
                                        Toast.makeText(customer_user.this,"The email: "+email.getText().toString()+" is NOT registered in the system",Toast.LENGTH_SHORT).show();
                                        Log.d(TAG, "No such document");
                                    }
                                }
                                else {
                                    Log.d(TAG, "get failed with ", task.getException());
                                }
                            }
                        });
                    }
                    else {
                        DocumentReference docRef = firestore.collection("costumer_accounts").document(email.getText().toString());
                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        Map<String, Object> info = document.getData();
                                        if(info.containsValue(password.getText().toString())){
                                            Toast.makeText(customer_user.this,"SINGIN SUCCESSFUL.\nHELLO COSTUMER!",Toast.LENGTH_SHORT).show();
                                            Auto_login(email,password);
                                            openCostumers();
                                        }
                                        else {
                                            Toast.makeText(customer_user.this,"PASSWORD IS UNCORRECTED! \nTRY AGAIN PLEASE.",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    else {
                                        Toast.makeText(customer_user.this,"The email: "+email.getText().toString()+" is NOT registered in the system",Toast.LENGTH_SHORT).show();
                                        Log.d(TAG, "No such document");
                                    }
                                }
                                else {
                                    Log.d(TAG, "get failed with ", task.getException());
                                }
                            }
                        });
                    }

                }

//                if(email.getText().toString().equals("") && password.getText().toString().equals("")){
//                    //correct
//                    Toast.makeText(customer_user.this,"LOGIN SUCCESSFUL.\nHELLO COSTUMER!",Toast.LENGTH_SHORT).show();
//                    signInAnonymously();
//                    openCostumers();
//                }else
//                    //incorrect
//                    Toast.makeText(customer_user.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();



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


    private void signInAnonymously() {
        auth.signInAnonymously().addOnSuccessListener(this, new  OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        // do your stuff
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.e(TAG, "signInAnonymously:FAILURE", exception);
                    }
                });
    }



        public void Auto_login (TextView email,TextView password) {
            if (auth.getCurrentUser() == null) {
                FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(customer_user.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmailAndPassword:success");
                                    auth.getCurrentUser().sendEmailVerification();
                                } else {
                                    signInAnonymously();
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmailAndPassword:failure", task.getException());
                                }
                            }
                        });
            }
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