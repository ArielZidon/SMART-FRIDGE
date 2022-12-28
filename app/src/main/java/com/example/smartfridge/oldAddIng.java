package com.example.smartfridge;

public class oldAddIng {
    //    Button b1, b2;
//    EditText inName, amount;
//    FirebaseFirestore firestore;
//    static int counter = 0;

//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_ingredient);
//
//        b1 = (Button) findViewById(R.id.button);
//        b2 = (Button) findViewById(R.id.button2);
//        inName = (EditText) findViewById(R.id.editText);
//        amount = (EditText) findViewById(R.id.editText2);
//        if(counter == 0) {
//
//            this.ingArray = ingredient.newInArray();
//            this.recipeKey = ingredient.newNamesArray();
//        }else{
//            this.ingArray = ingredient.getIngArray();
//            this.recipeKey = ingredient.getNamesArrayList();
//        }
//
//        b1.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                IngredientsLists ingredient = new IngredientsLists(inName.getText().toString(), amount.getText().toString());
//                ingArray.add(ingredient);
//                counter++;
//                Log.d(TAG,ingArray.toString());
//                openAddIngredients();
//            }
//        });
//
//
//        firestore = FirebaseFirestore.getInstance();
//        CollectionReference Recipes_Db = firestore.collection("recipes");
//        b2.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                IngredientsLists ingredient = new IngredientsLists(inName.getText().toString(), amount.getText().toString());
//                ingArray.add(ingredient);
//                String collection = "recipes";
//                recipeKey = ingredient.getNamesArrayList();
//                for (IngredientsLists i : ingArray) {
//                    recipeKey.add(i.InName);
//                }
//                Log.d(TAG,recipeKey.toString());
//                DocumentReference docRef = firestore.collection(collection).document(recipeKey.toString());
//                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                        if (task.isSuccessful()) {
//                            Log.d(TAG, ingArray.toString());
//                            Log.d(TAG, "success!!");
//
//                            openWhatToCook();
//
//                        } else {
//                            Log.d(TAG, "get failed with ", task.getException());
//
//                        }
//                    }
//                });
//            }
//        });
//
//    }
//    public void openAddIngredients(){
//        Intent intent = new Intent(this, add_Ingredients.class);
//        startActivity(intent);
//    }
//    public void openWhatToCook() {
////        Intent intent = new Intent(this, whatToCook.class);
//        Intent result = new Intent();
//        setResult(RESULT_OK,result);
//        finish();
//    }
//
//    /**take us back to the customers menu*/
//    @Override
//    public void onBackPressed() {
//        Log.d(TAG, ingArray.toString()); //for test only
//        Intent intent = new Intent(this, whatToCook.class);
//        startActivity(intent);
//    }
//}




}
