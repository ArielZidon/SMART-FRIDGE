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

public class usersManage extends AppCompatActivity {

    private FirebaseFirestore firestore;
    static List<user> users_list = new ArrayList<>();
    Map<String, Object> users_map;
    RecyclerView cardView;
    admin_user_Adapter Adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_mange);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference recipeCollection = db.collection("costumer_accounts");
        recipeCollection.get()
            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    users_list.clear();
                    List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot document : documents) {
                        Log.d(TAG, "onSuccess: "+document.getData().get("password"));
                        users_map = document.getData();
                        users_list.add(new user(users_map.get("name").toString(),
                                document.getId()
                                , users_map.get("password").toString()));

                        cardView = (RecyclerView) findViewById(R.id.recyclerView_id);

                        Adapter = new admin_user_Adapter(usersManage.this, users_list);

                        cardView.setLayoutManager(new GridLayoutManager(usersManage.this, 1));

                        cardView.setAdapter(Adapter);
                    }
                }
            });
    }

    private void editUsers(){}

    protected static void deleteUsers(user user){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Get a reference to the document you want to delete
        DocumentReference docRef = db.collection("costumer_accounts").document(user.getEmail().toString());
        // Delete the document
        docRef.delete();
        Log.d(TAG, "deleteRecipes: "+ user.getEmail());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, adminView.class);
        startActivity(intent);
    }
}