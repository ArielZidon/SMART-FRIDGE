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
    void nextPage();


}
