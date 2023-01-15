package com.example.smartfridge.recipesDB;

import static android.content.ContentValues.TAG;

import static com.example.smartfridge.business_logic.SortProducts.giveMeKeys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.smartfridge.R;
import com.example.smartfridge.business_entities.ModelClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;

public class recipes_wind extends AppCompatActivity {

    private FirebaseFirestore firestore;
    static ArrayList<String> keys = new ArrayList<>();

    RecyclerView myrecyclerView;
    RecyclerViewAdapter myAdapter;

    static List<recipe> recipes1 = new ArrayList<recipe>();
    static List<ModelClass> fullView = new ArrayList<ModelClass>();

    Map<String, Object> recipe_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_wind);


        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference pathReference = storageRef.child("images/2023_01_11_19_42_38.jpg");





        firestore = FirebaseFirestore.getInstance();//Initialization of the object firestore

        //CollectionReference object - open a database collection (if exist - open, else - create & open )
        CollectionReference recipe_DB = firestore.collection("recipe_DB");

//        recipe_map = new HashMap<>();
//        recipe_map.put("recipeName",null);
//        recipe_map.put("recipeTime",null);
//        recipe_map.put("recipeIngredients",null);
//        recipe_map.put("recipe",null);
//        recipe_map.put("status",null);

        keys.clear();
        giveMeKeys(keys);


        recipe_DB.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                for (int i = 0; i < keys.size(); i++) {
                                    recipes1.clear();
                                if (document.toString().contains("1")) {
//                                    Log.d(TAG, "convertURI: *******************************************"+recipe_map.get("image").toString()+"*************************************");
                                }
                                            convertURI(document.getData());
//                                }
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


//        for (int i = 0; i < keys.size(); i++) {
//            recipes1.clear();
//            DocumentReference docRef = firestore.collection("recipe_DB").document(keys.get(i));
//            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                    if (task.isSuccessful()) {
//                        DocumentSnapshot document = task.getResult();
//                        if (document.exists()) {
//                            recipe_map = document.getData();
//                            insertToDisplay(recipe_map);
//                            }
//                            else {
//                                Log.d(TAG, "document doesn't exist: no Result for  ");
//                            }
//                        }
//                        else {
//                            Log.d(TAG, "No such document");
//                        }
//
//                    }
//
//            });
//       }



//        recipes1.add(new recipe("Chicken Roll","20 min","200 gm chopped into cubes chicken" +
//                "1 medium chopped tomato" +
//                "1/2 pinch red chilli powder" +
//                "2 tablespoon vegetable oil" +
//                "1/2 cut into strips cucumber" +
//                "1/2 tablespoon chopped coriander leaves" +
//                "1 large thinly sliced onion" +
//                "2 medium chopped green chilli" +
//                "2 pinches garam masala powder" +
//                "1 lemon wedges" +
//                "1 teaspoon tomato ketchup" +
//                "1 tablespoon green chilli sauce",
//                "Chicken Roll is a delectable North Indian recipe made using all purpose flour, " +
//                        "stir-fried chicken, yoghurt and a variety of vegetables rolled into paranthas. On days you do not want to prepare an elaborate meal, this delectable dish can be a saviour. Rolls are quite popular across India and they are often a favourite evening snack. Egg Roll, Kathi Kebab Roll, Mutton Roll, Paneer Roll, Potato Roll and even Fish Roll are among its many variations. This easy roll recipe is made using chicken and has the unforgettable aroma of Indian spices. You can also utilize your leftover parathas and chapatis in making this recipe. If you do not like to use all-purpose flour or maida, you can make it with whole wheat flour too. In fact, it can be made even with leftover chicken. All you have to ensure is that you use the right amount of spices so that the flavour is not lost. Easy to pack and carry, you can also take it to office or prepare it for picnics and day trips. A must try roll recipe for all chicken lovers!\n"
//                ,R.drawable.chicken_roll));
//
//        recipes1.add(new recipe("Donut", "20 min","1 c. whole milk" +
//                "1/4 c. plus 1 tsp. granulated sugar, divided" +
//                "1 packet (or 2 1/4 tsp.) active dry yeast " +
//                "4 c. all-purpose flour, plus more if needed" +
//                "1/2 tsp. kosher salt" +
//                "6 tbsp. melted butter" +
//                "2 large eggs" +
//                "1/2 tsp. pure vanilla extract" +
//                "Canola or vegetable oil, for frying","\n" +
//                "Grease a large bowl with cooking spray and set aside. In a small, microwave-safe bowl or glass measuring cup, add milk. Microwave until lukewarm, 40 seconds. Add a teaspoon of sugar and stir to dissolve, then sprinkle over yeast and let sit until frothy, about 8 minutes.\n" +
//                "Make glaze: In a large bowl, whisk together milk, powdered sugar, and vanilla until smooth. Set aside.\n" +
//                "Line a large baking sheet with paper towels. In a large dutch oven over medium heat, heat 2'' oil to 350°. Cook doughnuts, in batches, until deeply golden on both sides, about 1 minute per side. Holes will cook even faster!\n" +
//                "Transfer doughnuts to paper towel-lined baking sheet to drain and cool slightly. Dip into glaze, then place onto a cooling rack (or eat immediately!)",R.drawable.donut1));
//        recipes1.add(new recipe("Dosa","20 min","3 cups rice" +
//                "1 cup urad daal (split, skinless black gram)" +
//                "3/4 teaspoon fenugreek seeds" +
//                "Salt (to taste)" +
//                "Vegetable / canola / sunflower cooking oil",
//                "Wash the rice and urad daal well. Add the fenugreek seeds to the mix and fill enough water in the rice-daal bowl to cover them about 2-inch deep. Soak overnight.\n" +
//                        "Put some cooking oil in a small bowl and keep ready. You will also need a bowl of ice cold water, a large, flat nonstick pan, 2 sheets of paper towel, a ladle, a spatula, and a basting brush.\n" +
//                        "When the upper surface begins to look cooked (it will no longer look soft or runny), flip the dosa. By this time, ideally, the surface that was underneath should be light golden in color. Cook for 1 minute after flipping.\n" +
//                        "The dosa is almost done. Fold it in half and allow to cook for 30 seconds more.",R.drawable.dosa1));
//        recipes1.add(new recipe("Pancake","20 min","1 1/4 cups milk" +
//                "1 egg" +
//                "3 tablespoons butter melted" +
//                "1 1/2 cups all-purpose flour" +
//                "3 1/2 teaspoons baking powder" +
//                "1 teaspoon salt" +
//                "1 tablespoon white sugar",
//                "In a large bowl, sift together the flour, baking powder, salt and sugar. Make a well in the center and pour in the milk, egg and melted butter; mix until smooth." +
//                        "Heat a lightly oiled griddle or frying pan over medium high heat. Pour or scoop the batter onto the griddle, using approximately 1/4 cup for each pancake. Brown on both sides and serve hot.",R.drawable.pancakes));
//        recipes1.add(new recipe("Pasta","20 min","1 tsp oil" +
//                "1 tsp butter" +
//                "2 clove garlic, finely chopped" +
//                "1 inch ginger, finely chopped" +
//                "½ onion, finely chopped" +
//                "1 cup tomato pulp" +
//                "¼ tsp turmeric / haldi" +
//                "½ tsp kashmiri red chilli powder" +
//                "2 tbsp tomato sauce" +
//                "½ tsp garam masala",
//                "firstly, saute 1 inch ginger and 2 clove garlic in 1 tsp oil and 1 tsp butter." +
//                        "further saute ½ onion till they turn soft." +
//                        "additionally add 1 cup tomato pulp and saute well." +
//                        "keep stirring till the tomato pulp thickens." +
//                        "now add ¼ tsp turmeric, ½ tsp chilli powder, ½ tsp garam masala and ½ tsp salt." +
//                        "saute till the spices gets cooked completely." +
//                        "now add 2 tbsp corn, ¼ capsicum, ¼ carrot, 2 tbsp peas and 7 florets broccoli. saute for a minute." +
//                        "add in 3 tbsp water and mix well." +
//                        "cover and simmer for 5 minutes." +
//                        "now add in 2 tbsp tomato sauce and ½ tsp mixed herbs. mix well." +
//                        "add in cooked pasta and mix gently till the sauce gets coated well." +
//                        "finally, serve masala pasta indian style hot topped with cheese if required.",R.drawable.pasta1));
//
//


//        myrecyclerView = (RecyclerView)findViewById(R.id.recyclerView_id);


//        myAdapter = new RecyclerViewAdapter(this,recipes1);
//
//
//        myrecyclerView.setLayoutManager(new GridLayoutManager(this,1));
//
//
//        myrecyclerView.setAdapter(myAdapter);


    }

//    public void imageDisplay(){
//        StorageReference storageRef =
//                FirebaseStorage.getInstance().getReference();
//        storageRef.child(recipe_map.get("image").toString()).getDownloadUrl()
//                .addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                    public void onSuccess(Uri uri) {
//                        // Got the download URL for 'users/me/profile.png'
//                    }
//                });
//    }
//
//        public static Drawable drawableFromUrl(String url) throws IOException {
//            Bitmap x;
//
//            StorageReference storageRef =
//                    FirebaseStorage.getInstance().getReference();
//
//            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//            connection.connect();
//            InputStream input = connection.getInputStream();
//
//            x = BitmapFactory.decodeStream(input);
//            return new BitmapDrawable(Resources.getSystem(), x);
//
//        }


    public void convertURI(Map<String, Object> recipe_map){

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference pathReference = storageRef.child("/images/2023_01_12_09_00_18");
        //smart-fridge-c19d3.appspot.com/images/2023_01_11_17_06_18

        pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'images/my_image.jpg'
                String packageName = getApplicationContext().getPackageName();
                Resources resources = getApplicationContext().getResources();
                int drawableId = resources.getIdentifier(packageName + ":drawable/" + uri.getLastPathSegment(), null, null);


                Log.d(TAG, "onSuccess:&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&7"+packageName + ":drawable/" + uri.getLastPathSegment()+"&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&77");

                recipes1.add(new recipe(recipe_map.get("recipeName").toString()
                        , recipe_map.get("recipeTime").toString()
                        , recipe_map.get("recipeIngredients").toString()
                        , recipe_map.get("recipe").toString()
                        , drawableId));

                myrecyclerView = (RecyclerView)findViewById(R.id.recyclerView_id);

                myAdapter = new RecyclerViewAdapter(recipes_wind.this,recipes1);


                myrecyclerView.setLayoutManager(new GridLayoutManager(recipes_wind.this,1));


                myrecyclerView.setAdapter(myAdapter);


//                try {
//                    InputStream inputStream = getContentResolver().openInputStream(uri);
//                    String packageName = getApplicationContext().getPackageName();
//                    Resources resources = getApplicationContext().getResources();
//                    int drawableId = resources.getIdentifier(packageName + ":drawable/" + uri.getLastPathSegment(), null, null);
//                    Drawable myDrawable = resources.getDrawable(drawableId);
////                  myDrawable = Drawable.createFromStream(inputStream, uri.toString() );
//
//
//                    recipes1.add(new recipe(recipe_map.get("recipeName").toString()
//                            , recipe_map.get("recipeTime").toString()
//                            , recipe_map.get("recipeIngredients").toString()
//                            , recipe_map.get("recipe").toString()
//                            , drawableId));
//
//                    myrecyclerView = (RecyclerView)findViewById(R.id.recyclerView_id);
//
//                    myAdapter = new RecyclerViewAdapter(recipes_wind.this,recipes1);
//
//
//                    myrecyclerView.setLayoutManager(new GridLayoutManager(recipes_wind.this,1));
//
//
//                    myrecyclerView.setAdapter(myAdapter);
//
//
//                    // use the Drawable as you need
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

    }

//    public void signIn(){
//        FirebaseAuth auth = FirebaseAuth.getInstance();
//        FirebaseUser user = auth.getCurrentUser();
//        if (user != null) {
//            user.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
//                public void onComplete(@NonNull Task<GetTokenResult> task) {
//                    if (task.isSuccessful()) {
//                        String idToken = task.getResult().getToken();
//                        // Sign in successful, proceed to access Firebase Storage
//                        // ...
//                    } else {
//                        // Handle error -> task.getException();
//                    }
//                }
//            });
//        } else {
//            // user not signed in, please sign in before trying to get a token
//            auth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if (task.isSuccessful()) {
//                        // Sign in successful
//                        FirebaseUser user = task.getResult().getUser();
//                        user.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
//                            public void onComplete(@NonNull Task<GetTokenResult> task) {
//                                if (task.isSuccessful()) {
//                                    String idToken = task.getResult().getToken();
//                                    // Sign in successful, proceed to access Firebase Storage
//                                    // ...
//                                } else {
//                                    // Handle error -> task.getException();
//                                }
//                            }
//                        });
//                    } else {
//                        // Handle error -> task.getException();
//                    }
//                }
//            });
//        }
//
//    }

    public void insertToDisplay(Map<String, Object> recipe_map){
//        if (recipe_map.get("image") == null) {
//            recipes1.add(new recipe(recipe_map.get("recipeName").toString()
//                    , recipe_map.get("recipeTime").toString()
//                    , recipe_map.get("recipeIngredients").toString()
//                    , recipe_map.get("recipe").toString()
//                    , R.drawable.no_IMAGE));
//
//        }
//        else {
            recipes1.add(new recipe(recipe_map.get("recipeName").toString()
                    , recipe_map.get("recipeTime").toString()
                    , recipe_map.get("recipeIngredients").toString()
                    , recipe_map.get("recipe").toString()
                    , R.drawable.chicken_roll));
//        recipes1.add(new recipe(recipe_map.get("recipeName").toString()
//                , recipe_map.get("recipeTime").toString()
//                , recipe_map.get("recipeIngredients").toString()
//                , recipe_map.get("recipe").toString()
//                , "2023_01_11_19_42_38"));
//        }

        myrecyclerView = (RecyclerView)findViewById(R.id.recyclerView_id);

        myAdapter = new RecyclerViewAdapter(recipes_wind.this,recipes1);


        myrecyclerView.setLayoutManager(new GridLayoutManager(recipes_wind.this,1));


        myrecyclerView.setAdapter(myAdapter);
    }
//    public static void throwAll(List<recipe> recipes1) {
//        recipes2.add(recipes1.get(0));
//    }

}
