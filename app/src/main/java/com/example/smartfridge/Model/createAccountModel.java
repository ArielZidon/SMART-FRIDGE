package com.example.smartfridge.Model;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.smartfridge.accountDB.createAccountActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class createAccountModel {
    private createAccountActivity activity;
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    String costumer_collection = "costumer_accounts";
    String managers_collection = "manager_accounts";
    CollectionReference costumers_DB;
    CollectionReference managers_DB;
    Map<String, Object> user_info = new HashMap<>();
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userType;


    public createAccountModel(createAccountActivity activity) {
        this.activity = activity;
        this.firestore = FirebaseFirestore.getInstance();
        this.costumers_DB = firestore.collection(costumer_collection);
        this.managers_DB = firestore.collection(managers_collection);
        this.auth = FirebaseAuth.getInstance();
    }

    public void setNewAccount(String userType, String user_name, String email, String password) {
        this.userType = userType;
        this.userName = user_name;
        this.userEmail = email;
        this.userPassword = password;
        Log.d(TAG, userType + userName +userEmail +userPassword);
        if (userEmail.equals("") || userPassword.length() < 5
                || userType.equals("") || userName.equals("")) {
            Toast.makeText(activity, "filled", Toast.LENGTH_SHORT).show();
        } else {
            if (userType.equals("Costumer")) {
                insertIntoCostumers();
            }
                //if user is a manager  + base terms to create a new manager account.
            else{
                insertIntoManagers();}

            }
        }
    public void insertIntoCostumers(){


        //this commend open a Document from our firestore cloud collection by a string collection path and document path.
        //if the Document does not exist it will create the document as the path name.
        DocumentReference docRef = firestore.collection(costumer_collection).document(userEmail.toString());
        //after we open or create the doc we will they to get and set the info.
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    //if get successful we will use a DocumentSnapshot to capture the data and read\set\..
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //if document exists we wont create it again to prevent duplicate.
                        Toast.makeText(activity, "The email: " + userEmail + " is registered in the system", Toast.LENGTH_SHORT).show();
                    } else {

                        //supposed to prevent authentication twice who at the second I delete the email from the authentication.
                        //not ideal but working.
                        if (!managers_DB.document(userEmail).get().isSuccessful()) {
                            //authentication.
                            Auto_user(userEmail, userPassword, userType);
                        }
                        //if document does not exists - open a map & insert user data to the map.

                        user_info.put("name", userName);
                        user_info.put("user_type", userType);
                        user_info.put("password", userPassword);
//                        if(auth != null) {
//                            user_info.put("Uid", Objects.requireNonNull(auth.getCurrentUser()).getUid());
//                        }                        //insert map into database by a document path.
                        costumers_DB.document(userEmail).set(user_info);
                        Toast.makeText(activity, "Sign-Up SUCCESSFUL.\nHELLO COSTUMER!", Toast.LENGTH_LONG).show();
                        Auto_login(userEmail, userPassword);
                        activity.openCostumers();
                    }
                } else {
                    //if failed to complete task.
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }
    public void insertIntoManagers(){
        String collection = "manager_accounts";
        DocumentReference docRef = firestore.collection(collection).document(userEmail);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Toast.makeText(activity, "The email: " + userEmail + " is registered in the system", Toast.LENGTH_SHORT).show();
                    } else {
                        if (!managers_DB.document(userEmail).get().isSuccessful()) {
                            Auto_user(userEmail, userPassword, userType);
                        }
                        user_info.put("name", userName);
                        user_info.put("user_type", userType);
                        user_info.put("password", userPassword);
//                        if(auth != null) {
//                            user_info.put("Uid", Objects.requireNonNull(auth.getCurrentUser()).getUid());
//                        }
                        //insert map into database by a document path.
                        managers_DB.document(userEmail).set(user_info);
                        Toast.makeText(activity, "SING-UP SUCCESSFUL.\nHELLO MANAGER!", Toast.LENGTH_LONG).show();
                        Auto_login(userEmail, userPassword);
                        activity.openManagers();
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    public void Auto_user( String email, String password, String user) {
        //create the user in the authentication on the firestore.
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    Objects.requireNonNull(auth.getCurrentUser()).sendEmailVerification();
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                    Toast.makeText(createAccount.this, "Authentication failed.",
//                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void Auto_login(String email, String password) {
        if (auth.getCurrentUser() == null) {
            FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmailAndPassword:success");
                                auth.getCurrentUser().sendEmailVerification();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmailAndPassword:failure", task.getException());
                            }
                        }
                    });
        }

    }
}
