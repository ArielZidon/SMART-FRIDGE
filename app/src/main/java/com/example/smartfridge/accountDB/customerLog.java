package com.example.smartfridge.accountDB;

public class customerLog {
    String result;

    public customerLog(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean getPermission()
    {
       return true;
    }

    public char logIn()
    {
        return this.getResult().charAt(this.getResult().length()-3);
    }
}
