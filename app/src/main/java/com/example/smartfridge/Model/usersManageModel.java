package com.example.smartfridge.Model;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartfridge.R;
import com.example.smartfridge.admin.adminAdapter;
import com.example.smartfridge.admin.admin_recipe;
import com.example.smartfridge.admin.admin_user_Adapter;
import com.example.smartfridge.admin.user;
import com.example.smartfridge.admin.usersManageActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class usersManageModel {
    usersManageActivity activity;
    FirebaseFirestore db;
    String collection_name = "costumer_accounts";
    CollectionReference costumerCollection;
    static List<user> users_list = new ArrayList<>();
    Map<String, Object> users_map;
    RecyclerView cardView;
    admin_user_Adapter Adapter;

    public usersManageModel(usersManageActivity activity){
        this.activity = activity;
        this.db = FirebaseFirestore.getInstance();
        this.costumerCollection = db.collection(collection_name);

    }
    /**adapter to the admin that can see the users*/
    public void setUsers(RecyclerView cardView){
        this.cardView = cardView;
        costumerCollection.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        users_list.clear();
                        List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot document : documents) {
                            Log.d(TAG, "onSuccess: "+ Objects.requireNonNull(document.getData()).get("password"));
                            users_map = document.getData();
                            if (users_map != null) {
                                String name = ((String) users_map.get("recipeName"));
                                String password = (String) users_map.get("password");

                                users_list.add(new user(name,
                                        document.getId()
                                        , password));
                            }




                            Adapter = new admin_user_Adapter(activity, users_list);

                            cardView.setLayoutManager(new GridLayoutManager(activity, 1));

                            cardView.setAdapter(Adapter);
                        }
                    }
                });
    }
        //        Log.d(TAG, "onSuccess: "+ recipes_list);

        /**delete user from the DB*/
        public static void deleteUsers(user user){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Get a reference to the document you want to delete
        DocumentReference docRef = db.collection("costumer_accounts").document(user.getEmail().toString());
        // Delete the document
        docRef.delete();
        Log.d(TAG, "deleteRecipes: "+ user.getEmail());
    }


}
