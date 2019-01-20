package com.example.vmohanav.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class Registration extends AppCompatActivity {

    public static Integer Custid=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tab_example);


    }

    public String dateformatting(int year,int month,int day){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
        Date d = new Date(year-1901, month, day);
        String date = dateFormatter.format(d);
        return date;
    }

    public void cancelclick(View button){
        Intent intent = new Intent(getApplicationContext(), HomePage.class);
        startActivity(intent);
    }

    public void submitclick(View button){
        //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        //Button button =  findViewById(R.id.submit);
        final EditText fname = findViewById(R.id.FNameText);
        final DatePicker dob = findViewById(R.id.DateOfBirthvalue);
        final Spinner Gender = findViewById(R.id.GenderSpinner);


      /* button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {*/
                String data=fname.getText().toString().trim();
                String Givendob = dateformatting(dob.getYear(),dob.getMonth(),dob.getDayOfMonth());
                String SelectedGender = Gender.getSelectedItem().toString();


                String url = "https://webapp-181209061846.azurewebsites.net/Leprosy/";
                RequestQueue MyRequestQueue = Volley.newRequestQueue(getApplicationContext());
                JSONObject json = new JSONObject();

                try {
                    json.put("name",data);
                    json.put("dob",Givendob);
                    json.put("gender",SelectedGender);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest (Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //This code is executed if the server responds, whether or not the response contains data.
                        //The String 'response' contains the server's response.
                        String id=null;
                        try {
                            id=response.getString("id");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //ContactsTab fragment = new ContactsTab();
                        //Bundle bundle = new Bundle();
                        //bundle.putString("id",id);
                        //fragment.setArguments(bundle);
                        Registration.Custid = Integer.parseInt(id);

                        //Inflate the fragment
                        //getFragmentManager().beginTransaction().add(R.id.container,fragment).commit();
                        Log.e("request:","correct response");
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Profile updated successfully",
                                Toast.LENGTH_SHORT);
                        toast.show();

                        Intent intent = new Intent(getApplicationContext(), TabExample.class);
                        startActivity(intent);

                    }
                }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //This code is executed if there is an error.
                        Log.e("request:","Incorrect response");
                        Toast toast = Toast.makeText(getApplicationContext(),
                                error.toString(),
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
                MyRequestQueue.add(jsonObjectRequest );
            }
        //});
    }
//}
