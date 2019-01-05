package com.example.vmohanav.myfirstapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OtherdetailsTab extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static OtherdetailsTab newInstance(int sectionNumber) {
        OtherdetailsTab fragment = new OtherdetailsTab();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get the view from fragmenttab1.xml
        View view = inflater.inflate(R.layout.otherdetails, container, false);
        return view;
    }

}
