package costumer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.R;
import com.google.android.material.button.MaterialButton;

public class customer_USER extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_user);

        TextView username =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
//        activity_customer_user.xml
        //admin and admin

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("") && password.getText().toString().equals("")){
                    //correct
                    Toast.makeText(customer_USER.this,"LOGIN SUCCESSFUL.\nHELLO COSTUMER!",Toast.LENGTH_SHORT).show();
                    openUploadRecipes();
                }else
                    //incorrect
                    Toast.makeText(customer_USER.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void openUploadRecipes() {
        Intent intent = new Intent(this, costumers.class);
        startActivity(intent);
    }
}