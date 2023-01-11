package com.example.smartfridge.recipesDB;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.R;
import com.example.smartfridge.business_entities.IngredientsLists;
import com.example.smartfridge.ui.main.MainMenu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import com.example.smartfridge.business_entities.IngredientsLists;

public class insertRecipe extends AppCompatActivity {
    Button b1, b2,cancel;
    EditText recipeName, pTime, pOrder;
    FirebaseFirestore firestore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_to_cook);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        cancel = (Button) findViewById(R.id.cancel_button);
        recipeName = (EditText) findViewById(R.id.editText);
        pTime = (EditText) findViewById(R.id.editText2);
        pOrder = (EditText) findViewById(R.id.editText4);

        firestore = FirebaseFirestore.getInstance();
        CollectionReference Recipes_Db = firestore.collection("get_recipes"); //reference to the recipeObject collection

        //"Ingredients" button

        //don't enter the recipeObject, and start over
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatToCook();
            }
        });
        //"enter" button
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String collection = "get_recipes";
                DocumentReference docRef = firestore.collection(collection).document(IngredientsLists.namesArrayList.toString());
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            Map<String, Object> info_c = new HashMap<>();//creating hashmap for the document
                            //insert the data from the user to the hashmap
                            info_c.put("Instructions", pOrder.getText().toString());
                            info_c.put("Ingredients", IngredientsLists.inArrayList.toString());
                            info_c.put("Preparing Time", pTime.getText().toString());
                            info_c.put("Recipe Name", recipeName.getText().toString());
                            Recipes_Db.document(IngredientsLists.namesArrayList.toString()).set(info_c);//enter the hashmap to the document with the keys of the ingredients
                            Toast.makeText(insertRecipe.this, "recipeObject in", Toast.LENGTH_LONG).show();//message to the user
                            Log.d(TAG, "success to enter the recipeObject");
                            IngredientsLists.clearIngredients();//functions to clear the arrays in the IngredientsLists object
                            IngredientsLists.clearNames();

                            openWhatToCook();//open this page again
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }

                });
            }
        });
    }

    public void openWhatToCook() {
        Intent intent = new Intent(this, insertRecipe.class);
        startActivity(intent);
    }
    //open the "addIngredients" class and save the data that already entered to the intent
//    public void openAddIngredients() {
//        Intent intent = new Intent(this, add_Ingredients.class);
//        intent.putExtra("et1", recipeName.getText().toString());
//        intent.putExtra("et2", pTime.getText().toString());
//        intent.putExtra("et3", pOrder.getText().toString());
//        startActivityForResult(intent,1);
//    }

    /**take us back to the rest of the recipeObject*/
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}