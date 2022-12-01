package com.example.smartfridge;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class page_meat extends AppCompatActivity {

    EditText etName;
    EditText etCount;
    Button btSave;
    TextView tvSize;
    ArrayList<ModelClass> arrayList;


    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_meat);

        etName = findViewById(R.id.et_name);
        etCount = findViewById(R.id.et_count);
        btSave = findViewById(R.id.bt_sava);
        tvSize = findViewById(R.id.tv_size);
        loadData();

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData(etName.getText().toString(), etCount.getText().toString());
            }
        });

    }

    private void loadData() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Item_Data", null);
        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();
        arrayList = gson.fromJson(json, type);
        if(arrayList == null){
            arrayList = new ArrayList<>();
            tvSize.setText(""+0);
        }else {
            for (int i = 0; i < arrayList.size(); i++){
                tvSize.setText(tvSize.getText().toString()+"\n"+arrayList.get(i).itemName);
            }
        }
    }

    private void saveData(String name, String count) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        arrayList.add((new ModelClass(name, count)));
        String json = gson.toJson(arrayList);
        editor.putString("Item_Data", json);
        editor.apply();
        tvSize.setText("List Data\n");
        loadData();
    }
}