package com.example.smartfridge;

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

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyHolder> {

    private Context mContext;
    private List<recipe> lisrecipe;

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

                Intent intent = new Intent(mContext,recipe_activity.class);

                intent.putExtra("RecipeName",lisrecipe.get(i).getRecipeName());
                intent.putExtra("Time",lisrecipe.get(i).getTime());
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
            recipeTitle = (TextView) itemView.findViewById(R.id.text_recipe);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }
}