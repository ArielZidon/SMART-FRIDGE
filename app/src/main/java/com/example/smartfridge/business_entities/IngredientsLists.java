package com.example.smartfridge.business_entities;

import java.util.ArrayList;

//This class meant to save the data of the ingredients in arrays
public class IngredientsLists {
    public static ArrayList<ModelClass> inArrayList;//arraylist of the the ingredients object (name and amount)
    public static ArrayList<String> namesArrayList;//arraylist only of the names of the ingredients - this for the recipeObject key

//    create empty arrays
    public IngredientsLists(){
        namesArrayList = new ArrayList<String>();
        inArrayList = new ArrayList<ModelClass>();
    }
    //constructor
    public IngredientsLists(ArrayList<String> a, ArrayList<ModelClass> b){
        namesArrayList = a;
        inArrayList = b;
    }
    //constructor
    public IngredientsLists(ArrayList<ModelClass> a ){
        inArrayList = a;

    }
    //gets functions
    public static ArrayList<String> getNamesArrayList() {
        return IngredientsLists.namesArrayList;
    }
    public static ArrayList<ModelClass> getIngArray() {
        return IngredientsLists.inArrayList;
    }



    public static void setInArrayList(ArrayList<ModelClass> inArrayList) {
        IngredientsLists.inArrayList = inArrayList;
    }

    public static void setNamesArrayList(ArrayList<String> namesArrayList) {
        IngredientsLists.namesArrayList = namesArrayList;
    }

    //clear arraylists
    public static void clearIngredients(){
        inArrayList.clear();
    }
    public static void clearNames(){
        namesArrayList.clear();
    }
}
