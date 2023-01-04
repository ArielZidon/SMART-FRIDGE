package com.example.smartfridge.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.smartfridge.R;

import java.util.ArrayList;

public class recipesManage extends AppCompatActivity {
    ArrayList<admin_recipe> recipes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_manage);
    }
    private void editRecipes(){}

    private void deleteRecipes(){}

    private void loadRecipes(){}
}