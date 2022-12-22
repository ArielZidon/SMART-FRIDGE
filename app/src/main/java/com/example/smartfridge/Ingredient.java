package com.example.smartfridge;

import java.util.ArrayList;

public class Ingredient {
    public String InName;
    public String amount;
    static ArrayList<Ingredient> inArrayList ;
    static ArrayList<String> namesArrayList ;

    public Ingredient(){//empty constructor

    }
    ArrayList <Ingredient> newInArray(){
        inArrayList = new ArrayList<>();
        return inArrayList;
    }
    void clearIngredients(){
        inArrayList.clear();
    }

    public Ingredient(String name, String am){
        InName = name;
        amount = am;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "InName='" + InName + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }

    ArrayList <String> newNamesArray(){
        namesArrayList = new ArrayList<>();
        return namesArrayList;
    }
    public ArrayList<String> getNamesArrayList() {
        return namesArrayList;
    }
    void clearNames(){
        namesArrayList.clear();
    }

    public ArrayList<String> getNamesArray() {
        return namesArrayList;
    }
}
