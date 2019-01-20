package com.example.vmohanav.myfirstapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
        final Boolean GivenTreatmentStatusvalue;

        Button button = view.findViewById(R.id.otherdetailssubmit);

        final RadioGroup TreatmentStatusGroup = view.findViewById(R.id.Treatmentstatus);
        final RadioGroup PersonStatusGroup = view.findViewById(R.id.personstatus);
        final TextView OtherDrugsValue =  view.findViewById(R.id.OtherDrugsValue);
        final TextView Remarks =  view.findViewById(R.id.RemarksValue);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String GivenOtherDrugsValue = OtherDrugsValue.getText().toString();
                String GivenRemarks = Remarks.getText().toString();

                // get selected radio button from radioGroup
                int selectedTreatmentStatus = TreatmentStatusGroup.getCheckedRadioButtonId();
                int selectedPersonStatus = PersonStatusGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                RadioButton GivenTreatmentStatus =  getView().findViewById(selectedTreatmentStatus);
                RadioButton GivenPersonStatus =  getView().findViewById(selectedPersonStatus);

                boolean valu= StrtoBool(GivenTreatmentStatus.getText());
                boolean valu1=StrtoBool(GivenPersonStatus.getText());

                String url = "https://webapp-181209061846.azurewebsites.net/Leprosy/others/"+Registration.Custid;
                RequestQueue MyRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                JSONObject json = new JSONObject();

                try {
                    json.put("otherdrugs",GivenOtherDrugsValue);
                    json.put("treatmentcomplete",StrtoBool(GivenTreatmentStatus.getText()));
                    json.put("personstatus",StrtoBool(GivenPersonStatus.getText()));
                    json.put("remarks",GivenRemarks);
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
                                "otherdetails updated successfully",
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

    public boolean StrtoBool(CharSequence str){
        if(str.toString().equals("Yes")){
            return true;
        }
        else{
            return false;
        }
    }
}
