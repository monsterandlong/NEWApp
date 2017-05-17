package com.bf.com.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bf.com.myapplication.R;


public class LoginFragment extends Fragment {


    private ImageView imgLoginFragLogin;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static String name(String name){
        return name;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

}
