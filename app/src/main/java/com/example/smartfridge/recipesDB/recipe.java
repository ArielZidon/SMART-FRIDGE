package com.example.smartfridge.recipesDB;

public class recipe {

    private String RecipeName;
    private String RecipeIngredientsTitle;
    private String RecipeIngredients;
    private String RecipeMethodTitle;
    private String Recipe;
    private String RecipeTime;
    private int Thumbnail;
    int flag;
    private String user;


    public recipe(String recipeName, String recipeTime , String recipeIngredients,String recipe, int thumbnail,String user){

        RecipeName = recipeName;
        RecipeTime = recipeTime;
        RecipeIngredientsTitle = "Ingredients:";
        RecipeIngredients = recipeIngredients;
        RecipeMethodTitle = "Method:";
        Recipe = recipe;
        Thumbnail = thumbnail;
        this.user = user;
    }
    public recipe(String recipeName, String recipeTime , String recipeIngredients,String recipe){

        RecipeName = recipeName;
        RecipeTime = recipeTime;
        RecipeIngredientsTitle = "Ingredients:";
        RecipeIngredients = recipeIngredients;
        RecipeMethodTitle = "Method:";
        Recipe = recipe;
        this.user = user;
    }


    public  String getRecipeName(){

        return RecipeName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
