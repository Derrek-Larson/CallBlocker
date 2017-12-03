package com.bignerdranch.android.callblocker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by derrek1 on 11/30/17.
 */

public class BlockerFragment extends Fragment{

    private CallerHolder mCallerHolder;
    private CallerAdapter mCallerAdapter;
    private RecyclerView mRecyclerView;
    private static final int REQUEST_BLOCKED = 1;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.blocker_fragment,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        CallCenter callCenter = CallCenter.get(getActivity());
        List<BlockedCaller> blockedCallersList = callCenter.getBlockedCallers();
        if (mCallerAdapter == null) {
           mCallerAdapter = new CallerAdapter(blockedCallersList);
            mRecyclerView.setAdapter(mCallerAdapter);
        } else {
           mCallerAdapter.setBlockedCallers(blockedCallersList);
            mCallerAdapter.notifyDataSetChanged();
            mCallerAdapter = new CallerAdapter(blockedCallersList);
            mRecyclerView.swapAdapter(mCallerAdapter, false);

        }
    }


    private class CallerHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        private TextView mNameTextView;
        private TextView mNumberTextView;
        private BlockedCaller mBlockedCaller;
        private int mPosition;


        public CallerHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.caller_holder,parent,false ));
            itemView.setOnClickListener(this);
            mNameTextView = (TextView) itemView.findViewById(R.id.caller_name);
            mNumberTextView = (TextView) itemView.findViewById(R.id.caller_number);

        }
        @Override
        public void onClick(View view){
            Intent intent = BlockedCallerActivity.newIntent(getActivity(), mBlockedCaller.getNumber(),mPosition);
            startActivityForResult(intent, REQUEST_BLOCKED);
        }
        public void bind (BlockedCaller blockedCaller, int position){
            mBlockedCaller = blockedCaller;
            mNameTextView.setText(mBlockedCaller.getName());
            mNumberTextView.setText(mBlockedCaller.getNumber());
            mPosition=position;
        }
    }
    private class CallerAdapter extends RecyclerView.Adapter<CallerHolder>{
        private List<BlockedCaller> mBlockedCallers = CallCenter.get(getActivity()).getBlockedCallers();


        public CallerAdapter(List<BlockedCaller> callers){
            mBlockedCallers =  callers;
        }
        public void setBlockedCallers(List<BlockedCaller> callers){
            mBlockedCallers = callers;
        }
        @Override
        public CallerHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new CallerHolder(inflater, parent);
        }
        @Override
        public void onBindViewHolder(CallerHolder holder, int position){
            BlockedCaller caller = mBlockedCallers.get(position);
            holder.bind(caller,position);
        }
        @Override
        public int getItemCount(){
            return mBlockedCallers.size();
        }

    }


}
