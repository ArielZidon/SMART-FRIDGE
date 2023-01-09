package com.example.smartfridge.recipesDB;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.R;
import com.example.smartfridge.ui.main.MainMenu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class insert_recipe extends AppCompatActivity {

    private FirebaseFirestore firestore;

    private int IMAGE;
    ImageView imgGallery;
    LinearLayout layoutList;
    Button add;
    Button submit_list;
    String recipe_ingredients = "";
    String DocumentName = "";
    ArrayList<Ingredient> ingredients_List = new ArrayList<>();
    Map<String, Object> new_recipe = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_recipe);

        firestore = FirebaseFirestore.getInstance();//Initialization of the object firestore

        //CollectionReference object - open a database collection (if exist - open, else - create & open )
        CollectionReference recipe_DB = firestore.collection("recipe_DB");

        TextView recipe_name = (TextView) findViewById(R.id.recipe_name);
        TextView recipe_time = (TextView) findViewById(R.id.recipe_time);
        TextView recipe_instructions = (TextView) findViewById(R.id.recipe_instructions);

        layoutList = (LinearLayout) findViewById(R.id.layout_list);
        add = (Button) findViewById(R.id.button_add);
        submit_list = (Button) findViewById(R.id.button_submit_list);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addView();
            }
        });



        submit_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfValidAndRead()){
                    recipe_ingredients = "";
                    for (int i = 0; i <ingredients_List.size() ; i++) {
                        insert_ingredients(ingredients_List.get(i).getIngredientName(),ingredients_List.get(i).getQuantity());
                    }

                    sort_ingredients();
                    DocumentName = "";
                    for (int i = 0; i < ingredients_List.size(); i++) {
                        documentName(ingredients_List.get(i).getIngredientName());
                    }
                    Log.d(TAG, "onClick: ********************"+DocumentName+"******************");
                    DocumentReference docRef = firestore.collection("recipe_DB").document(DocumentName);
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    //if document exists we wont create it again to prevent duplicate.
                                    Toast.makeText(insert_recipe.this, "This This recipe exists - add of change ingredient", Toast.LENGTH_SHORT).show();
                                }
                                else {

                                    new_recipe.put("recipeName",recipe_name.getText().toString());
                                    new_recipe.put("recipeTime",recipe_time.getText().toString());
                                    new_recipe.put("recipeIngredients",recipe_ingredients);
                                    new_recipe.put("recipe",recipe_instructions.getText().toString());
                                    new_recipe.put("status","Unapproved recipe");

                                    Toast.makeText(insert_recipe.this,"Your RECIPE sent to admin!",Toast.LENGTH_LONG).show();
                                    recipe_DB.document(DocumentName).set(new_recipe);
                                    openMainMenu();
                                }
                            }
                            else Toast.makeText(insert_recipe.this,"sent to admin has failed!",Toast.LENGTH_LONG).show();
                        }
                    });

                }
                else {Toast.makeText(insert_recipe.this,"Unsuccessful!",Toast.LENGTH_LONG).show();}

                //****need to send to database;
            }
        });


        imgGallery = (ImageView) findViewById(R.id.img_gallery);
        Button open_gallery = (Button) findViewById(R.id.upload_image_bt);

        open_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent(Intent.ACTION_PICK);
                gallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery,IMAGE);

                Toast.makeText(insert_recipe.this,"Your IMAGE is upload!",Toast.LENGTH_LONG).show();

                new_recipe.put("image",IMAGE);

//                ArrayList<Integer> recipe_image = new ArrayList<>();
//                recipe_image.add(IMAGE);

                //***NEED INSERT IMAGE TO DATABASE
            }
        });
    }

    private boolean checkIfValidAndRead() {
        ingredients_List.clear();
        boolean result = true;

        for (int i = 0; i < layoutList.getChildCount(); i++) {

            View cricketerView = layoutList.getChildAt(i);

            EditText ingred = (EditText)cricketerView.findViewById(R.id.edit_ingredient_name);
            EditText quantity = (EditText)cricketerView.findViewById(R.id.quantity);

            Ingredient Ingredient = new Ingredient();

            if (!ingred.getText().toString().equals("")) {
                Ingredient.setIngredientName(ingred.getText().toString());
            }
            else {
                result = false;
                break;
            }

            if (!quantity.getText().toString().equals("")) {
                Ingredient.setQuantity(quantity.getText().toString());
            }
            else {
                result = false;
                break;
            }

            ingredients_List.add(Ingredient);

        }

        if (ingredients_List.size() == 0) {
            result = false;
            Toast.makeText(this, "Add Cricketers First!", Toast.LENGTH_SHORT).show();
        }

        else if(!result){
            Toast.makeText(this, "Enter All Details Correctly!", Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    private void addView(){

        final View cricketerView = getLayoutInflater().inflate(R.layout.activity_row_add_cricketer,null,false);

        EditText ingred = (EditText)cricketerView.findViewById(R.id.edit_ingredient_name);
        EditText quantity = (EditText)cricketerView.findViewById(R.id.quantity);
        ImageView imageClose = (ImageView)cricketerView.findViewById(R.id.image_remove);
        
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item);

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(cricketerView);
            }
        });

        layoutList.addView(cricketerView);

    }

    private void sort_ingredients(){
        Collections.sort(ingredients_List, new Comparator<Ingredient>() {
            @Override
            public int compare(Ingredient s1, Ingredient s2) {
                return s1.getIngredientName().compareToIgnoreCase(s2.getIngredientName());
            }
        });
    }

    private void insert_ingredients(String ingredient,String quantity){
        if (recipe_ingredients == "") {
            recipe_ingredients = ingredient;
            recipe_ingredients += (" " + quantity + "," + "\n");
        }
        else {
            recipe_ingredients += ingredient;
            recipe_ingredients += (" " + quantity + "," + "\n");
        }
    }

    private void documentName (String ingredient){
        if (DocumentName.equals("")) {
            DocumentName = ingredient;
        }
        else {
            DocumentName+=("," + ingredient);
        }
    }

    private void removeView(View view){

        layoutList.removeView(view);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK) {

            if (requestCode == IMAGE) {
                //FOR GALLERY
                imgGallery.setImageURI(data.getData());
            }

        }
    }

    public void openMainMenu(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}