package com.example.smartfridge.recipesDB;

import java.io.Serializable;

public class Ingredient implements Serializable {

    public String ingred;
    public String quantity;

    public Ingredient() {

    }

    public Ingredient(String ingred, String quantity) {
        this.ingred = ingred;
        this.quantity = quantity;
    }

    public String getIngredientName() {
        return ingred;
    }

    public void setIngredientName(String ingred) {
        this.ingred = ingred;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


}
