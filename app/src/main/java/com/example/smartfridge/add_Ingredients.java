package com.example.smartfridge;

import static android.content.ContentValues.TAG;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.annotation.SuppressLint;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.lang.reflect.Type;
import java.util.ArrayList;

import yuku.ambilwarna.AmbilWarnaDialog;

public class add_Ingredients extends AppCompatActivity {
        AlertDialog dialog;
        EditText name;
        EditText number;
        Button btSave;
        TextView nameView;
        ImageButton home;
        AlertDialog rename;
        LinearLayout layout;
        ImageButton color;
        ImageButton editName;
        CardView myCard;
        int defaultColor;
        ArrayList<String> recipeKey = new ArrayList<>();
        ArrayList<ModelClass> ingArray =new ArrayList<>();

        RelativeLayout test;

        @SuppressLint("MissingInflatedId")
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_my_category);

            btSave = findViewById(R.id.bt_sava);
            nameView = findViewById(R.id.name_view);
            layout = findViewById(R.id.container);
            name = findViewById(R.id.nameEdit);
            number = findViewById(R.id.numberEdit);

            color = findViewById(R.id.color);
            editName = findViewById(R.id.edit);
            myCard = findViewById(R.id.edit_color_card);
            IngredientsLists.setInArrayList(ingArray);
            IngredientsLists.setNamesArrayList(recipeKey);

            loadData();
            buildDialog();
            buildRename();

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

                    recipeKey = new ArrayList<String>();
                    for (ModelClass i : ingArray) {
                        recipeKey.add(i.itemName);
                    }
                    IngredientsLists.setInArrayList(ingArray);
                    IngredientsLists.setNamesArrayList(recipeKey);
                    Log.d(TAG, IngredientsLists.getIngArray().toString());
                    Log.d(TAG, IngredientsLists.getNamesArrayList().toString());
                    openWhatToCook();
                }
            });
        }

        private void openColoePicker() {
            AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, defaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                @Override
                public void onCancel(AmbilWarnaDialog dialog) {
                }

                @Override
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    defaultColor = color;
                    myCard.setCardBackgroundColor(defaultColor);
                }
            });
            ambilWarnaDialog.show();
        }

        private void homePage() {
            Intent intent = new Intent(this, com.example.smartfridge.costumer.costumers.class);
            startActivity(intent);
        }


        /**
         * Upload items form sharedPreferences
         * if list == null => create new empty list
         * else => show on the screen all items from the sharedPreferences "Item_Data_meat"
         */
        private void loadData() {
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA", MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences.getString("Ingredients", null);
            Type type = new TypeToken<ArrayList<ModelClass>>() {
            }.getType();
            ingArray = gson.fromJson(json, type);
            if (ingArray == null) {
                ingArray = new ArrayList<ModelClass>();
//            tvSize.setText(""+0);
            } else {
                for (int i = 0; i < ingArray.size(); i++) {
                    addCard(ingArray.get(i).itemName, ingArray.get(i).itemNumber);
                }
            }
        }

        /**
         * @param name  => item name
         * @param count => count of items (it String because we want to be able to show different options)
         *              save on sharedPreferences item (name, count)
         *              and upload the view with the new item
         */
        private void saveData(String name, String count) {
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            ingArray.add((new ModelClass(name, count)));
            String json = gson.toJson(ingArray);
            editor.putString("Ingredients", json);
            editor.apply();
            addCard(name, count);
            Log.d(TAG, IngredientsLists.getIngArray().toString());
        }

        /**
         * create a view dialog between the customers on user to adds items
         * add new card with the name and number from the dialog
         * update the list with the new item
         */
        private void buildDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = getLayoutInflater().inflate(R.layout.dialog, null);

            EditText name = view.findViewById(R.id.nameEdit);
            EditText number = view.findViewById(R.id.numberEdit);

            builder.setView(view);
            builder.setTitle("Enter Item")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            saveData(name.getText().toString(), number.getText().toString());
                        }
                    })
                    .setNegativeButton("Cencel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            dialog = builder.create();
        }

        private void buildRename() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = getLayoutInflater().inflate(R.layout.rename, null);

            EditText name = view.findViewById(R.id.nameEdit);

            builder.setView(view);
            builder.setTitle("Enter Item")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            nameView.setText(name.getText());
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            rename = builder.create();
        }

        public boolean onCreteOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.main_menu, menu);
            return true;
        }

        /**
         * @param name   => item name
         * @param number => number of item
         *               update the view screen with new card (name, number)
         */
        private void addCard(String name, String number) {
            View view = getLayoutInflater().inflate(R.layout.selected_card, null);

            TextView nameView = view.findViewById(R.id.name);
            TextView countView = view.findViewById(R.id.number);
            Button delete = view.findViewById(R.id.delete);

            nameView.setText(name);
            countView.setText(number);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeArray(nameView.getText().toString(), countView.getText().toString());
                    layout.removeView(view);
                }
            });
            layout.addView(view);
        }

        /**
         * @param name  => item name that customer select to delete
         * @param count => count item that customer select to delete
         *              1. remove item from the screen
         *              2. remove item from sharedPreferences
         */
        private void removeArray(String name, String count) {
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            for (int i = 0; i < ingArray.size(); i++) {
                if (ingArray.get(i).itemName.equals(name) &&
                       ingArray.get(i).itemNumber.equals(count)) {
                    ingArray.remove(i);
                }
            }
            String json = gson.toJson(ingArray);
            editor.putString("Ingredients", json);
            editor.apply();
        }

        public void openWhatToCook() {
//        Intent intent = new Intent(this, whatToCook.class);
            Log.d(TAG, IngredientsLists.getIngArray().toString());
            Intent result = new Intent();
            setResult(RESULT_OK, result);
            finish();
        }
    }
