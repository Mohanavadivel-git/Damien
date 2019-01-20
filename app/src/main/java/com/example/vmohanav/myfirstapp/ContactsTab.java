package com.example.vmohanav.myfirstapp;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    /*@Override
    public void onStart(){
        super.onStart();
        Toast toast = Toast.makeText(getActivity(),
                "Starting the fragment",
                Toast.LENGTH_SHORT);
        toast.show();
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts, container, false);
        Button button = view.findViewById(R.id.Contactsubmit);
        final EditText PhoneNumber =  view.findViewById(R.id.PhoneNumberText);
        final EditText Address =  view.findViewById(R.id.AddressText);
        final Spinner StateSpinner = view.findViewById(R.id.StateSpinner);
        final Spinner DistrictSpinner = view.findViewById(R.id.DistrictSpinner);
        final Spinner HealthFacilitySpinner =  view.findViewById(R.id.HealthFacilitySpinner);


        ArrayAdapter<CharSequence> StateAdapter = ArrayAdapter.createFromResource(getContext(), R.array.state_list, android.R.layout.simple_spinner_item);
        StateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        final ArrayAdapter<CharSequence> TN_DistrictAdapter = ArrayAdapter.createFromResource(getContext(), R.array.tn_districts, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> AP_DistrictAdapter = ArrayAdapter.createFromResource(getContext(), R.array.ap_districts, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> Bihar_DistrictAdapter = ArrayAdapter.createFromResource(getContext(), R.array.bihar_district, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> Jharkhand_DistrictAdapter = ArrayAdapter.createFromResource(getContext(), R.array.jharkhand_district, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> Delhi_DistrictAdapter = ArrayAdapter.createFromResource(getContext(), R.array.delhi, android.R.layout.simple_spinner_item);

        final ArrayAdapter<CharSequence> TN_HealthFacilityAdapter = ArrayAdapter.createFromResource(getContext(), R.array.tn_hf, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> AP_HealthFacilityAdapter = ArrayAdapter.createFromResource(getContext(), R.array.ap_hf, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> Bihar_HealthFacilityAdapter = ArrayAdapter.createFromResource(getContext(), R.array.br_hf, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> Jharkhand_HealthFacilityAdapter = ArrayAdapter.createFromResource(getContext(), R.array.jh_hf, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> Delhi_HealthFacilityAdapter = ArrayAdapter.createFromResource(getContext(), R.array.dl_hf, android.R.layout.simple_spinner_item);

        TN_DistrictAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AP_DistrictAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Bihar_DistrictAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Jharkhand_DistrictAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Delhi_DistrictAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        TN_HealthFacilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AP_HealthFacilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Bihar_HealthFacilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Jharkhand_HealthFacilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Delhi_HealthFacilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        StateSpinner.setAdapter(StateAdapter);
        StateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    DistrictSpinner.setAdapter(TN_DistrictAdapter);
                    HealthFacilitySpinner.setAdapter(TN_HealthFacilityAdapter);
                }
                else if(position==1){
                    DistrictSpinner.setAdapter(AP_DistrictAdapter);
                    HealthFacilitySpinner.setAdapter(AP_HealthFacilityAdapter);
                }
                else if(position==2){
                    DistrictSpinner.setAdapter(Bihar_DistrictAdapter);
                    HealthFacilitySpinner.setAdapter(Bihar_HealthFacilityAdapter);
                }
                else if(position==3){
                    DistrictSpinner.setAdapter(Jharkhand_DistrictAdapter);
                    HealthFacilitySpinner.setAdapter(Jharkhand_HealthFacilityAdapter);
                }
                else if(position==4){
                    DistrictSpinner.setAdapter(Delhi_DistrictAdapter);
                    HealthFacilitySpinner.setAdapter(Delhi_HealthFacilityAdapter);
                }

                DistrictSpinner.setEnabled(true);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String GivenPhoneNumber=PhoneNumber.getText().toString().trim();
                String GivenAddress=Address.getText().toString().trim();
                String GivenState = StateSpinner.getSelectedItem().toString();
                String GivenDistrict = DistrictSpinner.getSelectedItem().toString();
                String GivenHealthFacility = HealthFacilitySpinner.getSelectedItem().toString();

                String url = "https://webapp-181209061846.azurewebsites.net/Leprosy/contacts/"+Registration.Custid;
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


