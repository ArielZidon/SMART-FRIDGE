package com.example.smartfridge;

public class recipe {

    private String RecipeName;
    private String RecipeIngredients;
    private String RecipeMethodTitle;
    private String Recipe;
    private String Time;

    public recipe(String name, String recipeIngredients, String recipeMethodTitle,String recipe, String Time){

        RecipeName = name;
        RecipeIngredients = recipeIngredients;
        RecipeMethodTitle = recipeMethodTitle;
        Recipe = recipe;
        Time = Time;

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

}
