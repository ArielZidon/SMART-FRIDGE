package com.example.smartfridge.costumer;

import android.view.Menu;

public interface category {

    void loadData();
    void homePage();
    void onBackPressed();
    void removeArray(String name, String count);
    void addCard(String name, String number);
    void buildDialog();
    void saveData(String name, String count);
    void openDryFood();
    void beckPage();
    void openMeat();
    void openMilaky();
    void openVegetables();
    void openClean();
    void loadName();
    void saveName(String str);
    void buildRename();


}
