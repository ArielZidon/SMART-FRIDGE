package com.example.smartfridge.business_logic;

import java.util.ArrayList;
import java.util.Arrays;

public class SortProducts {
    protected static ArrayList<String> keys = new ArrayList<>();
    protected static ArrayList<String> singel_key = new ArrayList<>();

    public static ArrayList<String> getKeys() {
        return keys;
    }

    public static void setKeys(ArrayList<String> keys) {
        SortProducts.keys = keys;
    }

    public static ArrayList<String> getSingel_key() {
        return singel_key;
    }

    public static void setSingel_key(ArrayList<String> singel_key) {
        SortProducts.singel_key = singel_key;
    }

    /**
     arr[]  ---> Input Array
     data[] ---> Temporary array to store current combination
     start & end ---> Starting and Ending indexes in arr[]
     index  ---> Current index in data[]
     r ---> Size of a combination to be printed
     */
    static void combinationUtil(String[] products, String[] groupOfProducts, int start,
                                int end, int index, int r) {
        // Current combination is ready to be key, sort it
        if (index == r) {
            singel_key.addAll(Arrays.asList(groupOfProducts));
            String key = sort(singel_key);//send the products to sort (a,b,c compering)
            keys.add(key);
            singel_key.clear();
            return;
        }
        /**
         * replace index with all possible elements. The condition
         "end-i+1 >= r-index" makes sure that including one element
         at index will make a combination with remaining elements
         at remaining positions
         */
        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            groupOfProducts[index] = products[i];
            combinationUtil(products, groupOfProducts, i + 1, end, index + 1, r);
        }
    }

    /**
     The main function that give us all combinations of size r (get_recipes with this size of products)
     in arr[] of size n. This function mainly uses combinationUtil()
     */
    public static void mixCombination(String[] products, int n, int r) {
        String[] TreesOfProducts = new String[r];
        combinationUtil(products, TreesOfProducts, 0, n - 1, 0, r);
    }

    /**
     * SORT
     * */
    static String sort(ArrayList<String> groupOfProducts) {
        //logic for sorting
        for (int i = 0; i < groupOfProducts.size() - 1; i++) {
            for (int j = i + 1; j < groupOfProducts.size(); j++) {
                //compares each elements of the array to all the remaining elements
                if (groupOfProducts.get(i).compareTo(groupOfProducts.get(j)) > 0) {
                    //swapping array elements
                    String temp = groupOfProducts.get(i);
                    groupOfProducts.set(i, groupOfProducts.get(j));
                    groupOfProducts.set(j, temp);
                }
            }
        }

    /**
     * CREATE THE KEY
     * */
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < groupOfProducts.size(); i++) {
            String groupOfProduct = groupOfProducts.get(i);
            key.append(groupOfProduct);
            if(i < groupOfProducts.size()-1)
                key.append(",");
        }
        return key.toString();
    }
    /**
    calling from get_recipes activity to get the keys the algorithm provide
     */
    public static void giveMeKeys(ArrayList<String> recipes)
    {
        recipes.addAll(keys);
        keys.clear();
    }
}
