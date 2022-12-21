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

    private LinearLayout layout;
    private ImageButton beckView;
    private ImageButton home;
    private ArrayList<ModelClass> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_view);
        }

//        layout = findViewById(R.id.container);
//        loadData();
//
//
//        /* Button to go beck to dry food page */
//        beckView = (ImageButton) findViewById(R.id.bt_beck);
//        beckView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                beckPage();
//            }
//        });
//
//        /* Button to return home costumer */
//        home = (ImageButton) findViewById(R.id.bt_home);
//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                homePage();
//            }
//        });
//
//    }
//
//    private void homePage() {
//        Intent intent = new Intent(this, com.example.smartfridge.costumer.costumers.class);
//        startActivity(intent);
//    }
//
//    private void beckPage() {
//        Intent intent = new Intent(this, com.example.smartfridge.costumer.page_dryFood.class);
//        startActivity(intent);
//    }
//
//    /**
//     * 1 -> meat    // Item_Data_meat
//     * 2 -> milky   // Item_Data_milky
//     * 3 -> vege    // Item_Data_vege
//     * 4 -> clean   // Item_Data_clean
//     * 5 -> dry     // Item_Data_dry
//     */
//    public void loadData(){
//        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
//        Gson gson = new Gson();
//        String json = sharedPreferences.getString("Item_Data_meat", null);
//        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();
//        arrayList = gson.fromJson(json, type);
//        if(arrayList == null){
//            arrayList = new ArrayList<>();
//        }else {
//            for (int i = 0; i < arrayList.size(); i++){
//                addCard(arrayList.get(i).itemName, arrayList.get(i).itemNumber, 1);
//            }
//        }
//
//        json = sharedPreferences.getString("Item_Data_milky", null);
//        arrayList = gson.fromJson(json, type);
//        if(arrayList == null){
//            arrayList = new ArrayList<>();
//        }else {
//            for (int i = 0; i < arrayList.size(); i++){
//                addCard(arrayList.get(i).itemName, arrayList.get(i).itemNumber, 2);
//            }
//        }
//
//        json = sharedPreferences.getString("Item_Data_vege", null);
//        arrayList = gson.fromJson(json, type);
//        if(arrayList == null){
//            arrayList = new ArrayList<>();
//        }else {
//            for (int i = 0; i < arrayList.size(); i++){
//                addCard(arrayList.get(i).itemName, arrayList.get(i).itemNumber, 3);
//            }
//        }
//
//        json = sharedPreferences.getString("Item_Data_clean", null);
//        arrayList = gson.fromJson(json, type);
//        if(arrayList == null){
//            arrayList = new ArrayList<>();
//        }else {
//            for (int i = 0; i < arrayList.size(); i++){
//                addCard(arrayList.get(i).itemName, arrayList.get(i).itemNumber, 4);
//            }
//        }
//
//        json = sharedPreferences.getString("Item_Data_dry", null);
//        arrayList = gson.fromJson(json, type);
//        if(arrayList == null){
//            arrayList = new ArrayList<>();
//        }else {
//            for (int i = 0; i < arrayList.size(); i++){
//                addCard(arrayList.get(i).itemName, arrayList.get(i).itemNumber, 5);
//            }
//        }
//    }
//
//    private void addCard(String name, String number, int type) {
//
//        View view = null;
//        switch(type) {
//            case 1:
//                view = getLayoutInflater().inflate(R.layout.meat_card, null);
//                break;
//            case 2:
//                view = getLayoutInflater().inflate(R.layout.milky_card, null);
//                break;
//            case 3:
//                view = getLayoutInflater().inflate(R.layout.vege_card, null);
//                break;
//            case 4:
//                view = getLayoutInflater().inflate(R.layout.clean_card, null);
//                break;
//            case 5:
//                view = getLayoutInflater().inflate(R.layout.dry_card, null);
//                break;
//        }
//        TextView nameView = view.findViewById(R.id.name);
//        TextView countView = view.findViewById(R.id.number);
//        Button delete = view.findViewById(R.id.delete);
//
//        nameView.setText(name);
//        countView.setText(number);
//
//        View finalView = view;
//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                removeArray(nameView.getText().toString(), countView.getText().toString());
//                layout.removeView(finalView);
//            }
//        });
//        layout.addView(view);
//    }
//
//    private void removeArray(String toString, String toString1) {
//    }
}
