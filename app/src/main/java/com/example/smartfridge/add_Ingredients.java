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

import com.example.smartfridge.costumer.costumers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.annotation.SuppressLint;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.lang.reflect.Type;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import yuku.ambilwarna.AmbilWarnaDialog;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import static android.content.ContentValues.TAG;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.example.smartfridge.ModelClass;
import com.example.smartfridge.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import yuku.ambilwarna.AmbilWarnaDialog;

public class add_Ingredients extends AppCompatActivity {
    Button b1, b2;
    EditText inName, amount;
    FirebaseFirestore firestore;
    static int counter = 0;

//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_ingredient);
//
//        b1 = (Button) findViewById(R.id.button);
//        b2 = (Button) findViewById(R.id.button2);
//        inName = (EditText) findViewById(R.id.editText);
//        amount = (EditText) findViewById(R.id.editText2);
//        if(counter == 0) {
//
//            this.ingArray = ingredient.newInArray();
//            this.recipeKey = ingredient.newNamesArray();
//        }else{
//            this.ingArray = ingredient.getIngArray();
//            this.recipeKey = ingredient.getNamesArrayList();
//        }
//
//        b1.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Ingredient ingredient = new Ingredient(inName.getText().toString(), amount.getText().toString());
//                ingArray.add(ingredient);
//                counter++;
//                Log.d(TAG,ingArray.toString());
//                openAddIngredients();
//            }
//        });
//
//
//        firestore = FirebaseFirestore.getInstance();
//        CollectionReference Recipes_Db = firestore.collection("recipes");
//        b2.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Ingredient ingredient = new Ingredient(inName.getText().toString(), amount.getText().toString());
//                ingArray.add(ingredient);
//                String collection = "recipes";
//                recipeKey = ingredient.getNamesArrayList();
//                for (Ingredient i : ingArray) {
//                    recipeKey.add(i.InName);
//                }
//                Log.d(TAG,recipeKey.toString());
//                DocumentReference docRef = firestore.collection(collection).document(recipeKey.toString());
//                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                        if (task.isSuccessful()) {
//                            Log.d(TAG, ingArray.toString());
//                            Log.d(TAG, "success!!");
//
//                            openWhatToCook();
//
//                        } else {
//                            Log.d(TAG, "get failed with ", task.getException());
//
//                        }
//                    }
//                });
//            }
//        });
//
//    }
//    public void openAddIngredients(){
//        Intent intent = new Intent(this, add_Ingredients.class);
//        startActivity(intent);
//    }
//    public void openWhatToCook() {
////        Intent intent = new Intent(this, whatToCook.class);
//        Intent result = new Intent();
//        setResult(RESULT_OK,result);
//        finish();
//    }
//
//    /**take us back to the customers menu*/
//    @Override
//    public void onBackPressed() {
//        Log.d(TAG, ingArray.toString()); //for test only
//        Intent intent = new Intent(this, whatToCook.class);
//        startActivity(intent);
//    }
//}




        AlertDialog dialog;
        EditText name;
        EditText number;
        Button btSave;
        TextView nameView;
        ArrayList<ModelClass> arrayList;
        ImageButton home;

        AlertDialog rename;
        LinearLayout layout;
        ImageButton color;
        ImageButton editName;
        CardView myCard;
        int defaultColor;
        Ingredient ingredient = new Ingredient();
        ArrayList<String> recipeKey;
        ArrayList<Ingredient> ingArray = ingredient.newInArray();
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
//        defaultColor = ContextCompat.getColor(my_category.this, R.color.black);
//        myCard.setCardBackgroundColor(defaultColor);
//        myCard.setBackgroundColor(defaultColor);
//        test.setBackgroundColor(defaultColor);

            loadData();
            buildDialog();
            buildRename();

            color.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openColoePicker();
                }
            });

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
                    Log.d(TAG,ingArray.toString());
                    recipeKey = ingredient.newNamesArray();
                    for (Ingredient i : ingArray) {
                        recipeKey.add(i.InName);
                    }
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
            Type type = new TypeToken<ArrayList<Ingredient>>() {
            }.getType();
            ingArray = gson.fromJson(json, type);
            if (ingArray == null) {
                ingArray = ingredient.newInArray();
//            tvSize.setText(""+0);
            } else {
                for (int i = 0; i < ingArray.size(); i++) {
                    addCard(ingArray.get(i).InName, ingArray.get(i).amount);
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
            ingArray.add((new Ingredient(name, count)));
            String json = gson.toJson(ingArray);
            editor.putString("Ingredients", json);
            editor.apply();
            addCard(name, count);
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
                if (ingArray.get(i).InName.equals(name) &&
                       ingArray.get(i).amount.equals(count)) {
                    ingArray.remove(i);
                }
            }
            String json = gson.toJson(ingArray);
            editor.putString("Ingredients", json);
            editor.apply();
        }

        public void openWhatToCook() {
//        Intent intent = new Intent(this, whatToCook.class);
            Intent result = new Intent();
            setResult(RESULT_OK, result);
            finish();
        }
    }

