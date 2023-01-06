package com.example.smartfridge.recipesDB;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.smartfridge.R;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class insert_recipe extends AppCompatActivity {

    private int IMAGE;
    ImageView imgGallery;
    LinearLayout layoutList;
    Button add;
    String ingredients = null;

//    public insert_recipe(int image) {
//        IMAGE = image;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_recipe);

        layoutList = (LinearLayout) findViewById(R.id.layout_list);
        add = (Button) findViewById(R.id.button_add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addView();
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

            }
        });
    }

    private void addView(){

        final View cricketerView = getLayoutInflater().inflate(R.layout.activity_row_add_cricketer,null,false);

        EditText ingredient = (EditText)cricketerView.findViewById(R.id.edit_ingredient_name);
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
//        insert_ingredient(ingredient,quantity);

    }

    private void insert_ingredient(EditText ingredient,EditText quantity){

        ingredients.concat(ingredient.getText().toString());
        ingredients.concat(" " + quantity.getText().toString());
        ingredients.concat("\n");
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
}