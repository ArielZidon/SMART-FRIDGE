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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import com.example.smartfridge.costumer.costumers;
import com.example.smartfridge.manager.manager;

public class createAccount extends AppCompatActivity {

    private FirebaseFirestore firestore;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        firestore = FirebaseFirestore.getInstance();//Initialization of the object firestore
        //CollectionReference object - open a database collection (if exist - open, else - create & open )
        CollectionReference costumers_DB = firestore.collection("costumer_accounts");
        //CollectionReference object - open a database collection (if exist - open, else - create & open )
        CollectionReference managers_DB = firestore.collection("manager_accounts");
        auth = FirebaseAuth.getInstance();//Initialization of the object


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
                //base terms to create a new account.
                if (email.getText().toString().equals("") || password.getText().toString().length() < 5
                || user.getText().toString().equals("") || name.getText().toString().equals("")) {
                    Toast.makeText(createAccount.this, "filled", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(user.getText().toString().equals("costumer")){
                        String collection = "costumer_accounts";
                        //this commend open a Document from our firestore cloud collection by a string collection path and document path.
                        //if the Document does not exist it will create the document as the path name.
                        DocumentReference docRef = firestore.collection(collection).document(email.getText().toString());
                        //after we open or create the doc we will they to get and set the info.
                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    //if get successful we will use a DocumentSnapshot to capture the data and read\set\..
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        //if document exists we wont create it again to prevent duplicate.
                                        Toast.makeText(createAccount.this, "The email: " + email.getText().toString() + " is registered in the system", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        //supposed to prevent authentication twice who at the second I delete the email from the authentication.
                                        //not ideal but working.
                                        if (!managers_DB.document(email.getText().toString()).get().isSuccessful()){
                                            //authentication.
                                            Auto_user(name,email,password,user);
                                        }
                                        //if document does not exists - open a map & insert user data to the map.
                                        Map<String, Object> info = new HashMap<>();
                                        info.put("name", name.getText().toString());
                                        info.put("user_type", user.getText().toString());
                                        info.put("password", password.getText().toString());
                                        info.put("Uid",auth.getCurrentUser().getUid());
                                        //insert map into database by a document path.
                                        costumers_DB.document(email.getText().toString()).set(info);
                                        Toast.makeText(createAccount.this,"SINGUP SUCCESSFUL.\nHELLO COSTUMER!",Toast.LENGTH_LONG).show();
                                        openCostumers();
                                    }
                                }
                                else {
                                    //if failed to complete task.
                                    Log.d(TAG, "get failed with ", task.getException());
                                }
                            }
                        });
                    }
                    //if user is a manager  + base terms to create a new manager account.
                    if(user.getText().toString().equals("manager") && password.getText().toString().contains("SFmanager")){
                        String collection = "manager_accounts";
                        DocumentReference docRef = firestore.collection(collection).document(email.getText().toString());
                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        Toast.makeText(createAccount.this, "The email: " + email.getText().toString() + " is registered in the system", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        if (!costumers_DB.document(email.getText().toString()).get().isSuccessful()){
                                            Auto_user(name,email,password,user);
                                        }
                                        Map<String, Object> info = new HashMap<>();
                                        info.put("name", name.getText().toString());
                                        info.put("user_type", user.getText().toString());
                                        info.put("password", password.getText().toString());
                                        info.put("Uid",auth.getCurrentUser().getUid());
                                        managers_DB.document(email.getText().toString()).set(info);
                                        Toast.makeText(createAccount.this,"SINGUP SUCCESSFUL.\nHELLO MANAGER!",Toast.LENGTH_LONG).show();
                                        openManagers();
                                    }
                                }
                                else {
                                    Log.d(TAG, "get failed with ", task.getException());
                                }
                            }
                        });
                    }
//                    else Toast.makeText(createAccount.this,"ERROR SINGING UP",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void Auto_user(TextView name,TextView email,TextView password,TextView user){
        //create the user in the authentication on the firestore.
        auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(createAccount.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    auth.getCurrentUser().sendEmailVerification();
                }
                else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                    Toast.makeText(createAccount.this, "Authentication failed.",
//                            Toast.LENGTH_SHORT).show();
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
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, com.example.smartfridge.MainActivity.class);
        startActivity(intent);
    }
}

