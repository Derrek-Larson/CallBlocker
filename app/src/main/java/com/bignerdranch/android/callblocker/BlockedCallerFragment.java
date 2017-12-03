package com.bignerdranch.android.callblocker;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by derrek1 on 11/30/17.
 */

public class BlockedCallerFragment extends Fragment {
    private static final String EXTRA_NUMBER = "blocked_number";
    private String blockedNumber;
    private ImageButton saveInfo;
    private BlockedCaller mBlockedCaller;
    private EditText mNameView;
    private EditText mNumberView;

    public static BlockedCallerFragment newInstance(String phoneNumber) {
        Bundle args = new Bundle();
        args.putString(EXTRA_NUMBER, phoneNumber);
        BlockedCallerFragment fragment = new BlockedCallerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // setContentView(R.layout.blocked_details);
        View view = inflater.inflate(R.layout.blocked_details, container, false);

        saveInfo = (ImageButton) view.findViewById(R.id.saved_blocked);
        mNameView = (EditText) view.findViewById(R.id.caller_name);
        mNumberView = (EditText) view.findViewById(R.id.caller_number);

        blockedNumber = getArguments().getString(EXTRA_NUMBER);
        final CallCenter callCenter = CallCenter.get(getActivity());
        mBlockedCaller = callCenter.getCaller(blockedNumber);

        mNameView.setText(mBlockedCaller.getName());
        mNumberView.setText(mBlockedCaller.getNumber());

        saveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlockedCaller newCallerData = new BlockedCaller();
                newCallerData.setName(mNameView.getText().toString());
                newCallerData.setNumber(mNumberView.getText().toString());
                callCenter.getCaller(newCallerData.getNumber()).setNumber(newCallerData.getNumber());
                callCenter.getCaller(newCallerData.getNumber()).setName(newCallerData.getName());
            }
        }); // end onClickListener
    return view;
    }


}