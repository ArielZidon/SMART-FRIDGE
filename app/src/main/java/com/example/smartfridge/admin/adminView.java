package com.example.smartfridge.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.R;
import com.example.smartfridge.business_logic.getOut;
import com.example.smartfridge.ui.main.MainActivity;

public class adminView extends AppCompatActivity {
    private Button button;
    ImageButton users;        //ImageButton that represent the Button "insertRecipe"
    ImageButton Recipes;     //ImageButton that represent the Button "myShoppingList"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);
        users = (ImageButton) findViewById(R.id.bt_EditUsers);
        Recipes = (ImageButton) findViewById(R.id.EnterRecipes);

        users.setOnClickListener(v -> openAdminUsers());
        Recipes.setOnClickListener(v -> openAdminRecipes());
    }

    public void openAdminUsers() {
        Intent intent = new Intent(this, usersManage.class);
        startActivity(intent);
    }
    public void openAdminRecipes() {
        Intent intent = new Intent(this, recipesManage.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        getOut sure_you_want = new getOut();
        sure_you_want.show(getSupportFragmentManager(), "example dialog");
        if(getOut.getTemp() == 1)
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}