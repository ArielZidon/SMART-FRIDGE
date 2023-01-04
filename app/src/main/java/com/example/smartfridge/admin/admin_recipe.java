package com.example.smartfridge.admin;

import java.util.ArrayList;

public class admin_recipe {
    String name;
    ArrayList<String>listOfItem;
    String ingredients;
    String order;
    String preparingTime;

    public admin_recipe(String name, String ingredients, String order, String preparingTime) {
        this.name = name;
        this.listOfItem = new ArrayList<>();
        this.ingredients = ingredients;
        this.order = order;
        this.preparingTime = preparingTime;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getListOfItem() {
        return listOfItem;
    }

    public void setListOfItem(ArrayList<String> listOfItem) {
        this.listOfItem = listOfItem;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getPreparingTime() {
        return preparingTime;
    }

    public void setPreparingTime(String preparingTime) {
        this.preparingTime = preparingTime;
    }
}