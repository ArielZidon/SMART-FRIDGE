package com.example.smartfridge;

import java.util.ArrayList;

public class SortProducts {
    protected static ArrayList<String> keys = new ArrayList<>();
    /* arr[]  ---> Input Array
     data[] ---> Temporary array to store current combination
     start & end ---> Starting and Ending indexes in arr[]
     index  ---> Current index in data[]
     r ---> Size of a combination to be printed */
    static void combinationUtil(String[] products, String[] groupOfProducts, int start,
                                int end, int index, int r) {
        // Current combination is ready to be printed, print it
        if (index == r) {
            String key = sort(groupOfProducts);
            keys.add(key);
            return;
        }
        // replace index with all possible elements. The condition
        // "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements
        // at remaining positions
        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            groupOfProducts[index] = products[i];
            combinationUtil(products, groupOfProducts, i + 1, end, index + 1, r);
        }
    }

    // The main function that prints all combinations of size r
    // in arr[] of size n. This function mainly uses combinationUtil()
    static void printCombination(String[] products, int n, int r) {
        String[] TreesOfProducts = new String[r];
        combinationUtil(products, TreesOfProducts, 0, n - 1, 0, r);
    }

//bad sort need to replace it!!!!!!
    /* *********************-----SORT-----********************** */
    private static String sort(String[] groupOfProducts) {
        int size = groupOfProducts.length;
        //logic for sorting
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < groupOfProducts.length; j++) {
                //compares each elements of the array to all the remaining elements
                if (groupOfProducts[i].compareTo(groupOfProducts[j]) > 0) {
                    //swapping array elements
                    String temp = groupOfProducts[i];
                    groupOfProducts[i] = groupOfProducts[j];
                    groupOfProducts[j] = temp;
                }
            }
        }
        /* *********************-----CREATE KEY-----********************** */
        StringBuilder key = new StringBuilder();
        for (String groupOfProduct : groupOfProducts) {
            key.append(groupOfProduct).append(",");
        }
        return key.toString();
    }

    public static void giveMeKeys(ArrayList<String> recipes)
    {
        recipes.addAll(keys);
        keys.clear();
    }
}

