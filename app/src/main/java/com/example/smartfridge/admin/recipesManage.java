package com.example.smartfridge.admin;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartfridge.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class recipesManage extends AppCompatActivity {
    private FirebaseFirestore firestore;
    static ArrayList<String> keys = new ArrayList<>();
    static List<admin_recipe> recipes_list = new ArrayList<>();
    Map<String, Object> recipe_map;
    RecyclerView cardView;
    adminAdapter Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_manage);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference recipeCollection = db.collection("recipe_DB");
        recipeCollection.get()
        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                recipes_list.clear();
                List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot document : documents) {
                    recipe_map = document.getData();
                    recipes_list.add(new admin_recipe(recipe_map.get("recipeName").toString()
                            , recipe_map.get("recipeTime").toString()
                            , recipe_map.get("recipeIngredients").toString()
                            , recipe_map.get("recipe").toString()
                            , R.drawable.chicken_roll
                            , document.getId()));
                    cardView = (RecyclerView) findViewById(R.id.recyclerView_id);

                    Adapter = new adminAdapter(recipesManage.this, recipes_list);

                    cardView.setLayoutManager(new GridLayoutManager(recipesManage.this, 1));

                    cardView.setAdapter(Adapter);
                }
            }
        });
//        Log.d(TAG, "onSuccess: "+ recipes_list);
    }
    private void editRecipes(){}

    protected static void deleteRecipes(admin_recipe recipe){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Get a reference to the document you want to delete
        DocumentReference docRef = db.collection("recipe_DB").document(recipe.getKey().toString());
        // Delete the document
        docRef.delete();
        Log.d(TAG, "deleteRecipes: "+ recipe.getKey());
    }

    protected static void loadRecipes(admin_recipe recipe){}

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, adminView.class);
        startActivity(intent);
    }
}