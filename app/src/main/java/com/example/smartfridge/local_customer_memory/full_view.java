package com.example.smartfridge.local_customer_memory;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.Model.fullViewModel;
import com.example.smartfridge.business_entities.ModelClass;
import com.example.smartfridge.R;
import com.example.smartfridge.ui.main.MainMenu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class full_view extends AppCompatActivity {
    fullViewModel model = new fullViewModel(this);
    LinearLayout layout;
    ImageButton beckView;
    ImageButton home;
    ArrayList<ModelClass> arrayList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_view);
        layout = findViewById(R.id.container);
        model.loadData();

        /* Button to go beck to dry food page */
        beckView = (ImageButton) findViewById(R.id.bt_beck);
        beckView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                beckPage();
            }
        });

        /* Button to return home customer */
        home = (ImageButton) findViewById(R.id.bt_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homePage();
            }
        });

    }

    private void homePage() {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    private void beckPage() {
        Intent intent = new Intent(this, ShoppingTables.class);
        startActivity(intent);
    }

    /** add card vegetavles and delete from the memory **/
    public void addCard_vege(String name, String number) {
        View view = getLayoutInflater().inflate(R.layout.vege_card, null);
        TextView nameView = view.findViewById(R.id.vege_name);
        TextView countView = view.findViewById(R.id.vege_number);
        Button delete = view.findViewById(R.id.vege_delete);

        nameView.setText(name);
        countView.setText(number);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.removeArray_vege(nameView.getText().toString(), countView.getText().toString());
                layout.removeView(view);
            }
        });
        layout.addView(view);
    }

    /** add card meat and delete from the memory **/
    public void addCard_meat(String name, String number) {
        View view = getLayoutInflater().inflate(R.layout.meat_card, null);

        TextView nameView = view.findViewById(R.id.name);
        TextView countView = view.findViewById(R.id.number);
        Button delete = view.findViewById(R.id.delete);

        nameView.setText(name);
        countView.setText(number);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.removeArray_meat(nameView.getText().toString(), countView.getText().toString());
                layout.removeView(view);
            }
        });
        layout.addView(view);
    }

    /** add card milky and delete from the memory **/
    public void addCard_milky(String name, String number) {
        View view = getLayoutInflater().inflate(R.layout.milky_card, null);
        TextView nameView = view.findViewById(R.id.name);
        TextView countView = view.findViewById(R.id.number);
        Button delete = view.findViewById(R.id.delete);

        nameView.setText(name);
        countView.setText(number);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.removeArray_milky(nameView.getText().toString(), countView.getText().toString());
                layout.removeView(view);
            }
        });
        layout.addView(view);
    }

    /** add card cleaning and materials and delete from the memory **/
    public void addCard_clean(String name, String number) {
        View view = getLayoutInflater().inflate(R.layout.clean_card, null);

        TextView nameView = view.findViewById(R.id.clean_name);
        TextView countView = view.findViewById(R.id.clean_number);
        Button delete = view.findViewById(R.id.clean_delete);

        nameView.setText(name);
        countView.setText(number);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.removeArray_clean(nameView.getText().toString(), countView.getText().toString());
                layout.removeView(view);
            }
        });
        layout.addView(view);
    }

    /** add card dry food and delete from the memory **/
    public void addCard_dry(String name, String number) {
        View view = getLayoutInflater().inflate(R.layout.dry_card, null);
        TextView nameView = view.findViewById(R.id.name);
        TextView countView = view.findViewById(R.id.number);
        Button delete = view.findViewById(R.id.delete);

        nameView.setText(name);
        countView.setText(number);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.removeArray_dry(nameView.getText().toString(), countView.getText().toString());
                layout.removeView(view);
            }
        });
        layout.addView(view);
    }

    /** add card dry food and delete from the memory **/
    public void addCard_my_category(String name, String number) {
        View view = getLayoutInflater().inflate(R.layout.selected_card, null);
        TextView nameView = view.findViewById(R.id.name);
        TextView countView = view.findViewById(R.id.number);
        Button delete = view.findViewById(R.id.delete);

        nameView.setText(name);
        countView.setText(number);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.removeArray_my_category(nameView.getText().toString(), countView.getText().toString());
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
