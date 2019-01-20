package com.example.vmohanav.myfirstapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class NoRegistration extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static NoRegistration newInstance(int sectionNumber) {
        NoRegistration fragment = new NoRegistration();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart(){
        super.onStart();
        Toast toast = Toast.makeText(getActivity(),
                "Starting the fragment",
                Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Get the view from fragmenttab1.xml
        View view = inflater.inflate(R.layout.noregistration, container, false);
        return view;
    }

}
