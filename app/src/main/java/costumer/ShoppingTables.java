package costumer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.R;

public class ShoppingTables extends AppCompatActivity {

    private ImageButton bt_meat;
    private ImageButton bt_milky;
    private ImageButton bt_clean;
    private ImageButton bt_dry;
    private ImageButton bt_vegetables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_tables);

        bt_meat = (ImageButton) findViewById(R.id.bt_meat);
        bt_milky = (ImageButton) findViewById(R.id.bt_milky);
        bt_clean = (ImageButton) findViewById(R.id.bt_clean);
        bt_dry = (ImageButton) findViewById(R.id.bt_dty);
        bt_vegetables = (ImageButton) findViewById(R.id.bt_vegetables);

        bt_meat.setOnClickListener(v -> openPage_meat());
        bt_milky.setOnClickListener(v -> openPage_milky());
        bt_clean.setOnClickListener(v -> openPage_clean());
        bt_dry.setOnClickListener(v -> openPage_dry());
        bt_vegetables.setOnClickListener(v -> openPage_bt_vegetables());

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
}