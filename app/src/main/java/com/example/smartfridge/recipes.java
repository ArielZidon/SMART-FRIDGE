package com.example.smartfridge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class recipes extends AppCompatActivity {
    AlertDialog dialog;
    LinearLayout layout;
//    ArrayAdapter<String> adapter;

    ListView listViewDate;
    EditText name;
    EditText number;
    Button buttonSave;
    Button add;
    TextView tvSize;
    ArrayList<ModelClass> arrayList;

//    String[] array = {"afik damri", "Cabin in the wood", "doctor sleep"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        loadData();
        listViewDate = findViewById(R.id.listViewMeat);
        name = findViewById(R.id.nameEdit);
        number = findViewById(R.id.numberEdit);
        add = findViewById(R.id.add);
        layout = findViewById(R.id.container);
        buttonSave = findViewById(R.id.savaData);
        buildDialog();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("DATA",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Item Data", null);
        Type type = new TypeToken<ArrayAdapter<ModelClass>>(){}.getType();
        arrayList = gson.fromJson(json, type);
        if(arrayList == null){
            arrayList = new ArrayList<>();
        }
    }
//            tvSize.setText("" + 0);
//        }else{
//            for(int i = 0; i < arrayList.size(); i++) {
//                name = view.findViewWithTag(arrayList.get(i).itemName.toString());
//                number = view.findViewWithTag(arrayList.get(i).itemNumber.toString());
//                addCard(arrayList.get(i).itemName.toString(), arrayList.get(i).itemNumber.toString());
//                tvSize.setText(tvSize.getText().toString() + "\n" + arrayList.get(i).itemName + "\n");
//            }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("DATA",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString("Item Data", json);
        editor.apply();
//        arrayList.add(new ModelClass(name, number));
//        loadData();
    }

    private void buildDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog, null);

        name = view.findViewById(R.id.nameEdit);
        number = view.findViewById(R.id.numberEdit);

        builder.setView(view);
        builder.setTitle("Enter Item")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addCard(name.getText().toString(), number.getText().toString());

                    }
                })
                .setNegativeButton("Cencel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        dialog = builder.create();
    }

    private void addCard(String name, String number) {
        View view = getLayoutInflater().inflate(R.layout.card, null);

        TextView nameView = view.findViewById(R.id.name);
        TextView numberView = view.findViewById(R.id.number);
        Button delete = view.findViewById(R.id.delete);

        nameView.setText(name);
        numberView.setText(number);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.removeView(view);
            }
        });
        layout.addView(view);
    }

    public boolean onCreteOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

//    public boolean onOptionsItemSelected(@NonNull MenuItem item){
//        int id = item.getItemId();
//        if(id == R.id.item_done){
//            String itemSelected = "Selected  item: \n";
//            for (int i = 0; i < listViewDate.getCount(); i++){
//                if(listViewDate.isItemChecked(i)) {
//                    itemSelected += listViewDate.getItemAtPosition(i) + "\n";
//                }
//            }
//            Toast.makeText(this, itemSelected, Toast.LENGTH_SHORT).show();
//        }
//        return super.onOptionsItemSelected(item);
//    }

}