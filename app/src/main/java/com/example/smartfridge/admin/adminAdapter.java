package com.example.smartfridge.admin;

import static com.example.smartfridge.Model.recipesManageModel.deleteRecipes;
import static com.example.smartfridge.Model.recipesManageModel.loadRecipes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartfridge.R;
import com.example.smartfridge.recipesDB.recipe_activity;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class adminAdapter extends RecyclerView.Adapter<adminAdapter.MyHolder> {

    private Context mContext;
    private List<admin_recipe> lisrecipe;

    public adminAdapter (Context mContext , List<admin_recipe> lisrecipe){
        this.mContext = mContext;
        this.lisrecipe = lisrecipe;
    }
    /**adapter for admin recipes DB*/
    @NonNull
    @Override
    public adminAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.admin_card_view,parent,false);
        return new adminAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adminAdapter.MyHolder holder, @SuppressLint("RecyclerView") int i) {
        holder.recipeTitle.setText(lisrecipe.get(i).getRecipeName());
        holder.img.setImageResource(lisrecipe.get(i).getThumbnail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, recipe_activity.class);

                intent.putExtra("RecipeName",lisrecipe.get(i).getRecipeName());
                intent.putExtra("Time","Time: " + lisrecipe.get(i).getRecipeTime());
                intent.putExtra("RecipeIngredientsTitle",lisrecipe.get(i).getRecipeIngredientsTitle());
                intent.putExtra("RecipeIngredients",lisrecipe.get(i).getRecipeIngredients());
                intent.putExtra("RecipeMethodTitle",lisrecipe.get(i).getRecipeMethodTitle());
                intent.putExtra("Recipe",lisrecipe.get(i).getRecipe());
                intent.putExtra("Thumbnail",lisrecipe.get(i).getThumbnail());
                intent.putExtra("user",lisrecipe.get(i).getUser());

                mContext.startActivity(intent);

            }
        });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, recipesManageActivity.class);
                loadRecipes(lisrecipe.get(i));
                deleteRecipes(lisrecipe.get(i));
                mContext.startActivity(intent);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, recipesManageActivity.class);
                deleteRecipes(lisrecipe.get(i));
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
        MaterialButton delete;
        MaterialButton add;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            recipeTitle = (TextView) itemView.findViewById(R.id.recipe_admin_text);
            img = (ImageView)itemView.findViewById(R.id.recipe_img_admin_id);
            cardView = (CardView) itemView.findViewById(R.id.card_admin_id);
            delete = itemView.findViewById(R.id.delete);
            add = itemView.findViewById(R.id.add);
        }
    }
}
