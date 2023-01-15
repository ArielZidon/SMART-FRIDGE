package com.example.smartfridge.recipesDB;

import static android.content.ContentValues.TAG;
import static com.example.smartfridge.business_logic.SortProducts.giveMeKeys;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartfridge.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        CollectionReference recipe_DB = firestore.collection("users_recipes");

        keys.clear();
        giveMeKeys(keys);

        for (int i = 0; i < keys.size(); i++) {
            Log.d(TAG, "onCreate: "+ keys.get(i));
            DocumentReference docRef = firestore.collection("users_recipes").document(keys.get(i));
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists())
                        {
                            recipe_map = document.getData();
//                            convertURI(recipe_map);
                            recipes1.add(new recipe(Objects.requireNonNull(recipe_map.get("recipeName")).toString()
                                , Objects.requireNonNull(recipe_map.get("recipeTime")).toString()
                                , Objects.requireNonNull(recipe_map.get("recipeIngredients")).toString()
                                , Objects.requireNonNull(recipe_map.get("recipe")).toString()
                                , R.drawable.ic_kitchen
                                , Objects.requireNonNull(recipe_map.get("user")).toString()));

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




    public void convertURI(Map<String, Object> recipe_map){

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference pathReference = storageRef.child("/images/2023_01_12_09_00_18");
        //smart-fridge-c19d3.appspot.com/images/2023_01_11_17_06_18

        pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'images/my_image.jpg'
                String packageName = getApplicationContext().getPackageName();
                Resources resources = getApplicationContext().getResources();
                int drawableId = resources.getIdentifier(packageName + ":drawable/" + uri.getLastPathSegment(), null, null);


                Log.d(TAG, "onSuccess:&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&7"+packageName + ":drawable/" + uri.getLastPathSegment()+"&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&77");

                recipes1.add(new recipe(Objects.requireNonNull(recipe_map.get("recipeName")).toString()
                        , Objects.requireNonNull(recipe_map.get("recipeTime")).toString()
                        , Objects.requireNonNull(recipe_map.get("recipeIngredients")).toString()
                        , Objects.requireNonNull(recipe_map.get("recipe")).toString()
                        , R.drawable.ic_kitchen
                        , Objects.requireNonNull(recipe_map.get("user")).toString()));

                myrecyclerView = (RecyclerView)findViewById(R.id.recyclerView_id);

                myAdapter = new RecyclerViewAdapter(recipes_wind.this,recipes1);


                myrecyclerView.setLayoutManager(new GridLayoutManager(recipes_wind.this,1));


                myrecyclerView.setAdapter(myAdapter);


//                try {
//                    InputStream inputStream = getContentResolver().openInputStream(uri);
//                    String packageName = getApplicationContext().getPackageName();
//                    Resources resources = getApplicationContext().getResources();
//                    int drawableId = resources.getIdentifier(packageName + ":drawable/" + uri.getLastPathSegment(), null, null);
//                    Drawable myDrawable = resources.getDrawable(drawableId);
////                  myDrawable = Drawable.createFromStream(inputStream, uri.toString() );
//
//
//                    recipes1.add(new recipe(recipe_map.get("recipeName").toString()
//                            , recipe_map.get("recipeTime").toString()
//                            , recipe_map.get("recipeIngredients").toString()
//                            , recipe_map.get("recipe").toString()
//                            , drawableId));
//
//                    myrecyclerView = (RecyclerView)findViewById(R.id.recyclerView_id);
//
//                    myAdapter = new RecyclerViewAdapter(recipes_wind.this,recipes1);
//
//
//                    myrecyclerView.setLayoutManager(new GridLayoutManager(recipes_wind.this,1));
//
//
//                    myrecyclerView.setAdapter(myAdapter);
//
//
//                    // use the Drawable as you need
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

    }
}
