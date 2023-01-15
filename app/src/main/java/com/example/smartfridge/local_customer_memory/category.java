package com.example.smartfridge.local_customer_memory;

public interface category
{
    void loadData();

    void removeArray(String name, String count);

    void saveData(String name, String count);
}