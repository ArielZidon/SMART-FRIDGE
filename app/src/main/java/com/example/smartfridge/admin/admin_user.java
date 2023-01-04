package com.example.smartfridge.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.R;
import com.google.android.material.button.MaterialButton;

public class admin_user extends AppCompatActivity {

    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_user);

        firestore = FirebaseFirestore.getInstance();//Initialization of the object firestore

        TextView username =(TextView) findViewById(R.id.m_username);
        TextView password =(TextView) findViewById(R.id.m_password);


//        CollectionReference manager_DB = firestore.collection("manager_accounts");


        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.m_loginbtn);

        //admin and admin
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                DocumentReference docRef = firestore.collection("manager_accounts").document(username.getText().toString());
//                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                        if (task.isSuccessful()) {
//                            DocumentSnapshot document = task.getResult();
//                            if (document.exists()) {
//                                Map<String, Object> info = document.getData();
//                                if(info.containsValue(password.getText().toString())){
//                                    Toast.makeText(admin_user.this,"SINGIN SUCCESSFUL.\nHELLO MANAGER!",Toast.LENGTH_SHORT).show();
//                                    openManagers();
//                                }
//                                else {
//                                    Toast.makeText(admin_user.this,"PASSWORD IS UNCORRECTED! \nTRY AGAIN PLEASE.",Toast.LENGTH_LONG).show();
//                                }
//                            }
//                            else {
//                                Toast.makeText(admin_user.this,"The email: "+ username.getText().toString() +" is NOT registered in the system",Toast.LENGTH_SHORT).show();
//                                Log.d(TAG, "No such document");
//                            }
//                        }
//                        else {
//                            Log.d(TAG, "get failed with ", task.getException());
//                        }
//                    }
//                });

                        if (username.getText().toString().equals("") && password.getText().toString().equals("")) {
                            //correct
                            Toast.makeText(admin_user.this, "LOGIN SUCCESSFUL.\nHELLO MANAGER!", Toast.LENGTH_SHORT).show();
                            openManagers();
                        } else
                            //incorrect
                            Toast.makeText(admin_user.this, "LOGIN FAILED !!!", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            public void openManagers() {
                Intent intent = new Intent(this, adminView.class);
                startActivity(intent);
            }
        }
