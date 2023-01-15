package com.example.smartfridge.Model;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartfridge.R;
import com.example.smartfridge.admin.adminAdapter;
import com.example.smartfridge.admin.admin_recipe;
import com.example.smartfridge.admin.recipesManageActivity;
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
import java.util.Objects;

public class recipesManageModel {
    recipesManageActivity activity;
    FirebaseFirestore db;
    String collection_name = "recipe_DB";
    CollectionReference recipesCollection;
    static List<admin_recipe> recipes_list = new ArrayList<>();
    Map<String, Object> recipe_map;
    RecyclerView cardView;
    adminAdapter Adapter;


    public recipesManageModel(recipesManageActivity activity){
        this.activity = activity;
        this.db = FirebaseFirestore.getInstance();
        this.recipesCollection = db.collection(collection_name);

    }
    /**adapter to the admin that can see the recipe*/
    public void setRecipes(RecyclerView cardView){
        this.cardView = cardView;
    this.recipesCollection.get()
            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
        @Override
        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
            recipes_list.clear();
            List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
            for (DocumentSnapshot document : documents) {
                recipe_map = document.getData();
                if (recipe_map != null) {
                    String name = ((String) recipe_map.get("recipeName"));
                    String time = (String) recipe_map.get("recipeTime");
                    String recipe =(String) recipe_map.get("recipe");
                    String ing =(String) recipe_map.get("recipeIngredients");
                    String user = (String)recipe_map.get("user");
                    recipes_list.add(new admin_recipe(name
                            , time
                            ,ing
                            , recipe
                            , R.drawable.ic_kitchen
                            , document.getId()
                            , user));


                    Adapter = new adminAdapter(activity, recipes_list);

                    cardView.setLayoutManager(new GridLayoutManager(activity, 1));

                    cardView.setAdapter(Adapter);

                }else{
                    Log.d(TAG, "recipe_map is null ");
                }
            }

        }
    });
        //        Log.d(TAG, "onSuccess: "+ recipes_list);
}
    private void editRecipes(){}//need to be dev in the future

    /**delete recipe from DB*/
    public static void deleteRecipes(admin_recipe recipe){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Get a reference to the document you want to delete
        DocumentReference docRef = db.collection("recipe_DB").document(recipe.getKey().toString());
        // Delete the document
        docRef.delete();
        Log.d(TAG, "deleteRecipes: "+ recipe.getKey());
    }
    /**load the recipe to the costumers DB that they can see it*/
    public static void loadRecipes(admin_recipe recipe){
        Map<String, Object> enterRecipe = new HashMap<>();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        CollectionReference recipe_DB = firestore.collection("users_recipes");
        DocumentReference docRef = firestore.collection("users_recipes").document(recipe.getKey().toString());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                enterRecipe.put("recipeName",recipe.getRecipeName());
                enterRecipe.put("recipeTime",recipe.getRecipeTime());
                enterRecipe.put("recipeIngredients",recipe.getRecipeIngredients());
                enterRecipe.put("recipe",recipe.getRecipe());
                enterRecipe.put("status","Enter Recipe");
                String username = makeUserName(recipe.getUser());
                enterRecipe.put("user",username);
                recipe_DB.document(recipe.getKey().toString()).set(enterRecipe);
            }
        });
    }

    protected static String makeUserName(String email)
    {
        String userName ="";
        for (int i = 0; i <email.length() && email.charAt(i) != '@' ; i++) {
            userName += email.charAt(i);
        }
        return userName;
    }

}
