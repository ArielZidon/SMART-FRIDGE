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

    public admin_recipe(String recipeName, String recipeTime , String recipeIngredients,String recipe, int thumbnail,Object key){

        RecipeName = recipeName;
        RecipeTime = recipeTime;
        RecipeIngredientsTitle = "Ingredients:";
        RecipeIngredients = recipeIngredients;
        RecipeMethodTitle = "Method:";
        Recipe = recipe;
        Thumbnail = thumbnail;
        this.key = key;
    }
    public admin_recipe(String recipeName, String recipeTime , String recipeIngredients,String recipe,Object key){

        RecipeName = recipeName;
        RecipeTime = recipeTime;
        RecipeIngredientsTitle = "Ingredients:";
        RecipeIngredients = recipeIngredients;
        RecipeMethodTitle = "Method:";
        Recipe = recipe;
        this.key = key;
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