package com.example.smartfridge.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.R;
import com.example.smartfridge.business_logic.getOut;
import com.example.smartfridge.ui.main.MainActivity;

public class adminView extends AppCompatActivity {
    private Button button;
    Button Users;        //ImageButton that represent the Button "insertRecipe"
    Button Recipes;     //ImageButton that represent the Button "myShoppingList"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);
        Users = (Button) findViewById(R.id.bt_EditUsers);
        Recipes = (Button) findViewById(R.id.EnterRecipes);

        Users.setOnClickListener(v -> openAdminUsers());
        Recipes.setOnClickListener(v -> openAdminRecipes());
    }

    /**let admin edit and delete the users*/
    public void openAdminUsers() {
        Intent intent = new Intent(this, usersManageActivity.class);
        startActivity(intent);
    }
    /**let admin edit and delete the recipes*/
    public void openAdminRecipes() {
        Intent intent = new Intent(this, recipesManageActivity.class);
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