package com.bignerdranch.android.callblocker;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by derrek1 on 11/30/17.
 */

public class BlockerActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, BlockerActivity.class);
    }


    @Override
    protected Fragment createFragment() {
        return new BlockerFragment();
    }


    //move this to fragment

}


