package com.example.smartfridge.local_customer_memory;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.Model.vegModel;
import com.example.smartfridge.R;
import com.example.smartfridge.business_entities.ModelClass;
import com.example.smartfridge.ui.main.MainMenu;

import java.util.ArrayList;

public class vegCategoryActivity extends AppCompatActivity  {
    vegModel model= new vegModel(this);
    AlertDialog dialog;
    LinearLayout layout;
    EditText name;
    EditText number;
    Button btSave;
    TextView tvSize;
    ArrayList<ModelClass> arrayList;
    ImageButton nextView;
    ImageButton beckView;
    ImageButton home;



    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_vegetables);

        btSave = findViewById(R.id.bt_sava);
        tvSize = findViewById(R.id.tv_size);
        layout = findViewById(R.id.container);
        name = findViewById(R.id.nameEdit);
        number = findViewById(R.id.numberEdit);
        model.loadData();
        buildDialog();

        /* Button to go next dry food page */
        nextView = (ImageButton) findViewById(R.id.next_to_clean);
        nextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextPage();
            }
        });

        /* Button to go beck to dry food page */
        beckView = (ImageButton) findViewById(R.id.bt_beck);
        beckView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                beckPage();
            }
        });

        /* Button to add new item */
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        /* Button to return home costumer */
        home = (ImageButton) findViewById(R.id.bt_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homePage();
            }
        });
    }

    public void homePage() {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    public void beckPage() {
        Intent intent = new Intent(this, milkCategoryActivity.class);
        startActivity(intent);
    }

    public void nextPage() {
        Intent intent = new Intent(this, cleaningCategoryActivity.class);
        startActivity(intent);
    }

    /**
     * create a view dialog between the customers on use to adds items
     * add new card with the name and number from the dialog
     * update the list with the new item
     */
    public void buildDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog, null);

        EditText name = view.findViewById(R.id.nameEdit);
        EditText number = view.findViewById(R.id.numberEdit);

        builder.setView(view);
        builder.setTitle("Enter Item")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        model.saveData(name.getText().toString(), number.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        dialog = builder.create();
    }

    public boolean onCreteOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * @param name => item name
     * @param number => number of item
     * update the view screen with new card (name, cumber)
     */
    public void addCard(String name, String number) {
        View view = getLayoutInflater().inflate(R.layout.vege_card, null);

        TextView nameView = view.findViewById(R.id.vege_name);
        TextView countView = view.findViewById(R.id.vege_number);
        Button delete = view.findViewById(R.id.vege_delete);

        nameView.setText(name);
        countView.setText(number);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.removeArray(nameView.getText().toString(), countView.getText().toString());
                layout.removeView(view);
            }
        });
        layout.addView(view);
    }

   @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ShoppingTables.class);
        startActivity(intent);
    }
}