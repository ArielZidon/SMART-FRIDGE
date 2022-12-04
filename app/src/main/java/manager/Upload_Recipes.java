package manager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartfridge.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Upload_Recipes extends AppCompatActivity {

    FirebaseFirestore firestore; //item that represent the DB firestore
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_recipes);

        firestore = FirebaseFirestore.getInstance();//Initialization of the object firestore

        CollectionReference accounts = firestore.collection("accounts");

        Map<String, Object> data1 = new HashMap<>();
        data1.put("Name", "ariel");
        data1.put("Last Name", "Zidon");
        data1.put("Country", "israel");
        data1.put("Password","Acb97531!!");
        accounts.document("314789264").set(data1);

        Map<String, Object> data2 = new HashMap<>();
        data2.put("Name", "eran");
        data2.put("Last Name", "tzarom");
        data2.put("Country", "israel");
        data2.put("Password","1234565rtA");
        accounts.document("20898392").set(data2);

        Map<String, Object> data3 = new HashMap<>();
        data3.put("Name", "ofir");
        data3.put("Last Name", "regev");
        data3.put("Country", "israel");
        data3.put("Password","12345");
        accounts.document("284618492").set(data3);

        Map<String, Object> data4 = new HashMap<>();
        data4.put("Name", "afik");
        data4.put("Last Name", "damri");
        data4.put("Country", "israel");
        data4.put("Password","123 :(");
        accounts.document("208567198").set(data4);
    }
}