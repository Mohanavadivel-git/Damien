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

public class ContactsTab extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static ContactsTab newInstance(int sectionNumber) {
        ContactsTab fragment = new ContactsTab();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Retrieve the value
        final String id = getArguments().getString("id");
        // Get the view from fragmenttab1.xml
        View view = inflater.inflate(R.layout.contacts, container, false);

        Button button = view.findViewById(R.id.Contactsubmit);
        final EditText PhoneNumber =  view.findViewById(R.id.PhoneNumberText);
        final EditText Address =  view.findViewById(R.id.AddressText);
        final Spinner State =  view.findViewById(R.id.StateSpinner);
        final Spinner District =  view.findViewById(R.id.DistrictSpinner);
        final Spinner HealthFacility =  view.findViewById(R.id.HealthFacilitySpinner);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String GivenPhoneNumber=PhoneNumber.getText().toString().trim();
                String GivenAddress=Address.getText().toString().trim();
                String GivenState = State.getSelectedItem().toString();
                String GivenDistrict = District.getSelectedItem().toString();
                String GivenHealthFacility = HealthFacility.getSelectedItem().toString();

                String url = "https://webapp-181209061846.azurewebsites.net/Leprosy/"+TabExample.id;
                RequestQueue MyRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                JSONObject json = new JSONObject();

                try {
                    json.put("state",GivenState);
                    json.put("district",GivenDistrict);
                    json.put("healthfacility",GivenHealthFacility);
                    json.put("phonenumber",GivenPhoneNumber);
                    json.put("address",GivenAddress);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest (Request.Method.PUT, url, json, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //This code is executed if the server responds, whether or not the response contains data.
                        //The String 'response' contains the server's response.

                        Log.e("request:","correct response");
                        Toast toast = Toast.makeText(getActivity(),
                                "Contacts updated successfully",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //This code is executed if there is an error.
                        Log.e("request:","Incorrect response");
                        Toast toast = Toast.makeText(getActivity(),
                                error.toString(),
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
                MyRequestQueue.add(jsonObjectRequest );
            }

        });
        return view;
    }

}


