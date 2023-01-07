package com.example.smartfridge.recipesDB;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartfridge.R;
import com.example.smartfridge.ui.main.MainMenu;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class insert_recipe extends AppCompatActivity {

    private int IMAGE;
    ImageView imgGallery;
    LinearLayout layoutList;
    Button add;
    String ingredients = null;
    Button submit_list;
    ArrayList<Ingredient> ingredients_List = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_recipe);

        TextView recipe_name = (TextView) findViewById(R.id.recipe_name);
        TextView recipe_time = (TextView) findViewById(R.id.recipe_time);
        TextView recipe_instructions = (TextView) findViewById(R.id.recipe_instructions);

        layoutList = (LinearLayout) findViewById(R.id.layout_list);
        add = (Button) findViewById(R.id.button_add);
        submit_list = (Button) findViewById(R.id.button_submit_list);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addView();
            }
        });



        submit_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkIfValidAndRead();
//                if(checkIfValidAndRead()){
////                    Intent intent = new Intent(insert_recipe.this,ingredient_recycler.class);
////                    Bundle bundle = new Bundle();
////                    bundle.putSerializable("list",ingredients_List);
////                    intent.putExtras(bundle);
////                    startActivity(intent);
//                }
                for (int i = 0; i < ingredients_List.size(); i++) {
                    insert_ingredients(ingredients_List.get(i).getIngredientName(),ingredients_List.get(i).getQuantity());
                }
                ArrayList<String> new_recipe = new ArrayList<>();
                new_recipe.add(recipe_name.getText().toString());
                new_recipe.add(recipe_time.getText().toString());
                new_recipe.add(recipe_instructions.getText().toString());
                new_recipe.add(ingredients);

                Toast.makeText(insert_recipe.this,"Your RECIPE sent to admin!",Toast.LENGTH_LONG).show();

                openMainMenu();

                //****need to send to database;
            }
        });


        imgGallery = (ImageView) findViewById(R.id.img_gallery);
        Button open_gallery = (Button) findViewById(R.id.upload_image_bt);

        open_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent(Intent.ACTION_PICK);
                gallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery,IMAGE);
                //***NEED INSERT IMAGE TO DATABASE
            }
        });
    }

    private boolean checkIfValidAndRead() {
        ingredients_List.clear();
        boolean result = true;

        for (int i = 0; i < layoutList.getChildCount(); i++) {

            View cricketerView = layoutList.getChildAt(i);

            EditText ingred = (EditText)cricketerView.findViewById(R.id.edit_ingredient_name);
            EditText quantity = (EditText)cricketerView.findViewById(R.id.quantity);

            Ingredient Ingredient = new Ingredient();

            if (!ingred.getText().toString().equals("")) {
                Ingredient.setIngredientName(ingred.getText().toString());
            }
            else {
                result = false;
                break;
            }

            if (!quantity.getText().toString().equals("")) {
                Ingredient.setQuantity(quantity.getText().toString());

                ingredients_List.add(Ingredient);
            }
            else {
                result = false;
                break;
            }


            if (ingredients_List.size() == 0) {
                result = false;
                Toast.makeText(this, "Add Cricketers First!", Toast.LENGTH_SHORT).show();
            }

            else if(!result){
                Toast.makeText(this, "Enter All Details Correctly!", Toast.LENGTH_SHORT).show();
            }

        }
        return result;
    }

    private void addView(){

        final View cricketerView = getLayoutInflater().inflate(R.layout.activity_row_add_cricketer,null,false);

        EditText ingred = (EditText)cricketerView.findViewById(R.id.edit_ingredient_name);
        EditText quantity = (EditText)cricketerView.findViewById(R.id.quantity);
        ImageView imageClose = (ImageView)cricketerView.findViewById(R.id.image_remove);
        
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item);

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(cricketerView);
            }
        });

        layoutList.addView(cricketerView);

    }

    private void insert_ingredients(String ingredient,String quantity){
        if (ingredients == null) {
            ingredients = ingredient;
            ingredients.concat(" " + quantity);
            ingredients.concat("\n");
        }
        else {
            ingredients.concat(ingredient);
            ingredients.concat(" " + quantity);
            ingredients.concat("\n");
        }
    }

    private void removeView(View view){

        layoutList.removeView(view);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK) {

            if (requestCode == IMAGE) {
                //FOR GALLERY
                imgGallery.setImageURI(data.getData());
            }

        }
    }

    public void openMainMenu(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}