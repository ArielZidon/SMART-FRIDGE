package com.example.smartfridge.local_customer_memory;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.Model.fullViewModel;
import com.example.smartfridge.R;
import com.example.smartfridge.ui.main.MainMenu;

public class ShoppingTables extends AppCompatActivity {

    private ImageButton bt_meat;
    private ImageButton bt_milky;
    private ImageButton bt_clean;
    private ImageButton bt_dry;
    private ImageButton bt_vegetables;
    private ImageButton bt_fullView;
    private Button bt_category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_tables);

        bt_meat = (ImageButton) findViewById(R.id.bt_meat);
        bt_milky = (ImageButton) findViewById(R.id.bt_milky);
        bt_clean = (ImageButton) findViewById(R.id.bt_clean);
        bt_dry = (ImageButton) findViewById(R.id.bt_dty);
        bt_vegetables = (ImageButton) findViewById(R.id.bt_vegetables);
        bt_fullView = (ImageButton) findViewById(R.id.bt_full_view);
        bt_category = (Button) findViewById(R.id.my_category);

        bt_meat.setOnClickListener(v -> openPage_meat());
        bt_milky.setOnClickListener(v -> openPage_milky());
        bt_clean.setOnClickListener(v -> openPage_clean());
        bt_dry.setOnClickListener(v -> openPage_dry());
        bt_vegetables.setOnClickListener(v -> openPage_bt_vegetables());
        bt_fullView.setOnClickListener(v -> openPage_full());
        bt_category.setOnClickListener(v -> openPage_bt_my_category());


    }

    private void openPage_full() {
        Intent intent = new Intent(this, full_view.class);
        startActivity(intent);
    }
    public void openPage_meat() {
        Intent intent = new Intent(this, meatCategoryActivity.class);
        startActivity(intent);
    }
    public void openPage_milky() {
        Intent intent = new Intent(this, milkCategoryActivity.class);
        startActivity(intent);
    }
    public void openPage_clean() {
        Intent intent = new Intent(this, cleaningCategoryActivity.class);
        startActivity(intent);
    }
    public void openPage_dry() {
        Intent intent = new Intent(this, dryFoodCategoryActivity.class);
        startActivity(intent);
    }
    public void openPage_bt_vegetables() {
        Intent intent = new Intent(this, vegCategoryActivity.class);
        startActivity(intent);
    }
    public void openPage_bt_my_category() {
        Intent intent = new Intent(this, myCategoryActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}