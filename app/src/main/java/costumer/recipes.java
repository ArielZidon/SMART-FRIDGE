package costumer;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class recipes extends AppCompatActivity {
    MaterialButton read;
    FirebaseFirestore db;
    static ArrayList<String> recipes = new ArrayList<>();
    static ArrayList<String> recipesToDisplay = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        db = FirebaseFirestore.getInstance();
        read = findViewById(R.id.read);
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipes.clear();
                //giveMeKeys(recipes);
                recipes.add("milk,eggs,bread");
                recipes.add("eggs,milk,bread");
                for (String s : recipes) {
                DocumentReference docRef = db.collection("recipes").document(s);
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                                recipesToDisplay.add(s);
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
            }
            }
        });
        openRecipesMenu(recipesToDisplay);
    }


    public void openRecipesMenu(ArrayList <String> recipesDisplay) { //this function display the relevant recipes

        Intent i = new Intent(this, recipesDisplay.class);
        for (String s : recipesDisplay) {
            i.putExtra("recipes",s);
        }
        startActivity(i);
    }
}

