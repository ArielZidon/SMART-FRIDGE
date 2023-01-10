package com.example.smartfridge.admin;

public class admin_recipe {
    private String RecipeName;
    private String RecipeIngredientsTitle;
    private String RecipeIngredients;
    private String RecipeMethodTitle;
    private String Recipe;
    private String RecipeTime;
    private int Thumbnail;
    private Object key;
    private String user;

    public admin_recipe(String recipeName, String recipeTime , String recipeIngredients,String recipe, int thumbnail,Object key,String user){

        this.RecipeName = recipeName;
        this.RecipeTime = recipeTime;
        this.RecipeIngredientsTitle = "Ingredients:";
        this.RecipeIngredients = recipeIngredients;
        this.RecipeMethodTitle = "Method:";
        this.Recipe = recipe;
        this.Thumbnail = thumbnail;
        this.key = key;
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Object getKey() {
        return key;
    }

    public String getRecipeName() {
        return RecipeName;
    }

    public void setRecipeName(String recipeName) {
        RecipeName = recipeName;
    }

    public String getRecipeIngredientsTitle() {
        return RecipeIngredientsTitle;
    }

    public void setRecipeIngredientsTitle(String recipeIngredientsTitle) {
        RecipeIngredientsTitle = recipeIngredientsTitle;
    }

    public String getRecipeIngredients() {
        return RecipeIngredients;
    }

    public void setRecipeIngredients(String recipeIngredients) {
        RecipeIngredients = recipeIngredients;
    }

    public String getRecipeMethodTitle() {
        return RecipeMethodTitle;
    }

    public void setRecipeMethodTitle(String recipeMethodTitle) {
        RecipeMethodTitle = recipeMethodTitle;
    }

    public String getRecipe() {
        return Recipe;
    }

    public void setRecipe(String recipe) {
        Recipe = recipe;
    }

    public String getRecipeTime() {
        return RecipeTime;
    }

    public void setRecipeTime(String recipeTime) {
        RecipeTime = recipeTime;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}