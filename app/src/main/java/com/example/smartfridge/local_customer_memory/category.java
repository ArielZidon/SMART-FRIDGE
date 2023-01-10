package com.example.smartfridge.local_customer_memory;

import android.view.Menu;

public interface category
{
    void loadData();
    void homePage();
    void onBackPressed();
    void removeArray(String name, String count);
    void addCard(String name, String number);

    void loadName();

    void saveName(String str);

    void buildRename();



    void buildDialog();
    void saveData(String name, String count);
    void nextPage();
}