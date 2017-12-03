package com.bignerdranch.android.callblocker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * Created by derrek1 on 11/30/17.
 */

public class BlockedCallerActivity extends SingleFragmentActivity {
    private static final String EXTRA_NUMBER = "blocked_number";
    private String blockedNumber;
    private ImageButton saveInfo;
    private BlockedCaller mBlockedCaller;
    private EditText mNameView;
    private EditText mNumberView;

    public static Intent newIntent(Context packageContext, String number, int position) {
        Intent intent = new Intent(packageContext, BlockedCallerActivity.class);
        intent.putExtra(EXTRA_NUMBER, number);
        return intent;
    }

        @Override
        protected Fragment createFragment(){
            return new BlockedCallerFragment();

    }
    public void returnResult(){
        setResult(Activity.RESULT_OK);
    }
}
