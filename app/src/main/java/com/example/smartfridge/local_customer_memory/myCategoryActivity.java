package com.example.smartfridge.local_customer_memory;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartfridge.Model.myCategoryModel;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.business_entities.ModelClass;
import com.example.smartfridge.R;
import com.example.smartfridge.ui.main.MainMenu;

import java.util.ArrayList;

public class myCategoryActivity extends AppCompatActivity{
    myCategoryModel model = new myCategoryModel(this);
    AlertDialog dialog;
    EditText name;
    EditText number;
    Button btSave;
    public TextView nameView;
    ArrayList<ModelClass> arrayList;
    ImageButton home;

    AlertDialog rename;
    LinearLayout layout;
    ImageButton editName;


    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_category);


        btSave = findViewById(R.id.bt_sava);
        nameView = findViewById(R.id.name_view);
        layout = findViewById(R.id.container);
        name = findViewById(R.id.nameEdit);
        number = findViewById(R.id.numberEdit);

        editName = findViewById(R.id.edit);

        model.loadData();
        buildDialog();
        buildRename();
        model.loadName();

        editName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rename.show();
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


    /**
     * create a view dialog between the customers on user to adds items
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

    public void buildRename() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.rename, null);

        EditText name = view.findViewById(R.id.nameEdit);

        builder.setView(view);
        builder.setTitle("Enter Item")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        model.saveName(name.getText().toString());
                        nameView.setText(name.getText());
                    }
                })
                .setNegativeButton("Cencel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        rename = builder.create();
    }


    /**
     * @param name   => item name
     * @param number => number of item
     *               update the view screen with new card (name, number)
     */
    public void addCard(String name, String number) {
        View view = getLayoutInflater().inflate(R.layout.selected_card, null);

        TextView nameView = view.findViewById(R.id.name);
        TextView countView = view.findViewById(R.id.number);
        Button delete = view.findViewById(R.id.delete);

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
        Intent intent = new Intent(this, com.example.smartfridge.local_customer_memory.ShoppingTables.class);
        startActivity(intent);
    }
}