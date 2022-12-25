package com.example.smartfridge;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartfridge.costumer.customer_USER;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class forgot_password extends AppCompatActivity {

    private FirebaseFirestore firestore;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        firestore = FirebaseFirestore.getInstance();//Initialization of the object firestore
        //CollectionReference object - open a database collection (if exist - open, else - create & open )
        CollectionReference costumers_DB = firestore.collection("costumer_accounts");
        //CollectionReference object - open a database collection (if exist - open, else - create & open )
        CollectionReference managers_DB = firestore.collection("manager_accounts");
        auth = FirebaseAuth.getInstance();//Initialization of the object

        TextView email = (TextView) findViewById(R.id.email);
        MaterialButton send = (MaterialButton) findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //***need to send a code to user and verify the code***
                //***random 6 number between 0-9 send to an email or a sms.
                if (auth.getCurrentUser().isEmailVerified()) {
                    //open wind new password
//                    auth.getCurrentUser().updatePassword(password.getText().toString());
                    DocumentReference docRef = firestore.collection("costumer_accounts").document(email.getText().toString());
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    Map<String, Object> info = document.getData();
//                                    info.entrySet("password",password.getText().toString())
                                    costumers_DB.document(email.getText().toString()).set(info);
                                }
                                else {
                                    Toast.makeText(forgot_password.this, "The email: " + email.getText().toString() + " is NOT registered in the system", Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "No such document");
                                }
                            }
                        }
                    });
                }

            }
        });
    }
}

