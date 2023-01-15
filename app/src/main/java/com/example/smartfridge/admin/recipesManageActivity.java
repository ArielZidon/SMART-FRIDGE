package com.example.smartfridge.admin;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartfridge.Model.recipesManageModel;
import com.example.smartfridge.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class recipesManageActivity extends AppCompatActivity {
    static List<admin_recipe> recipes_list = new ArrayList<>();
    recipesManageModel model = new recipesManageModel(this);
    Map<String, Object> recipe_map;
    RecyclerView cardView;
    adminAdapter Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_manage);
        cardView = (RecyclerView) findViewById(R.id.recyclerView_id);
        /**bring the recipe card visibility*/
        model.setRecipes(cardView);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, adminView.class);
        startActivity(intent);
    }
}