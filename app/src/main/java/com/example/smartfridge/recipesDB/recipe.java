package com.example.smartfridge.recipesDB;

import com.example.smartfridge.R;

public class recipe {

    private String RecipeName;
    private String RecipeIngredientsTitle;
    private String RecipeIngredients;
    private String RecipeMethodTitle;
    private String Recipe;
    private String RecipeTime;
    private int Thumbnail;


    public recipe(String recipeName, String recipeTime , String recipeIngredients,String recipe, int thumbnail){

        RecipeName = recipeName;
        RecipeTime = recipeTime;
        RecipeIngredientsTitle = "Ingredients:";
        RecipeIngredients = recipeIngredients;
        RecipeMethodTitle = "Method:";
        Recipe = recipe;
        Thumbnail = thumbnail;

    }


    public  String getRecipeName(){

        return RecipeName;
    }

    public String getRecipeIngredientsTitle(){
        return RecipeIngredientsTitle;
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
        return RecipeTime;
    }

    public int getThumbnail(){
        return Thumbnail;
    }

}
