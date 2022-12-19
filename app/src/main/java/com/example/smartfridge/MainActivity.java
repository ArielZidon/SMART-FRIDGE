package com.example.smartfridge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.smartfridge.databinding.ActivityMainBinding;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import com.example.smartfridge.costumer.customer_USER;
import com.example.smartfridge.manager.management_USER;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firestore = FirebaseFirestore.getInstance();//Initialization of the object firestore

//        CollectionReference account = firestore.collection("account");
//        CollectionReference costumers_DB = firestore.collection("costumer_accounts");
//        CollectionReference managers_DB = firestore.collection("manager_accounts");



        //tast - insert eran as a costumer and as a manager
        //DONT FORGET TO DELETE THIS!!!!!!
//        Map<String, Object> info_c = new HashMap<>();
//        info_c.put("name","erantzarum");
//        info_c.put("user_type","user");
//        info_c.put("password","password");
//        costumers_DB.document("tzarum77@gmail.com").set(info_c);
//
//        Map<String, Object> info_m = new HashMap<>();
//        info_m.put("name","erantzarum");
//        info_m.put("user_type","user");
//        info_m.put("password","password");
//        managers_DB.document("eran.davidtz@gmail.com").set(info_m);
        //DONT FORGET TO DELETE THIS!!!!!!

//        //tast - insert eran as a costumer and as a manager
//        //DONT FORGET TO DELETE THIS!!!!!!
//        Map<String, Object> accounts2 = new HashMap<>();
//
//        firestore.collection("accounts2").add(accounts2).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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

//        Map<String, Object> INFO_t = new HashMap<>();
//        INFO_t.put("name","Ariel");
//        INFO_t.put("user_type","user");
//        INFO_t.put("password","password");
////        accounts2.put("tzarum77@email.com",INFO_t);
//
//        account.document("Ariel@email.com").set(INFO_t);
        //DONT FORGET TO DELETE THIS!!!!!!

//        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("USERS");

        TextView tital = (TextView) findViewById(R.id.title);

        MaterialButton signupbtn = (MaterialButton) findViewById(R.id.signupbtn);
        MaterialButton signinbtn = (MaterialButton) findViewById(R.id.signinbtn);
        MaterialButton mamagebtn = (MaterialButton) findViewById(R.id.mamagebtn);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to - create_account wind..
                openCreateAccount();
            }
        });

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to - customer_user login wind.
                openLogin();
            }
        });

        mamagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to - management_user login wind.
                openManagement();
            }
        });

    }

    public void  openCreateAccount(){
        Intent intent = new Intent(this, createAccount.class);
        startActivity(intent);
    }

    public void openLogin() {
        Intent intent = new Intent(this, customer_USER.class);
        startActivity(intent);
    }

    public void openManagement() {
        Intent intent = new Intent(this, management_USER.class);
        startActivity(intent);
    }
/*
    public void openRecipes() {
        Intent intent = new Intent(this, recipes.class);
        startActivity(intent);
    }
 */
}