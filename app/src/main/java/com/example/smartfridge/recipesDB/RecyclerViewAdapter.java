package com.example.smartfridge.recipesDB;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.drawable.Drawable;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartfridge.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyHolder> {

    private Context mContext;
    private List<recipe> lisrecipe;
    static String imageName;
    ImageView img;


    public RecyclerViewAdapter (Context mContext , List<recipe> lisrecipe){
        this.mContext = mContext;
        this.lisrecipe = lisrecipe;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.cardview_recipe,parent,false);


        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int i) {
        holder.recipeTitle.setText(lisrecipe.get(i).getRecipeName());
        holder.img.setImageResource(lisrecipe.get(i).getThumbnail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, recipe_activity.class);

                intent.putExtra("RecipeName",lisrecipe.get(i).getRecipeName());
                intent.putExtra("Time","Time: " + lisrecipe.get(i).getTime());
                intent.putExtra("RecipeIngredientsTitle",lisrecipe.get(i).getRecipeIngredientsTitle());
                intent.putExtra("RecipeIngredients",lisrecipe.get(i).getRecipeIngredients());
                intent.putExtra("RecipeMethodTitle",lisrecipe.get(i).getRecipeMethodTitle());
                intent.putExtra("Recipe",lisrecipe.get(i).getRecipe());
                intent.putExtra("Thumbnail",lisrecipe.get(i).getThumbnail());

                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return lisrecipe.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView recipeTitle;
        CardView cardView;
        ImageView img;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            recipeTitle = (TextView) itemView.findViewById(R.id.recipe_text);
            img = (ImageView)itemView.findViewById(R.id.recipe_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);

//            try {
//                FirebaseStorage storage = FirebaseStorage.getInstance();
//                StorageReference storageRef = storage.getReference();
//                StorageReference imageRef = storageRef.child("images/"+RecyclerViewAdapter.imageName+".jpg");
//                File localFile = File.createTempFile("image", "jpg");
//                imageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                        // Local temp file has been created
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception exception) {
//                        // Handle any errors
//                    }
//                });
//                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
//                img.setImageBitmap(bitmap);
//            }
//            catch (@NonNull Exception exception){
//
//            }



        }
    }


}