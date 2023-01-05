package com.example.smartfridge.recipesDB;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.smartfridge.R;

import org.jetbrains.annotations.Nullable;

public class insert_recipe extends AppCompatActivity {

    private final int IMAGE;
    ImageView imgGallery;

    public insert_recipe(int image) {
        IMAGE = image;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_recipe);

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