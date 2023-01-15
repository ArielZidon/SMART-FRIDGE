package com.example.smartfridge.accountDB;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.R;
import com.example.smartfridge.admin.adminView;
import com.example.smartfridge.ui.main.MainActivity;
import com.example.smartfridge.ui.main.MainMenu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class customer_user extends AppCompatActivity {

    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    public static String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_user);

        firestore = FirebaseFirestore.getInstance();//Initialization of the object firestore
        auth = FirebaseAuth.getInstance();//Initialization of the object


        TextView email =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        MaterialButton forgotpass = (MaterialButton) findViewById(R.id.forgotpass);


        //open MaterialButton loginbtn - 0n click domain
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public synchronized void onClick(View view) {
                char state;
                if (password.getText().toString().contains("SFmanager"))
                {
                    if (email.getText().toString().equals(""))
                        Toast.makeText(customer_user.this, "TSDhe email: " + email.getText().toString() + " is NOT registered in the system", Toast.LENGTH_SHORT).show();
                    else {
                        /**the URL is for the emolator/phone this is way its 10.0.2.2 */
                        JSONTask connect = (JSONTask) new JSONTask().execute("http://10.0.2.2:8000/admin/" + email.getText().toString() + "/" + password.getText().toString());
                        while (Objects.equals(connect.res, "")) {}
                        customerLog logTrying = new customerLog(connect.res);
                        state = logTrying.logIn();
                        switch (state) {
                            /**swich case -
                             * 0: LOGIN ADMIN SUCCESSFUL
                             * 1: LOGIN ADMIN FAILED Incorrect Password
                             * 2: LOGIN ADMIN FAILED user Does Not Exist
                             * */
                            case '0':
                                userName = email.getText().toString();
                                Toast.makeText(customer_user.this, "LOGIN ADMIN SUCCESSFUL.\nHELLO " + userName + "! \uD83D\uDE03", Toast.LENGTH_SHORT).show();
                                openManager();
                                break;
                            case '1':
                                Toast.makeText(customer_user.this, "LOGIN ADMIN FAILED.\n" + "Incorrect Password! \uD83D\uDE1F", Toast.LENGTH_SHORT).show();
                                break;
                            case '2':
                                Toast.makeText(customer_user.this, "LOGIN ADMIN FAILED.\n" + userName + "Does Not Exist! \uD83E\uDD37\u200D♂️", Toast.LENGTH_SHORT).show();
                                break;
                        }

                    }

                }
                else {
                    if (email.getText().toString().equals("") || password.getText().toString().equals(""))
                        Toast.makeText(customer_user.this, "TSDhe email: " + email.getText().toString() + " is NOT registered in the system", Toast.LENGTH_SHORT).show();
                    else {
                        JSONTask connect = (JSONTask) new JSONTask().execute("http://10.0.2.2:8000/users/" + email.getText().toString() + "/" + password.getText().toString());
                        while (Objects.equals(connect.res, "")) {}
                        customerLog logTrying = new customerLog(connect.res);
                        state = logTrying.logIn();
                        switch (state) {
                            /**swich case -
                             * 0: LOGIN ADMIN SUCCESSFUL
                             * 1: LOGIN ADMIN FAILED Incorrect Password
                             * 2: LOGIN ADMIN FAILED manger Does Not Exist
                             * */
                            case '0':
                                userName = email.getText().toString();
                                Toast.makeText(customer_user.this, "LOGIN SUCCESSFUL.\nHELLO " + userName + "! \uD83D\uDE03", Toast.LENGTH_SHORT).show();
                                openCostumers();
                                break;
                            case '1':
                                Toast.makeText(customer_user.this, "LOGIN FAILED.\n" + "Incorrect Password! \uD83D\uDE1F", Toast.LENGTH_SHORT).show();
                                break;
                            case '2':
                                Toast.makeText(customer_user.this, "LOGIN FAILED.\n" + userName + "Does Not Exist! \uD83E\uDD37\u200D♂️", Toast.LENGTH_SHORT).show();
                                break;
                        }

                    }
                }

            }
        });



        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to - create_account wind..
                openForgotpass();
            }
        });;
    }



        public void Auto_login (TextView email,TextView password) {
            if (auth.getCurrentUser() == null) {
                FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(customer_user.this, new OnCompleteListener<AuthResult>() {
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

        public void  openCostumers(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    public void  openManager(){
        Intent intent = new Intent(this, adminView.class);
        startActivity(intent);
    }

    public void  openForgotpass(){
        Intent intent = new Intent(this, forgot_password.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
}
}


