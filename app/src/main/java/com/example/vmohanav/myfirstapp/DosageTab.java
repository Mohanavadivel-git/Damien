package com.example.vmohanav.myfirstapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.text.SimpleDateFormat;
import java.util.Date;

public class DosageTab extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static DosageTab newInstance(int sectionNumber) {
        DosageTab fragment = new DosageTab();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Retrieve the value
        String value = getArguments().getString("id");
        // Get the view from fragmenttab1.xml
        View view = inflater.inflate(R.layout.dosage, container, false);

        Button button = view.findViewById(R.id.dosagesubmit);
        final DatePicker Dose40mg =  view.findViewById(R.id.DoseIssued_40mg_dt);
        final DatePicker Dose30mg =  view.findViewById(R.id.DoseIssued_30mg_dt);
        final DatePicker Dose20mg =  view.findViewById(R.id.DoseIssued_20mg_dt);
        final DatePicker Dose15mg =  view.findViewById(R.id.DoseIssued_15mg_dt);
        final DatePicker Dose10mg =  view.findViewById(R.id.DoseIssued_10mg_dt);
        final DatePicker Dose05mg =  view.findViewById(R.id.DoseIssued_05mg_dt);


        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String GivenDose40mg = dateformatting(Dose40mg.getYear(),Dose40mg.getMonth()+1,Dose40mg.getDayOfMonth());
                String GivenDose30mg = dateformatting(Dose30mg.getYear(),Dose30mg.getMonth()+1,Dose30mg.getDayOfMonth());
                String GivenDose20mg = dateformatting(Dose20mg.getYear(),Dose20mg.getMonth()+1,Dose20mg.getDayOfMonth());
                String GivenDose15mg = dateformatting(Dose15mg.getYear(),Dose15mg.getMonth()+1,Dose15mg.getDayOfMonth());
                String GivenDose10mg = dateformatting(Dose10mg.getYear(),Dose10mg.getMonth()+1,Dose10mg.getDayOfMonth());
                String GivenDose05mg = dateformatting(Dose05mg.getYear(),Dose05mg.getMonth()+1,Dose05mg.getDayOfMonth());


                String url = "https://webapp-181209061846.azurewebsites.net/Leprosy/dosage/"+Registration.Custid;
                RequestQueue MyRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                JSONObject json = new JSONObject();

                try {
                    json.put("dose40mg",GivenDose40mg);
                    json.put("dose30mg",GivenDose30mg);
                    json.put("dose20mg",GivenDose20mg);
                    json.put("dose15mg",GivenDose15mg);
                    json.put("dose10mg",GivenDose10mg);
                    json.put("dose5mg",GivenDose05mg);

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
                                "Dosage updated successfully",
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



    public String dateformatting(int year,int month,int day){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
        Date d = new Date(year-1901, month, day);
        String date = dateFormatter.format(d);
        return date;
    }


}



