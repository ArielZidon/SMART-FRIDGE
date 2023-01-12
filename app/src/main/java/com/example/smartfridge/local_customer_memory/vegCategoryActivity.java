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

//    /**
//     * Upload items form sharedPreferences
//     * if list == null => create new empty list
//     * else => show on the screen all items from the sharedPreferences "Item_Data_Clean"
//     */
//    public void loadData() {
//        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
//        Gson gson = new Gson();
//        String json = sharedPreferences.getString("Item_Data_Clean", null);
//        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();
//        arrayList = gson.fromJson(json, type);
//        if(arrayList == null){
//            arrayList = new ArrayList<>();
////            tvSize.setText(""+0);
//        }else {
//            for (int i = 0; i < arrayList.size(); i++){
//                addCard(arrayList.get(i).itemName, arrayList.get(i).itemNumber);
//            }
//        }
//    }
//
//    /**
//     * @param name => item name
//     * @param count => count of items (it String because we want to be able to show different options)
//     *  save on sharedPreferences item (name, count)
//     *  and upload the view with the new item
//     */
//    public void saveData(String name, String count) {
//        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        Gson gson = new Gson();
//        arrayList.add((new ModelClass(name, count)));
//        String json = gson.toJson(arrayList);
//        editor.putString("Item_Data_Clean", json);
//        editor.apply();
//        addCard(name, count);
//    }

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
        View view = getLayoutInflater().inflate(R.layout.clean_card, null);

        TextView nameView = view.findViewById(R.id.clean_name);
        TextView countView = view.findViewById(R.id.clean_number);
        Button delete = view.findViewById(R.id.clean_delete);

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


    //    /**
//     * @param name => item name that customer select to delete
//     * @param count => count item that customer select to delete
//     *  1. remove item from the screen
//     *  2. remove item from sharedPreferences
//     */
//    public void removeArray(String name, String count) {
//        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        Gson gson = new Gson();
//        for (int i = 0; i < arrayList.size();i++) {
//            if (arrayList.get(i).getItemName().equals(name) &&
//                    arrayList.get(i).itemNumber.equals(count)) {
//                arrayList.remove(i);
//            }
//        }
//        String json = gson.toJson(arrayList);
//        editor.putString("Item_Data_Clean", json);
//        editor.apply();
//    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ShoppingTables.class);
        startActivity(intent);
    }
}