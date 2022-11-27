package com.example.smartfridge;

public class SortProducts {

    /* arr[]  ---> Input Array
     data[] ---> Temporary array to store current combination
     start & end ---> Starting and Ending indexes in arr[]
     index  ---> Current index in data[]
     r ---> Size of a combination to be printed */
    static void combinationUtil(String[] products, String[] TreesOfProducts, int start,
                                int end, int index, int r) {
        // Current combination is ready to be printed, print it
        if (index == r) {
            sort(TreesOfProducts);

            return;
        }
        // replace index with all possible elements. The condition
        // "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements
        // at remaining positions
        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            TreesOfProducts[index] = products[i];
            combinationUtil(products, TreesOfProducts, i + 1, end, index + 1, r);
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
    private static void sort(String[] TreesOfProducts) {
        int size = TreesOfProducts.length;
        //logic for sorting
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < TreesOfProducts.length; j++) {
                //compares each elements of the array to all the remaining elements
                if (TreesOfProducts[i].compareTo(TreesOfProducts[j]) > 0) {
                    //swapping array elements
                    String temp = TreesOfProducts[i];
                    TreesOfProducts[i] = TreesOfProducts[j];
                    TreesOfProducts[j] = temp;
                }
            }
        }
    }


    /* *********************-----CREATE KEY-----********************** */
    public static void createKey(String[] TreesOfProducts) {
        StringBuilder key = new StringBuilder();
        for (String treesOfProduct : TreesOfProducts) {
            key.append(treesOfProduct);
            key.append(" ");
        }
    }
}

