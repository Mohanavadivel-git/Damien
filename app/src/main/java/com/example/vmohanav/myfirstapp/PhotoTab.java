package com.example.vmohanav.myfirstapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PhotoTab extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static PhotoTab newInstance(int sectionNumber) {
        PhotoTab fragment = new PhotoTab();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get the view from fragmenttab1.xml
        View view = inflater.inflate(R.layout.activity_imageupload, container, false);
        return view;
    }

}