package com.example.smartfridge.costumer;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.smartfridge.ModelClass;
import com.example.smartfridge.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class full_view extends AppCompatActivity {

    LinearLayout layout;
    ImageButton beckView;
    ImageButton home;
    ArrayList<ModelClass> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_view);

        layout = findViewById(R.id.container);
        loadData();


        /* Button to go beck to dry food page */
        beckView = (ImageButton) findViewById(R.id.bt_beck);
        beckView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                beckPage();
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

    private void homePage() {
        Intent intent = new Intent(this, com.example.smartfridge.costumer.costumers.class);
        startActivity(intent);
    }

    private void beckPage() {
        Intent intent = new Intent(this, com.example.smartfridge.costumer.ShoppingTables.class);
        startActivity(intent);
    }

    /**
     * reload data to full view with all card and type of card
     */
    public void loadData(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();

        String json = sharedPreferences.getString("Item_Data_meat", null);
        if (json != null) {
            arrayList = gson.fromJson(json, type);
            for (int i = 0; i < arrayList.size(); i++) {
                addCard_meat(arrayList.get(i).itemName, arrayList.get(i).itemNumber);
            }
        }

        json = sharedPreferences.getString("Item_Data_milky", null);
        if (json != null) {
            arrayList = gson.fromJson(json, type);
            for (int i = 0; i < arrayList.size(); i++) {
                addCard_milky(arrayList.get(i).itemName, arrayList.get(i).itemNumber);
            }
        }

        json = sharedPreferences.getString("Item_Data_vege", null);
        if (json != null) {
            arrayList = gson.fromJson(json, type);
            for (int i = 0; i < arrayList.size(); i++) {
                addCard_vege(arrayList.get(i).itemName, arrayList.get(i).itemNumber);
            }
        }

        json = sharedPreferences.getString("Item_Data_Clean", null);
        if (json != null) {
            arrayList = gson.fromJson(json, type);
            for (int i = 0; i < arrayList.size(); i++) {
                addCard_clean(arrayList.get(i).itemName, arrayList.get(i).itemNumber);
            }
        }

        json = sharedPreferences.getString("Item_Data_Dry", null);
        if (json != null) {
            arrayList = gson.fromJson(json, type);
            for (int i = 0; i < arrayList.size(); i++) {
                addCard_dry(arrayList.get(i).itemName, arrayList.get(i).itemNumber);
            }
        }

        json = sharedPreferences.getString("Item_Data_my_category", null);
        if (json != null) {
            arrayList = gson.fromJson(json, type);
            for (int i = 0; i < arrayList.size(); i++) {
                addCard_my_category(arrayList.get(i).itemName, arrayList.get(i).itemNumber);
            }
        }
    }

    /** add card vegetavles and delete from the memory **/
    private void addCard_vege(String name, String number) {
        View view = getLayoutInflater().inflate(R.layout.vege_card, null);
        TextView nameView = view.findViewById(R.id.vege_name);
        TextView countView = view.findViewById(R.id.vege_number);
        Button delete = view.findViewById(R.id.vege_delete);

        nameView.setText(name);
        countView.setText(number);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeArray_vege(nameView.getText().toString(), countView.getText().toString());
                layout.removeView(view);
            }
        });
        layout.addView(view);
    }

    /** add card meat and delete from the memory **/
    private void addCard_meat(String name, String number) {
        View view = getLayoutInflater().inflate(R.layout.meat_card, null);

        TextView nameView = view.findViewById(R.id.name);
        TextView countView = view.findViewById(R.id.number);
        Button delete = view.findViewById(R.id.delete);

        nameView.setText(name);
        countView.setText(number);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeArray_meat(nameView.getText().toString(), countView.getText().toString());
                layout.removeView(view);
            }
        });
        layout.addView(view);
    }

    /** add card milky and delete from the memory **/
    private void addCard_milky(String name, String number) {
        View view = getLayoutInflater().inflate(R.layout.milky_card, null);
        TextView nameView = view.findViewById(R.id.name);
        TextView countView = view.findViewById(R.id.number);
        Button delete = view.findViewById(R.id.delete);

        nameView.setText(name);
        countView.setText(number);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeArray_milky(nameView.getText().toString(), countView.getText().toString());
                layout.removeView(view);
            }
        });
        layout.addView(view);
    }

    /** add card cleaning and materials and delete from the memory **/
    private void addCard_clean(String name, String number) {
        View view = getLayoutInflater().inflate(R.layout.clean_card, null);

        TextView nameView = view.findViewById(R.id.clean_name);
        TextView countView = view.findViewById(R.id.clean_number);
        Button delete = view.findViewById(R.id.clean_delete);

        nameView.setText(name);
        countView.setText(number);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeArray_clean(nameView.getText().toString(), countView.getText().toString());
                layout.removeView(view);
            }
        });
        layout.addView(view);
    }

    /** add card dry food and delete from the memory **/
    private void addCard_dry(String name, String number) {
        View view = getLayoutInflater().inflate(R.layout.dry_card, null);
        TextView nameView = view.findViewById(R.id.name);
        TextView countView = view.findViewById(R.id.number);
        Button delete = view.findViewById(R.id.delete);

        nameView.setText(name);
        countView.setText(number);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeArray_dry(nameView.getText().toString(), countView.getText().toString());
                layout.removeView(view);
            }
        });
        layout.addView(view);
    }

    /** add card dry food and delete from the memory **/
    private void addCard_my_category(String name, String number) {
        View view = getLayoutInflater().inflate(R.layout.selected_card, null);
        TextView nameView = view.findViewById(R.id.name);
        TextView countView = view.findViewById(R.id.number);
        Button delete = view.findViewById(R.id.delete);

        nameView.setText(name);
        countView.setText(number);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeArray_dry(nameView.getText().toString(), countView.getText().toString());
                layout.removeView(view);
            }
        });
        layout.addView(view);
    }

    /**
     * @param name => item name that customer select to delete
     * @param count => count item that customer select to delete
     *  1. remove item from the screen
     *  2. remove item from sharedPreferences
     */
    private void removeArray_vege(String name, String count) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        for (int i = 0; i < arrayList.size();i++) {
            if (arrayList.get(i).getItemName().equals(name) &&
                    arrayList.get(i).itemNumber.equals(count)) {
                arrayList.remove(i);
            }
        }
        String json = gson.toJson(arrayList);
        editor.putString("Item_Data_vege", json);
        editor.apply();
    }


    /**
     * @param name => item name that customer select to delete
     * @param count => count item that customer select to delete
     *  1. remove item from the screen
     *  2. remove item from sharedPreferences
     */
    private void removeArray_meat(String name, String count) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        for (int i = 0; i < arrayList.size();i++) {
            if (arrayList.get(i).getItemName().equals(name) &&
                    arrayList.get(i).itemNumber.equals(count)) {
                arrayList.remove(i);
            }
        }
        String json = gson.toJson(arrayList);
        editor.putString("Item_Data_meat", json);
        editor.apply();
    }

    /**
     * @param name => item name that customer select to delete
     * @param count => count item that customer select to delete
     *  1. remove item from the screen
     *  2. remove item from sharedPreferences
     */
    private void removeArray_milky(String name, String count) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        for (int i = 0; i < arrayList.size();i++) {
            if (arrayList.get(i).getItemName().equals(name) &&
                    arrayList.get(i).itemNumber.equals(count)) {
                arrayList.remove(i);
            }
        }
        String json = gson.toJson(arrayList);
        editor.putString("Item_Data_milky", json);
        editor.apply();
    }

    /**
     * @param name => item name that customer select to delete
     * @param count => count item that customer select to delete
     *  1. remove item from the screen
     *  2. remove item from sharedPreferences
     */
    private void removeArray_clean(String name, String count) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        for (int i = 0; i < arrayList.size();i++) {
            if (arrayList.get(i).getItemName().equals(name) &&
                    arrayList.get(i).itemNumber.equals(count)) {
                arrayList.remove(i);
            }
        }
        String json = gson.toJson(arrayList);
        editor.putString("Item_Data_Clean", json);
        editor.apply();
    }

    /**
     * @param name => item name that customer select to delete
     * @param count => count item that customer select to delete
     *  1. remove item from the screen
     *  2. remove item from sharedPreferences
     */
    private void removeArray_dry(String name, String count) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        for (int i = 0; i < arrayList.size();i++) {
            if (arrayList.get(i).getItemName().equals(name) &&
                    arrayList.get(i).itemNumber.equals(count)) {
                arrayList.remove(i);
            }
        }
        String json = gson.toJson(arrayList);
        editor.putString("Item_Data_Dry", json);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, com.example.smartfridge.costumer.ShoppingTables.class);
        startActivity(intent);
    }
}
