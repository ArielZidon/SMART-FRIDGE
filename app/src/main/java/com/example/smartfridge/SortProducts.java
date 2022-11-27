package com.example.smartfridge;

public class SortProducts {
        static int count = 0;
   /* arr[]  ---> Input Array
    data[] ---> Temporary array to store current combination
    start & end ---> Starting and Ending indexes in arr[]
    index  ---> Current index in data[]
    r ---> Size of a combination to be printed */

        static void combinationUtil(Object[] products, Object[] TreesOfProducts, int start,
                                    int end, int index, int r)
        {
            // Current combination is ready to be printed, print it
            if (index == r)
            {
                /*
                for (int j=0; j<r; j++)
                    System.out.print(TreesOfProducts[j]+" ");
                System.out.println("");
                count++;
                */
                return;
            }

            // replace index with all possible elements. The condition
            // "end-i+1 >= r-index" makes sure that including one element
            // at index will make a combination with remaining elements
            // at remaining positions
            for (int i=start; i<=end && end-i+1 >= r-index; i++)
            {
                TreesOfProducts[index] = products[i];
                combinationUtil(products, TreesOfProducts, i+1, end, index+1, r);
            }
        }

        // The main function that prints all combinations of size r
        // in arr[] of size n. This function mainly uses combinationUtil()
        static void printCombination(Object[] products, int n, int r)
        {
            // A temporary array to store all combination one by one
            Object[] TreesOfProducts = new Object[r];

            // Print all combination using temporary array 'data[]'
            combinationUtil(products, TreesOfProducts, 0, n-1, 0, r);
        }
/*
        public static void main (String[] args) {
            Object[] items;
            items = new Object[20];
            items[0] = "שום";
            items[1] = "ביצים";
            items[2] = "בשר טחון";
            items[3] = "מיץ לימון";
            items[4] = "שוקולד מריר";
            items[5] = "חלב";
            items[6] = "כוסברה";
            items[7] = "פטרוזיליה";
            items[8] = "דגים";
            items[9] = "תפוחי אדמה";
            items[10] = "גזר";
            items[11] = "גמבה";
            items[12] = "פלפל ירוק";
            items[13] = "שמנת מתוקה";
            items[14] = "קישוא";
            items[15] = "בצל";
            items[16] = "בצל סגול";
            items[17] = "עגבניות";
            items[18] = "כרעיים";
            items[19] = "אבקת סוכר";

            printCombination(items, items.length, 5);
            printCombination(items, items.length, 4);
            printCombination(items, items.length, 3);

            System.out.println("sum: "+count);
        }
*/
    }


