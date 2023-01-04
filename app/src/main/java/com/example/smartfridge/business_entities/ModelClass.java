package com.example.smartfridge.business_entities;

public class ModelClass {
    public String itemName;
    public String itemNumber;


    public ModelClass (String item, String number){
        this.itemName = item;
        this.itemNumber = number;
    }

    public String getItemName(){
        return itemName;
    }
    public String getItemNumber(){
        return itemNumber;
    }
    public String toString(){
        return "name: " + itemName + ",amount: "+ itemNumber;
    }
}
