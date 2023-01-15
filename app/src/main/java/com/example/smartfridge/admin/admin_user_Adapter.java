package com.example.smartfridge.admin;

import static com.example.smartfridge.Model.usersManageModel.deleteUsers;

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
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class admin_user_Adapter extends RecyclerView.Adapter<admin_user_Adapter.MyHolder> {

    private Context mContext;
    private List<user> usersList;

    public admin_user_Adapter (Context mContext , List<user> usersList){
        this.mContext = mContext;
        this.usersList = usersList;
    }
    /**adapter for costumer DB*/
    @NonNull
    @Override
    public admin_user_Adapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.admin_user_card_view,parent,false);
        return new admin_user_Adapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull admin_user_Adapter.MyHolder holder, @SuppressLint("RecyclerView") int i) {

        holder.userTitle.setText(usersList.get(i).getEmail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, admin_user_activity.class);
                intent.putExtra("Name",usersList.get(i).getName());
                intent.putExtra("Email","Email: " + usersList.get(i).getEmail());
                intent.putExtra("password","Password: "+usersList.get(i).getPassword());
                mContext.startActivity(intent);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, usersManageActivity.class);
                deleteUsers(usersList.get(i));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        TextView userTitle;
        CardView cardView;
        ImageView img;
        MaterialButton delete;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            userTitle = (TextView) itemView.findViewById(R.id.User_name);
            img = (ImageView)itemView.findViewById(R.id.imageView2);
            cardView = (CardView) itemView.findViewById(R.id.user_card_admin_id);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
