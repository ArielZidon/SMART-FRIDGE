package costumer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.R;

import org.w3c.dom.Text;

public class recipesDisplay extends  AppCompatActivity{

        ImageButton Recipes;        //ImageButton that represent the Button "recipes"

        @SuppressLint("SetTextI18n")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            TextView myTextView =  (TextView)findViewById(Integer.parseInt("@+id/textView"));
            setContentView(R.layout.display_recipes);
            String value1 = super.getIntent().getExtras().getString("recipes");
            myTextView.setText("value1: " + value1 + "");

        }
}
