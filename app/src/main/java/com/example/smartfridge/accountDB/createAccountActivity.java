package com.example.smartfridge.accountDB;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.example.smartfridge.Model.createAccountModel;
import com.example.smartfridge.R;
import com.example.smartfridge.admin.adminView;
import com.example.smartfridge.ui.main.MainActivity;
import com.example.smartfridge.ui.main.MainMenu;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;


public class createAccountActivity extends AppCompatActivity {


//    private List<String> teamList = new ArrayList<>();
    createAccountModel model = new createAccountModel(this);
    TextView createAccount;
    TextView name;
    TextView email;
    TextView password;
    TextView userType;
    MaterialButton createBtn;
    private List<String> teamList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        createAccount = findViewById(R.id.createaccount);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        userType = findViewById(R.id.user);
        createBtn = (MaterialButton) findViewById(R.id.createbtn);


        teamList.add("Customer");
        teamList.add("Manager");
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, teamList);
        AppCompatSpinner spinnerTeam = (AppCompatSpinner) findViewById(R.id.spinner_team);
        spinnerTeam.setAdapter(arrayAdapter);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = name.getText().toString();
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                String userType1 = spinnerTeam.getSelectedItem().toString();
                Log.d(TAG,name1);
                model.setNewAccount(userType1,name1,email1,password1);
            }
        });
    }
        public void openCostumers() {
            Intent intent = new Intent(this, MainMenu.class);
            startActivity(intent);
        }

        public void openManagers () {
            Intent intent = new Intent(this, adminView.class);
            startActivity(intent);
        }

        public void onBackPressed () {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }



