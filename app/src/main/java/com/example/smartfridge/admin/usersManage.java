package com.example.smartfridge.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.smartfridge.R;

import java.util.ArrayList;

public class usersManage extends AppCompatActivity {
    ArrayList<user> users = new ArrayList<>();
    ArrayList<user> usersAreDeleted = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_mange);
    }
    private void editUsers(){}

    private void deleteUsers(){}
}