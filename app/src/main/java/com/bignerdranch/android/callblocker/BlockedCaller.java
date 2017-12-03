package com.bignerdranch.android.callblocker;

/**
 * Created by derrek1 on 11/30/17.
 */

public class BlockedCaller {

    private String mName;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        mNumber = number;
    }

    private String mNumber;
}
