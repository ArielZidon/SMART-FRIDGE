package com.example.smartfridge;

import android.provider.MediaStore;

public class recipe {

    private String RecipeName;
    private String RecipeIngredients;
    private String RecipeMethodTitle;
    private String Recipe;
    private String Time;
    private int Thumbnail;


    public recipe(String name, String Time , String recipeIngredients, String recipeMethodTitle,String recipe, int thumbnail){

        RecipeName = name;
        Time = Time;
        RecipeIngredients = recipeIngredients;
        RecipeMethodTitle = recipeMethodTitle;
        Recipe = recipe;
        Thumbnail = thumbnail;

    }

    public  String getRecipeName(){

        return RecipeName;
    }
    public String getRecipeIngredients(){
        return RecipeIngredients;
    }

    public String getRecipeMethodTitle(){
        return RecipeMethodTitle;
    }

    public String getRecipe(){
        return Recipe;
    }

    public String getTime(){
        return Time;
    }

    public int getThumbnail(){
        return Thumbnail;
    }

}
