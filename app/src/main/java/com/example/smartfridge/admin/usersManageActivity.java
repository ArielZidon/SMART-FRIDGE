package com.example.smartfridge.admin;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartfridge.Model.usersManageModel;
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
import java.util.Objects;

public class usersManageActivity extends AppCompatActivity {
    usersManageModel model = new usersManageModel(this);
    private FirebaseFirestore firestore;
    static List<user> users_list = new ArrayList<>();
    Map<String, Object> users_map;
    RecyclerView cardView;
    admin_user_Adapter Adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_mange);
        cardView = (RecyclerView) findViewById(R.id.recyclerView_id);
        /**bring the user card visibility*/
        model.setUsers(cardView);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, adminView.class);
        startActivity(intent);
    }
}