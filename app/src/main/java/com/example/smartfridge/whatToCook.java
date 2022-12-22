package com.example.smartfridge;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class whatToCook extends AppCompatActivity {
    Button b1, b2;
    EditText recipeName, pTime, Ingredients, pOrder;
    FirebaseFirestore firestore;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_to_cook);

        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button);
        recipeName = (EditText) findViewById(R.id.editText);
        pTime = (EditText) findViewById(R.id.editText2);
        pOrder = (EditText) findViewById(R.id.editText4);

        firestore = FirebaseFirestore.getInstance();
        CollectionReference Recipes_Db = firestore.collection("recipes");


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> info_c = new HashMap<>();
                info_c.put("name", recipeName.getText().toString());
                info_c.put("Preparing Time", pTime.getText().toString());
                info_c.put("Preparing Order", pOrder.getText().toString());
//                                    authentication.createUserWithEmailAndPassword(String.valueOf(email), String.valueOf(password));
//                                    firestore.collection("costumer_accounts").document(String.valueOf(email)).set(info_c);
                Recipes_Db.document(recipeName.getText().toString()).set(info_c);
                Toast.makeText(whatToCook.this, "recipe in", Toast.LENGTH_LONG).show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
