package com.bf.com.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bf.com.myapplication.R;


public class LeftlistFragment extends Fragment {
    public LeftlistFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_leftlist, container, false);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
