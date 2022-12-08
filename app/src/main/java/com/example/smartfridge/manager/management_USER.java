package com.example.smartfridge.manager;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

public class management_USER extends AppCompatActivity {

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

                DocumentReference docRef = firestore.collection("manager_accounts").document(username.getText().toString());
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Map<String, Object> info = document.getData();
                                if(info.containsValue(password.getText().toString())){
                                    Toast.makeText(management_USER.this,"SINGIN SUCCESSFUL.\nHELLO MANAGER!",Toast.LENGTH_SHORT).show();
                                    openManagers();
                                }
                                else {
                                    Toast.makeText(management_USER.this,"PASSWORD IS UNCORRECTED! \nTRY AGAIN PLEASE.",Toast.LENGTH_LONG).show();
                                }
                            }
                            else {
                                Toast.makeText(management_USER.this,"The email: "+ username.getText().toString() +" is NOT registered in the system",Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "No such document");
                            }
                        }
                        else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });

//                if(username.getText().toString().equals("") && password.getText().toString().equals("")){
//                    //correct
//                    Toast.makeText(management_USER.this,"LOGIN SUCCESSFUL.\nHELLO MANAGER!",Toast.LENGTH_SHORT).show();
//                    openManagers();
//                }else
//                    //incorrect
//                    Toast.makeText(management_USER.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void  openManagers(){
        Intent intent = new Intent(this, manager.class);
        startActivity(intent);
    }
}
