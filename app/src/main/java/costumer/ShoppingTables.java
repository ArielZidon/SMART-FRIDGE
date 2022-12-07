package costumer;

import static com.example.smartfridge.SortProducts.mixCombination;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.ModelClass;
import com.example.smartfridge.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ShoppingTables extends AppCompatActivity {

    private ImageButton bt_meat;
    private ImageButton bt_milky;
    private ImageButton bt_clean;
    private ImageButton bt_dry;
    private ImageButton bt_vegetables;
    private ImageButton bt_recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_tables);

        bt_meat = (ImageButton) findViewById(R.id.bt_meat);
        bt_milky = (ImageButton) findViewById(R.id.bt_milky);
        bt_clean = (ImageButton) findViewById(R.id.bt_clean);
        bt_dry = (ImageButton) findViewById(R.id.bt_dty);
        bt_vegetables = (ImageButton) findViewById(R.id.bt_vegetables);
        bt_recipes = (ImageButton) findViewById(R.id.bt_recipes);

        bt_meat.setOnClickListener(v -> openPage_meat());
        bt_milky.setOnClickListener(v -> openPage_milky());
        bt_clean.setOnClickListener(v -> openPage_clean());
        bt_dry.setOnClickListener(v -> openPage_dry());
        bt_vegetables.setOnClickListener(v -> openPage_bt_vegetables());
        bt_recipes.setOnClickListener(v -> createRecipes());

    }

    public void openPage_meat() {
        Intent intent = new Intent(this, page_meat.class);
        startActivity(intent);
    }
    public void openPage_milky() {
        Intent intent = new Intent(this, page_milky.class);
        startActivity(intent);
    }
    public void openPage_clean() {
        Intent intent = new Intent(this, page_cleaning_materials.class);
        startActivity(intent);
    }
    public void openPage_dry() {
        Intent intent = new Intent(this, page_dryFood.class);
        startActivity(intent);
    }
    public void openPage_bt_vegetables() {
        Intent intent = new Intent(this, page_vegetables.class);
        startActivity(intent);
    }
    public void createRecipes(){
        ArrayList<ModelClass> arrayList_all = new ArrayList<ModelClass>();
        ArrayList<ModelClass> arrayList_temp = new ArrayList<ModelClass>();

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Item_Data_meat", null);
        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();
        arrayList_all = gson.fromJson(json, type);

        json = sharedPreferences.getString("Item_Data_milky", null);
        arrayList_temp = gson.fromJson(json, type);
        arrayList_all.addAll(arrayList_temp);

        json = sharedPreferences.getString("Item_Data_vege", null);
        arrayList_temp = gson.fromJson(json, type);
        arrayList_all.addAll(arrayList_temp);

        json = sharedPreferences.getString("Item_Data_Clean", null);
        arrayList_temp = gson.fromJson(json, type);
        arrayList_all.addAll(arrayList_temp);

        json = sharedPreferences.getString("Item_Data_Dry", null);
        arrayList_temp = gson.fromJson(json, type);
        arrayList_all.addAll(arrayList_temp);

        String[] products = new String[arrayList_all.size()];

        for (int i = 0; i <products.length ; i++) {
            products[i] = arrayList_all.get(i).getItemName();
        }
        mixCombination( products,products.length,3);
    }
}