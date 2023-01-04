package com.example.smartfridge;

/**
Retrieval. Update of the database based on the keys (commodities)
 we received from the user.
 */
public class KeyToTheRecipe {
    private String keyA;
    private String keyB;
    private String keyC;

    public KeyToTheRecipe(String keyA)
    {
        this.keyA = keyA;
        this.keyB = null;
        this.keyC = null;
    }

    public KeyToTheRecipe(String keyA,String keyB)
    {
        this.keyA = keyA;
        this.keyB = keyB;
        this.keyC = null;
    }

    public KeyToTheRecipe(String keyA,String keyB,String keyC)
    {
        this.keyA = keyA;
        this.keyB = keyB;
        this.keyC = keyC;
    }

    /**
    Generating the key by accepting objects from the user and
    turning them into a long string that is a key
    */
    public String createKey()
    {
        if (this.keyB == null && this.keyC == null)
            return this.keyA;
        else if (this.keyC == null)
            return this.keyA + this.keyB;
        else return this.keyA + this.keyB + this.keyC;
    }

    public String getKeyA() {
        return keyA;
    }

    public String getKeyB() {
        return keyB;
    }

    public String getKeyC() {
        return keyC;
    }

    public void setKeyA(String keyA) {
        this.keyA = keyA;
    }

    public void setKeyB(String keyB) {
        this.keyB = keyB;
    }

    public void setKeyC(String keyC) {
        this.keyC = keyC;
    }
}