package com.example.vmohanav.myfirstapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Test extends AppCompatActivity {
    String url = "https://webapp-181209061846.azurewebsites.net/blog/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);

    /*------------------------------------------------------------------------------------------------*/

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest=new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("RestResponse:",response.toString());
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Profile updated successfully",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ErrorResponse:",error.toString());
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Failed to update",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
        );
        requestQueue.add(objectRequest);
    }
       public void submitclick (View button){

            Toast toast = Toast.makeText(getApplicationContext(),
                    "Profile updated",
                    Toast.LENGTH_SHORT);
            toast.show();
            Intent i = new Intent(getApplicationContext(), damienpost.class);
            startActivity(i);
        }
}

