package com.example.smartfridge.accountDB;

import static android.content.ContentValues.TAG;

import android.util.Log;

import java.util.Objects;

public class customerLog {

    public boolean getPermission()
    {
       return true;
    }

    public void logIn(String email,String password)
    {
        JSONTask a = (JSONTask) new JSONTask().execute("http://10.0.2.2:8000/users/"+email+"/"+password);
        while (Objects.equals(a.res, "")){}
        Log.d(TAG, "onClick: " + a.res);
    }
}
