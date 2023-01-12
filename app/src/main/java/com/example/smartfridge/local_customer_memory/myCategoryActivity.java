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
    ImageButton color;
    ImageButton editName;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    private String text;


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


//    /**
//     * Upload items form sharedPreferences
//     * if list == null => create new empty list
//     * else => show on the screen all items from the sharedPreferences "Item_Data_meat"
//     */
//    public void loadData() {
//        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA", MODE_PRIVATE);
//        Gson gson = new Gson();
//        String json = sharedPreferences.getString("Item_Data_my_category", null);
//        Type type = new TypeToken<ArrayList<ModelClass>>() {
//        }.getType();
//        arrayList = gson.fromJson(json, type);
//        if (arrayList == null) {
//            arrayList = new ArrayList<>();
////            tvSize.setText(""+0);
//        } else {
//            for (int i = 0; i < arrayList.size(); i++) {
//                addCard(arrayList.get(i).itemName, arrayList.get(i).itemNumber);
//            }
//        }
//    }
//
//    /**
//     * @param name  => item name
//     * @param count => count of items (it String because we want to be able to show different options)
//     *              save on sharedPreferences item (name, count)
//     *              and upload the view with the new item
//     */
//    public void saveData(String name, String count) {
//        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        Gson gson = new Gson();
//        arrayList.add((new ModelClass(name, count)));
//        String json = gson.toJson(arrayList);
//        editor.putString("Item_Data_my_category", json);
//        editor.apply();
//        addCard(name, count);
//    }
//
//
//

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

//    /**
//     * @param str -> the name we what to view
//     *  save str in sharedPreferences
//     */
//    public void saveName(String str){
//        SharedPreferences sharedPreferences = getSharedPreferences("Name", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putString("text", str);
//        editor.apply();
//
//        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
//    }
//
//    /**
//     * load the name from sharedPreferences
//     */
//    public void loadName() {
//        SharedPreferences sharedPreferences = getSharedPreferences("Name", MODE_PRIVATE);
//        text = sharedPreferences.getString("text", "");
//        nameView.setText(text);
//
//    }

//    public boolean onCreteOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return true;
//    }

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

//    /**
//     * @param name  => item name that customer select to delete
//     * @param count => count item that customer select to delete
//     *              1. remove item from the screen
//     *              2. remove item from sharedPreferences
//     */
//    public void removeArray(String name, String count) {
//        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        Gson gson = new Gson();
//        for (int i = 0; i < arrayList.size(); i++) {
//            if (arrayList.get(i).getItemName().equals(name) &&
//                    arrayList.get(i).itemNumber.equals(count)) {
//                arrayList.remove(i);
//            }
//        }
//        String json = gson.toJson(arrayList);
//        editor.putString("Item_Data_my_category", json);
//        editor.apply();
//    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, com.example.smartfridge.local_customer_memory.ShoppingTables.class);
        startActivity(intent);
    }
}