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
        model.setRecipes(cardView);

//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        CollectionReference recipeCollection = db.collection("recipe_DB");
//        recipeCollection.get()
//        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                recipes_list.clear();
//                List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
//                for (DocumentSnapshot document : documents) {
//                    recipe_map = document.getData();
//                    recipes_list.add(new admin_recipe(recipe_map.get("recipeName").toString()
//                            , recipe_map.get("recipeTime").toString()
//                            , recipe_map.get("recipeIngredients").toString()
//                            , recipe_map.get("recipe").toString()
//                            , R.drawable.chicken_roll
//                            , document.getId()
//                            ,recipe_map.get("user").toString()));
//        cardView = (RecyclerView) findViewById(R.id.recyclerView_id);
//
//        Adapter = new adminAdapter(recipesManageActivity.this, recipes_list);
//
//        cardView.setLayoutManager(new GridLayoutManager(recipesManageActivity.this, 1));
//
//        cardView.setAdapter(Adapter);
    }


//        Log.d(TAG, "onSuccess: "+ recipes_list);

//    private void editRecipes(){}//need to be dev in the future
//
//    protected static void deleteRecipes(admin_recipe recipe){
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        // Get a reference to the document you want to delete
//        DocumentReference docRef = db.collection("recipe_DB").document(recipe.getKey().toString());
//        // Delete the document
//        docRef.delete();
//        Log.d(TAG, "deleteRecipes: "+ recipe.getKey());
//    }
//
//    protected static void loadRecipes(admin_recipe recipe){
//        Map<String, Object> enterRecipe = new HashMap<>();
//        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
//        CollectionReference recipe_DB = firestore.collection("users_recipes");
//        DocumentReference docRef = firestore.collection("users_recipes").document(recipe.getKey().toString());
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                enterRecipe.put("recipeName",recipe.getRecipeName());
//                enterRecipe.put("recipeTime",recipe.getRecipeTime());
//                enterRecipe.put("recipeIngredients",recipe.getRecipeIngredients());
//                enterRecipe.put("recipe",recipe.getRecipe());
//                enterRecipe.put("status","Enter Recipe");
//                String username = makeUserName(recipe.getUser());
//                enterRecipe.put("user",username);
//                recipe_DB.document(recipe.getKey().toString()).set(enterRecipe);
//            }
//        });
//    }
//
//    protected static String makeUserName(String email)
//    {
//        String userName ="";
//        for (int i = 0; i <email.length() && email.charAt(i) != '@' ; i++) {
//            userName += email.charAt(i);
//        }
//        return userName;


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, adminView.class);
        startActivity(intent);
    }
}