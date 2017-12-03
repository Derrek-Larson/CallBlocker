package com.bignerdranch.android.callblocker;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by derrek1 on 11/30/17.
 */

public class CallCenter {
    public static CallCenter mCallcenter;
    private List<BlockedCaller> mCallers;

    public static CallCenter get (Context context){
        if (mCallcenter == null) {
            mCallcenter = new CallCenter(context);
        }
        return mCallcenter;
    }

    private CallCenter(Context context){
        mCallers = new ArrayList<>();
        for(int i = 0; i<100; i++){
            BlockedCaller caller = new BlockedCaller();
            caller.setName("name"+i);
            caller.setNumber(i+"9208095006");
            mCallers.add(caller);
        }
    }
    public List getBlockedCallers(){
        return mCallers;
    }
    public BlockedCaller getCaller(String number){
        for (int i = 0; i<mCallers.size(); i++){
            if (mCallers.get(i).getNumber()==number){
                return mCallers.get(i);
            }
        }BlockedCaller newCaller = new BlockedCaller();
        return newCaller;
    }
    public void addCaller(BlockedCaller caller){
        mCallers.add(caller);
    }


}
