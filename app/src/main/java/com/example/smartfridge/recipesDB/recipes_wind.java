package com.example.smartfridge.recipesDB;

import static android.content.ContentValues.TAG;
import static com.example.smartfridge.business_logic.SortProducts.giveMeKeys;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartfridge.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class recipes_wind extends AppCompatActivity {

    private FirebaseFirestore firestore;
    static ArrayList<String> keys = new ArrayList<>();

    RecyclerView myrecyclerView;
    RecyclerViewAdapter myAdapter;

    static List<recipe> recipes1 = new ArrayList<recipe>();
    Map<String, Object> recipe_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_wind);

        firestore = FirebaseFirestore.getInstance();//Initialization of the object firestore

        //CollectionReference object - open a database collection (if exist - open, else - create & open )
        CollectionReference recipe_DB = firestore.collection("recipe_DB");

        keys.clear();
        giveMeKeys(keys);

        for (int i = 0; i < keys.size(); i++) {
            DocumentReference docRef = firestore.collection("recipe_DB").document(keys.get(i));
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists())
                        {
                            recipe_map = document.getData();
                            recipes1.add(new recipe(recipe_map.get("recipeName").toString()
                                , recipe_map.get("recipeTime").toString()
                                , recipe_map.get("recipeIngredients").toString()
                                , recipe_map.get("recipe").toString()
                                , R.drawable.chicken_roll));

                            myrecyclerView = (RecyclerView)findViewById(R.id.recyclerView_id);

                            myAdapter = new RecyclerViewAdapter(recipes_wind.this,recipes1);

                            myrecyclerView.setLayoutManager(new GridLayoutManager(recipes_wind.this,1));

                            myrecyclerView.setAdapter(myAdapter);
                        }
                        else {
                            Log.d(TAG, "document doesn't exist: no Result for  ");
                        }
                        }
                    else {
                        Log.d(TAG, "No such document");
                    }
                    }
            });
            recipes1.clear(); //need to develop this
       }

    }
}
