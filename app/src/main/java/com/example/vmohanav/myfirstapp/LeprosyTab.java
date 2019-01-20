package com.example.vmohanav.myfirstapp;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
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

public class LeprosyTab extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static LeprosyTab newInstance(int sectionNumber) {
        LeprosyTab fragment = new LeprosyTab();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public String dateformatting(int year,int month,int day){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
        Date d = new Date(year-1901, month, day);
        String date = dateFormatter.format(d);
        return date;
    }

   /* @Override
    public void onStart(){
        super.onStart();
        Toast toast = Toast.makeText(getActivity(),
                "Starting the fragment",
                Toast.LENGTH_SHORT);
        toast.show();
        String GivenState=getArguments().getString("state");
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get the view from fragmenttab1.xml
        View view = inflater.inflate(R.layout.leprosydetails, container, false);



        Button button = view.findViewById(R.id.leprosysubmit);
        final Spinner LeprosyType =  view.findViewById(R.id.LeprosyTypeSpinner);
        final DatePicker ReactionDate =  view.findViewById(R.id.Date_React_identify_val);
        final TextView DisabilityStatus =  view.findViewById(R.id.Disab_Status_Text);
        final Spinner ReactionType =  view.findViewById(R.id.TypeReactionSpinner);



        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Bundle bundle=getArguments();
                String state=bundle.getString("state");

                String SelectedLeprosyType = LeprosyType.getSelectedItem().toString();
                String SelectedReactionType = ReactionType.getSelectedItem().toString();
                String GivenDisabilityStatus = DisabilityStatus.getText().toString();
                String GivenReactiondate=dateformatting(ReactionDate.getYear(),ReactionDate.getMonth()+1,ReactionDate.getDayOfMonth());

                String url = "https://webapp-181209061846.azurewebsites.net/Leprosy/leprosy/"+Registration.Custid;
                RequestQueue MyRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                JSONObject json = new JSONObject();

                try {
                    json.put("leprosytype",SelectedLeprosyType);
                    json.put("identifiedtime",GivenReactiondate);
                    json.put("disabilitystatus",GivenDisabilityStatus);
                    json.put("reactiontype",SelectedReactionType);

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
                                "Leprosy details updated successfully",
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

